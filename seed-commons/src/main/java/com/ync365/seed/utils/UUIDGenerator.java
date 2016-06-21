package com.ync365.seed.utils;

import java.util.UUID;

public class UUIDGenerator {
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replaceAll("-", "");
    }
}
