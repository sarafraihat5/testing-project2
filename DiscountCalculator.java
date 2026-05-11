public class DiscountCalculator {

    public static double calculateDiscount(String customerType, int totalOrdersInLastYear, boolean isSubscribedToNewsletter) {
        // 1. Infeasible Constraint
        if (customerType.equalsIgnoreCase("NEW") && totalOrdersInLastYear >= 10) {
            throw new IllegalArgumentException("NEW customers cannot have 10 or more orders.");
        }

        double discount = 5.0; // Base Discount

        // 2. Subscription Bonus
        if (isSubscribedToNewsletter) {
            discount += 2.0;
        }

        // 3. Customer Type Bonus
        if (customerType.equalsIgnoreCase("REGULAR")) {
            discount += 3.0;
        } else if (customerType.equalsIgnoreCase("PREMIUM")) {
            discount += 5.0;
        }

        // 4. Loyalty Bonus
        if (totalOrdersInLastYear >= 10) {
            discount += 5.0;
        }

        // 5. Maximum Cap (15%)
        return Math.min(discount, 15.0);
    }
}