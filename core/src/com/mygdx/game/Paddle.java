package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int x;
    int y;
    int length;
    int height;
    int numBlocks;

    public Paddle(int x, int y, int length, int height){
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
    }

    public void update(){
        int inputX = Gdx.input.getX();
       // int inputY = Gdx.input.getY();
        if (inputX > 0 || inputX < Gdx.graphics.getWidth()){
            x = inputX - (length/2);
        }
        /*
        if (inputY > 0 || inputY < Gdx.graphics.getHeight()){
            y = Gdx.graphics.getHeight() - inputY;
        }
         */
    }



    public int getLength(){
        return length;
    }
    public int getHeight(){
        return height;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x, y, length, height);
    }
}
