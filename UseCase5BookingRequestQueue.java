import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

class BookingQueue {

    private Queue<Reservation> queue;

    BookingQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request added for " + r.guestName);
    }

    void showQueue() {
        System.out.println("\n===== Booking Request Queue (Version 5.0) =====");
        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        bookingQueue.showQueue();
    }
}