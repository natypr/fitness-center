package by.naty.fitnesscenter.model.pay;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OrderPayment {

    private static final int numberOfWorkout = 70;
    private static final int costPerOneWorkout = 25;
    private static final double discountOfClient = 0.0;

    private static final double calculatePrice = 1750;
    private static final double calculateNewDiscount = 7;


    //order.numberOfWorkout, order.getIdTrainer, trainer.getCostPerOneWorkout
    @Test
    public void testCalculatePrice() {
        int actual = numberOfWorkout * costPerOneWorkout;
        assertEquals(actual, calculatePrice);
    }

    //client.getDiscount, findDiscountOfThisOrder=numberOfWorkout/10
    @Test
    public void testCalculateNewDiscount() {
        int discountOfThisOrder = numberOfWorkout / 10;

        double actual = discountOfClient + discountOfThisOrder;
        assertEquals(actual, calculateNewDiscount);
    }

    //testCalculatePrice, calculateNewDiscount
    @Test
    public void calculateTotalPrice() {
        double actual = calculatePrice - calculatePrice * calculateNewDiscount / 100;
        assertEquals(actual, 1627.5);
    }
}
