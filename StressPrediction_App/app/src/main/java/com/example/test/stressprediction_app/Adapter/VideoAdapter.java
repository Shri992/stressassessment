package com.example.test.stressprediction_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.stressprediction_app.Model.Videos;
import com.example.test.stressprediction_app.R;
import com.example.test.stressprediction_app.VideoDetailActivity;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    Context context;
    List<Videos> mVideoList;

    public static class VideoViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgPlay;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtVName);
            imgPlay = itemView.findViewById(R.id.imgVPlay);
        }
    }

    public VideoAdapter(Context context, List<Videos> mVideoList) {
        this.context = context;
        this.mVideoList = mVideoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);
        VideoViewHolder vvh = new VideoViewHolder(v);
        return vvh;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int i) {
        final Videos vs = mVideoList.get(i);
        holder.txtName.setText(vs.getVideo());

        holder.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoDetailActivity.class);
                intent.putExtra("link",vs.getVideoLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }
}
