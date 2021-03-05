package com.williamwatkins.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView questionTextView;
    TextView resultTextView;
    TextView scoreTextView;
    int score = 0;
    int noOfQuestions = 0;
    TextView timerTextView;
    ConstraintLayout gameLayout;

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        questionTextView = findViewById(R.id.questionTextView);
        resultTextView  = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        gameLayout = findViewById(R.id.gameLayout);

        startButton.setText("GO!");

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        newQuestion();
    }


    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        score = 0;
        noOfQuestions = 0;
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        gameLayout.setVisibility(View.VISIBLE);

        newQuestion();

        new CountDownTimer(3100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView =findViewById(R.id.timerTextView);

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                resultTextView.setText("Your score is " +(String.format("%d/%d", score, noOfQuestions)));
                timerTextView.setText("0s");
                startButton.setText("Play again?");
                startButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view){

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong!");
        }
        noOfQuestions++;
        scoreTextView.setText(String.format("%d/%d", score, noOfQuestions));

        newQuestion();
    }

    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        questionTextView.setText(a +  " + " + b);

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i <4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {
                    answers.add(rand.nextInt(41));
                }
                answers.add(incorrectAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }
}