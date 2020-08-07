package com.example.books;

import android.graphics.Bitmap;

public class BookItem {

    private String title;
    private String author;
    private String description;
    private String PageCount;
    private Bitmap Image;
    private String PreviewLink;

    public BookItem(String title, String author, String description, String pageCount, Bitmap image) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.PageCount = pageCount;
        this.Image = image;
    }

    public void setPreviewLink(String previewLink) {
        PreviewLink = previewLink;
    }

    public String getPreviewLink() {
        return PreviewLink;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getPageCount() {
        return PageCount;
    }

    public Bitmap getImage() {
        return Image;
    }
}
