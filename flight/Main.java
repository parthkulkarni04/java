package flight;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Flight {
    private String flightNumber;
    private String departure;
    private String arrival;
    private int totalSeats;
    private int availableSeats;

    public Flight(String flightNumber, String departure, String arrival, int totalSeats) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeat() throws NoAvailableSeatsException {
        if (availableSeats > 0) {
            availableSeats--;
        } else {
            throw new NoAvailableSeatsException("No available seats on flight " + flightNumber);
        }
    }

    public void cancelSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                '}';
    }
}

class Reservation {
    private Flight flight;
    private String passengerName;

    public Reservation(Flight flight, String passengerName) {
        this.flight = flight;
        this.passengerName = passengerName;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getPassengerName() {
        return passengerName;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "flight=" + flight +
                ", passengerName='" + passengerName + '\'' +
                '}';
    }
}

class FlightReservationSystem {
    private List<Flight> flights = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public List<Flight> searchFlights(String departure, String arrival) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDeparture().equalsIgnoreCase(departure) &&
                    flight.getArrival().equalsIgnoreCase(arrival)) {
                results.add(flight);
            }
        }
        return results;
    }

    public void bookFlight(String flightNumber, String passengerName) throws Exception {
        Flight flight = findFlightByNumber(flightNumber);
        if (flight != null) {
            flight.bookSeat();
            Reservation reservation = new Reservation(flight, passengerName);
            reservations.add(reservation);
            System.out.println("Booking confirmed for " + passengerName);
        } else {
            throw new FlightNotFoundException("Flight " + flightNumber + " not found");
        }
    }

    public void cancelReservation(String flightNumber, String passengerName) throws Exception {
        Reservation reservation = findReservation(flightNumber, passengerName);
        if (reservation != null) {
            reservation.getFlight().cancelSeat();
            reservations.remove(reservation);
            System.out.println("Reservation canceled for " + passengerName);
        } else {
            throw new ReservationNotFoundException("Reservation for flight " + flightNumber + " and passenger " + passengerName + " not found");
        }
    }

    private Flight findFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    private Reservation findReservation(String flightNumber, String passengerName) {
        for (Reservation reservation : reservations) {
            if (reservation.getFlight().getFlightNumber().equalsIgnoreCase(flightNumber) &&
                    reservation.getPassengerName().equalsIgnoreCase(passengerName)) {
                return reservation;
            }
        }
        return null;
    }
}

class NoAvailableSeatsException extends Exception {
    public NoAvailableSeatsException(String message) {
        super(message);
    }
}

class FlightNotFoundException extends Exception {
    public FlightNotFoundException(String message) {
        super(message);
    }
}

class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        FlightReservationSystem system = new FlightReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nFlight Reservation System");
            System.out.println("1. Add Flight");
            System.out.println("2. Search Flights");
            System.out.println("3. Book Flight");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addFlight(system, scanner);
                    break;
                case 2:
                    searchFlights(system, scanner);
                    break;
                case 3:
                    bookFlight(system, scanner);
                    break;
                case 4:
                    cancelReservation(system, scanner);
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addFlight(FlightReservationSystem system, Scanner scanner) {
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter departure city: ");
        String departure = scanner.nextLine();
        System.out.print("Enter arrival city: ");
        String arrival = scanner.nextLine();
        System.out.print("Enter total seats: ");
        int totalSeats = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Flight flight = new Flight(flightNumber, departure, arrival, totalSeats);
        system.addFlight(flight);
        System.out.println("Flight added successfully.");
    }

    private static void searchFlights(FlightReservationSystem system, Scanner scanner) {
        System.out.print("Enter departure city: ");
        String departure = scanner.nextLine();
        System.out.print("Enter arrival city: ");
        String arrival = scanner.nextLine();

        List<Flight> flights = system.searchFlights(departure, arrival);
        if (flights.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            System.out.println("Available flights:");
            for (Flight flight : flights) {
                System.out.println(flight);
            }
        }
    }

    private static void bookFlight(FlightReservationSystem system, Scanner scanner) {
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        try {
            system.bookFlight(flightNumber, passengerName);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cancelReservation(FlightReservationSystem system, Scanner scanner) {
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        try {
            system.cancelReservation(flightNumber, passengerName);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
