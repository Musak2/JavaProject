# Library Management System

This project is a Java-based library management system. It simulates a library with functionalities for borrowing and returning books, listing available books, and handling different types of books including signed and rare editions.

## Features

- **Book Library**: Manages a collection of books and their quantities. Supports adding new books, borrowing and returning books.
- **Librarian Console**: A console-based interface for interacting with the library. Allows listing books, borrowing, and returning books.
- **Book Variants**: Supports different types of books, including regular books, signed books (with double value), and rare edition books (with increased value based on rarity).
- **Design Patterns**: Utilizes design patterns such as Singleton (for the library) and Builder (for creating book instances).
- **Inheritance and Interfaces**: Demonstrates use of inheritance (for different types of books) and interfaces (for readable items).

## How to Run

To run the library management system:

1. Ensure you have Java installed on your system.
2. Clone the repository to your local machine.
3. Navigate to the `src` directory.
4. Compile the Java files. For example, use `javac library/*.java models/*.java`.
5. Run the `Main` class. For example, use `java library.Main`.

## Usage

After starting the application, use the console interface to interact with the library:

- **Borrow a Book**: Lists all available books. Enter the number corresponding to the book you want to borrow.
- **Return a Book**: Allows you to return a book either by selecting from a list or by entering book details manually.
- **List Available Books**: Displays all books currently available in the library.
- **Exit**: Exits the application.
