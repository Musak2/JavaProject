import library.BookLibrary;
import library.Librarian;
import models.Book;
import models.RareEditionBook;
import models.SignedBook;

public class Main {
    public static void main(String[] args) {
        BookLibrary library = BookLibrary.getInstance();

        // Adding books programmatically
        library.addBook(new Book.Builder().setTitle("The Little Prince").setAuthor("Antoine de Saint-Exupéry").setISBN("978-8088797951").setValue(14.99).setYear(1943).build(), 5);
        library.addBook(new Book.Builder().setTitle("The Lord of the Rings").setAuthor("J. R. R. Tolkien").setISBN("978-0544003415").setValue(19.99).setYear(1954).build(), 3);
        library.addBook(new Book.Builder().setTitle("Harry Potter").setAuthor("J. K. Rowling").setISBN("978-0590353427").setValue(34.99).setYear(1997).build(), 1);

        // Create instances of signed and rare edition books
        SignedBook signedBook = new SignedBook("A Game of Thrones", "George R. R. Martin", "978-0007548231", 30.0, 1996);
        RareEditionBook rareBook = new RareEditionBook("Tom Sawyer", "Mark Twain", "978-1503215672", 30.0, 1876, 10);

        // Add these books to the library
        library.addBook(signedBook, 3);
        library.addBook(rareBook, 2);

        // Starting the interactive session with Librarian
        Librarian librarian = new Librarian();
        librarian.start();
    }
}