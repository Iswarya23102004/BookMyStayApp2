import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request Queue (FIFO)
 * Demonstrates handling booking requests using Queue
 * @version 5.0
 */

// Reservation class (represents a booking request)
class Reservation {
    private String customerName;
    private String roomType;

    public Reservation(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Customer: " + customerName + ", Room Type: " + roomType);
    }
}

// Booking Queue class
class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added for " + reservation.getCustomerName());
    }

    // Display all requests (FIFO order)
    public void displayRequests() {
        System.out.println("\n---- Booking Requests Queue ----");

        if (queue.isEmpty()) {
            System.out.println("No requests in queue.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v5.0");

        BookingQueue bookingQueue = new BookingQueue();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Display queue (FIFO order)
        bookingQueue.displayRequests();

        System.out.println("\nAll requests stored in arrival order.");
    }
}