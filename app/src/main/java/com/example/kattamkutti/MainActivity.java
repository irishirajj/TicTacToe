package com.example.kattamkutti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.cert.X509Certificate;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;

    //To denote the active player:
    // 0-O,
    // 1-X
    int activeplayer=1;
    //To denote the value at any box of the grid :
    // 2-blank
    // 1- X
    // 2- O
    int[] gameState={2,2,2,2,2,2,2,2,2};
    //Winning poistions in the game are limited :
    //If {0,1,2} positions are same ==> Game Won
    //Similarly, we have :
    int [] [] winPositions= {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img=(ImageView) view;
        int tappedImage= Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage]==2 ){
            gameState[tappedImage]=activeplayer;
            img.setTranslationY(-1000f);
            if(activeplayer==0){
                img.setImageResource(R.drawable.o);
                activeplayer=1;
                TextView status= findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play");
            }else {
                img.setImageResource(R.drawable.x);
                activeplayer=0;
                TextView status= findViewById(R.id.status);
                status.setText("O's Turn - Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]]==gameState[winPosition[1]] &&
                    gameState[winPosition[1]]==gameState[winPosition[2]]&&
                    gameState[winPosition[2]]!=2){
                if(gameState[winPosition[0]]==1){
                    TextView status= findViewById(R.id.status);
                    status.setText("X has won the game.");
                }
                else{
                    TextView status= findViewById(R.id.status);
                    status.setText("O has won the game.");
                }
                gameActive=false;
            }
        }
        //Condition for checking draw
        boolean emptysquare=false;
        for(int state:gameState){
            if(state==2) {
                emptysquare=true;
                break;
            }
        }
        if(!(emptysquare) && gameActive){
            gameActive=false;
            TextView status= findViewById(R.id.status);
            status.setText("The Game was draw.");
        }
    }
    public void gameReset(View view){
        gameActive=true;
        activeplayer=1;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        TextView status= findViewById(R.id.status);
        status.setText("X's Turn - Tap to Play");
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}