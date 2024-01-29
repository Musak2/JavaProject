package library;

import java.util.List;
import java.util.Scanner;

import models.Book;

public class Librarian {
    private final BookLibrary library;
    private final Scanner scanner;
    final String ANSI_BOLD = "\033[1m";
    final String ANSI_RESET = "\033[0m";
    final String ANSI_RED = "\033[0;31m";
    final String ANSI_GREEN = "\033[0;32m";

    // Constructor for Librarian. Initializes the BookLibrary singleton and the
    // Scanner object.
    public Librarian() {
        this.library = BookLibrary.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            // Display the main menu
            System.out.println(ANSI_BOLD
                    + "\n Welcome to the Library! Choose an option: \n1. Borrow a book \n2. Return a book \n3. List available books \n4. Exit \n"
                    + ANSI_RESET);
            try {
                // Get user choice and process it
                int choice = Integer.parseInt(scanner.nextLine());
                System.out.println();
                switch (choice) {
                    case 1:
                        borrowBookProcess();
                        break;
                    case 2:
                        returnBookProcess();
                        break;
                    case 3:
                        library.listBooks();
                        break;
                    case 4:
                        System.out.println(ANSI_GREEN + "Thank you for visiting the library.\n" + ANSI_RESET);
                        return;
                    default:
                        System.out.println(ANSI_RED + "Invalid option, please try again.\n" + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid input. Please enter a number.\n" + ANSI_RESET);
            }
        }
    }

    /**
     * Handles the process of borrowing a book from the library.
     * Users can search for a book by title or author and choose from the search
     * results to borrow.
     */
    private void borrowBookProcess() {
        System.out.println("Enter the title or author of the book you want to borrow:");
        String query = scanner.nextLine();

        List<Book> foundBooks = library.searchBooks(query);
        if (foundBooks.isEmpty()) {
            System.out.println(ANSI_RED + "No books found." + ANSI_RESET);
            return;
        }

        for (int i = 0; i < foundBooks.size(); i++) {
            Book book = foundBooks.get(i);
            System.out.println(
                    (i + 1) + ". " + book.getTitle() + " by " + book.getAuthor() + " - ISBN: " + book.getISBN());
        }

        System.out.println("Enter the number of the book you want to borrow:");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
            return;
        }

        if (choice < 1 || choice > foundBooks.size()) {
            System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
            return;
        }

        Book bookToBorrow = foundBooks.get(choice - 1);
        Book borrowedBook = library.borrowBook(bookToBorrow.getISBN());
        if (borrowedBook != null) {
            System.out.println("You borrowed: " + bookToBorrow.getTitle());
            borrowedBook.read(); // Simulating reading the book
        } else {
            System.out.println(ANSI_RED + "This book is currently not available." + ANSI_RESET);
        }
    }

    /**
     * Handles the process of returning a book to the library.
     * Users can either return a book from the list of available books or enter book
     * details manually.
     */
    private void returnBookProcess() {
        library.listBooks();

        System.out.println("Do you want to return one of the above books? (y/n)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("y")) {
            returnBookFromList();
        } else if (response.equals("n")) {
            returnBookManually();
        } else {
            System.out.println(ANSI_RED + "Invalid response. Please type 'y' or 'n'." + ANSI_RESET);
        }
    }

    // Handles returning a book selected from the list of available books.
    private void returnBookFromList() {
        System.out.println("Enter the number of the book you want to return:");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
            return;
        }

        List<BookLibrary.BookWithQuantity> availableBooks = library.getAvailableBooksWithQuantities(); // Implement this
                                                                                                       // method
        if (choice < 1 || choice > availableBooks.size()) {
            System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
            return;
        }

        BookLibrary.BookWithQuantity bookWithQuantity = availableBooks.get(choice - 1);
        library.returnBook(bookWithQuantity.getBook());
        System.out.println(ANSI_GREEN + "Book returned: " + bookWithQuantity.getBook().getTitle() + ANSI_RESET);
    }

    // Handles manually returning a book by entering its details.
    private void returnBookManually() {
        System.out.println("Enter book details to return.");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Value: ");
        double value;
        while (true) {
            try {
                value = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print(ANSI_RED + "Please enter a valid value: " + ANSI_RESET);
            }
        }

        System.out.print("Year: ");
        int year;
        while (true) {
            try {
                year = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print(ANSI_RED + "Please enter a valid year: " + ANSI_RESET);
            }
        }

        Book book = new Book.Builder().setTitle(title).setAuthor(author).setISBN(isbn).setValue(value).setYear(year)
                .build();
        library.returnBook(book);
        System.out.println(ANSI_GREEN + "Book returned: " + title + ANSI_RESET);
    }
}
