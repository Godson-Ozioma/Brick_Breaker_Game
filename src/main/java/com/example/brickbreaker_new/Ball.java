package com.example.brickbreaker_new;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ball extends Objects {

    double width, height;

    public Ball(double x, double y, double w, double h) {
        super(x, y);
        width  = w;
        height = h;

        //ball movement speed
        dx = 3;
        dy = 3;

        collisionRectangle = new Rectangle(x, y, width, height);
        //collisionRectangle = new Rectangle(x, y, width, height);

        img = new Image(this.getClass().getResourceAsStream("ball.png"));
    }

    public void move()
    {
        x += dx;
        if (x > 800){
            dx = -dx;
        }

        if(x<0){
            dx=-dx;
        }

        y+=dy;
        if(y>600){
            dy=-dy;
        }

        if(y<0){
            dy=-dy;
        }

        updateBallRectangle();

    }


    public void render(GraphicsContext gc){
        gc.drawImage(img, x, y, width, height);
        gc.setFill(Color.RED);

        gc.strokeRect(collisionRectangle.getX(), collisionRectangle.getY(), collisionRectangle.getWidth(), collisionRectangle.getHeight());

    }

    public void updateBallRectangle(){
        collisionRectangle.setX(x);
        collisionRectangle.setY(y);

    }

    public void update(){
        move();
        updateBallRectangle();


    }


}
