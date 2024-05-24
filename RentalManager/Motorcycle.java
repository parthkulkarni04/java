package RentalManager;
public class Motorcycle implements Rentable {
    private boolean isRented;
    private static final double RATE_PER_HOUR = 15.0;

    @Override
    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println("Motorcycle has been rented.");
        } else {
            System.out.println("Motorcycle is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Motorcycle has been returned.");
        } else {
            System.out.println("Motorcycle was not rented.");
        }
    }

    @Override
    public double calculateRentalCost(int rentalDuration) {
        return rentalDuration * RATE_PER_HOUR;
    }
}
