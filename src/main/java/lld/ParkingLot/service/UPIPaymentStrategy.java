package lld.ParkingLot.service;

public class UPIPaymentStrategy implements  PaymentStrategy{
    public boolean pay(int amount){
        System.out.println("Paid "+amount+ " through UPI");
        return true;
    }
}
