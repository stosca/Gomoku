package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SetupGame extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[5][5];

    private boolean playerstatus_1 = true;

    private int rounds = 0;

    private TextView player_1;

    private TextView player_2;

    private int playerscores_1;

    private int playerscores_2;


    public static String EXTRA_MESSAGE = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra(MainActivity.FIRST_NAME);
        String secondName = intent.getStringExtra(MainActivity.SECOND_NAME);

        player_1 = findViewById(R.id.textview_player1);
        player_1.setText(firstName);
        player_2 = findViewById(R.id.textview_player2);
        player_2.setText(secondName);



        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonId = "button_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button newgameButton = findViewById(R.id.button_new_game);
        newgameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (playerstatus_1) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        rounds++;
        if (rounds == 25) {
            tie();
        } else if (winning()) {
            if (playerstatus_1) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else {
            playerstatus_1 = !playerstatus_1;
        }
    }

    private void tie() {
        Toast.makeText(this,"Tie!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void player1Wins() {
        playerscores_1++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }
    private void player2Wins() {
        playerscores_2++;
        Toast.makeText(this,"Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
            }
        }
        rounds = 0;
        playerstatus_1 = true;
    }

    private void resetGame() {
        playerscores_1 = 0;
        playerscores_2 = 0;
        updatePoints();
        resetBoard();
    }
    private boolean winning() {
        String[][] grids = new String[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grids[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 5; i++) {
            if (!grids[i][0].equals("")
                    && grids[i][0].equals(grids[i][1])
                    && grids[i][0].equals(grids[i][2])
                    && grids[i][0].equals(grids[i][3])
                    && grids[i][0].equals(grids[i][4])) {
                return true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (!grids[0][i].equals("")
                    && grids[0][i].equals(grids[1][i])
                    && grids[0][i].equals(grids[2][i])
                    && grids[0][i].equals(grids[3][i])
                    && grids[0][i].equals(grids[4][i])) {
                return true;
            }
        }

        if (!grids[0][0].equals("") &&
                grids[0][0].equals(grids[1][1])
                && grids[0][0].equals(grids[2][2])
                && grids[0][0].equals(grids[3][3])
                && grids[0][0].equals(grids[4][4])) {
            return true;
        }

        if (!grids[0][4].equals("") &&
                grids[0][4].equals(grids[1][3])
                && grids[0][4].equals(grids[2][2])
                && grids[0][4].equals(grids[3][1])
                && grids[0][4].equals(grids[4][0])) {
            return true;
        }
        return false;
    }
    private void updatePoints() {
        player_1.setText("Player 1: " + playerscores_1);
        player_2.setText("Player 2: " + playerscores_2);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("rounds", rounds);
        outState.putInt("playerscores_1", playerscores_1);
        outState.putInt("playerscores_2", playerscores_2);
        outState.putBoolean("playerstatus_1", playerstatus_1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        rounds = savedInstanceState.getInt("rounds");
        playerscores_1 = savedInstanceState.getInt("playerscores_1");
        playerscores_2 = savedInstanceState.getInt("playerscores_2");
        playerstatus_1 = savedInstanceState.getBoolean("playerstatus_1");
    }
}
