package models;

import interfaces.Readable;

/**
 * Abstract class representing a generic piece of literature.
 * It implements the Readable interface, indicating that it can be read.
 * This class serves as a base for more specific types of literature like books.
 */
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

    // Getter methods for each attribute.
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return isbn; }
    public double getValue() { return value; }
    public Integer getYear() { return year; }

    /**
     * Implements the read action from the Readable interface.
     * Currently, it simulates reading by printing a message to the console.
     */
    @Override
    public void read() {
        System.out.println("\nReading " + title + " by " + author);
    }
}
