package library;


public class Client {
    public String clientName;
    public String clientSurname;
    public int client_id;
    public int bookBorrowedId;


    public Client(String clientName, String clientSurname) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
    }


    public Client(int bookBorrowedId) {

        this.bookBorrowedId = bookBorrowedId;
    }


    @Override
    public String toString() {
        return
                "\nName: " + clientName +
                        ", Surname: " + clientSurname;
    }
}
