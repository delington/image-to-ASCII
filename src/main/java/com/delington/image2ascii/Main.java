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

import static com.delington.image2ascii.Properties.FONT_PATH_ANONYMUS;
import static com.delington.image2ascii.Properties.FONT_PATH_CONSOLA;
import static com.delington.image2ascii.Properties.FONT_PATH_CONSOLAB;
import static com.delington.image2ascii.Properties.FONT_SIZE;
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
        fontAtlas.addFontFromFileTTF(FONT_PATH_ANONYMUS, FONT_SIZE, fontConfig);

        fontConfig.destroy();
    }

    @Override
    public void process() {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(INPUT_FILE_PATH_IMAGE));

            final var resizedImage = ImageProcessor.resizeImage(myPicture, 80, 40);
            ImageProcessor.writeGreyScaleValueToDisplay(resizedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        launch(new Main());
    }
}