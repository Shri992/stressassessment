package com.example.test.stressprediction_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TipsActivity extends AppCompatActivity {

    ImageView imgMusic,imgVideo,imgMeditation,imgSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        imgMusic = findViewById(R.id.imgMusic);
        imgVideo = findViewById(R.id.imgVideo);
        imgMeditation = findViewById(R.id.imgMeditation);
        imgSearch = findViewById(R.id.imgSearch);

        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TipsActivity.this,MusicActivity.class));
            }
        });

        imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TipsActivity.this,VideoActivity.class);
                intent.putExtra("IsFromHome",true);
                startActivity(intent);
            }
        });

        imgMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TipsActivity.this,MeditationActivity.class));
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
                //startActivity(new Intent(TipsActivity.this,V));
            }
        });
    }
}
