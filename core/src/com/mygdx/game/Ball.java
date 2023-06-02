package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {

    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    boolean start = false;
    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

    }

    public void update(Paddle paddle) {
        if (start) {
            x += xSpeed;
            y += ySpeed;
            if (x - size < 0 || x + size > Gdx.graphics.getWidth()) {
                xSpeed = -xSpeed;
            }
            if (y + size > Gdx.graphics.getHeight()) {
                ySpeed = -ySpeed;
            }
            if (y < 0) {
                MyGdxGame.setGameOver(true);
                //ySpeed = -ySpeed;
            }
        }else {
            x = paddle.getX() + paddle.getLength()/2;
            y = paddle.getY() + paddle.getHeight() + size;
        }

    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void checkCollision(Paddle paddle) {
        if (collidesWith(paddle)) {
            if (x < paddle.getX() + (paddle.getLength() / 2) && xSpeed > 0) {
                if (xSpeed == 1 || xSpeed == -1){
                    xSpeed = -xSpeed;
                } else if (xSpeed > -6 && (xSpeed != 1 || xSpeed != -1)) {
                    xSpeed = xSpeed - 2;
                }
                ySpeed = -ySpeed;
                xSpeed = -xSpeed;
            } else if (x < paddle.getX() + (paddle.getLength() / 2)) {
                if (xSpeed > -6) {
                    xSpeed = xSpeed - 2;
                }
                ySpeed = -ySpeed;
            } else if (x > paddle.getX() + (paddle.getLength() / 2) && xSpeed < 0) {
                if (xSpeed < 6) {
                    xSpeed = xSpeed + 2;
                }
                xSpeed = -xSpeed;
                ySpeed = -ySpeed;
            } else if (x > paddle.getX() + (paddle.getLength() / 2)) {
                ySpeed = -ySpeed;
                if (xSpeed < 6) {
                    xSpeed = xSpeed + 2;
                }
            }
        }
    }


    private boolean collidesWith(Paddle paddle) {
        if (x - size > paddle.getX() + paddle.getLength()) {
            return false;
        } else if (x + size < paddle.getX()) {
            return false;
        } else if (y - size > paddle.getY() + paddle.getHeight()) {
            return false;
        } else if (y + size < paddle.getY()) {
            return false;
        } else {
            return true;
        }
    }


    public void checkCollision(Block block){
        if (collidesWith(block)){
            if (block.getY() < y + size && block.getY() + block.getHeight() > y - size){
                xSpeed = -xSpeed;
            }else{
                ySpeed = -ySpeed;
            }
            block.destroyed(true);
        }
    }
    private boolean collidesWith(Block block) {
        if (x - size >= block.getX() + block.getWidth()) {
            return false;
        } else if (x + size < block.getX()) {
            return false;
        } else if (y - size > block.getY() + block.getHeight()) {
            return false;
        } else if (y + size < block.getY()) {
            return false;
        } else {
            return true;
        }
    }

    public void setStart(boolean bool){
        start = bool;
    }

    public void reset(){
        x = Gdx.graphics.getWidth()/2;
        y = Gdx.graphics.getHeight()/2;
        ySpeed = -ySpeed;
    }
    public int getSize(){
        return size;
    }
    public void reverseYSpeed(){
        ySpeed = -ySpeed;
    }
}
