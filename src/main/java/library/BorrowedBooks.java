package library;


public class BorrowedBooks {


    public int id;
    public Client client_id;


    public BorrowedBooks(int id, Client client_id) {
        this.id = id;
        this.client_id = client_id;

    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {

        return
                "book id: " + client_id.bookBorrowedId;
    }
}
