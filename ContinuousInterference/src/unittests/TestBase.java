package unittests;

public class TestBase {
	public static void main(String[] args) {
		Test[] tests = {
			new ChannelLinkTest("Channel Test")	
		};
		
		for(Test t: tests) {
			t.printOutcome();
		}
	}

}
