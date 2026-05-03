package ru.netology.graphics.image;

public class CharactersMapper implements TextColorSchema {

    private char[] schema = {'▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-'};

    public void setSchema(char[] customSchema) {
        schema = customSchema;
    }

    @Override
    public char convert(int color) {
        return schema[255 / schema[color]];
    }
}
