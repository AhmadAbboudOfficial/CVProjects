package com.example.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class BookAdapter extends ArrayAdapter<BookItem> {

    private BookItem item;
    private ImageView ItemImage;
    private TextView ItemTitle;
    private TextView ItemAuthor;


    public BookAdapter(@NonNull Context context, @NonNull ArrayList<BookItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        item = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        ItemImage = (ImageView) convertView.findViewById(R.id.image);
        ItemTitle = (TextView) convertView.findViewById(R.id.book_title);
        ItemAuthor = (TextView) convertView.findViewById(R.id.author);

        ItemImage.setImageBitmap(item.getImage());
        ItemTitle.setText(item.getTitle());
        ItemAuthor.setText(item.getAuthor());

        return convertView;
    }
}
