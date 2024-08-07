package com.example.brickbreaker_new;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Objects implements GameUpdateable{

    double x, y;
    GraphicsContext gc;
    Image img;

    //handle collision of objects
    Rectangle collisionRectangle;


    //directional movement and speed
    double dx, dy;

    //constructor of the Objects Class
    public Objects(double x, double y) {
        this.x = x;
        this.y = y;

    }


    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {

    }
}
