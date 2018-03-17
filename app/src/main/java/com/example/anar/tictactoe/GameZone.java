package com.example.anar.tictactoe;

//required libraries
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.Random;

public class GameZone extends AppCompatActivity {

    MediaPlayer backgroundMusic;
    private String player = "";
    private Button[] buttons;
    int movesMade = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_zone);

        //media player---------------------------------------------------------
        backgroundMusic = MediaPlayer.create(this, R.raw.background);
        backgroundMusic.start(); // we will add this to a refresh button :)

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            player = extras.getString("player");
        }
        //-----------------------------------------------------------------------

        //GAMING BUTTONS----------------------------------------------------------------
        //storing buttons in the array
        buttons = new Button[9];

        //storing our buttons in the array so that we can iterate them
        buttons[0] = (Button) findViewById(R.id.btn1);
        buttons[1] = (Button) findViewById(R.id.btn2);
        buttons[2] = (Button) findViewById(R.id.btn3);
        buttons[3] = (Button) findViewById(R.id.btn4);
        buttons[4] = (Button) findViewById(R.id.btn5);
        buttons[5] = (Button) findViewById(R.id.btn6);
        buttons[6] = (Button) findViewById(R.id.btn7);
        buttons[7] = (Button) findViewById(R.id.btn8);
        buttons[8] = (Button) findViewById(R.id.btn9);
        //------------------------------------------------------------------
    }


    //this method listens to TicTacToe buttons
    public void gamingButtonListener(View v){
        int buttonIndex = Integer.valueOf(String.valueOf(v.getTag())) - 1; //I cannot convert getTag to integer in one go so I first convert it to String
        if(player.equals("X")){

            buttons[buttonIndex].setText("X");
            controller(buttonIndex);
        }
    }



    //this method controls the bot and gives command to the controller method that in its turn decides the winner
    public void controller(final int btn) {

        movesMade++;
        String winner = "";

        if (movesMade < 9) {
            //we need to call the bot method after a delay so that there will be more time for the application to set text of the button
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bot(btn);
                }
            }, 100);
        }

        else {

        }

    }

    //

    //this method announces the winner
    public void announcement(String text, final Button[] buttons){
        AlertDialog.Builder builder = new AlertDialog.Builder(GameZone.this);
        builder.setTitle(text + " won");
        builder.setMessage("");

        //if user wants to play again
        builder.setPositiveButton("New Game", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){

                for(int j=0;j<=8;j++){
                    buttons[j].setText("");
                    buttons[j].setVisibility(View.VISIBLE);
                }

                movesMade = 1; //set the counter to 0 so that the game can keep track of moves one more time
            }
        });

        //if user decides to quit the game
        builder.setNegativeButton("Quit", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                System.exit(0);
            }
        });

        builder.show();
    }




    //this bot gonna choose a random button where it gonna insert the circle but it also ensures that the button was not already clicked
    public void bot(int btn){

        int randBtn;
        Random randGnrt = new Random();
        Log.d("PRIVET", "TEST");
        //do{
           // randBtn = randGnrt.nextInt(9) ;//rand.nextInt((max - min) + 1) + min;
           // Log.d("PRIVET", String.valueOf(randBtn));
           // Log.d("PRIVET", String.valueOf(buttons[randBtn].getText().equals("")));

        //} while( !(buttons[randBtn].getText().equals(""))); //problem that do always does

        while(true){
            randBtn = randGnrt.nextInt(9) ;
            Log.d("PRIVET", String.valueOf(movesMade));

            if((buttons[randBtn].getText().equals(""))){
                break;
            }
            if(movesMade>=9){break;}
        }

        movesMade++;
        buttons[randBtn].setText("0");


        }


    }



