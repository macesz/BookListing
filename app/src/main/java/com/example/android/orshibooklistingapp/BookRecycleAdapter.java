package com.example.android.orshibooklistingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orsi on 07/06/2017.
 */

public class BookRecycleAdapter extends RecyclerView.Adapter<BookRecycleAdapter.ViewHolder> {

    CustomItemClickListener listener;

    //I add a constructor to the custom adapter so that it has a handle to the data that the RecyclerView displays.
    private ArrayList<Book> mDataset;

    public BookRecycleAdapter(ArrayList<Book> books, CustomItemClickListener listener) {
        mDataset = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        return new ViewHolder(itemLayout);
    }

    // Create the custom ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView authors;
        TextView title;

        // Inside the constructor of our custom ViewHolder, initialize the views that belong to the items of our RecyclerView.
        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            authors = (TextView) itemView.findViewById(R.id.author_text_view);
            title = (TextView) itemView.findViewById((R.id.title_text_view));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri bookUri = Uri.parse(mDataset.get(getAdapterPosition()).getUrl());
                    // Create a new intent to view the earthquake URI
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, bookUri);
                    // Send the intent to launch a new activity
                    v.getContext().startActivity(webIntent);
                }
            });
        }

        // ezeket elvileg simán az onBindViewHolder-ben kene definialni... mi;rt is csinaltuk igy?

        public void setItem(Book item) {
            Picasso.with(image.getContext()).load(item.getImage()).into(image);
            authors.setText(item.getAuthor());
            title.setText(item.getTitle());
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(mDataset.get(position));
    }

    // Return the size of your dataset, the number of items present in the book. (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
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
}
