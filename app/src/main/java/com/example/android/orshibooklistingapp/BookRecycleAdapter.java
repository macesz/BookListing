package com.example.android.orshibooklistingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.orshibooklistingapp.R.id.books;
import static com.example.android.orshibooklistingapp.R.id.image;

/**
 * Created by orsi on 07/06/2017.
 */

public class BookRecycleAdapter extends RecyclerView.Adapter<BookRecycleAdapter.ViewHolder> {

    //I add a constructor to the custom adapter so that it has a handle to the data that the RecyclerView displays.
    private ArrayList<Book> mDataset;
    public OnItemClickListener mOnItemClickListener;


    public BookRecycleAdapter(ArrayList<Book> books) {
        mDataset = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);
        ViewHolder holder = new ViewHolder(itemLayout);


        return holder;
    }

    @Override
    public void onBindViewHolder(final BookRecycleAdapter.ViewHolder holder, final int position) {
        final Book book = mDataset.get(position);
        holder.setItem(mDataset.get(position));

//        holder.title.setText((CharSequence) mDataset.get(position));
//        holder.title.setText((CharSequence) mDataset.get(position));


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(book);
            }
        };
        holder.image.setOnClickListener(listener);
        holder.title.setOnClickListener(listener);
        holder.authors.setOnClickListener(listener);
    }
    // Return the size of your dataset, the number of items present in the book. (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView authors;
        protected TextView title;

        // Inside the constructor of our custom ViewHolder, initialize the views that belong to the items of our RecyclerView.
        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            authors = (TextView) itemView.findViewById(R.id.author_text_view);
            title = (TextView) itemView.findViewById((R.id.title_text_view));

        }

            // ezt elvileg simán az onBindViewHolder-ben kene definialni?

        public void setItem(Book item) {
            authors.setText(item.getAuthor());
            title.setText(item.getTitle());
            Picasso.with(image.getContext()).load(item.getImage()).into(image);
        }

    }

    // Return the position of your dataset
    public Book getItem(int position) {
        return mDataset.get(position);
    }

    // Clear dataset
    public void clear() {
        mDataset.clear();
    }

    // Add books to the Book array
    public void addBooks(List<Book> books) {
        mDataset.addAll(books);
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}

