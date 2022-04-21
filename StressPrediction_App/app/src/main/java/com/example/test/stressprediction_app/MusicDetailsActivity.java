package com.example.test.stressprediction_app;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MusicDetailsActivity extends AppCompatActivity {

    ImageView imgPlay;
    boolean flag = false;
    MediaPlayer mediaPlayer;
    int res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

        imgPlay = findViewById(R.id.imgPlay);

        Intent intent = getIntent();
        res = intent.getIntExtra("file",0);

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;

                if (flag){  // is playing
                    //Toast.makeText(MusicDetailsActivity.this,"Play",Toast.LENGTH_LONG).show();
                    //if (mediaPlayer != null)
                        mediaPlayer = new MediaPlayer();

                    AssetFileDescriptor afd = getResources().openRawResourceFd(res);
                    try {
                        mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();

                    imgPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);

                }
                else{
                    imgPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    if (mediaPlayer.isPlaying())
                        mediaPlayer.pause();
                    //Toast.makeText(MusicDetailsActivity.this,"Pause",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }
}
