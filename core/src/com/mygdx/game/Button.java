package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Button {
    int x;
    int y;
    int width;
    int height;
    Color color;
    boolean pushed;

    public Button(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        color = Color.WHITE;
        pushed = false;
    }
    public void update(StartScreen start){
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        boolean click = Gdx.input.isButtonJustPressed(0);
        if (isOn(x,y)){
            color = Color.GREEN;
            if (click){
                pushed = true;
            }
        }else{
            color = Color.WHITE;
        }

    }
    public boolean isOn(int x, int y){
        if (x < this.x){
            return false;
        }else if(x > this.x + width){
            return false;
        }else if (Gdx.graphics.getHeight() - y < this.y){
            return false;
        }else if (Gdx.graphics.getHeight() -y > this.y + height){
            return false;
        }else{
            return true;
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public boolean getPushed(){
        return pushed;
    }
    public void setPushed(boolean bool){
        pushed = bool;
    }
    public void draw(ShapeRenderer shape){
        shape.setColor(color);
        shape.rect(x,y, width, height);

    }
}
