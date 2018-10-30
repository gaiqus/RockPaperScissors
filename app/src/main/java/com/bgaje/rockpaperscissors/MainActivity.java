package com.bgaje.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


//    Button startGameButton = findViewById(R.id.startButton);

    int playersScore;
    int computerScore;
//    0 = rock
//      1 = paper
//      2 = scissors
    int computerChoice;
    int playerChoice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rockButton = findViewById(R.id.rockButton);
        Button paperButton = findViewById(R.id.paperButton);
        Button scissorsButton = findViewById(R.id.scissorsButton);

        rockButton.setClickable(false);
        paperButton.setClickable(false);
        scissorsButton.setClickable(false);

    }

    public void startGame(View view) {

        TextView resultTextView = findViewById(R.id.resultTextView);

        Button rockButton = findViewById(R.id.rockButton);
        Button paperButton = findViewById(R.id.paperButton);
        Button scissorsButton = findViewById(R.id.scissorsButton);

        rockButton.setClickable(true);
        paperButton.setClickable(true);
        scissorsButton.setClickable(true);

        playersScore = 0;
        computerScore = 0;

        actualizeScore();
        resultTextView.setText("Let's play!");

    }

    public int computerMove(){

        ImageView computerImageView = findViewById(R.id.computerImageView);
        computerChoice = (int) (Math.random() * 3);


        if(computerChoice == 0){
            computerImageView.setImageResource(R.drawable.rock);
        }
        else if(computerChoice == 1){
            computerImageView.setImageResource(R.drawable.paper);
        }
        else if(computerChoice == 2){
            computerImageView.setImageResource(R.drawable.scissors);
        }

        return computerChoice;

    }

    public void actualizeScore(){
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + playersScore + "/" + computerScore);}


    public void compare(){
        TextView resultTextView = findViewById(R.id.resultTextView);


        if(computerChoice == playerChoice){

            resultTextView.setText("Draw");
        }
        else if((computerChoice == 0 && playerChoice == 1) || (computerChoice == 1 && playerChoice == 2) || (computerChoice == 2 && playerChoice == 0)) {
            resultTextView.setText("You win");
            playersScore++;
            actualizeScore();}
        else if((computerChoice == 0 && playerChoice == 2) || (computerChoice == 1 && playerChoice == 0) || (computerChoice == 2 && playerChoice == 1)) {
            resultTextView.setText("You lost");
            computerScore++;
            actualizeScore();}
    }

    public int playerRock(View view) {

        playerChoice = 0;
        ImageView playerImageView = findViewById(R.id.playerImageView);
        playerImageView.setImageResource(R.drawable.rock);
        computerMove();
        compare();



        return playerChoice;

    }

    public int playerPaper(View view) {

        playerChoice = 1;
        ImageView playerImageView = findViewById(R.id.playerImageView);
        playerImageView.setImageResource(R.drawable.paper);
        computerMove();
        compare();

        return playerChoice;
    }

    public int playerScissors(View view) {

        playerChoice = 2;
        ImageView playerImageView = findViewById(R.id.playerImageView);
        playerImageView.setImageResource(R.drawable.scissors);
        computerMove();
        compare();

        return playerChoice;
    }
}
