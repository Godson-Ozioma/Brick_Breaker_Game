package com.example.brickbreaker_new;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application implements GameUpdateable {

    Pane root = new Pane();
    Scene scene = new Scene(root, 800, 600);
    Canvas canvas;
    GraphicsContext gc;

    //Objects objects;
    Ball ball;

    Bar bar;

    Brick brick = new Brick(0, 0, 0, 0, Color.BLACK);

    //Array list that stores array lists of bricks
    ArrayList<List<Brick>> bricks = new ArrayList<>();
    //ArrayList<Brick> bricks = new ArrayList<Brick>();
    double brickMargin = 1;

    //launch method to launch the app
    public static void main(String[] args) {
        launch();
    }


    //Animation Timer
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {

            update();
            render(gc);

        }
    };

    public void update(){
        //resets the background to remove object trails
        gc.setFill(Color.LIGHTCYAN);
        //background measurements to fill
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        checkForCollision();
        //gameOver();

        ball.update();
        bar.update();
    }


    public void render(GraphicsContext gc){
        ball.render(gc);
        bar.render(gc);
        for (List<Brick> b : bricks){
            for (Brick brick : b){
                brick.render(gc);
            }

        }

        brick.render(gc);
    }

    public void checkForCollision(){
        // ball and bar
        if (ball.collisionRectangle.intersects(bar.collisionRectangle.getX(), bar.collisionRectangle.getY(), bar.collisionRectangle.getWidth(), bar.collisionRectangle.getHeight())){
            ball.dy = -ball.dy;
        }

        for (List<Brick> b : bricks){
            for (Brick brick : b){

                if (ball.collisionRectangle.intersects(brick.collisionRectangle.getX(), brick.collisionRectangle.getY(), brick.collisionRectangle.getWidth(), brick.collisionRectangle.getHeight())){

                    //delete bricks
                    b.remove(brick);

                    ball.dy = -ball.dy;

                    //bricks.remove(brick);
                }

                //brick.render(gc);
            }

        }

    }

//    public void gameOver(){
//
//       // if(bricks.size())
//        for (List<Brick> b : bricks){
//            for (Brick brick : b){
//
//                if (b.size() == 0){
//                    ball.dy = 0;
//                    ball.dx = 0;
//                }
//
//
//            }
//
//        }
//
//    }


    //move the bar at the bottom using the keyboard [WASD]
    EventHandler<KeyEvent> barMovementHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {

            //move right
            if (keyEvent.getCode() == KeyCode.D){
                bar.moveRight();
            }

            //move left
            if (keyEvent.getCode() == KeyCode.A){
                bar.moveLeft();
            }


        }
    };



    @Override
    public void start(Stage stage) throws Exception {

        //set the scene
        stage.setScene(scene);
        //display the stage
        stage.show();

        //building the canvas
        canvas = new Canvas(800,600);
        gc = canvas.getGraphicsContext2D();

        //set background colour
        gc.setFill(Color.LIGHTCYAN);
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        //display the canvas
        root.getChildren().add(canvas);

        //create a ball object
        ball = new Ball(400, 300, 20, 20);
        ball.render(gc);

        //create a bar object
        bar = new Bar(350, 570, 100, 10, Color.BLACK);
        bar.render(gc);


        for (int j = 0; j < 4; j++){

            List<Brick> row = new ArrayList<>();
            double y = brickMargin;

            if (j != 0){
                //gets a brick from the row of bricks above
                Brick oldBrick = bricks.get(j - 1).get(0);
                y = oldBrick.y + oldBrick.height + brickMargin;
            }

            for (int i = 0; i < 38; i++){
                // to add first brick
                if(i == 0){
                    row.add(new Brick(brickMargin, y, 20, 20, Color.BLACK));
                }else{
                    Brick prevBrick = row.get(i - 1);
                    row.add(new Brick(prevBrick.x + prevBrick.width + brickMargin, y, 20, 20, Color.BLACK));
                }

            }

            bricks.add(row);



        }







        //handle keyboard movement event for the bar
        scene.setOnKeyPressed(barMovementHandler);

        timer.start();

       // bar.collisionRectangle = new Rectangle(x, y, widt);

    }
}
