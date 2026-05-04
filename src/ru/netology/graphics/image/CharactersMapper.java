package ru.netology.graphics.image;

public class CharactersMapper implements TextColorSchema {

    private char[] schema = {'▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-'};

    public void setSchema(char[] customSchema) {
        schema = customSchema;
    }

    @Override
    public char convert(int color) {
        int index = color / (255 / schema.length);
        index = Math.min(index, schema.length - 1);
        return schema[index];
    }
}
