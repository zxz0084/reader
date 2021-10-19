package com.swufestu.reader.domain;

public class Book {
    private int id;
    private String bookPath;

    public int getId() {
        return id;
    }

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    public void setId(int id) {
        this.id = id;
    }
}
