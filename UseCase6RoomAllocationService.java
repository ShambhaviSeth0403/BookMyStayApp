import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void decreaseAvailability(String roomType) {
        inventory.put(roomType, getAvailability(roomType) - 1);
    }

    void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

class BookingService {

    private Queue<Reservation> queue;
    private HashMap<String, Set<String>> allocatedRooms;
    private int roomCounter = 1;

    BookingService(Queue<Reservation> queue) {
        this.queue = queue;
        allocatedRooms = new HashMap<>();
    }

    void processBookings(RoomInventory inventory) {

        System.out.println("===== Processing Bookings (Version 6.0) =====");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();
            String type = r.roomType;

            if (inventory.getAvailability(type) > 0) {

                String roomId = type.substring(0, 2).toUpperCase() + roomCounter++;

                allocatedRooms.putIfAbsent(type, new HashSet<>());

                Set<String> roomSet = allocatedRooms.get(type);

                if (!roomSet.contains(roomId)) {
                    roomSet.add(roomId);
                    inventory.decreaseAvailability(type);

                    System.out.println("Booking Confirmed for " + r.guestName +
                            " | Room Type: " + type +
                            " | Room ID: " + roomId);
                }

            } else {
                System.out.println("Booking Failed for " + r.guestName +
                        " | Room Type: " + type + " (Not Available)");
            }
        }
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room"));
        queue.add(new Reservation("David", "Suite Room"));

        RoomInventory inventory = new RoomInventory();

        BookingService service = new BookingService(queue);

        service.processBookings(inventory);

        inventory.displayInventory();
    }
}