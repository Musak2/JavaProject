package models;

/**
 * Represents a book extending the abstract Literature class.
 * This class inherits common literature attributes and behavior from the Literature class.
 */
public class Book extends Literature {

    // Constructor for the Book class.
    public Book(String title, String author, String isbn, double value, Integer year) {
        super(title, author, isbn, value, year);
    }

    /**
     * Static Builder class for Book.
     * Provides a fluent interface for creating Book objects.
     */
    public static class Builder {
        private String title;
        private String author;
        private String isbn;
        private double value;
        private Integer year;

        // Builder setters

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setISBN(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder setValue(double value) {
            this.value = value;
            return this;
        }

        public Builder setYear(Integer year) {
            this.year = year;
            return this;
        }

        public Book build() {
            return new Book(title, author, isbn, value, year);
        }
    }
}
