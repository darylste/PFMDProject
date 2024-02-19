package com.example.projectapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class Spaceship {

    RectF rect;
    private Bitmap bitmap;
    private Bitmap bitmapleft;
    private Bitmap bitmapright;
    private Bitmap bitmapdown;
    public Bitmap currentBitmap;
    private float height;
    private float length;
    private float x;
    private float y;

    private float SpaceShipSpeed;
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;
    public final int UP = 3;
    public final int DOWN = 4;

    ///maybe more movement than this
    private int SpaceShipMoving = STOPPED;
    private int shipSpeed;

    public Spaceship(Context context, int screenX, int screenY){

        rect = new RectF();

        length = screenX/10;
        height = screenY/10;

        x = screenX / 2;
        y = screenY / 2;

        shipSpeed = 350;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceshipright);

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);

        //  bitmapup = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceshipup);
        //  bitmapup = Bitmap.createScaledBitmap(bitmapup, (int) (length), (int) (height),false);

        bitmapright = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceshipright);
        bitmapright = Bitmap.createScaledBitmap(bitmapright, (int) (length), (int) (height),false);

        bitmapleft = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceshipleft);
        bitmapleft = Bitmap.createScaledBitmap(bitmapleft, (int) (length), (int) (height),false);

        bitmapdown = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceshipdown);
        bitmapdown = Bitmap.createScaledBitmap(bitmapdown, (int) (length), (int) (height),false);

        // currentBitmap = bitmap;
    }

    public void setMovementState(int state){
        SpaceShipMoving = state;
    }


    public void update(long fps){
        //  if(SpaceShipMoving == LEFT){
        //      x = x - SpaceShipSpeed / fps;
        //       currentBitmap = bitmapleft;
        //   }
        //    if(SpaceShipMoving == RIGHT){
        //        x = x + SpaceShipSpeed / fps;
        //        currentBitmap = bitmapright;
        //    }
        //    if(SpaceShipMoving == UP){
        //        y = y - SpaceShipSpeed / fps;
        // currentBitmap = bitmapup;

        //    }

        //    if(SpaceShipMoving == DOWN){
        //        y=y + SpaceShipSpeed / fps;
        //   currentBitmap = bitmapdown;

        //   }

        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;

    }


    public RectF getRect(){
        return rect;
    }

    public Bitmap getBitmap(){

        return bitmap;
    }

    public float getX(){
        return x;
    }

    public float getLength(){
        return length;
    }




}
