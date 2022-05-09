package com.delington.image2ascii;

public class Utils {

    private static final String PATH_SEPARATOR = "/";
    private static final String ACCEPTED_PATH_SEPARATOR = "\\\\";

    public static String getAbsoluteFilePath(String filePath) {
        return Utils.class.getClassLoader().getResource(filePath).getPath();
    }

    // Index is 1 to have path without the starting '/' path separator
    public static String prepareFontPath(String fontPath) {
        return getAbsoluteFilePath(fontPath).substring(1).replace(PATH_SEPARATOR, ACCEPTED_PATH_SEPARATOR);
    }
}
