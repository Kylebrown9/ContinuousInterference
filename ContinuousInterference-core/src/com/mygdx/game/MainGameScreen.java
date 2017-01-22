package com.mygdx.game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import engine.GameEngine;
import interfaces.CompletionListener;
import interfaces.TimeProvider;
import model.Level;
import model.Obstacle;
import model.Player;
import model.Source;
import model.Target;

public class MainGameScreen extends ScreenAdapter {

	private float worldHue = 180;

	/**
	 * "Logical" viewport size units.
	 */
	public static final float VP_WIDTH = 160;
	public static final float VP_HEIGHT = 90;

	private float wavesWidth = 160;
	private float wavesHeight = wavesWidth * (VP_WIDTH / VP_HEIGHT);
	private float wavesCoordScale = VP_HEIGHT / wavesHeight;

	private void setWavesTextureSize(float wavesWidth) {
		wavesHeight = wavesWidth * (VP_WIDTH / VP_HEIGHT);
		wavesCoordScale = VP_HEIGHT / wavesHeight;
	}

	private MyGame game;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	private ShapeRenderer shapeRenderer;

	private FPSLogger fps = new FPSLogger();

	private float currTime = 0;
	private int scrollWheel = 0;

	private float cameraXOffset = 0;

	private Vector3 mousePos = new Vector3();

	/**
	 * game logic
	 */
	private GameEngine gameEngine;

	private float sourceAmplitude = 100F;

	public MainGameScreen(MyGame g) {
		// All the mundane initializations
		game = g;
		Gdx.app.getGraphics();
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		// Flat 2D rendering
		camera = new OrthographicCamera();

		// // Ensure that the world is always a static height, while allowing
		// the
		// // screen to become arbitrarily narrow
		// viewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, camera);
		// viewport.setMaxWorldHeight(VP_HEIGHT);
		// viewport.setMinWorldHeight(VP_HEIGHT);
		// lock game to 16:9 aspect ratio
		viewport = new FitViewport(VP_WIDTH, VP_HEIGHT, camera);

		// Move camera
		camera.translate(VP_WIDTH / 2, VP_HEIGHT / 2);
		camera.update();

		// Initialize game engine
		// TODO: Load games levels
		FileHandle file = Gdx.files.internal("testLevel.json");
		String testLevelText = file.readString();
		LinkedList<String> levels = new LinkedList<String>();
		levels.add(testLevelText);
		gameEngine = new GameEngine(new Point(5, 5), levels, new TimeProvider() {

			@Override
			public float getTime() {
				return currTime;
			}

		}, new CompletionListener() {

			@Override
			public void notifyCompleted() {
				System.out.println("COMPLETED!");
			}
		});
		// addDebugLevels();
		// gameEngine.start();

		// Get game engine input events
		Gdx.input.setInputProcessor(new InputProcessor() {

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				// Translate between screen pixels and click position
				camera.unproject(mousePos.set(screenX, screenY, 0));
				// Just left-right with movement of camera
				mousePos.x += cameraXOffset;
				// System.out.println(mousePos);
				gameEngine.notifyClick(mousePos.x, mousePos.y);
				return true;
			}

			@Override
			public boolean scrolled(int amount) {
				scrollWheel += amount;
				return true;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// Translate between screen pixels and click position
				camera.unproject(mousePos.set(screenX, screenY, 0));
				// Just left-right with movement of camera
				mousePos.x += cameraXOffset;
				// System.out.println(mousePos);
				gameEngine.notifyMousePos(mousePos.x, mousePos.y);
				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				gameEngine.notifyKeyEvent(keycode);
				return true;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean keyDown(int keycode) {
				return false;
			}
		});

		setWavesTextureSize(Integer.parseInt(JOptionPane.showInputDialog("What is your worth?")));
	}

