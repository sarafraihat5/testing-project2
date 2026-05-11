import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DiscountCalculatorTest {

    @ParameterizedTest
    @CsvSource({
        "NEW,5,true,7.0",
        "REGULAR,12,false,13.0",
        "PREMIUM,8,false,10.0",
        "PREMIUM,15,true,15.0",
        "REGULAR,4,true,10.0"
    })
    @DisplayName("Valid discount calculations")
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

    @ParameterizedTest
    @CsvSource({
        "NEW,10,false"
    })
    @DisplayName("Invalid combinations should throw exception")
    void shouldThrowExceptionForInvalidCases(
            String customerType,
            int totalOrders,
            boolean isSubscribed) {

        assertThrows(IllegalArgumentException.class, () -> {
            DiscountCalculator.calculateDiscount(
                    customerType,
                    totalOrders,
                    isSubscribed);
        });
    }
}