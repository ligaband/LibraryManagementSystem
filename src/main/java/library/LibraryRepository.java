package library;

import database.DBHandler;

import java.sql.*;
import java.util.ArrayList;

public class LibraryRepository {

    DBHandler dbHandler = new DBHandler();

    public void createTableBooks() throws SQLException {
        String query = "CREATE TABLE books (id int primary key not null auto_increment, title VARCHAR (255) not null, available_quantity int)";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.execute();
        preparedStatement.close();


    }

    public void createTableClient() throws SQLException {
        String query = "CREATE TABLE clients (client_id int primary key not null auto_increment, client_name VARCHAR (255) not null, client_surname VARCHAR (255) not null)";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.execute();
        preparedStatement.close();

    }

    public void createTableBorrowedBooks() throws SQLException {
        String query = "CREATE TABLE borrowed_books (id int primary key not null auto_increment, client_id int, borrowed_book int, borrowed_quantity int, date_borrowed DATE, date_returned DATE )";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO books (title, available_quantity) VALUES (?,?)";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, book.title);
        preparedStatement.setInt(2, book.available_quantity);

        preparedStatement.execute();
        preparedStatement.close();
    }

    public void addClient(Client client) throws SQLException {
        String query = "INSERT INTO clients (client_name, client_surname) VALUES (?,?)";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, client.clientName);
        preparedStatement.setString(2, client.clientSurname);

        preparedStatement.execute();
        preparedStatement.close();
    }

    public void borrowBook(int client_id, int id, int borrowedQuantity, String dateBorrowed) throws SQLException {
        String query = "INSERT INTO borrowed_books (client_id, borrowed_book, borrowed_quantity, date_borrowed) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, client_id);
        preparedStatement.setInt(2, id);
        preparedStatement.setInt(3, borrowedQuantity);
        preparedStatement.setString(4, dateBorrowed);


        preparedStatement.execute();
        preparedStatement.close();


    }

    public ArrayList<Book> viewAllBooks() throws SQLException {
        String query = "SELECT * FROM books";
        Statement statement = dbHandler.getConnection().createStatement();
        ResultSet result = statement.executeQuery(query);

        ArrayList<Book> books = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("id");
            String title = result.getString("title");
            int available_quantity = result.getInt("available_quantity");

            Book book = new Book(id, title, available_quantity);
            books.add(book);


        }
        return books;

    }

    public void deleteBook(int id) throws SQLException {
        String query = "DELETE FROM books WHERE id=?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();

    }

    public void deleteBook(String title) throws SQLException {
        String query = "DELETE FROM books WHERE title=?" + title;
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.execute();
        preparedStatement.close();

    }

    public ArrayList<Book> findBookByTitle(String title) throws SQLException {
        String query = "SELECT * FROM books WHERE title=?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.execute();

        ResultSet result = preparedStatement.getResultSet();

        ArrayList<Book> books = new ArrayList<>();

        while (result.next()) {
            int id = result.getInt("id");
            String book_title = result.getString("title");
            int available_quantity = result.getInt("available_quantity");

            Book book = new Book(id, book_title, available_quantity);
            books.add(book);

        }
        preparedStatement.close();
        return books;

    }


    public Book findBookById(int id) throws SQLException {
        String query = "SELECT * FROM books WHERE id= " + id;
        Statement statement = dbHandler.getConnection().createStatement();
        ResultSet result = statement.executeQuery(query);
        if (result.next()) {
            int book_id = result.getInt("id");
            String book_title = result.getString("title");
            int quantity = result.getInt("available_quantity");

            Book book = new Book(book_title, quantity);
            book.id = book_id;
            statement.close();
            return book;
        } else {
            return null;
        }
    }

    public Client findClient(int id) throws SQLException {
        String query = "SELECT * FROM clients WHERE client_id= " + id;
        Statement statement = dbHandler.getConnection().createStatement();
        ResultSet result = statement.executeQuery(query);

        if (result.next()) {
            int client_id = result.getInt("client_id");
            String client_name = result.getString("client_name");
            String client_surname = result.getString("client_surname");
            Client client = new Client(client_name, client_surname);
            client.client_id = client_id;
            statement.close();
            return client;
        } else {
            return null;
        }
    }


    public ArrayList<BorrowedBooks> borrowedClientBooks(int client_id) throws SQLException {
        String query = "SELECT * FROM borrowed_books WHERE date_returned IS NULL AND client_id= " + client_id;
        Statement statement = dbHandler.getConnection().createStatement();
        ResultSet result = statement.executeQuery(query);

        ArrayList<BorrowedBooks> borrowedBooks = new ArrayList<>();
        while (result.next()) {
            int clientId = result.getInt("client_id");
            int book_id = result.getInt("borrowed_book");

            Client client = new Client(clientId);
            client.client_id = clientId;
            BorrowedBooks bookBorrowed = new BorrowedBooks(book_id, client);
            bookBorrowed.id = book_id;
            client.bookBorrowedId = book_id;

            BorrowedBooks borrowedBook = new BorrowedBooks(book_id, client);
            borrowedBooks.add(borrowedBook);

            if (borrowedBooks.size() > 4) {
                break;
            }
        }
        return borrowedBooks;
    }


    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE books SET title=?, available_quantity=? WHERE id=?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, book.title);
        preparedStatement.setInt(2, book.available_quantity);
        preparedStatement.setInt(3, book.id);

        preparedStatement.execute();
        preparedStatement.close();

    }


    public void returnBook(int client_id, int id, int borrowedQuantity, String dateReturned) throws SQLException {
        String query = "UPDATE borrowed_books SET borrowed_quantity=?, date_returned=? WHERE client_id=? AND borrowed_book=?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);

        preparedStatement.setInt(1, borrowedQuantity);
        preparedStatement.setString(2, dateReturned);
        preparedStatement.setInt(3, client_id);
        preparedStatement.setInt(4, id);

        preparedStatement.execute();
        preparedStatement.close();
    }

}
