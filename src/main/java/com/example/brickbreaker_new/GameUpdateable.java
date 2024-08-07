package com.example.brickbreaker_new;

import javafx.scene.canvas.GraphicsContext;

public interface GameUpdateable {
    public void update();
    public void render(GraphicsContext gc);
}
