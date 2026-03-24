import java.util.ArrayList;
import java.util.HashMap;

abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: Rs. " + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 2000);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 3500);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 6000);
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class SearchService {

    void searchAvailableRooms(ArrayList<Room> rooms, RoomInventory inventory) {

        System.out.println("===== Available Rooms (Version 4.0) =====");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.type);

            if (available > 0) {
                room.displayDetails();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom());
        rooms.add(new DoubleRoom());
        rooms.add(new SuiteRoom());

        RoomInventory inventory = new RoomInventory();

        SearchService search = new SearchService();

        search.searchAvailableRooms(rooms, inventory);
    }
}