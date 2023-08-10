package com.example.musicforkids;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.animation.AnimatableView;

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.ViewHolder>  {
    private String[] nameSong;
    private int[] imageSong;
    private Listener listener;

    interface Listener {
        void onClick(int position);
    }
    public void setListener(Listener listener){
        this.listener = listener;
    }



    public AdapterMusic(String[] nameSong, int[] imageSong){
        this.imageSong=imageSong;
        this.nameSong=nameSong;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_item,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imageMusic);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageSong[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(nameSong[position]);
        TextView textView = (TextView) cardView.findViewById(R.id.nameMusic);
        textView.setText(nameSong[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return imageSong.length;
    }


}
