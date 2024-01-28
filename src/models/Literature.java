package models;

import interfaces.Readable;

public abstract class Literature implements Readable {
    private final String title;
    private final String author;
    private final String isbn;
    private final double value;
    private final Integer year;

    protected Literature(String title, String author, String isbn, double value, Integer year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.value = value;
        this.year = year;
    }

    // Common getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return isbn; }
    public double getValue() { return value; }
    public Integer getYear() { return year; }

    @Override
    public void read() {
        System.out.println("\nReading " + title + " by " + author);
    }
}
