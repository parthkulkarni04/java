package RentalManager;
public class Bicycle implements Rentable {
    private boolean isRented;
    private static final double RATE_PER_HOUR = 5.0;

    @Override
    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println("Bicycle has been rented.");
        } else {
            System.out.println("Bicycle is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Bicycle has been returned.");
        } else {
            System.out.println("Bicycle was not rented.");
        }
    }

    @Override
    public double calculateRentalCost(int rentalDuration) {
        return rentalDuration * RATE_PER_HOUR;
    }
}
