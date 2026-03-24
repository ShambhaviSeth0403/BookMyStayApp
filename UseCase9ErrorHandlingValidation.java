import java.util.HashMap;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, -1);
    }

    void validateRoomType(String roomType) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
    }

    void validateAvailability(String roomType) throws InvalidBookingException {
        if (getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("No availability for: " + roomType);
        }
    }

    void decreaseAvailability(String roomType) throws InvalidBookingException {
        int current = getAvailability(roomType);

        if (current <= 0) {
            throw new InvalidBookingException("Cannot reduce availability below zero for: " + roomType);
        }

        inventory.put(roomType, current - 1);
    }
}

class BookingService {

    private RoomInventory inventory;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void bookRoom(String guestName, String roomType) {
        try {
            inventory.validateRoomType(roomType);
            inventory.validateAvailability(roomType);

            inventory.decreaseAvailability(roomType);

            System.out.println("Booking successful for " + guestName + " | Room: " + roomType);

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed for " + guestName + " → " + e.getMessage());
        }
    }
}

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.bookRoom("Alice", "Single Room");
        service.bookRoom("Bob", "Suite Room");
        service.bookRoom("Charlie", "Luxury Room");
        service.bookRoom("David", "Single Room");
        service.bookRoom("Eve", "Single Room");
    }
}