	/**
	 * Generates a couple of levels for the game.
	 */
	private void addDebugLevels() {
		Level l = new Level(80, 90, 0);
		l.addObstacle(new Obstacle(new Rectangle(20, 20, 30, 10), false));
		l.addObstacle(new Obstacle(new Rectangle(10, 60, 30, 10), true));
		l.addSource(new Source(50, 60, 5, 10));
		l.addSource(new Source(10, 10, 2, 10));
		l.addTarget(new Target(l, "bojack", 80, 80, -1, 0.04f));
		gameEngine.getModel().addLevel(l);
	}

	private LinkedList<Source> getAllSources() {
		LinkedList<Source> allSources = new LinkedList<Source>();
		for (int i = 0; i < gameEngine.getModel().getNumLevels(); i++) {
			allSources.addAll(gameEngine.getModel().getLevel(i).getSources());
		}
		return allSources;
	}

	private LinkedList<Target> getAllTargets() {
		LinkedList<Target> allTargers = new LinkedList<Target>();
		for (int i = 0; i < gameEngine.getModel().getNumLevels(); i++) {
			allTargers.addAll(gameEngine.getModel().getLevel(i).getTargets());
		}
		return allTargers;
	}

	private LinkedList<Obstacle> getAllObstacles() {
		LinkedList<Obstacle> allObstacles = new LinkedList<Obstacle>();
		for (int i = 0; i < gameEngine.getModel().getNumLevels(); i++) {
			allObstacles.addAll(gameEngine.getModel().getLevel(i).getObstacles());
		}
		return allObstacles;
	}

	private LinkedList<Obstacle> getAllWaveObstacles() {
		LinkedList<Obstacle> allObstacles = new LinkedList<Obstacle>();
		for (int i = 0; i < gameEngine.getModel().getNumLevels(); i++) {
			for (Obstacle o : gameEngine.getModel().getLevel(i).getObstacles()) {
				if (!o.getPermittive()) {
					allObstacles.add(o);
				}
			}
		}
		return allObstacles;
	}

	@Override
	public void render(float delta) {
		worldHue += delta * 3;

		// Keep FPS log
		fps.log();

		// Increment game time
		currTime += delta;

		// Update game engine
		gameEngine.update();

		// Reposition camera
		// cameraXOffset += delta;
		// camera.translate(delta, 0);
		camera.update();

		// Render the geometry of all levels
		// Render order:
		// Wave plane
		// Player
		// Sources, targets
		// Doors
		// Obstacles

		// Render wave plane
		// Over the entire viewport, calculate the summed effects of every
		// source on the map
		// TODO: Optimize. Write to byte array, then PixMap, etc.
		int wavesWidth = (int) (wavesHeight * (VP_WIDTH / VP_HEIGHT));
		Pixmap wavePix = new Pixmap(wavesWidth, (int) wavesHeight, Format.RGBA8888);
		for (int x = 0; x < wavesWidth; x++) {
			for (int y = 0; y < wavesHeight; y++) {
				float xWorld = x * wavesCoordScale + cameraXOffset;
				float yWorld = VP_HEIGHT - y * wavesCoordScale;

				float sumAtPoint = 30;
				sourceLoop: for (Source source : getAllSources()) {
					if (source.isActive()) {
						// Check that there is line of sight (no obstacles)
						// between
						// source and pixel under consideration
						for (Obstacle o : getAllWaveObstacles()) {
							if (o.getRect()
									.intersectsLine(new Line2D.Float(xWorld, yWorld, source.getX(), source.getY()))) {
								continue sourceLoop;
							}
						}

						// Calculate distance from source to pixel under
						// consideration
						float dx = Math.abs(xWorld - source.getX());
						float dy = Math.abs(yWorld - source.getY());
						float dist = (float) Math.sqrt(dx * dx + dy * dy);

						float valueAtPoint = source.getWaveEquation().evaluate(currTime, dist);
						valueAtPoint = valueAtPoint / 2 + 0.5f;
						sumAtPoint += valueAtPoint * sourceAmplitude;
					}
				}

				wavePix.drawPixel(x, y, Color.rgba8888((ColorUtils.HSV_to_RGB(worldHue, 50, sumAtPoint))));
			}
		}

		Texture waveTexture = new Texture(wavePix);
		waveTexture.setFilter(TextureFilter.Nearest, TextureFilter.Linear);

		// Now render to the screen!
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Wave Plane
		batch.begin();
		batch.draw(waveTexture, cameraXOffset, 0.5f, viewport.getWorldWidth(), viewport.getWorldHeight());
		batch.end();

		// Player
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.FIREBRICK);
		shapeRenderer.circle((float) gameEngine.getModel().getPlayer().getX(),
				(float) gameEngine.getModel().getPlayer().getY(), Player.RADIUS, 100);
		// System.out.println(gameEngine.getModel().getPlayer().getX() + ":" +
		// gameEngine.getModel().getPlayer().getY());

