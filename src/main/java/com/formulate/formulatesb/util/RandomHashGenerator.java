package com.formulate.formulatesb.util;

import java.util.UUID;

public class RandomHashGenerator {
    public static String generateRandomHash() {
        UUID uuid = UUID.randomUUID();
        long lsb = uuid.getLeastSignificantBits();
        long msb = uuid.getMostSignificantBits();
        long hash = lsb ^ msb;
        return Long.toHexString(hash);
    }
}
