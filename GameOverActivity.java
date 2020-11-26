package com.example.johndoe.eaglehunting;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity
{
    private Button StartAgain;
    private TextView DisplayScore;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // receive the score from flyingEagle class that matches "Score" in get("Score")
        score = getIntent().getExtras().get("Score").toString();

        StartAgain = (Button) findViewById(R.id.playAgain);
        DisplayScore = (TextView) findViewById(R.id.displayScore);


        StartAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main =  new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });

        DisplayScore.setText("Score: " + score);
    }

    public void quitGame(View v){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            finishAndRemoveTask();
        }
        finish();
    }
}
