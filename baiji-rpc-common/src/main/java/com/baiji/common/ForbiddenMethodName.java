package com.baiji.common;

import java.util.Set;

public class ForbiddenMethodName {
    private static Set<String> FORBIDDEN_METHOD_NAMES =
            Set.of(
                    "checkHealth".toLowerCase(),
                    "all".toLowerCase()
            );

    public static boolean inForbidNames(String methodName) {
        return FORBIDDEN_METHOD_NAMES.contains(methodName.toLowerCase());
    }
}
