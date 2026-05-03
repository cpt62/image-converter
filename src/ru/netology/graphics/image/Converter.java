package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    private int width;
    private int height;
    private double ratio;
    private TextColorSchema schema;

    public Converter() {
        this.schema = new CharactersMapper();
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage image = ImageIO.read(new URL(url));
        int currWidth = image.getWidth();
        int currHeight = image.getHeight();
        double currRatio = (double) currWidth / currHeight;

        if (ratio != 0 && currRatio > ratio) {
            throw new BadImageSizeException(currRatio, ratio);
        } else {
            ratio = currRatio;
        }

        width = (int) (currWidth / currRatio);
        height = (int) (currHeight / currRatio);

        Image scaledImage = image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImage.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);

        ImageIO.write(bwImage, "png", new File("out.png"));

        return "";
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double ratio) {
        this.ratio = ratio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}
