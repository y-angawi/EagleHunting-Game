package com.example.johndoe.eaglehunting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class flyingeagle extends View
{
    private Bitmap eagle[] = new Bitmap[2];
    private int eagleX = 10;
    private int eagleY;
    private int eagleSpeed;
    private boolean touch = false;

    private int canvasWidth, canvasHeight;

    private int yellowX, yellowY, yellowSpeed = 16;
    private Paint yellowPaint = new Paint();

    private int score, lifeCounter;

    private int greenX, greenY, greenSpeed = 20;
    private Paint greenPaint = new Paint();

    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();

    private Bitmap bakImg;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    public flyingeagle(Context context) {
        super(context);

        eagle[0] = BitmapFactory.decodeResource(getResources(), R.drawable.eagle1);
        eagle[1] = BitmapFactory.decodeResource(getResources(), R.drawable.eagle2);
        bakImg = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorePaint.setColor(Color.WHITE );
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.hgrey);

        eagleY = 550;
        score = 0;
        lifeCounter = 3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(bakImg, 0, 0, null);

        int minEagleY = eagle[0].getHeight();
        int maxEagleY = canvasHeight - eagle[0].getHeight() * 3;
        eagleY = eagleY + eagleSpeed;

        if (eagleY < minEagleY)
        {
            eagleY = minEagleY;
        }

        if (eagleY > maxEagleY)
        {
            eagleY = maxEagleY;
        }
        eagleSpeed += 2;

        if (touch)
        {
            canvas.drawBitmap(eagle[1], eagleX, eagleY, null);
            touch = false;
        }
        else
        {
            canvas.drawBitmap(eagle[0], eagleX, eagleY, null);
        }

        yellowX -= yellowSpeed;
        if (hitBallChecker(yellowX, yellowY))
        {
            score += 10;
            yellowX = -100;
        }

        if (yellowX < 0)
        {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxEagleY - minEagleY)) + minEagleY;
        }
        canvas.drawCircle(yellowX, yellowY, 25, yellowPaint);

        greenX -= greenSpeed;
        if (hitBallChecker(greenX, greenY))
        {
            score += 20;
            greenX = -100;
        }

        if (greenX < 0)
        {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxEagleY - minEagleY)) + minEagleY;
        }
        canvas.drawCircle(greenX, greenY, 25, greenPaint);

        redX -= redSpeed;
        if (hitBallChecker(redX, redY))
        {
            redX = -100;
            lifeCounter--;

            if (lifeCounter == 0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                // sed the user to GameOverActivity by Intent
                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                // send the score to the GameOverActivity.
                gameOverIntent.putExtra("Score", score);
                getContext().startActivity(gameOverIntent);

            }
        }

        if (redX < 0)
        {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxEagleY - minEagleY)) + minEagleY;
        }
        canvas.drawCircle(redX, redY, 30, redPaint);

        canvas.drawText("Score: " + score, 20, 60, scorePaint);

        for (int i=0; i<3; i++)
        {
            int x = (int) (580 + life[0].getWidth() * 1.5 * i);
            int y = 30;
            if (i < lifeCounter)
            {
                canvas.drawBitmap(life[0], x, y, null);
            }
            else
            {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
        // for loop instead of this
//        canvas.drawBitmap(life[0], 580, 10, null);
//        canvas.drawBitmap(life[0], 680, 10, null);
//        canvas.drawBitmap(life[0], 780, 10, null);
    }

    public boolean hitBallChecker(int x, int y)
    {
        if(eagleX < x && x < (eagleX + eagle[0].getWidth()) && eagleY < y &&  y < (eagleY + eagle[0].getHeight()))
        {
            return true;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
            eagleSpeed = -22;
        }
        return true;
    }
}
