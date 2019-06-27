package com.example.a12_playaudiotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSubviews();
    }

    private void initSubviews() {
        Button play = findViewById(R.id.play_button);
        Button pause = findViewById(R.id.pause_button);
        Button stop = findViewById(R.id.stop_button);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        initMediaPlayer();
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MainActivity.this, new String[] {
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
//        } else {
//            initMediaPlayer();
//        }
    }

    private void initMediaPlayer() {
        try {
//            File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
//            mediaPlayer.setDataSource(file.getPath());
//            mediaPlayer.prepare();

//            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music);
//            mediaPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

        MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.music);
        player.start();
        Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();

//        switch (view.getId()) {
//            case R.id.play_button:
//                if (!mediaPlayer.isPlaying()) {
//                    mediaPlayer.start();
//                }
//                break;
//
//            case R.id.pause_button:
//                if (!mediaPlayer.isPlaying()) {
//                    mediaPlayer.pause();
//                }
//                break;
//
//            case R.id.stop_button:
//                if (!mediaPlayer.isPlaying()) {
//                    mediaPlayer.reset();
//                    initMediaPlayer();
//                }
//                break;
//                default:
//                    break;
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this,"deny perssion will can`t use app", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
