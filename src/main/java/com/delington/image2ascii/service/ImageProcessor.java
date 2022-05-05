package com.delington.image2ascii.service;

import imgui.ImGui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;

public class ImageProcessor {

    public static void showPicture(BufferedImage myPicture) {
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);

        JFrame f = new JFrame();
        f.setSize(new Dimension(myPicture.getWidth(), myPicture.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
    }

    public static void writeGreyScaleValue(BufferedImage myPicture, FileWriter writer) throws IOException {
        for (int y = 0; y < myPicture.getHeight(); y++) {
            for (int x = 0; x < myPicture.getWidth(); x++) {
                //Retrieving contents of a pixel
                int pixel = myPicture.getRGB(x, y);

                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);

                final var grayScalePixelValue =
                        color.getRed() * 0.299 + color.getBlue() * 0.587 + color.getBlue() * 0.144;

                final int grayValueAsInteger = (int) (grayScalePixelValue);
                writer.write(strChar(grayValueAsInteger));
            }
            writer.write("\n");
        }
        writer.close();
    }

    public static void writeGreyScaleValueToDisplay(BufferedImage myPicture) {
        
        for (int y = 0; y < myPicture.getHeight(); y++) {

            StringBuilder text = new StringBuilder();
            

            for (int x = 0; x < myPicture.getWidth(); x++) {
                //Retrieving contents of a pixel
                int pixel = myPicture.getRGB(x, y);

                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);

                final var grayScalePixelValue =
                        color.getRed() * 0.299 + color.getBlue() * 0.587 + color.getBlue() * 0.144;

                final int grayValueAsInteger = (int) (grayScalePixelValue);
                text.append(strChar(grayValueAsInteger));
            }
            
            ImGui.text(text.toString() + "\n");
            //System.out.println(text.toString());
        }
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

        return outputImage;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth) throws IOException {
        final var ratio = originalImage.getWidth() / (float) originalImage.getHeight();

        final var targetHeight = (int) (targetWidth / ratio);
        return resizeImage(originalImage, targetWidth, targetHeight);
    }

    private static String strChar(int greyScaleValue) {
        String mappedCharacter = " ";

        if (greyScaleValue >= 230) {
            mappedCharacter = "@";
        } else if (greyScaleValue >= 200) {
            mappedCharacter = "%";
        } else if (greyScaleValue >= 180) {
            mappedCharacter = "#";
        } else if (greyScaleValue >= 150) {
            mappedCharacter = "*";
        } else if (greyScaleValue >= 130) {
            mappedCharacter = "+";
        } else if (greyScaleValue >= 100) {
            mappedCharacter = "=";
        } else if (greyScaleValue >= 75) {
            mappedCharacter = "-";
        } else if (greyScaleValue >= 50) {
            mappedCharacter = ":";
        } else if (greyScaleValue >= 25) {
            mappedCharacter = ".";
        } else {
            mappedCharacter = " ";
        }
        return mappedCharacter;
    }
}
