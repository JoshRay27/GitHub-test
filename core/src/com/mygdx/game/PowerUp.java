package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;


public class PowerUp {

    int x;
    int y;
    int ySpeed;
    int xSpeed;
    int size;
    boolean isHit;
    Color color = Color.YELLOW;

    public PowerUp(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.ySpeed = 5;
        this.xSpeed = 0;
        this.size = size;
        this.isHit = false;
    }
    public void update(){
        if (isHit){
            y -= ySpeed;
            x += xSpeed;
            if (x-size < 0 || x+size > Gdx.graphics.getWidth()){
                xSpeed = -xSpeed;
            }
            if ( y+size > Gdx.graphics.getHeight()) {
                ySpeed = -ySpeed;
            }
            /*
            if (y < 0 ){
                //MyGdxGame.setGameOver(true);
                ySpeed = - ySpeed;
            }

             */
        }

    }
    public void checkCollision(Block block){
        if (isHit) {
            if (collidesWith(block)) {
                if (block.getY() < y + size && block.getY() + block.getHeight() > y - size) {
                    xSpeed = -xSpeed;
                } else {
                    ySpeed = -ySpeed;
                }
                block.destroyed(true);
            }
        }
    }
    private boolean collidesWith(Block block) {
        if (x - size > block.getX() + block.getWidth()) {
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

    public void checkCollision(Paddle paddle){
        if (collidesWith(paddle)){
            ySpeed = -ySpeed;
            xSpeed = 5;
        }
    }

    private boolean collidesWith(Paddle paddle){
        if (y - size > paddle.getY() + paddle.getHeight()){
            return false;
        }else if(y + size < paddle.getY()){
            return false;
        }else if (x - size > paddle.getX() + paddle.getLength()){
            return false;
        }else if (x + size < paddle.getX()){
            return false;
        }else{
            return true;
        }
    }
    public void setHit(Boolean bool){
        isHit = bool;
    }
    public void draw(ShapeRenderer shape){
        if (isHit){
            shape.circle(x, y, size);
            shape.setColor(color);
        }

    }
}
