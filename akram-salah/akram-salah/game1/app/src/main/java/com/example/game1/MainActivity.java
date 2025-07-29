package com.example.game1;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buttonLeft, buttonRight;
    TextView pointsText;
    int score = 0;
    Random rand = new Random();

    void generateNumbers() {
        int left = rand.nextInt(100);
        int right = rand.nextInt(100);

        while (left == right) {
            right = rand.nextInt(100);
        }

        buttonLeft.setText(String.valueOf(left));
        buttonRight.setText(String.valueOf(right));
    }
    void updatePointsText() {
        String pointsStr = getString(R.string.points_text, score);
        pointsText.setText(pointsStr);
    }

    void checkAnswer(boolean correct) {
        if (correct) {
            score++;
            Toast.makeText(this, getString(R.string.correct_message), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.wrong_message), Toast.LENGTH_SHORT).show();
            if (score > 0) score--;
        }
        updatePointsText();
        generateNumbers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);
        pointsText = findViewById(R.id.pointsText);

        generateNumbers();
        updatePointsText();
        buttonLeft.setOnClickListener(v -> {
            int left = Integer.parseInt(buttonLeft.getText().toString());
            int right = Integer.parseInt(buttonRight.getText().toString());
            checkAnswer(left > right);
        });

        buttonRight.setOnClickListener(v -> {
            int left = Integer.parseInt(buttonLeft.getText().toString());
            int right = Integer.parseInt(buttonRight.getText().toString());
            checkAnswer(right > left);
        });
    }
}
