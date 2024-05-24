package RentalManager;
public interface Rentable {
    void rent();
    void returnVehicle();
    double calculateRentalCost(int rentalDuration); // rentalDuration in hours
}
