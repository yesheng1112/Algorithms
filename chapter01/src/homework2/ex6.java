package homework2;

import edu.princeton.cs.algs4.StdOut;

public class ex6 {
    public static boolean isCircularRotation(String s, String t) {
        return s.length() == t.length() && (s.concat(s)).contains(t);
    }

    public static boolean MyIsCircularRotation(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] chars = s.toCharArray();
        int index = -1;
        for (int i = 0; i < chars.length; i++) {
            if (t.indexOf(chars[i]) == -1)
                return false;
            else {
                if (index == t.indexOf(chars[i])) {
                    if (t.indexOf(chars[i], index + 1) == -1)
                        return false;
                    else {
                        index = t.indexOf(chars[i], index + 1);
                        continue;
                    }
                }
                index = t.indexOf(chars[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StdOut.println(isCircularRotation("12345", "45123"));
    }
}
