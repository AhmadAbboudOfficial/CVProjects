package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    ImageView image_icon;
    TextView title,page_count,description;
    Button Review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent j = getIntent();

        String title = j.getStringExtra("title");
        String description = j.getStringExtra("description");
        String page_count = j.getStringExtra("page count");
        Bitmap image = j.getParcelableExtra("image");
        //image icon
        image_icon = findViewById(R.id.image2);
        image_icon.setImageBitmap(image);
        //title
        this.title = findViewById(R.id.title2);
        this.title.setText(title);
        //page count
        this.page_count = findViewById(R.id.page_count);
        this.page_count.setText(page_count+" Page");
        //description
        this.description = findViewById(R.id.description);
        this.description.setText(description);

        //previewLink
        String previewLink = j.getStringExtra("previewLink");
        final String url = previewLink;
        //button
        this.Review = findViewById(R.id.btn_book_link);
        this.Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

    }
}
