package com.victorcharl.tictactoegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private String players = "PLAYER O TURNS";
    private int totalMoves = 9;

    private String winner = "";

    private ImageView iv1, iv2, iv3 ,iv4, iv5 ,iv6 ,iv7 ,iv8 ,iv9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);
        iv7 = findViewById(R.id.imageView7);
        iv8 = findViewById(R.id.imageView8);
        iv9 = findViewById(R.id.imageView9);

    }

    public void placeYourLogo(View view) {
        ImageView playerLogo = (ImageView)view;
        if(players.equals("PLAYER O TURNS"))
        {
            playerLogo.setImageResource(R.drawable.o_player);
            playerLogo.setClickable(false);
            checkWhoIsWinning();
            players = "PLAYER X TURNS";
        }
        else
        {
            playerLogo.setImageResource(R.drawable.x_player);
            playerLogo.setEnabled(false);
            checkWhoIsWinning();
            players = "PLAYER O TURNS";
        }
        totalMoves--;
        playerLogo.animate().scaleX(0.7f).scaleY(0.7f);
    }

    private void checkWhoIsWinning(){
        ImageView[] imageViews = new ImageView[]{null, iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9};
        Integer[][] winningCombinations = new Integer[][]{{1,2,3}, {4,5,6}, {7,8,9}, {1,4,7}, {2,5,8}, {3,6,9}, {1,5,9}, {3,5,7}};

        for (Integer[] winningCombination : winningCombinations) {
            if(!(imageViews[winningCombination[0]].isClickable()) && !(imageViews[winningCombination[1]].isClickable()) &&
                    !(imageViews[winningCombination[2]].isClickable())) {
                winner = "O";
                showOutcomeDialog();
            }
            else if(!(imageViews[winningCombination[0]].isEnabled()) && !(imageViews[winningCombination[1]].isEnabled()) &&
                    !(imageViews[winningCombination[2]].isEnabled())) {
                winner = "X";
                showOutcomeDialog();
            }
        }

        if(totalMoves == 1)
            showOutcomeDialog();
    }

    private void showOutcomeDialog(){
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