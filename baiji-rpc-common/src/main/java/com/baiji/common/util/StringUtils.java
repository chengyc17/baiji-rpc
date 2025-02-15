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
}
