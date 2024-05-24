package RentalManager;
public class Car implements Rentable {
    private boolean isRented;
    private static final double RATE_PER_HOUR = 20.0;

    @Override
    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println("Car has been rented.");
        } else {
            System.out.println("Car is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Car has been returned.");
        } else {
            System.out.println("Car was not rented.");
        }
    }

    @Override
    public double calculateRentalCost(int rentalDuration) {
        return rentalDuration * RATE_PER_HOUR;
    }
}
