package library;


public class Book {


    public String title;
    public int available_quantity;
    public int id;


    public Book(String title, int availableQuantity) {
        this.title = title;
        this.available_quantity = availableQuantity;
    }

    public Book(int id, String title, int availableQuantity) {
        this.id = id;
        this.title = title;
        this.available_quantity = availableQuantity;
    }


    public void borrowBook(int amountBorrowed) {
        this.available_quantity -= amountBorrowed;
    }

    public void returnBook(int amountBorrowed) {
        this.available_quantity += amountBorrowed;
    }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "\nid: " + id + " | " + " Title: " + title + " | " +
                        " available quantity: " + available_quantity;
    }
}
