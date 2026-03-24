import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}

class RoomInventory implements Serializable {

    HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    void display() {
        System.out.println("\nInventory State:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
    }
}

class SystemState implements Serializable {
    RoomInventory inventory;
    List<Reservation> history;

    SystemState(RoomInventory inventory, List<Reservation> history) {
        this.inventory = inventory;
        this.history = history;
    }
}

class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    void save(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    SystemState load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("System state loaded successfully.");
            return (SystemState) ois.readObject();
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        SystemState state = service.load();

        RoomInventory inventory;
        List<Reservation> history;

        if (state == null) {
            inventory = new RoomInventory();
            history = new ArrayList<>();

            history.add(new Reservation("SI1", "Alice", "Single Room"));
            history.add(new Reservation("DB2", "Bob", "Double Room"));
        } else {
            inventory = state.inventory;
            history = state.history;
        }

        System.out.println("\nBooking History:");
        for (Reservation r : history) {
            r.display();
        }

        inventory.display();

        service.save(new SystemState(inventory, history));
    }
}