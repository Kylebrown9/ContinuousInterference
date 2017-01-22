package unittests;

public abstract class Test {
	private String name;
	
	public Test(String name) {
		this.name = name;
	}
	
	public abstract boolean getOutcome();
	
	public void printOutcome() {
		System.out.println(name + ": " + getOutcome());
	}
}
