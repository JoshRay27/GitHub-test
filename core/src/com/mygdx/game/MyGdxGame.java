package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;


public class MyGdxGame extends ApplicationAdapter {

	ShapeRenderer shapeBlock;
	ShapeRenderer shape;
	ShapeRenderer shapePaddle;
	ShapeRenderer shapePowerUp;
	ShapeRenderer shapeButton;
	Ball ball;
	Paddle paddle;
	ArrayList<Block> blocks = new ArrayList<>();
	ArrayList<PowerUp> powerUps = new ArrayList<>();
	ArrayList<Ball> powerBalls = new ArrayList<>();
	PowerUp powerTest;
	Texture texture;
	Texture gameOverTex;
	Texture youWinTex;
	Texture startButtonTex;
	SpriteBatch batch;
	TextureRegion region1;
	TextureRegion region2;
	Random rand = new Random();
	static Boolean gameOver = false;
	static boolean restart = false;
	Boolean youWin = false;
	StartScreen start;
	Button startButton;

	@Override
	public void create() {


		start = new StartScreen();

		batch = new SpriteBatch();
		texture = new Texture("pico 8 pipe puzzle.png");
		region1 = new TextureRegion(texture,80 ,16, 16, 16);
		region2 = new TextureRegion(texture, 64,16,16,16);
		gameOverTex = new Texture("gameOver.jpg");
		youWinTex = new Texture("youWin.jpg");
		startButtonTex = new Texture("start.jpg");

		shape = new ShapeRenderer();
		shapePaddle = new ShapeRenderer();
		shapeBlock = new ShapeRenderer();
		shapePowerUp = new ShapeRenderer();
		shapeButton = new ShapeRenderer();

		startButton = new Button( Gdx.graphics.getWidth()/2 - 75, Gdx.graphics.getHeight()/2, 150, 35);

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		ball = new Ball(width / 2,
				height / 4,
				10, 5, 5);

		paddle = new Paddle(width / 2, 10, 128, 16);

		powerTest = new PowerUp(50,50, 25);

		generateBlocks();


/*
		int blockWidth = 63;
		int blockHeight = 20;
		int i = 0;
		int j = 0;
		for (int y = height / 2; y < height; y += blockHeight + 20) {
			for (int x = 0; x < width; x += blockWidth + 10) {
				blocks.add(new Block(x, y, blockWidth, blockHeight));
				if (rand.nextInt(100) < 10){
					powerUps.add(new PowerUp(x + blockWidth/2, y + blockHeight/2, blockHeight/2));
					powerBalls.add(new Ball(paddle.getX() + (paddle.getLength()/2), paddle.getY() + paddle.getHeight()+10, 10,0,0) );
					blocks.get(i).setPowerUp(true);
					blocks.get(i).setPower(powerUps.get(j));
					j++;
				}
				i++;
			}
		}
		*/

	}

	@Override
	public void render() {
		if (!start.getStart()) {
			if (!gameOver) {
				//ScreenUtils.clear(0,0,0,0);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				shape.begin(ShapeRenderer.ShapeType.Filled);
				shapePaddle.begin(ShapeRenderer.ShapeType.Filled);
				shapeBlock.begin(ShapeRenderer.ShapeType.Filled);
				shapePowerUp.begin(ShapeRenderer.ShapeType.Filled);

				ball.checkCollision(paddle);

				paddle.update();
				//paddle.draw(shapePaddle);

				batch.begin();
				batch.draw(region1, paddle.getX() - 20, paddle.getY(), 168, 16);
				batch.end();

				if (Gdx.input.isButtonJustPressed(0)){
					ball.setStart(true);
				}


				ball.update(paddle);
				ball.draw(shape);

				batch.begin();
				for (Block block : blocks) {
				//for (int i = 0; i < blocks.size(); i++){
					//block.update();
					batch.draw(region2, block.getX() - 10, block.getY(), 84, 20);
					//block.draw(shapeBlock);
					//System.out.println(blocks.size());
					ball.checkCollision(block);

				}
				batch.end();

				for (int i = 0; i < blocks.size(); i++) {
					Block b = blocks.get(i);
					if (b.isDestroyed()) {
						if (b.isPowerUp()) {
							b.getPower().setHit(true);
						}
						blocks.remove(b);
						i--;
					}
				}

				for (int i = 0; i < powerUps.size(); i++) {
					for (int j = 0; j < blocks.size(); j++) {
						powerUps.get(i).checkCollision(blocks.get(j));
					}
					powerUps.get(i).checkCollision(paddle);
					powerUps.get(i).update();
					powerUps.get(i).draw(shapePowerUp);

				}

				shapePowerUp.end();
				shape.end();
				shapePaddle.end();
				shapeBlock.end();

				if (blocks.size() < 1) {
					gameOver = true;
					youWin = true;
				}
			} else {
				if (restart) {
					start.setStart(true);
					startButton.setPushed(false);
					setGameOver(false);
					ball.reset();
				}
				gameOver();

			}
		}else{
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			shapeButton.begin(ShapeRenderer.ShapeType.Filled);
			//startButton.draw(shapeButton);
			batch.begin();
			batch.draw(startButtonTex, startButton.getX(), startButton.getY(), startButton.getWidth(),startButton.getHeight());
			batch.end();
			startButton.update(start);
			shapeButton.end();
			if (startButton.getPushed()){
				start.setStart(false);
			}
		}
	}

	private void gameOver(){
		batch.begin();
		if (youWin) {
			batch.draw(youWinTex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		} else {
			batch.draw(gameOverTex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			restart = Gdx.input.isButtonJustPressed(0);
			if (restart){
				generateBlocks();
			}

		}
		batch.end();


	}

	static public void setGameOver(Boolean bool){
		gameOver = bool;
	}

	public void dispose(){
		batch.dispose();
		texture.dispose();
	}

	public void generateBlocks(){
		blocks = new ArrayList<Block>();
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		int blockWidth = 63;
		int blockHeight = 20;
		int i = 0;
		int j = 0;
		for (int y = height / 2; y < height; y += blockHeight + 20) {
			for (int x = 0; x < width; x += blockWidth + 10) {
				blocks.add(new Block(x, y, blockWidth, blockHeight));
				if (rand.nextInt(100) < 10){
					powerUps.add(new PowerUp(x + blockWidth/2, y + blockHeight/2, blockHeight/2));
					powerBalls.add(new Ball(paddle.getX() + (paddle.getLength()/2), paddle.getY() + paddle.getHeight()+10, 10,0,0) );
					blocks.get(i).setPowerUp(true);
					blocks.get(i).setPower(powerUps.get(j));
					j++;
				}
				i++;
			}
		}
	}
}