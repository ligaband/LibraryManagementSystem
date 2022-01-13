package library;

import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryController {
    LibraryRepository libraryRepository = new LibraryRepository();

    public String createTableBooks(){
        try {
            libraryRepository.createTableBooks();
            return "Table \"Books\" created successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error creating table \"Books\"";
        }
    }

    public String createTableBorrowedBooks(){
        try {
            libraryRepository.createTableBorrowedBooks();
            return "Table \"Borrowed books\" created successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error creating table \"Borrowed books\"";
        }
    }

    public String createTableClient(){
        try {
            libraryRepository.createTableClient();
            return "Table \"Clients\" created successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error creating table \"Clients\"";
        }
    }

    public String addBook(Book book){
        try {
            libraryRepository.addBook(book);
            return "Book added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding book";
        }

    }

    public String addClient(Client client){
        try {
            libraryRepository.addClient(client);
            return "Client added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding client";
        }
    }


    public void borrowBook(int client_id, int id, int borrowedQuantity, String dateBorrowed){
        try {
            libraryRepository.borrowBook(client_id, id, borrowedQuantity, dateBorrowed);

            System.out.println("Book borrowed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int client_id, int id, int borrowedQuantity, String dateReturned){
        try {
            libraryRepository.returnBook(client_id, id, borrowedQuantity, dateReturned);
            System.out.println("Book returned successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> viewAllBooks(){
        try {

            return libraryRepository.viewAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<BorrowedBooks> borrowedClientBooks(int client_id){
        try {
            return libraryRepository.borrowedClientBooks(client_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    public void deleteBook(int id){
        try {
            libraryRepository.deleteBook(id);
            System.out.println("Book deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteBook(String title){
        try {
            libraryRepository.deleteBook(title);
            System.out.println("Book " + title + " deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> findBookByTitle(String title){
        try {
            return libraryRepository.findBookByTitle(title);
        } catch (SQLException e) {
            System.out.println("Can't find book with title ");
            e.printStackTrace();
            return null;
        }
    }
    public Book findBookById(int id){
        try {
            return libraryRepository.findBookById(id);
        } catch (SQLException e) {
            System.out.println("Cannot find book with id:" + id);
            e.printStackTrace();
            return null;
        }
    }

    public Client findClient (int id){
        try {
            return libraryRepository.findClient(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateBook(Book book){
        try {
            libraryRepository.updateBook(book);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
