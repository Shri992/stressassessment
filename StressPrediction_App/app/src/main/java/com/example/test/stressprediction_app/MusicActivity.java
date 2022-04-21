package com.example.test.stressprediction_app;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.test.stressprediction_app.Adapter.MusicAdapter;
import com.example.test.stressprediction_app.Model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity {

    //MediaPlayer mediaPlayer;
    List<Music> mMusicList;
    MusicAdapter musicAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mMusicList = new ArrayList<>();
        mMusicList.add(new Music("Acoustic guitars ambient uplifting background music","02:14",R.raw.acousticguitars));
        mMusicList.add(new Music("A piece of motivation","01:22",R.raw.motivation));
        mMusicList.add(new Music("Chilled acoustic","02:20",R.raw.chilled));
        mMusicList.add(new Music("Cinematic fairy tale","02:07",R.raw.cinematic));
        mMusicList.add(new Music("Happy go lucky","03:17",R.raw.happy_go));
        mMusicList.add(new Music("Morning Light Ambient","02:59",R.raw.happy_go));
        mMusicList.add(new Music("Om Namah Shivaya","05:05",R.raw.om));
        mMusicList.add(new Music("Rain","00:33",R.raw.rain));
        mMusicList.add(new Music("Relax in forest","03:15",R.raw.relax_in));
        mMusicList.add(new Music("Watching the stars","01:27",R.raw.watching));
       //mediaPlayer = MediaPlayer.create(MusicActivity.this, R.raw.file1);
        //mediaPlayer.start(); // no need to call prepare(); create() does that for you

        recyclerView = findViewById(R.id.rvMusic);
        musicAdapter = new MusicAdapter(this,mMusicList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(musicAdapter);
    }

    @Override
    public void onBackPressed() {
        //mediaPlayer.release();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        //mediaPlayer.release();
        super.onDestroy();
    }
}
