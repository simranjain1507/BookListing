package com.example.android.booklisting;

/**
 * Created by simranjain1507 on 19/07/17.
 */

public class BookList {
    String id;
    String preview;
    String title;
    String author;

    public BookList(String id, String author, String title, String preview) {
        this.id = id;
        this.preview = preview;
        this.title = title;
        this.author = author;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
