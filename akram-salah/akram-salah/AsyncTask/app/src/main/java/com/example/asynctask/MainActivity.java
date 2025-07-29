package com.example.asynctask;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/** @noinspection ALL*/
public class MainActivity extends AppCompatActivity {
    Button btnprocess;
    ProgressBar progressBar;
    TextView txtpercentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnprocess = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressbar);
        txtpercentage= findViewById(R.id.txtpercentage);

        btnprocess.setOnClickListener(v -> {
            btnprocess.setEnabled(false);
            new DoingAsyncTask().execute();
        });
    }
    /** @noinspection deprecation*/
    @SuppressLint("StaticFieldLeak")
    private class DoingAsyncTask extends AsyncTask<Void, Integer, Void> {

        int progress_status;
        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Invoke onPreExecute() Process", Toast.LENGTH_SHORT).show();
            progress_status = 0;
            txtpercentage.setText("Processing 0%");
        }
        @Override
        protected Void doInBackground(Void... params) {
            while(progress_status<100){
                progress_status += 5;
                publishProgress(progress_status);
                SystemClock.sleep(200);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            txtpercentage.setText("Processing " +values[0]+"%");
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(MainActivity.this,"Invoke onPostExecute() Process", Toast.LENGTH_SHORT).show();
            txtpercentage.setText("Processing complete");
            btnprocess.setEnabled(true);
        }
    }
}