		// Sources
		for (Source s : getAllSources()) {
			if (s.isActive()) {
				shapeRenderer.setColor(ColorUtils.HSV_to_RGB(worldHue, 50,
						sourceAmplitude * s(s.getWaveEquation().evaluate(currTime, 0))));
				shapeRenderer.circle(s.getX(), s.getY(), Player.RADIUS / 2, 100);
			}
		}

		// Targets
		for (Target t : getAllTargets()) {
			shapeRenderer.setColor(ColorUtils.HSV_to_RGB((float) (worldHue), s((float) t.getTargetVal()), 100));
			shapeRenderer.circle(t.getX(), t.getY(), 3, 25);
		}

		// Doors around every level
		Pixmap doorPixmap = new Pixmap(3, 90, Format.RGBA8888);
		for (int x = 0; x < doorPixmap.getWidth(); x++) {
			for (int y = 0; y < doorPixmap.getHeight(); y++) {
				doorPixmap.drawPixel(x, y,
						Color.rgba8888(ColorUtils.HSV_to_RGB(worldHue, MathUtils.random(100), MathUtils.random(100))));
			}
		}
		Texture doorTexture = new Texture(doorPixmap);
		doorPixmap.dispose();
		batch.begin();
		for (int i = 0; i <= gameEngine.getModel().getNumLevels(); i++) {
			batch.draw(doorTexture, i * 80, 0, doorTexture.getWidth(), doorTexture.getHeight());
		}
		batch.end();
		doorTexture.dispose();

		// Obstacles
		for (Obstacle o : getAllObstacles()) {
			if (o.getActive()) {
				shapeRenderer.rect(o.getRect().x, o.getRect().y, o.getRect().width, o.getRect().height,
						getBackgroundAtX(o.getRect().x),
						getBackgroundAtX((float) (o.getRect().x + o.getRect().getWidth())),
						getBackgroundAtX((float) (o.getRect().x + o.getRect().getWidth())),
						getBackgroundAtX(o.getRect().x));
			}
		}

		// Finalize
		shapeRenderer.end();

		// Dispose of disposable things
		wavePix.dispose();
		waveTexture.dispose();
	}

	/**
	 * Scales input in range 1 to -1 ==> 1 to 0.
	 * 
	 * @param num
	 * @return
	 */
	private static float s(float num) {
		return num * 2 + 0.5f;
	}

	/**
	 * Returns the background color of the level, a linear gradient, at this x
	 * 
	 * @param x
	 * @return
	 */
	private Color getBackgroundAtX(float x) {
		Color bgStart = ColorUtils.HSV_to_RGB(worldHue, 50, 0);
		Color bgEnd = ColorUtils.HSV_to_RGB(worldHue, 50, 100);

		// Width of all levels
		float worldWidth = 0;
		for (int i = 0; i < gameEngine.getModel().getNumLevels(); i++) {
			worldWidth += 80;
		}
		Color resultColor = new Color(bgStart);
		resultColor.lerp(bgEnd, x / worldWidth);
		return resultColor;
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		shapeRenderer.dispose();
	}
}
