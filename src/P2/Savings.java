package P2;

/**
 * Represents a savings account.
 * Extends the Account class and provides functionality specific to savings accounts.
 * Manages the loyalty status of the account holder and calculates monthly interest and fees accordingly.
 * Provides methods for retrieving the account type and generating formatted strings.
 * Implements monthly interest and fee calculations based on the account's balance and loyalty status.
 * Uses constant values for annual interest rates, monthly fees, fee waivers, and bonuses.
 * @author [Toye Sobayo, Sean Thomas]
 */
public class Savings extends Account {

    private static final double ANNUAL_INTEREST_RATES = 0.04;
    private static final double LOYAL_CUSTOMER_INTEREST_RATES = 0.0425;
    protected boolean isLoyal;
    protected static final double MONTHLY_FEE = 25;
    private static final double FEE_WAIVER = 500;
    protected static final double BONUS = 0.0025;

    /**
     * Constructor for an account.
     *
     * @param holder  The profile of the account holder.
     * @param balance The starting balance of the account.
     * @param isLOYAL The loyalty status of the account holder.
     */
    public Savings(Profile holder, double balance, boolean isLOYAL) {
        super(holder, balance);
        this.isLoyal = isLOYAL;
    }

    /**
     * Constructor for an account with a provided profile.
     *
     * @param holder The profile of the account holder.
     */
    public Savings(Profile holder) {
        super(holder);
    }

    /**
     * Retrieves the loyalty status of the account holder.
     *
     * @return The loyalty status of the account holder.
     */
    public boolean loyaltyStatus() {
        return this.isLoyal;
    }

    /**
     * Constructor for an account with a provided profile and balance.
     *
     * @param holder  The profile of the account holder.
     * @param balance The starting balance of the account.
     */
    public Savings(Profile holder, double balance) {
        super(holder, balance);
    }

    /**
     * Calculates the monthly interest based on the current balance and loyalty status.
     *
     * @return The calculated monthly interest.
     */
    @Override
    public double monthlyInterest() {
        double rate = isLoyal ? LOYAL_CUSTOMER_INTEREST_RATES : ANNUAL_INTEREST_RATES;
        return balance * rate / MONTH;
    }

    /**
     * Calculates the monthly fee based on the current balance and fee waiver threshold.
     *
     * @return The calculated monthly fee.
     */
    @Override
    public double monthlyFee() {
        if (balance >= FEE_WAIVER) {
            return 0;
        }
        return MONTHLY_FEE;
    }

    /**
     * Retrieves the type of the account.
     *
     * @return The type of the account as a string.
     */
    public String getProfileType() {
        return "Savings";
    }

    /**
     * Returns a string representation of the account, including the account holder's profile and account type.
     *
     * @return A formatted string representation of the account.
     */
    @Override
    public String toString() {
        return this.holder.toString() + "(S)";
    }

    /**
     * Returns a string representation of the account holder's profile without the account type.
     *
     * @return A formatted string representation of the account holder's profile.
     */
    @Override
    public String toStringNoType() {
        return this.holder.toString();
    }

}
