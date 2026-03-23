import java.util.HashMap;
import java.util.Map;

// Renamed class
class RoomInventory4 {

    private Map<String, Integer> inventory;

    public RoomInventory4() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public boolean bookRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        } else {
            return false;
        }
    }

    public void displayInventory() {
        System.out.println("\n---- Room Inventory ----");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Main class
public class UseCase4RoomBooking {

    public static void main(String[] args) {

        System.out.println("Hotel Booking System v4.0");

        RoomInventory4 inventory = new RoomInventory4();

        inventory.displayInventory();

        System.out.println("\nBooking Single Room...");
        if (inventory.bookRoom("Single Room")) {
            System.out.println("Booking Successful!");
        } else {
            System.out.println("Booking Failed!");
        }

        System.out.println("\nTrying to overbook Suite Room...");
        inventory.bookRoom("Suite Room");
        inventory.bookRoom("Suite Room");
        boolean result = inventory.bookRoom("Suite Room");

        if (!result) {
            System.out.println("Booking Failed - Prevented double booking!");
        }

        inventory.displayInventory();
    }
}