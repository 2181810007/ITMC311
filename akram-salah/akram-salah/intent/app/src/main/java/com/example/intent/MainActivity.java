package com.example.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // العثور على الزر الأول
        Button explicitIntentButton = findViewById(R.id.explicit_intent_button);
        explicitIntentButton.setOnClickListener(v -> {
            // الانتقال إلى Activity الثانية باستخدام Intent صريح
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        // العثور على الزر الثاني
        Button implicitIntentButton = findViewById(R.id.implicit_intent_button);
        implicitIntentButton.setOnClickListener(v -> {
            // استخدام Intent ضمني لفتح متصفح
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.google.com"));
            startActivity(intent);
        });
    }
}
