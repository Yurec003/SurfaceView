package ru.pavlenty.surfacegame2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class Friend {
    private Paint paint;
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 8;
    private boolean boosting;
    private final int GRAVITY = -10;
    private int maxY;
    private int minY;
    private boolean d;
    private final int MIN_SPEED = 8;
    private final int MAX_SPEED = 20;
    private Rect detectCollision;
    private GameView win;
    Friend(GameView context, int screenX, int screenY){
        win=context;
        d=false;
        x = (int)(Math.random()*2000+2000);
        y = (int)(Math.random()*screenY);
        speed = 8;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.friend);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        boosting = false;

        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    public void update(int speed) {
        x-=this.speed;
        if (boosting) {
            this.speed=8;
        } else {
            this.speed=20;
        }

        if (x < 0 || d) {
            d=false;
            if(x<0) GameView.Stop();
            bitmap=BitmapFactory.decodeResource(win.getResources(), R.drawable.boom);
            x =(int)(Math.random()*2000)+3500;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            bitmap=BitmapFactory.decodeResource(win.getResources(), R.drawable.friend);

        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void destroyed() {
        bitmap=BitmapFactory.decodeResource(win.getResources(), R.drawable.boom);
        win.getClass().drawBitmap(bitmap, x, y, paint);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d=true;
    }
}