package library;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Book;

public class BookLibrary {
    private static BookLibrary instance;
    private final Map<String, BookWithQuantity> books;

    private BookLibrary() {
        books = new HashMap<>();
    }

    public static BookLibrary getInstance() {
        if (instance == null) {
            instance = new BookLibrary();
        }
        return instance;
    }

    public void addBook(Book book, int quantity) {
        books.put(book.getISBN(), new BookWithQuantity(book, quantity));
    }

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

    public void returnBook(Book book) {
        books.computeIfPresent(book.getISBN(), (isbn, bookWithQuantity) -> {
            bookWithQuantity.setQuantity(bookWithQuantity.getQuantity() + 1);
            return bookWithQuantity;
        });
        books.computeIfAbsent(book.getISBN(), isbn -> new BookWithQuantity(book, 1));
    }

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

    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        for (BookWithQuantity bookWithQuantity : books.values()) {
            allBooks.add(bookWithQuantity.getBook());
        }
        return allBooks;
    }

    // Inner class BookWithQuantity
    public class BookWithQuantity {
        private final Book book;
        private int quantity;

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
