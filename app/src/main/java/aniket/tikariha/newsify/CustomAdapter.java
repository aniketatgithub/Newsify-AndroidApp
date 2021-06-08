package aniket.tikariha.newsify;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.myViewHolder>{

    @NonNull

    ArrayList<ArrayList<News>> numberslist = new ArrayList<ArrayList<News>>();
    ArrayList<News> numbers = new ArrayList<News>();





    NewsItemClicked listner;

    public CustomAdapter(NewsItemClicked listner) {
        this.listner = listner;
    }


    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customlayout,parent,false);
        myViewHolder newview = new myViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onItemClicked(numbers.get(newview.getAdapterPosition()));
            }
        });
        return newview;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Object currentitem = numbers.get(position);
        holder.textView.setText(((News) currentitem).title);
        holder.author.setText(((News) currentitem).author);
        holder.description.setText(((News) currentitem).description);
        Glide.with(holder.itemView.getContext()).load(((News) currentitem).imageurl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return numbers.size();

    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        TextView author;
        TextView description;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.image);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);

        }



    }
    void updateNews(ArrayList<News> updatedNews){
        Log.d(TAG, "true" + updatedNews.toString());
        numberslist.clear();

        numberslist.add(updatedNews);
        numbers = numberslist.get(0);
        notifyDataSetChanged();
    }

    interface NewsItemClicked{
        void onItemClicked(News numbers);
    }

}