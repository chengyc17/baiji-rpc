package com.baiji.common.util;

public class StringUtils {

    public static final String Empty = "";

    public static String join(String sep, String... ss) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            String curr = ss[i];
            if (curr == null || curr.trim().isEmpty()) {
                continue;
            }
            sb.append(curr);
            if (i != ss.length - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

    public static boolean equals(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null || s2 == null) {
            return false;
        }
        return s1.equals(s2);
    }

    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null || s2 == null) {
            return false;
        }
        return s1.equalsIgnoreCase(s2);
    }

    public static boolean isBlank(String s) {
        return s == null ? true : "".equals(s.trim());
    }

    public static boolean startsWithIgnoreCase(String target, String prefix) {
        if (target == null || prefix == null) {
            return false;
        }
        return target.toLowerCase().startsWith(prefix.toLowerCase());
    }

    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        if (str.length() < suffix.length()) {
            return false;
        }
        return str.toLowerCase().endsWith(suffix.toLowerCase());
    }
}
