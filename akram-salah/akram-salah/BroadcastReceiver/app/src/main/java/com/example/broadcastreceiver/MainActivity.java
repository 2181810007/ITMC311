package com.example.broadcastreceiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textField;
    private ImageView batteryIcon;
    private MediaPlayer lowBatterySound;

    private final BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            // تحديث شريط التقدم
            progressBar.setProgress(level);
            textField.setText("Battery Level: " + level + "%");

            // تحديث الأيقونة حسب مستوى البطارية
            if (level >= 80) {
                batteryIcon.setImageResource(R.drawable.battery_full);
            } else if (level >= 40) {
                batteryIcon.setImageResource(R.drawable.battery_half);
            } else {
                batteryIcon.setImageResource(R.drawable.battery_low);
            }

            // تغيير لون progressBar
            if (level >= 50) {
                progressBar.setProgressTintList(ContextCompat.getColorStateList(context, R.color.green_500));
            } else if (level >= 20) {
                progressBar.setProgressTintList(ContextCompat.getColorStateList(context, R.color.yellow_700));
            } else {
                progressBar.setProgressTintList(ContextCompat.getColorStateList(context, R.color.red_700));
            }

            // تشغيل صوت عند انخفاض البطارية
            if (level <= 20 && lowBatterySound != null && !lowBatterySound.isPlaying()) {
                lowBatterySound.start();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ربط العناصر من الواجهة
        progressBar = findViewById(R.id.progressbar);
        textField = findViewById(R.id.textfield);
        batteryIcon = findViewById(R.id.battery_icon);

        // تحميل ملف الصوت
        lowBatterySound = MediaPlayer.create(this, R.raw.low_battery);

        // تسجيل المستقبل
        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // إلغاء التسجيل وإغلاق الصوت
        if (lowBatterySound != null) {
            lowBatterySound.release();
            lowBatterySound = null;
        }
        unregisterReceiver(mBatInfoReceiver);
    }
}
