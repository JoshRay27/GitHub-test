package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
public class Block {
    int x, y, width, height;
    boolean destroyed = false;
    boolean hasPowerUp = false;
    PowerUp power;

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.power = null;
    }

    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, width, height);
    }

    public void destroyed(Boolean isHit) {
        destroyed = isHit;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public PowerUp getPower() {
        return power;
    }

    public void setPower(PowerUp power) {
        this.power = power;
    }

    public void setPowerUp(Boolean bool) {
        hasPowerUp = bool;
    }

    public boolean isPowerUp() {
        return hasPowerUp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void reset() {
        destroyed(true);
    }
}