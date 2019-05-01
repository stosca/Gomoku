package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class SetupGame extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);
    }

    public void setNames() {
        Intent intent = new Intent(this, MainActivity.class);
        EditText firstName = findViewById(R.id.nameOne);
        EditText secondName = findViewById(R.id.nameTwo);
        String first_name = firstName.getText().toString();
        String second_name = secondName.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, first_name);
        intent.putExtra(EXTRA_MESSAGE, second_name);
        startActivity(intent);
    }


}
