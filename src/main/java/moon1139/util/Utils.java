package moon1139.util;

public class Utils {
	public static String removeLastChar(String s) {
	    return (s == null || s.length() == 0)
	      ? null
	      : (s.substring(0, s.length() - 1));
	}
}
