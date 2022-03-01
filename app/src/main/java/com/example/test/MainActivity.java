package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult, tvScore;
    private ImageView imRoulette;
    private Random random;
    private int oldDegree, degree, factorX, factorY;
    private static final float FACTOR = 4.86f;
    private String[] numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        tvResult.setText("");
        tvScore.setText("");
    }

    private void init() {
        tvResult = findViewById(R.id.textViewResult);
        tvScore = findViewById(R.id.textViewScore);
        imRoulette = findViewById(R.id.imageViewRoulette);
        random = new Random();
        oldDegree = 0;
        degree = 0;
        factorX = 1;
        factorY = 3;

        numbers = new String[]
                {"32 RED", "15 BLACK", "19 RED", "4 BLACK",
                "21 RED", "2 BLACK", "25 RED", "17 BLACK", "34 RED",
                "6 BLACK", "27 RED", "13 BLACK", "36 RED", "11 BLACK", "30 RED",
                "8 BLACK", "23 RED", "10 BLACK", "5 RED", "24 BLACK", "16 RED", "33 BLACK",
                "1 RED", "20 BLACK", "14 RED", "31 BLACK", "9 RED", "22 BLACK", "18 RED",
                "29 BLACK", "7 RED", "28 BLACK", "12 RED", "35 BLACK", "3 RED", "26 BLACK", "0"};
    }

    public void onClickSpin(View view) {
        oldDegree = degree % 360;
        degree = random.nextInt(3600) + 720;

        RotateAnimation rotateAnimation = new RotateAnimation(
                oldDegree,
                degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(3600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tvResult.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvResult.setText(getResult(360 - (degree % 360)));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imRoulette.startAnimation(rotateAnimation);
    }

    private String getResult(int degree) {
        String text = "";

        for (int i = 0; i < 37; i++) {
            if (degree >= (FACTOR * factorX) && degree <= (FACTOR * factorY)) {
                text = numbers[i];
            }
            factorX += 2;
            factorY += 2;
        }
        if ((degree >= (FACTOR * 73) && degree < 360) || (degree >= 0 && degree < (FACTOR * 1))) {
            text = numbers[numbers.length - 1];
        }

        return text;
    }

    @SuppressLint("SetTextI18n")
    public void onClickScore(View view) {
        tvScore.setText("Score : " + random.nextInt());
    }
}