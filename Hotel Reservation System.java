import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
class Reservation {
    private String customerName;
    private Room room;
    private int numberOfNights;
    private double totalCost;

    public Reservation(String customerName, Room room, int numberOfNights) {
        this.customerName = customerName;
        this.room = room;
        this.numberOfNights = numberOfNights;
        this.totalCost = room.getPrice() * numberOfNights;
        room.setAvailable(false);
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customerName='" + customerName + '\'' +
                ", room=" + room +
                ", numberOfNights=" + numberOfNights +
                ", totalCost=" + totalCost +
                '}';
    }
}
class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(String customerName, int roomNumber, int numberOfNights) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                Reservation reservation = new Reservation(customerName, room, numberOfNights);
                reservations.add(reservation);
                return reservation;
            }
        }
        return null;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);
        hotel.addRoom(new Room(101, "Single", 100));
        hotel.addRoom(new Room(102, "Double", 150));
        hotel.addRoom(new Room(103, "Suite", 300));

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Single/Double/Suite): ");
                    scanner.nextLine(); // Consume newline
                    String category = scanner.nextLine();
                    List<Room> availableRooms = hotel.searchAvailableRooms(category);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms in this category.");
                    } else {
                        System.out.println("Available rooms:");
                        for (Room room : availableRooms) {
                            System.out.println(room);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    scanner.nextLine();
                    String customerName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int numberOfNights = scanner.nextInt();
              Reservation reservation = hotel.makeReservation(customerName, roomNumber, numberOfNights);
                    if (reservation != null) {
                        System.out.println("Reservation successful! Your details:");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Reservation failed. Room may not be available.");
                    }
                    break;
                case 3:
                    List<Reservation> reservations = hotel.getReservations();
                    if (reservations.isEmpty()) {
                        System.out.println("No reservations found.");
                    } else {
                        System.out.println("Booking details:");
                        for (Reservation res : reservations) {
                            System.out.println(res);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting system.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

