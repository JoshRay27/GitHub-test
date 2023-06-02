package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class StartScreen {

    boolean start;
    boolean end;
    ShapeRenderer backround;
    public StartScreen(){
        start = true;
        end = false;
    }

    public boolean getStart(){
        return start;
    }
    public void setStart(boolean bool){
        start = bool;
    }
    public boolean getEnd(){
        return end;
    }
    public void setEnd(boolean bool){
        end = bool;
    }
}
