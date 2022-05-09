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

import static com.delington.image2ascii.Properties.FONT_ANONYMUS;
import static com.delington.image2ascii.Properties.FONT_SIZE;
import static com.delington.image2ascii.Properties.INPUT_IMAGE;

public class Main extends Application {

    public static void main(String[] args) throws IOException {
        launch(new Main());
    }

    @Override
    protected void configure(Configuration config) {
        config.setTitle("ImGUI - ASCII image");

        ImGui.createContext();
        final ImFontAtlas fontAtlas = ImGui.getIO().getFonts();
        final ImFontConfig fontConfig = new ImFontConfig();
        fontConfig.setGlyphRanges(fontAtlas.getGlyphRangesDefault());

        fontConfig.setPixelSnapH(true);
        fontAtlas.addFontFromFileTTF(Utils.prepareFontPath(FONT_ANONYMUS), FONT_SIZE, fontConfig);

        fontConfig.destroy();
    }

    @Override
    public void process() {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(Utils.getAbsoluteFilePath(INPUT_IMAGE)));

            final var resizedImage = ImageProcessor.resizeImage(myPicture, 80, 40);
            ImageProcessor.writeGreyScaleValueToDisplay(resizedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}