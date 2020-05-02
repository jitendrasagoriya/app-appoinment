package com.js.appointment.utils;

import java.util.UUID;

public class UniqueUUId {
    public static String getAlphaNumeric() {
        UUID uuid= UUID.randomUUID();
        String str=uuid.toString().replace("-", "");
        return str;
    }
}
