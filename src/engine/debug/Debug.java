package engine.debug;

public class Debug {
	private static boolean IsEnabled() {
		return true;
	}
	
	public static void Log(Object o) {
		if(IsEnabled())
			System.out.println(o);
	}
	
	public static void LogErr(Object o) {
		if(IsEnabled()) {
			System.err.println(o);
	    	System.exit(-1);
		}
	}
}
