package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MainGameScreen extends ScreenAdapter {

	public static final float VP_WIDTH = 800;
	public static final float VP_HEIGHT = 450;

	private static final float WAVES_WIDTH = VP_WIDTH / 3;
	private static final float WAVES_HEIGHT = VP_HEIGHT / 3;

	final private MyGame game;

	Texture waveTex = new Texture((int) WAVES_WIDTH, (int) WAVES_HEIGHT, Format.RGBA8888);
	FPSLogger fps = new FPSLogger();

	Vector3 mousePos = new Vector3();

	private OrthographicCamera camera;

	private ExtendViewport viewport;
	private SpriteBatch batch;

	public MainGameScreen(MyGame g) {
		game = g;
		batch = new SpriteBatch();

		waveTex.setFilter(TextureFilter.Nearest, TextureFilter.Linear);

		camera = new OrthographicCamera();
		// pick a viewport that suits your thing, ExtendViewport is a good start
		viewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, camera);

		Gdx.input.setInputProcessor(new InputProcessor() {

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				scrollPos += amount;
				System.out.println(scrollPos);
				return true;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	private float triangleWave(float rads) {
		rads /= 2 * Math.PI;
		float fracPart = rads - (int) rads;
		fracPart = Math.abs(fracPart);
		if (fracPart < 0.5) {
			return (2 * (fracPart * 2)) - 1;
		} else {
			fracPart -= 0.5;
			return -((2 * (fracPart * 2)) - 1);
		}
	}

	float currTime = 0;

	private int scrollPos = 500/5;

	@Override
	public void render(float delta) {
		currTime += delta;
		
		fps.log();

		camera.unproject(mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		
		// Render current wave texture
		Pixmap wavePix = new Pixmap((int) WAVES_WIDTH, (int) WAVES_HEIGHT, Format.RGBA8888);
		for (int x = 0; x < wavePix.getWidth(); x++) {
			for (int y = 0; y < wavePix.getHeight(); y++) {
				float t = (x + ((mousePos.x + 1) / 2) * -WAVES_WIDTH) * (x + ((mousePos.x + 1) / 2) * -WAVES_WIDTH)
						+ (y + ((1 - mousePos.y) / 2) * -WAVES_HEIGHT) * (y + ((1 - mousePos.y) / 2) * -WAVES_HEIGHT);
				t = (float) Math.sqrt(t);
				t /= (scrollPos / 20f);

				float normalizedT = 1 - MathUtils.clamp((t / WAVES_HEIGHT * 5), 0, 1);

				float waveSourceAtPos = (float) 0f + (50f * ((MathUtils.sin(t - 7 * currTime) + 1) / 2) * normalizedT);

				float t2 = (x - WAVES_WIDTH / 2) * (x - WAVES_WIDTH / 2)
						+ (y - WAVES_HEIGHT / 2) * (y - WAVES_HEIGHT / 2);
				t2 = (float) Math.sqrt(t2);
				t2 /= 5;
				if (t2 == 0) {
					t2 += 0.001;
				}
				
				float normalizedT2 = 1 - MathUtils.clamp((t2 / WAVES_HEIGHT * 5), 0, 1);

				waveSourceAtPos += (50f * ((MathUtils.sin((t2 - 5 * currTime)) + 1) / 2) * normalizedT2);

				// waveSourceAtPos += currTime * 2;

				while (waveSourceAtPos > 100) {
					waveSourceAtPos -= 100;
				}

				// wavePix.drawPixel(x, y,
				// Color.rgba8888(ColorUtils.HSV_to_RGB(waveSourceAtPos, 100,
				// 100)));
				
				wavePix.drawPixel(x, y, Color.rgba8888(ColorUtils.HSV_to_RGB(105, waveSourceAtPos, 100)));
			}
		}
		waveTex.draw(wavePix, 0, 0);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(waveTex, 0, 0, VP_WIDTH, VP_HEIGHT);
		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		waveTex.dispose();
	}

}
