package com.example.test.stressprediction_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.stressprediction_app.Model.Music;
import com.example.test.stressprediction_app.MusicActivity;
import com.example.test.stressprediction_app.MusicDetailsActivity;
import com.example.test.stressprediction_app.R;

import java.io.IOException;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private Context context;
    List<Music> mMusicList;
    MediaPlayer mediaPlayer;
    boolean flag = false;
    static int pos;

    public static class MusicViewHolder extends RecyclerView.ViewHolder{

        TextView txtName,txtDuration;
        ImageView imgPlay;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtMName);
            txtDuration = itemView.findViewById(R.id.txtMLength);
            imgPlay = itemView.findViewById(R.id.imgMPlay);
        }
    }

    public MusicAdapter(Context context, List<Music> mMusicList) {
        this.context = context;
        this.mMusicList = mMusicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.music_item,viewGroup,false);
        MusicViewHolder mvh = new MusicViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MusicViewHolder holder, final int index) {
        //mMusicList.get(index).setEnabled(true);
        final Music m = mMusicList.get(index);
        holder.txtName.setText(m.getName());
        holder.txtDuration.setText(m.getDuration());

        //holder.imgPlay.setEnabled(true);

        holder.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            Intent intent = new Intent(context,MusicDetailsActivity.class);
            intent.putExtra("name",mMusicList.get(index).getName());
            intent.putExtra("file",mMusicList.get(index).getResource());
            context.startActivity(intent);

//                String path = "android.resource://" + context.getPackageName() + "/raw/" + R.raw.morning;
//
//
//                //Uri uri = Uri.parse("android.resource://com.your.package/raw/filename");
//                Intent intent = new Intent();
//                intent.setAction(android.content.Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.parse(path), "audio/*");
//                context.startActivity(intent);



//                if (!flag)
//                    mMusicList.get(index).setEnabled(true);
//                else
//                    mMusicList.get(index).setEnabled(false);

//                for(int i=0;i<mMusicList.size();i++){
//                    if (i != index){
//                        mMusicList.get(i).setEnabled(false);
//                    }
//                    else
//                        mMusicList.get(i).setEnabled(true);
//                }
                //holder.imgPlay.setEnabled(mMusicList.get());


//                if (v instanceof ViewGroup) {
//                    ViewGroup viewGroup = (ViewGroup) v;
//                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
//                        //setClickable(viewGroup.getChildAt(i), clickable);
//                        if (i == index && mMusicList.get(index).isEnabled())
//                            ((ViewGroup) v).getChildAt(i).setClickable(true);
//                        else
//                            ((ViewGroup) v).getChildAt(i).setClickable(false);
//                    }
//                }


                //for (Music m : mMusicList){
                    //if (m)
                //}
                //m.setEnabled(false);

//                mediaPlayer = MediaPlayer.create(context,mMusicList.get(i).getResource());
//                if (mediaPlayer.isPlaying())
//                    mediaPlayer.stop();
//                mediaPlayer.start(); // no need to call prepare(); create() does that for you


//                flag = !flag;
//
//                if (flag){  // is playing
//                    Toast.makeText(context,"Play",Toast.LENGTH_LONG).show();
//                    //if (mediaPlayer != null)
//                        mediaPlayer = new MediaPlayer();
//
//                    AssetFileDescriptor afd = context.getResources().openRawResourceFd(mMusicList.get(index).getResource());
//                    try {
//                        mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
//                        mediaPlayer.prepare();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    mediaPlayer.start();
//
//                    holder.imgPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
//
//                }
//                else{
//                    holder.imgPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
//                    if (mediaPlayer.isPlaying())
//                        mediaPlayer.pause();
//                    Toast.makeText(context,"Pause",Toast.LENGTH_LONG).show();
//                }


               // m.setEnabled(false);

                //holder.imgPlay.setEnabled(m.isEnabled());
                //holder.imgPlay.setEnabled(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }
}
