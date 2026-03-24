import java.util.*;

class AddOnService {
    String name;
    double cost;

    AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {

    private HashMap<String, List<AddOnService>> serviceMap;

    AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services for Reservation ID: " + reservationId);
            return;
        }

        double total = 0;

        System.out.println("Add-On Services for Reservation ID: " + reservationId);

        for (AddOnService s : services) {
            System.out.println("- " + s.name + " (Rs. " + s.cost + ")");
            total += s.cost;
        }

        System.out.println("Total Add-On Cost: Rs. " + total);
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId1 = "SI1";
        String reservationId2 = "SU2";

        manager.addService(reservationId1, new AddOnService("Breakfast", 500));
        manager.addService(reservationId1, new AddOnService("Airport Pickup", 1200));
        manager.addService(reservationId2, new AddOnService("Spa", 2000));

        manager.displayServices(reservationId1);
        System.out.println();
        manager.displayServices(reservationId2);
    }
}