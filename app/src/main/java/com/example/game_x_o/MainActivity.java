package com.example.game_x_o;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button      playButton, resetButton;
    TextView    redScore, yellowScore, winerText;
    ImageView   image1, image2, image3, image4, image5, image6, image7, image8, image9;
    GridLayout  gameBord;


    //  0:red,  1:yellow,   2:empty
    int activePlayer = 0;

    // Punenje niza red/yellow
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // Brojach -- Tag(activiti_main)
    int tappedCounter;

    //  Mozni kombinacii na pobeda vo igrata
    int[][] winningPositions = { {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                 {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                 {0, 4, 8}, {2, 4, 6}  };

    // Players - Score
    int yellowPlayer = 0;
    int redPlayer = 0;

    // Start -- Stop  GAME
    boolean gameActive = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            initComponents();
            setComponents();
    }


    private void initComponents() {
        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);
        image5 = findViewById(R.id.imageView5);
        image6 = findViewById(R.id.imageView6);
        image7 = findViewById(R.id.imageView7);
        image8 = findViewById(R.id.imageView8);
        image9 = findViewById(R.id.imageView9);

        redScore    = findViewById(R.id.textViewYellowScore);
        yellowScore = findViewById(R.id.textViewRedScore);
        winerText   = findViewById(R.id.textViewWiner);

        playButton    = findViewById(R.id.buttonPlay);
        resetButton   = findViewById(R.id.buttonReset);

        gameBord  = findViewById(R.id.gridLayout);
    }


    private void setComponents() {

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropIn(image1);
                win();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropIn(image2);
                win();
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropIn(image3);
                win();
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropIn(image4);
                win();
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropIn(image5);
                win();
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropIn(image6);
                win();
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropIn(image7);
                win();
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropIn(image8);
                win();
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropIn(image9);
                win();
            }
        });


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playagain();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playagain();

                yellowScore.setText("0");
                yellowPlayer = 0;

                redScore.setText("0");
                redPlayer = 0;
            }
        });

    }


    // Setiranje na Polinja
    public void dropIn(ImageView image0) {

        //  Tag vo niza gameState []
        tappedCounter = Integer.parseInt(image0.getTag().toString());

        //proverka dali e prazno mestoto
        if (gameState[tappedCounter] == 2 && gameActive == true) {

            // Animacija -- Pagaat krukcinja
            image0.setTranslationY(-1500);
            if (activePlayer == 0) {
                image0.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                image0.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            image0.animate().translationYBy(1500).setDuration(300);
        }
    }


    // Proverka za WIN....
    public void win() {

        gameState[tappedCounter] = activePlayer;

        // Kombinacii na pobednik
        if(gameActive == true){

            //  Proverka na kombinacija
            for (int[] positions : winningPositions) {

                if (gameState[positions[0]] == gameState[positions[1]] &&
                    gameState[positions[1]] == gameState[positions[2]] &&
                    gameState[positions[0]] != 2) {

                    // Score -- TextView (REZULTAT - Yellow:1)
                    if (activePlayer == 0){

                        // Score (REZULTAT - Yellow)
                        yellowPlayer++;
                        yellowScore.setText(String.valueOf(yellowPlayer));

                        // Text WIN
                        winerText.setText("RED WIN...");

                        endGame();
                    }
                    else{
                        // Score (REZULTAT - Red:0)
                        redPlayer++;
                        redScore.setText(String.valueOf(redPlayer));

                        // Ispisi Text WIN
                        winerText.setText("YELLOW WIN...");

                        endGame();
                    }
                }
            }
        }

        // Ako E Nereseno
        if (gameState[0] != 2  &&  gameState[1] != 2  &&  gameState[2] != 2  &&
            gameState[3] != 2  &&  gameState[4] != 2  &&  gameState[5] != 2  &&
            gameState[6] != 2  &&  gameState[7] != 2  &&  gameState[8] != 2  &&
            gameActive == true) {

            endGame();
            winerText.setText("PLAY AGAIN...");
        }
    }


    // Kraj na igrata
    public void endGame(){
        // Pojavuvanje Kopcinja (Play - Reset)
        resetButton.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);

        // Kraj na igrata
        gameActive = false;
    }


    // Reset na komponenti
    public void playagain(){

        // Reset na parametri
        activePlayer = 0;
        gameState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};

        // Izbrisi Text WIN
        winerText.setText("");

        // Krienje Kopcinja (Play - Reset)
        resetButton.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.INVISIBLE);

        // Start nova Igra
        gameActive = true;

        //  Resetiranje na slikicki vo polinjata na igrata
        int count;
        count = gameBord.getChildCount();
            for (int i = 0; i <count ; i++) {
                ImageView child = (ImageView) gameBord.getChildAt(i);
                child.setImageDrawable(null);
            }
    }

}
