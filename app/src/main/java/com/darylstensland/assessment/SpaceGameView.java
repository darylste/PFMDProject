package com.darylstensland.assessment;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class SpaceGameView extends SurfaceView implements Runnable {

    private Context context;

    private Spaceship spaceship;

    private Thread gameThread = null;
    private SurfaceHolder ourHolder;

    private volatile boolean playing;

    private boolean paused = true;

    private Canvas canvas;
    private Paint paint;

    private long fps;

    private long timeThisFrame;
    private int screenX;
    private int screenY;

    private int score = 0;

    private int lives = 3;


    public SpaceGameView(Context context, int x, int y) {
        super(context);


        this.context = context;

        ourHolder = getHolder();
        paint = new Paint();

        screenX = x;
        screenY = y;

        spaceship = new Spaceship(context, x, y);

        initLevel();
    }


    private void initLevel() {


    }


    @Override
    public void run() {
        while (playing) {


            long startTime = System.currentTimeMillis();


            if (!paused) {
                update();
            }


            draw();


            long timeThis = System.currentTimeMillis() - startTime;
            if (timeThis >= 1) {
                fps = 1000 / timeThis;
            }

        }

    }


    private void update() {


    }


    private void draw() {

        if (ourHolder.getSurface().isValid()) {

            canvas = ourHolder.lockCanvas();
            canvas.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.background), 0, 0, paint);
            canvas.drawBitmap(Spaceship.getBitmap(), Spaceship.getX(), Spaceship.getY(), paint);
            paint.setColor(Color.argb(255, 249, 129, 0));

            paint.setTextSize(40);
            canvas.drawText("Score: " + score + "   Lives: " +
                    lives, 10, 50, paint);
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }


    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen

            case MotionEvent.ACTION_DOWN:

                paused = false;

                if(motionEvent.getY() > screenY - screenY / 2) {
                    if (motionEvent.getX() > screenX / 2) {
                        spaceship.setMovementState(spaceship.RIGHT);
                    } else {
                        spaceship.setMovementState(spaceship.LEFT);
                    }


                }

                if(motionEvent.getY() < screenY - screenY / 2) {
                    if (motionEvent.getX() > screenX / 2) {
                        spaceship.setMovementState(spaceship.UP);
                    } else {
                        spaceship.setMovementState(spaceship.DOWN);
                    }


                }

                //    if(motionEvent.getY() < screenY - screenY / 8) {
                // Shots fired
                //       if(bullet.shoot(playerShip.getX()+ playerShip.getLength()/2,screenY,bullet.UP)){
                //        soundPool.play(shootID, 1, 1, 0, 0, 1);
                //       }
                //   }

                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:

                //   if(motionEvent.getY() > screenY - screenY / 10) {
                spaceship.setMovementState(spaceship.STOPPED);
                //   }
                break;
        }
        return true;
    }

}