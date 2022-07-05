package com.pexapark.util;

public enum OperatingSystem {
    WINDOWS, MAC, OTHER;

    public static OperatingSystem getOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return OperatingSystem.WINDOWS;
        } else if (os.contains("mac")) {
            return OperatingSystem.MAC;
        }
        return OperatingSystem.OTHER;
    }

    public static boolean isMac() {
        return getOS().equals(OperatingSystem.MAC);
    }

}
