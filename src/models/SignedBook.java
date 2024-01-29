package models;

public class SignedBook extends Book {
    public SignedBook(String title, String author, String isbn, double baseValue, Integer year) {
        super(title, author, isbn, baseValue * 2, year); // Double the value for a signed book
    }
}
