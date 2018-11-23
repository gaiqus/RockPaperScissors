package com.bgaje.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int playersScore;
    int computerScore;

    ImageView playerImageView;
    TextView resultTextView;
    Button rockButton;
    Button paperButton;
    Button scissorsButton;

    Choice playerChoice;
    Choice computerChoice;


    public enum Choice{
        ROCK,
        PAPER,
        SCISSORS;

        /**
         * Pick a random value of the Choice enum.
         * @return a random Choice.
         */
        public static Choice getRandomChoice() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);

        rockButton.setClickable(false);
        paperButton.setClickable(false);
        scissorsButton.setClickable(false);

    }

    public void startGame(View view) {

        resultTextView = findViewById(R.id.resultTextView);

        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);

        rockButton.setClickable(true);
        paperButton.setClickable(true);
        scissorsButton.setClickable(true);

        playersScore = 0;
        computerScore = 0;

        actualizeScore();
        resultTextView.setText("Let's play!");

    }

    public Choice computerMove(){

        ImageView computerImageView = findViewById(R.id.computerImageView);

        computerChoice = Choice.getRandomChoice();
        switch (computerChoice){

            case ROCK:
                computerImageView.setImageResource(R.drawable.rock);
                break;
            case PAPER:
                computerImageView.setImageResource(R.drawable.paper);
                break;
            case SCISSORS:
                computerImageView.setImageResource(R.drawable.scissors);
                break;

        }

        return computerChoice;

    }

    public void actualizeScore(){
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + playersScore + "/" + computerScore);}


    public void compare(){
        resultTextView = findViewById(R.id.resultTextView);

        if(computerChoice == playerChoice){

            resultTextView.setText("Draw");
        }
        else if((computerChoice == Choice.ROCK && playerChoice == Choice.PAPER) || (computerChoice == Choice.PAPER && playerChoice == Choice.SCISSORS) || (computerChoice == Choice.SCISSORS && playerChoice == Choice.ROCK)) {
            resultTextView.setText("You win");
            playersScore++;
            actualizeScore();}
        else if((computerChoice == Choice.ROCK && playerChoice == Choice.SCISSORS) || (computerChoice == Choice.PAPER && playerChoice == Choice.ROCK) || (computerChoice == Choice.SCISSORS && playerChoice == Choice.PAPER)) {
            resultTextView.setText("You lost");
            computerScore++;
            actualizeScore();}
    }

    public Choice playerMove(View view) {


        switch(view.getId())
        {
            case R.id.rockButton:
                playerChoice = Choice.ROCK;

                playerImageView = findViewById(R.id.playerImageView);
                playerImageView.setImageResource(R.drawable.rock);

                break;

            case R.id.paperButton:
                playerChoice = Choice.PAPER;

                playerImageView = findViewById(R.id.playerImageView);
                playerImageView.setImageResource(R.drawable.paper);

                break;

            case R.id.scissorsButton:
                playerChoice = Choice.SCISSORS;

                playerImageView = findViewById(R.id.playerImageView);
                playerImageView.setImageResource(R.drawable.scissors);

                break;

        }

        computerMove();
        compare();

        return playerChoice;
    }

}
