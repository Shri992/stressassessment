package com.example.test.stressprediction_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.test.stressprediction_app.Adapter.MusicAdapter;
import com.example.test.stressprediction_app.Adapter.VideoAdapter;
import com.example.test.stressprediction_app.Model.Videos;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {

    List<Videos> mVideoList;
    VideoAdapter videoAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();
        boolean IsFromHome = intent.getBooleanExtra("IsFromHome",true);
        String Type = intent.getStringExtra("Type");
        String Level = intent.getStringExtra("Level");

        mVideoList = new ArrayList<>();

        if (IsFromHome) {   // if this page is called from home page
            mVideoList.add(new Videos("Video 1", "YhpU8VME8Gw"));
            mVideoList.add(new Videos("Video 2", "eAK14VoY7C0"));
            mVideoList.add(new Videos("Video 3", "m3-O7gPsQK0"));
            mVideoList.add(new Videos("Video 4", "jDU2OI1gjiA"));
            mVideoList.add(new Videos("Video 5", "-GXfLY4-d8w"));
            mVideoList.add(new Videos("Video 6", "WWloIAQpMcQ"));
            mVideoList.add(new Videos("Video 7", "td0PSS3lCQ4"));
            mVideoList.add(new Videos("Video 8", "gy1iH_Gxn0Q"));
            mVideoList.add(new Videos("Video 9", "-GHCZ1OSFuM"));
            mVideoList.add(new Videos("Video 10", "OJfH0j6iF8I"));
        }
        else{               // if page is called from result page
            if (Type.equals("Stress") && Level.equals("Low")){
                mVideoList.add(new Videos("Video 1", "hJbRpHZr_d0"));
                mVideoList.add(new Videos("Video 2", "8R2FfRl6V8U"));
                mVideoList.add(new Videos("Video 3", "wruCWicGBA4"));
                mVideoList.add(new Videos("Video 4", "YhpU8VME8Gw"));
                mVideoList.add(new Videos("Video 5", "tXt--rTkt_Y"));
            }
            else if (Type.equals("Stress") && Level.equals("High")){
                mVideoList.add(new Videos("Video 1", "YoSuVws4OTQ"));
                mVideoList.add(new Videos("Video 2", "qq0DBeFdDlM"));
                mVideoList.add(new Videos("Video 3", "7EX1Xnvvk5c"));
                mVideoList.add(new Videos("Video 4", "WWloIAQpMcQ"));
            }
            else if (Type.equals("Anxiety") && Level.equals("Low")){
                mVideoList.add(new Videos("Video 1", "hJbRpHZr_d0"));
                mVideoList.add(new Videos("Video 2", "8R2FfRl6V8U"));
                mVideoList.add(new Videos("Video 3", "wruCWicGBA4"));
                mVideoList.add(new Videos("Video 4", "YhpU8VME8Gw"));
                mVideoList.add(new Videos("Video 5", "tXt--rTkt_Y"));
            }
            else if (Type.equals("Anxiety") && Level.equals("High")){
                mVideoList.add(new Videos("Video 1", "YoSuVws4OTQ"));
                mVideoList.add(new Videos("Video 2", "qq0DBeFdDlM"));
                mVideoList.add(new Videos("Video 3", "7EX1Xnvvk5c"));
                mVideoList.add(new Videos("Video 4", "WWloIAQpMcQ"));
            }
            else if (Type.equals("Depression") && Level.equals("Low")){
                mVideoList.add(new Videos("Video 1", "_7-D4lmdb6g"));
                mVideoList.add(new Videos("Video 2", "VgdAcENXy84"));
                mVideoList.add(new Videos("Video 3", "8vOjYU-W0"));
            }
            else if (Type.equals("Depression") && Level.equals("High")){
                mVideoList.add(new Videos("Video 1", "9Hfnj8JIqBg"));
                mVideoList.add(new Videos("Video 2", "xRxT9cOKiM8"));
                mVideoList.add(new Videos("Video 3", "7DoQMnmo0v8"));
                mVideoList.add(new Videos("Video 4", "OJfH0j6iF8I"));
            }
        }


        recyclerView = findViewById(R.id.rvVideo);
        videoAdapter = new VideoAdapter(this,mVideoList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(videoAdapter);
    }
}
