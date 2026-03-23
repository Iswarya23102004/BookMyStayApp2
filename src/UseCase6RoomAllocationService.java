import java.util.*;

// Booking request class (unique for Use Case 6)
class BookingRequest6 {
    String customerName;
    String roomType;

    public BookingRequest6(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Inventory service class (unique for Use Case 6)
class InventoryService6 {

    private Map<String, Integer> inventory;

    public InventoryService6() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void reduceRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\n---- Current Inventory ----");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking service class (unique for Use Case 6)
class BookingService6 {

    private Queue<BookingRequest6> requestQueue;
    private Map<String, Set<String>> allocatedRooms;
    private InventoryService6 inventory;

    public BookingService6(InventoryService6 inventory) {
        this.inventory = inventory;
        requestQueue = new LinkedList<>();
        allocatedRooms = new HashMap<>();
    }

    // Add a booking request
    public void addRequest(BookingRequest6 request) {
        requestQueue.add(request);
        System.out.println("Request added for " + request.customerName);
    }

    // Process booking requests
    public void processBookings() {
        System.out.println("\n---- Processing Bookings ----");

        while (!requestQueue.isEmpty()) {
            BookingRequest6 request = requestQueue.poll();

            if (inventory.getAvailability(request.roomType) > 0) {
                // Generate a unique room ID
                String roomId = request.roomType.substring(0, 2).toUpperCase()
                        + UUID.randomUUID().toString().substring(0, 4);

                allocatedRooms.putIfAbsent(request.roomType, new HashSet<>());
                allocatedRooms.get(request.roomType).add(roomId);

                // Reduce room availability
                inventory.reduceRoom(request.roomType);

                System.out.println("Booking Confirmed for " + request.customerName +
                        " | Room: " + request.roomType +
                        " | Room ID: " + roomId);
            } else {
                System.out.println("Booking Failed for " + request.customerName +
                        " | No " + request.roomType + " available");
            }
        }
    }

    // Display all allocated rooms
    public void displayAllocations() {
        System.out.println("\n---- Allocated Rooms ----");
        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

// Main class (public class must match file name)
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v6.0");

        // Initialize inventory
        InventoryService6 inventory = new InventoryService6();

        // Initialize booking service
        BookingService6 service = new BookingService6(inventory);

        // Add booking requests
        service.addRequest(new BookingRequest6("Alice", "Single Room"));
        service.addRequest(new BookingRequest6("Bob", "Double Room"));
        service.addRequest(new BookingRequest6("Charlie", "Suite Room"));
        service.addRequest(new BookingRequest6("David", "Suite Room")); // This one should fail

        // Process all bookings
        service.processBookings();

        // Show allocated rooms
        service.displayAllocations();

        // Show current inventory
        inventory.displayInventory();

        System.out.println("\nApplication finished.");
    }
}