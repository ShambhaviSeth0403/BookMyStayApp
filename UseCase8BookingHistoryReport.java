import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType);
    }
}

class BookingHistory {

    private List<Reservation> history;

    BookingHistory() {
        history = new ArrayList<>();
    }

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    void displayAllBookings(List<Reservation> history) {

        System.out.println("===== Booking History (Version 8.0) =====");

        for (Reservation r : history) {
            r.display();
        }
    }

    void generateSummary(List<Reservation> history) {

        HashMap<String, Integer> summary = new HashMap<>();

        for (Reservation r : history) {
            summary.put(r.roomType, summary.getOrDefault(r.roomType, 0) + 1);
        }

        System.out.println("\n===== Booking Summary =====");

        for (String type : summary.keySet()) {
            System.out.println(type + " Bookings: " + summary.get(type));
        }
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("SI1", "Alice", "Single Room"));
        history.addReservation(new Reservation("SI2", "Bob", "Single Room"));
        history.addReservation(new Reservation("SU3", "Charlie", "Suite Room"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history.getAllReservations());

        reportService.generateSummary(history.getAllReservations());
    }
}
