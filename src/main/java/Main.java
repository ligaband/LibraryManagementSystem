import library.Book;
import library.BorrowedBooks;
import library.Client;
import library.LibraryController;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    Scanner scanner = new Scanner(System.in);
    Scanner intScanner = new Scanner(System.in);
    LibraryController libraryController = new LibraryController();


    public static void main(String[] args) {
        Main main = new Main();
        main.menu();

    }

    void menu() {
        System.out.println("\n\tWelcome to the Library");

        String choice;

        do {
            System.out.println("\n------------------------------");
            System.out.println("\nPlease choose an activity");
            System.out.println("\n1. Add a book");
            System.out.println("2. View all books");
            System.out.println("3. Borrow book");
            System.out.println("4. Return book");
            System.out.println("5. Delete a book by name");
            System.out.println("6. Delete book by id");
            System.out.println("7. Update book");
            System.out.println("8. Find book by title");
            System.out.println("9. Find book by ID");
            System.out.println("10. Add new client");
            System.out.println("11. Find client");
            System.out.println("12. View client's borrowed books");
            System.out.println("13. Create table BOOKS");
            System.out.println("14. Create table CLIENTS");
            System.out.println("15. Create table BORROW BOOKS");
            System.out.println("Enter \"Quit\" to exit menu");
            System.out.print("\nEnter your choice: \n");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    viewAllBook();
                    break;
                case "3":
                    borrowBook();
                    break;
                case "4":
                    returnBook();
                    break;
                case "5":
                    deleteBook();
                    break;
                case "6":
                    deleteBookById();
                    break;
                case "7":
                    updateBook();
                    break;
                case "8":
                    findBookByTitle();
                    break;
                case "9":
                    findBookById();
                    break;
                case "10":
                    addClient();
                    break;
                case "11":
                    findClient();
                    break;
                case "12":
                    viewClientBorrowedBooks();
                    break;
                case "13":
                    System.out.println(libraryController.createTableBooks());
                    break;
                case "14":
                    System.out.println(libraryController.createTableClient());
                    break;
                case "15":
                    System.out.println(libraryController.createTableBorrowedBooks());
                    break;
                case "Quit":
                    System.out.println("Quit menu");
                    break;
                default:
                    break;

            }
        } while (!choice.equalsIgnoreCase("Quit"));

    }

    void viewAllBook(){
        System.out.println("           ==========Available books==========");
        ArrayList<Book> books = libraryController.viewAllBooks();
        books.forEach(book -> System.out.println(book.toString()));

    }

    void addBook() {
        System.out.println("Enter book title");
        String title = scanner.nextLine();
        System.out.println("Enter available quantity of the book");
        int available_quantity = intScanner.nextInt();
        Book book = new Book(title, available_quantity);
        System.out.println(libraryController.addBook(book));


    }

    void addClient() {
        System.out.println("Enter client's name");
        String name = scanner.nextLine();
        System.out.println("Enter client's surname");
        String surname = scanner.nextLine();
        Client client = new Client(name, surname);
        System.out.println(libraryController.addClient(client));
    }


    void deleteBook() {
        System.out.println("           ==========Available books==========");
        viewAllBook();
        System.out.println("\nEnter book's title you want to delete");
        String title = scanner.nextLine();
        libraryController.deleteBook(title);

    }

    void deleteBookById() {
        System.out.println("           ==========Available books==========");
        System.out.println("\nEnter book's id you want to delete");
        int id = intScanner.nextInt();
        libraryController.deleteBook(id);

    }

    void findBookById() {
        System.out.println("Enter ID of the book you would like to find");
        int id = intScanner.nextInt();
        System.out.println(libraryController.findBookById(id));

    }

    void findClient() {
        System.out.println("Enter client's ID");
        int id = intScanner.nextInt();
        System.out.println(libraryController.findClient(id));

    }


    void findBookByTitle() {
        System.out.println("Enter title of the book you would like to find");
        String title = scanner.nextLine();
        if(libraryController.findBookByTitle(title).isEmpty()){
            System.out.println("There is no book with such title");
        }
        ArrayList<Book> books = libraryController.findBookByTitle(title);
        books.forEach(book -> System.out.println(book.toString()));


    }

    void updateBook() {
        System.out.println("           ==========Available books==========              ");
        viewAllBook();
        System.out.println("\nEnter book's id you would like to update");
        int id = intScanner.nextInt();
        Book book = libraryController.findBookById(id);
        System.out.println("Enter new title");
        String newTitle = scanner.nextLine();
        System.out.println("Enter available quantity");
        int quantity = intScanner.nextInt();
        book.title = newTitle;
        book.available_quantity = quantity;
        libraryController.updateBook(book);
        System.out.println("Book updated successfully");
    }

    void borrowBook() {
        System.out.println("           ==========Available books==========              ");
        viewAllBook();
        System.out.println("\nEnter client's ID");
        int clientId = intScanner.nextInt();
        System.out.println("===You have already borrowed=== \n");
        ArrayList<BorrowedBooks> borrowedBooks1 = libraryController.borrowedClientBooks(clientId);
        borrowedBooks1.forEach(borrowedBook -> System.out.print("\t\t" + borrowedBook.toString() + "\n" ));
        if (libraryController.borrowedClientBooks(clientId).size() > 4) {
            System.out.println("You can't borrow more than 5 books");
            return;
        }
        System.out.println("Book's id you would like to borrow");
        int id = intScanner.nextInt();
        Book book = libraryController.findBookById(id);

        if (book.available_quantity == 0) {
            System.out.println("Book is not available");
            return;
        }

        ArrayList<BorrowedBooks> borrowedBooks = libraryController.borrowedClientBooks(clientId);
        for (BorrowedBooks bookBorrowed : borrowedBooks) {

            if (bookBorrowed.getId() == book.getId()) {
                System.out.println("You can't borrow more than one copy of the book");
                return;
            }
        }

        System.out.println("Amount to borrow");
        int amount = intScanner.nextInt();
        if (amount > 1) {
            System.out.println("You can't borrow more than one copy of the book");
            return;
        }
        if (amount > book.available_quantity) {
            System.out.println("Amount is not available");
            return;
        }
        System.out.println("Borrowing date (yyyy-mm-dd)");
        String date = scanner.nextLine();

        libraryController.borrowBook(clientId, id, amount, date);

        book.borrowBook(amount);
        libraryController.updateBook(book);

    }

    void returnBook() {

        System.out.println("Enter client's ID");
        int clientId = intScanner.nextInt();
        System.out.println("             =========You have borrowed=========");
        System.out.println(libraryController.borrowedClientBooks(clientId));

        System.out.println("\nBook's id you would like to return");
        int id = intScanner.nextInt();
        System.out.println("Amount to return");
        int amount = intScanner.nextInt();
        System.out.println("Returning date (yyyy-mm-dd)");
        String date = scanner.nextLine();

        libraryController.returnBook(clientId, id, amount, date);
        Book book = libraryController.findBookById(id);
        book.returnBook(amount);
        libraryController.updateBook(book);

    }

    void viewClientBorrowedBooks() {
        System.out.println("Please enter client's ID");
        int id = intScanner.nextInt();
        System.out.println("======Borrowed books======\n");
        ArrayList<BorrowedBooks> borrowedBooks = libraryController.borrowedClientBooks(id);
        borrowedBooks.forEach(borrowedBooks1 -> System.out.print("\t\t" + borrowedBooks1.toString() + "\n" ));
    }


}


