
package com.example.musicforkids;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterWhySay extends RecyclerView.Adapter<AdapterWhySay.ViewHolder> {
    private String[] nameAnimal;
    private int[]   imageAnimal;
    private String[] soundAnimal;
    private Listner listner;


    interface Listner{
        void onClick(int postion);
    }
    public void setListner(Listner listner){
        this.listner = listner;
    }
    public AdapterWhySay(String[] name, int[] imageAnimal){
        this.nameAnimal=name;
        this.imageAnimal=imageAnimal;
        //this.soundAnimal=soundAnimal;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView v){
            super(v);
            cardView =v;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
          CardView cv =(CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animals_item,parent,false);
          return new ViewHolder(cv);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position){
     CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.animal_list_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(),imageAnimal[position]);
        imageView.setImageDrawable(drawable);
        ImageButton imageButton = (ImageButton) cardView.findViewById(R.id.animal_list_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "it's work");
                if(listner != null){
                    listner.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount(){
        return imageAnimal.length;
    }
}
