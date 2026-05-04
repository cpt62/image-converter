package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
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

        ratio = ratio == 0 ? (double) currWidth / currHeight : ratio;
        width = width == 0 ? currWidth : width;
        height = height == 0 ? currHeight : height;


        Image scaledImage = image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImage.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);

        ImageIO.write(bwImage, "png", new File("out.png"));

        WritableRaster bwRaster = bwImage.getRaster();

        StringBuilder sb = new StringBuilder(width * height + height);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                sb.append(schema.convert(bwRaster.getPixel(w, h, new int[3])[0]));
                sb.append(schema.convert(bwRaster.getPixel(w, h, new int[3])[0]));
            }
            sb.append("\n");
        }
        return sb.toString();
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
        this.schema = schema;
    }
}
