import java.util.*;

class Reservation {
    String reservationId;
    String roomType;

    Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    void increaseAvailability(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

class CancellationService {

    private HashMap<String, Reservation> confirmedBookings;
    private Stack<String> rollbackStack;

    CancellationService() {
        confirmedBookings = new HashMap<>();
        rollbackStack = new Stack<>();
    }

    void addBooking(Reservation r) {
        confirmedBookings.put(r.reservationId, r);
    }

    void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed → Reservation not found: " + reservationId);
            return;
        }

        Reservation r = confirmedBookings.get(reservationId);

        rollbackStack.push(r.reservationId);

        inventory.increaseAvailability(r.roomType);

        confirmedBookings.remove(reservationId);

        System.out.println("Cancellation Successful → Reservation ID: " + reservationId);
    }

    void showRollbackStack() {
        System.out.println("\nRollback Stack (LIFO): " + rollbackStack);
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        service.addBooking(new Reservation("SI1", "Single Room"));
        service.addBooking(new Reservation("SU2", "Suite Room"));

        service.cancelBooking("SI1", inventory);
        service.cancelBooking("XX9", inventory);

        inventory.displayInventory();

        service.showRollbackStack();
    }
}