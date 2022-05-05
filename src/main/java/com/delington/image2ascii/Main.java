package com.delington.image2ascii;

import com.delington.image2ascii.service.ImageProcessor;
import imgui.ImFontAtlas;
import imgui.ImFontConfig;
import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

import static com.delington.image2ascii.Properties.CUSTOM_FONT_PATH;
import static com.delington.image2ascii.Properties.FONT_SIZE;
import static com.delington.image2ascii.Properties.INPUT_FILE_PATH;
import static com.delington.image2ascii.Properties.INPUT_FILE_PATH_IMAGE;

public class Main extends Application {

    @Override
    protected void configure(Configuration config) {
        config.setTitle("ImGUI - ASCII image");

        ImGui.createContext();
        final ImFontAtlas fontAtlas = ImGui.getIO().getFonts();
        final ImFontConfig fontConfig = new ImFontConfig();
        fontConfig.setGlyphRanges(fontAtlas.getGlyphRangesDefault());

        fontConfig.setPixelSnapH(true);
        fontAtlas.addFontFromFileTTF(CUSTOM_FONT_PATH, FONT_SIZE, fontConfig);

        fontConfig.destroy();
    }

    @Override
    public void process() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(INPUT_FILE_PATH_IMAGE));

            // To write chars to file
//            final var writer = new FileWriter(OUTPUT_FILE_PATH);
//            ImageProcessor.writeGreyScaleValue(myPicture, writer);

            final var resizedImage = ImageProcessor.resizeImage(myPicture, 80, 40);
            ImageProcessor.writeGreyScaleValueToDisplay(resizedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        launch(new Main());
    }

    private static void writeToFile(String text, Writer writer) {
        try {
            writer.append(text);
//            writer.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}