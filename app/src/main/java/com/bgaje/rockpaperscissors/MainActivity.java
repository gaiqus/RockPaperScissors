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
    int choiceResource;

    ImageView playerImageView;
    TextView resultTextView;
    Button rockButton;
    Button paperButton;
    Button scissorsButton;
    ImageView computerImageView;
    TextView scoreTextView;

    Choice playerChoice;
    Choice computerChoice;

    public enum Choice {
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

    public int getChoiceResource(Choice choice) {

        if (choice == Choice.ROCK) {
            choiceResource = R.drawable.rock;
        } else if (choice == Choice.PAPER) {
            choiceResource = R.drawable.paper;
        } else if (choice == Choice.SCISSORS) {
            choiceResource = R.drawable.scissors;
        }
        return choiceResource;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize all the views
        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);
        resultTextView = findViewById(R.id.resultTextView);
        computerImageView = findViewById(R.id.computerImageView);
        scoreTextView = findViewById(R.id.scoreTextView);
        playerImageView = findViewById(R.id.playerImageView);

        //Set all the choice buttons not clickable TODO - make all player choice buttons not clickable in xml (simple command in xml not working)
        rockButton.setClickable(false);
        paperButton.setClickable(false);
        scissorsButton.setClickable(false);
    }

    public void startGame(View view) {

        //Set all the choice buttons clickable
        rockButton.setClickable(true);
        paperButton.setClickable(true);
        scissorsButton.setClickable(true);

        playersScore = 0;
        computerScore = 0;

        actualizeScore();
        resultTextView.setText("Let's play!");
    }

    public Choice computerMove() {

        computerChoice = Choice.getRandomChoice();
        computerImageView.setImageResource(getChoiceResource(computerChoice));

        return computerChoice;
    }

    public void actualizeScore() {

        scoreTextView.setText("Score: " + playersScore + "/" + computerScore);
    }

    public void compare() {

        if (computerChoice == playerChoice) {

            resultTextView.setText("Draw");
        } else if ((computerChoice == Choice.ROCK && playerChoice == Choice.PAPER) || (computerChoice == Choice.PAPER && playerChoice == Choice.SCISSORS) || (computerChoice == Choice.SCISSORS && playerChoice == Choice.ROCK)) {
            playerWins();
        } else if ((computerChoice == Choice.ROCK && playerChoice == Choice.SCISSORS) || (computerChoice == Choice.PAPER && playerChoice == Choice.ROCK) || (computerChoice == Choice.SCISSORS && playerChoice == Choice.PAPER)) {
            computerWins();
        }
    }

    public void playerWins() {
        resultTextView.setText("You win");
        playersScore++;
        actualizeScore();
    }

    public void computerWins() {
        resultTextView.setText("You lost");
        computerScore++;
        actualizeScore();
    }

    public Choice playerMove(View view) {

        switch (view.getId()) {
            case R.id.rockButton:
                playerChoice = Choice.ROCK;
                break;
            case R.id.paperButton:
                playerChoice = Choice.PAPER;
                break;
            case R.id.scissorsButton:
                playerChoice = Choice.SCISSORS;
                break;
        }

        playerImageView.setImageResource(getChoiceResource(playerChoice));
        computerMove();
        compare();

        return playerChoice;
    }

}
