import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DiscountCalculatorTest {

    @DisplayName("Valid discount calculations (PWC Test Cases TC1–TC5)")
    @ParameterizedTest(name = "[{index}] type={0}, orders={1}, subscribed={2} => {3}%")
    @CsvSource({
        // TC1: NEW   + low(5)   + true   => 5+0+2 = 7%
        "NEW,      5,  true,   7.0",
        // TC2: REGULAR + high(12) + false => 5+3+5 = 13%
        "REGULAR, 12,  false, 13.0",
        // TC3: PREMIUM + low(8)  + false => 5+5   = 10%
        "PREMIUM,  8,  false, 10.0",
        // TC4: PREMIUM + high(15)+ true  => 5+5+5+2 = 17% => capped 15%
        "PREMIUM, 15,  true,  15.0",
        // TC5: REGULAR + low(4)  + true  => 5+3+2 = 10%
        "REGULAR,  4,  true,  10.0"
    })
    void shouldCalculateDiscountCorrectly(
            String customerType,
            int totalOrders,
            boolean isSubscribed,
            double expectedDiscount) {

        double result = DiscountCalculator.calculateDiscount(
                customerType,
                totalOrders,
                isSubscribed);

        assertEquals(expectedDiscount, result, 0.001);
    }

    @DisplayName("TC6 – Infeasible: NEW customer with >= 10 orders throws IllegalArgumentException")
    @ParameterizedTest(name = "[{index}] NEW + orders={0}, subscribed={1} => Exception")
    @CsvSource({
        "10, false",
        "10, true"
    })
    void shouldThrowExceptionForInvalidCases(
            int totalOrders,
            boolean isSubscribed) {

        assertThrows(IllegalArgumentException.class, () ->
            DiscountCalculator.calculateDiscount("NEW", totalOrders, isSubscribed)
        );
    }
}