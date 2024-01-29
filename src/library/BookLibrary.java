package library;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Book;

/**
 * BookLibrary manages a collection of books, each with an associated quantity.
 * It supports adding books, borrowing books, and returning books.
 * This class follows the Singleton design pattern to ensure only one library instance.
 */
public class BookLibrary {
    private static BookLibrary instance;
    private final Map<String, BookWithQuantity> books;

    /**
     * Private constructor for Singleton pattern.
     * Initializes the map to store books.
     */
    private BookLibrary() {
        books = new HashMap<>();
    }

    public static BookLibrary getInstance() {
        if (instance == null) {
            instance = new BookLibrary();
        }
        return instance;
    }

    // Adds a book to the library collection or updates its quantity if it already exists.
    public void addBook(Book book, int quantity) {
        books.put(book.getISBN(), new BookWithQuantity(book, quantity));
    }

     /**
     * Borrows a book from the library by reducing its quantity.
     * Removes the book from the collection if its quantity reaches zero.
     */
    public Book borrowBook(String isbn) {
        BookWithQuantity bookWithQuantity = books.get(isbn);
        if (bookWithQuantity != null && bookWithQuantity.getQuantity() > 0) {
            bookWithQuantity.setQuantity(bookWithQuantity.getQuantity() - 1);
            if (bookWithQuantity.getQuantity() == 0) {
                books.remove(isbn);
            }
            return bookWithQuantity.getBook();
        }
        return null;
    }

    /**
     * Returns a book to the library, increasing its quantity.
     * If the book is not in the library, it is added with a quantity of 1.
     */
    public void returnBook(Book book) {
        books.computeIfPresent(book.getISBN(), (isbn, bookWithQuantity) -> {
            bookWithQuantity.setQuantity(bookWithQuantity.getQuantity() + 1);
            return bookWithQuantity;
        });
        books.computeIfAbsent(book.getISBN(), isbn -> new BookWithQuantity(book, 1));
    }

    // Lists all books currently available in the library with their quantities.
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books are currently available.");
        } else {
            System.out.println("Available books:");
            int index = 1;
            for (BookWithQuantity bookWithQuantity : books.values()) {
                Book book = bookWithQuantity.getBook();
                System.out.println(index + ". Title: " + book.getTitle() + " by " + book.getAuthor() + ", ISBN: "
                        + book.getISBN() + ", Value: " + book.getValue() + ", Quantity: " + bookWithQuantity.getQuantity());
                index++;
            }
        }
    }

    public List<BookWithQuantity> getAvailableBooksWithQuantities() {
        return new ArrayList<>(books.values());
    }

    // Searches for books in the library based on a query that matches the title or author.
    public List<Book> searchBooks(String query) {
        List<Book> foundBooks = new ArrayList<>();
        for (BookWithQuantity bookWithQuantity : books.values()) {
            Book book = bookWithQuantity.getBook();
            if (book.getTitle().toLowerCase().contains(query.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    // Gets a list of all books in the library.
    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        for (BookWithQuantity bookWithQuantity : books.values()) {
            allBooks.add(bookWithQuantity.getBook());
        }
        return allBooks;
    }

    // Inner class representing a book along with its quantity in the library.
    public class BookWithQuantity {
        private final Book book;
        private int quantity;

        // Constructor for BookWithQuantity.
        public BookWithQuantity(Book book, int quantity) {
            this.book = book;
            this.quantity = quantity;
        }

        // Getters and setters
        public Book getBook() {
            return book;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
