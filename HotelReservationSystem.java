import java.util.Scanner;

class Room {
    private int roomNumber;
    private boolean isReserved;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isReserved = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void reserve() {
        isReserved = true;
    }

    public void cancelReservation() {
        isReserved = false;
    }
}

class Hotel {
    private Room[] rooms;

    public Hotel(int numberOfRooms) {
        rooms = new Room[numberOfRooms];
        for (int i = 0; i < numberOfRooms; i++) {
            rooms[i] = new Room(i + 1);
        }
    }

    public boolean bookRoom(int roomNumber) {
        if (roomNumber < 1 || roomNumber > rooms.length) {
            System.out.println("Invalid room number. Please enter a valid room number.");
            return false;
        }
        if (rooms[roomNumber - 1].isReserved()) {
            System.out.println("Room " + roomNumber + " is already reserved. Please choose another room.");
            return false;
        }
        rooms[roomNumber - 1].reserve();
        System.out.println("Room " + roomNumber + " booked successfully.");
        return true;
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        boolean availableRooms = false;
        for (Room room : rooms) {
            if (!room.isReserved()) {
                System.out.println("Room " + room.getRoomNumber());
                availableRooms = true;
            }
        }
        if (!availableRooms) {
            System.out.println("No rooms are currently available.");
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel(10); // Create a hotel with 10 rooms

        while (true) {
            System.out.println("\nWelcome to Hotel Reservation System");
            System.out.println("1. Book a room");
            System.out.println("2. Display available rooms");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter room number to book: ");
                    int roomNumber;
                    try {
                        roomNumber = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid room number. Please enter a valid number.");
                        continue;
                    }
                    if (hotel.bookRoom(roomNumber)) {
                        System.out.println("Thank you for booking with us.");
                    }
                    break;
                case 2:
                    hotel.displayAvailableRooms();
                    break;
                case 3:
                    System.out.println("Thank you for using Hotel Reservation System. Bye!");
                    scanner.close(); // Close the scanner before exiting
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
