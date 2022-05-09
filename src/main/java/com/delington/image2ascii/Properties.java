package com.delington.image2ascii;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Properties {

    final static String ASCII_CHARS_STRING = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ";
    final static String ASCII_CHARS_STRING_SHORT = " .:-=+*#%@";
    final static List<String> ASCII_CHARS = getListOfCharacters(ASCII_CHARS_STRING_SHORT);
    final static int ASCII_CHARS_SIZE = ASCII_CHARS.size();
                    
    final static String INPUT_IMAGE_GRAYSCALE = "grayscale.jpg";
    final static String INPUT_IMAGE = "image.jpg";
    final static String INPUT_IMAGE2 = "image2.jpg";
    final static String OUTPUT_FILE_PATH = "image-to-ASCII/src/main/resources/sample.txt";

    public static final String FONT_CONSOLA = "consola.ttf";
    public static final String FONT_CONSOLAB = "consolab.ttf";
    public static final String FONT_ANONYMUS = "Anonymous.ttf";

    public static final int FONT_SIZE = 15;

    private static List<String> getListOfCharacters(String text) {
        final int NUMBER_OF_SEPARATION = 1;

        return Stream.of(text.split("(?<=\\G.{" + NUMBER_OF_SEPARATION + "})"))
                .collect(Collectors.toList());
    }
}
