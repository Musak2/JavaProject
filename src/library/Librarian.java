package library;
import java.util.List;
import java.util.Scanner;

import models.Book;

public class Librarian {
    private final BookLibrary library;
    private final Scanner scanner;
    final String ANSI_BOLD = "\033[1m";
    final String ANSI_RESET = "\033[0m";

    public Librarian() {
        this.library = BookLibrary.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println(ANSI_BOLD
                    + "\n Welcome to the Library! Choose an option: \n1. Borrow a book \n2. Return a book \n3. List available books \n4. Exit \n"
                    + ANSI_RESET);
            try {
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
                        System.out.println("Thank you for visiting the library.\n");
                        return;
                    default:
                        System.out.println("Invalid option, please try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.\n");
            }
        }
    }

    private void borrowBookProcess() {
        System.out.println("Enter the title or author of the book you want to borrow:");
        String query = scanner.nextLine();

        List<Book> foundBooks = library.searchBooks(query);
        if (foundBooks.isEmpty()) {
            System.out.println("No books found.");
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
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (choice < 1 || choice > foundBooks.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Book bookToBorrow = foundBooks.get(choice - 1);
        Book borrowedBook = library.borrowBook(bookToBorrow.getISBN());
        if (borrowedBook != null) {
            System.out.println("You borrowed: " + bookToBorrow.getTitle());
            borrowedBook.read(); // Simulating reading the book
        } else {
            System.out.println("This book is currently not available.");
        }
    }

    private void returnBookProcess() {
        library.listBooks();
    
        System.out.println("Do you want to return one of the above books? (y/n)");
        String response = scanner.nextLine().trim().toLowerCase();
    
        if (response.equals("y")) {
            returnBookFromList();
        } else if (response.equals("n")) {
            returnBookManually();
        } else {
            System.out.println("Invalid response. Please type 'y' or 'n'.");
        }
    }
    
    private void returnBookFromList() {
        System.out.println("Enter the number of the book you want to return:");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
    
        List<BookLibrary.BookWithQuantity> availableBooks = library.getAvailableBooksWithQuantities(); // Implement this method
        if (choice < 1 || choice > availableBooks.size()) {
            System.out.println("Invalid choice.");
            return;
        }
    
        BookLibrary.BookWithQuantity bookWithQuantity = availableBooks.get(choice - 1);
        library.returnBook(bookWithQuantity.getBook());
        System.out.println("Book returned: " + bookWithQuantity.getBook().getTitle());
    }   

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
                System.out.print("Please enter a valid value: ");
            }
        }

        System.out.print("Year: ");
        int year;
        while (true) {
            try {
                year = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid year: ");
            }
        }

        Book book = new Book.Builder().setTitle(title).setAuthor(author).setISBN(isbn).setValue(value).setYear(year)
                .build();
        library.returnBook(book);
        System.out.println("Book returned: " + title);
    }
}
