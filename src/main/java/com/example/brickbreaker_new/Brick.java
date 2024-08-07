package com.example.brickbreaker_new;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Objects{

    double width, height;
    Color color;

    public Brick(double x, double y, double width, double height, Color color) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = color;

        collisionRectangle = new Rectangle(x, y, width, height);
    }

    public void render(GraphicsContext gc){
        gc.setFill(color);
        gc.fillRect(x, y, width, height);

        gc.setStroke(Color.RED);

        gc.strokeRect(collisionRectangle.getX(), collisionRectangle.getY(), collisionRectangle.getWidth(), collisionRectangle.getHeight());

    }


    public void update() {
        collisionRectangle.setX(x);
        collisionRectangle.setY(y);
        collisionRectangle.setWidth(width);
        collisionRectangle.setHeight(height);
    }
}
