package com.victorcharl.tictactoegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    String players = "oPlayer";
    int totalMoves = 9;

    String winner = "";

    ImageView iv1, iv2, iv3 ,iv4, iv5 ,iv6 ,iv7 ,iv8 ,iv9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iv1 = (ImageView)findViewById(R.id.imageView1);
        iv2 = (ImageView)findViewById(R.id.imageView2);
        iv3 = (ImageView)findViewById(R.id.imageView3);
        iv4 = (ImageView)findViewById(R.id.imageView4);
        iv5 = (ImageView)findViewById(R.id.imageView5);
        iv6 = (ImageView)findViewById(R.id.imageView6);
        iv7 = (ImageView)findViewById(R.id.imageView7);
        iv8 = (ImageView)findViewById(R.id.imageView8);
        iv9 = (ImageView)findViewById(R.id.imageView9);

    }

    public void placeYourLogo(View view) {
        ImageView playerLogo = (ImageView)view;
        if(players.equals("oPlayer"))
        {
            playerLogo.setImageResource(R.drawable.o_player);
            playerLogo.setClickable(false);
            checkWhoIsWinning();
            players = "xPlayer";
        }
        else
        {
            playerLogo.setImageResource(R.drawable.x_player);
            playerLogo.setEnabled(false);
            checkWhoIsWinning();
            players = "oPlayer";
        }
        totalMoves--;
        playerLogo.animate().scaleX(0.7f).scaleY(0.7f);
    }

    public void checkWhoIsWinning(){
        /*Winning Combinations
        * { 123 , 456 , 789 } vertical win
        * { 147 , 258 , 369 } horizontal win
        * { 159 , 357 } diagonal win*/
        //Conditions for Player O
        if((!(iv1.isClickable()) && !(iv2.isClickable()) && !(iv3.isClickable())) ||
                (!(iv4.isClickable()) && !(iv5.isClickable()) && !(iv6.isClickable())) ||
                (!(iv7.isClickable()) && !(iv8.isClickable()) && !(iv9.isClickable())) ||
                (!(iv1.isClickable()) && !(iv4.isClickable()) && !(iv7.isClickable())) ||
                (!(iv2.isClickable()) && !(iv5.isClickable()) && !(iv8.isClickable())) ||
                (!(iv3.isClickable()) && !(iv6.isClickable()) && !(iv9.isClickable())) ||
                (!(iv1.isClickable()) && !(iv5.isClickable()) && !(iv9.isClickable())) ||
                (!(iv3.isClickable()) && !(iv5.isClickable()) && !(iv7.isClickable())))
            {
                winner = "O";
                showOutcomeDialog();
            }
        //Conditions for Player X
        if((!(iv1.isEnabled()) && !(iv2.isEnabled()) && !(iv3.isEnabled())) ||
                (!(iv4.isEnabled()) && !(iv5.isEnabled()) && !(iv6.isEnabled())) ||
                (!(iv7.isEnabled()) && !(iv8.isEnabled()) && !(iv9.isEnabled())) ||
                (!(iv1.isEnabled()) && !(iv4.isEnabled()) && !(iv7.isEnabled())) ||
                (!(iv2.isEnabled()) && !(iv5.isEnabled()) && !(iv8.isEnabled())) ||
                (!(iv3.isEnabled()) && !(iv6.isEnabled()) && !(iv9.isEnabled())) ||
                (!(iv1.isEnabled()) && !(iv5.isEnabled()) && !(iv9.isEnabled())) ||
                (!(iv3.isEnabled()) && !(iv5.isEnabled()) && !(iv7.isEnabled())))
            {
                winner = "X";
                showOutcomeDialog();
            }
        if(totalMoves == 1)
            showOutcomeDialog();
    }

    public void showOutcomeDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        if(winner.equals(""))
            dialog.setTitle("DRAW");
        else
            dialog.setTitle("PLAYER " + winner + " WINS");

        dialog.setCancelable(false);
        dialog.setMessage("YOU WANNA PLAY AGAIN?");
        dialog.setPositiveButton("PLAY AGAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });
        dialog.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.create().show();
    }
}