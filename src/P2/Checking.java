package P2;

/**
 * Represents a checking account.
 * Contains information and methods related to a checking account.
 * Inherits from the Account class.
 * Constants for interest rate and fee for Checking accounts are included.
 *
 * @author Toye Sobayo
 */
public class Checking extends Account {
    // Constants for interest rate and fee for Checking accounts
    private static final double INTEREST = 0.01;
    private static final double MONTHLY_FEE = 12;
    private static final double FEE_WAIVER_BALANCE = 1000;

    /**
     * Constructor for a checking account.
     *
     * @param holder The profile of the account holder.
     * @param balance The starting balance of the account.
     */
    public Checking(Profile holder, double balance) {
        super(holder, balance);
    }

    /**
     * Alternate constructor for a checking account without a specific balance.
     *
     * @param holder The profile of the account holder.
     */
    public Checking(Profile holder) {
        super(holder);
    }

    /**
     * Calculates the monthly interest for the checking account.
     *
     * @return The monthly interest.
     */
    @Override
    public double monthlyInterest() {
        return balance * INTEREST / 12;
    }

    /**
     * Calculates the monthly fee for the checking account.
     *
     * @return The monthly fee.
     */
    @Override
    public double monthlyFee() {
        if (balance >= FEE_WAIVER_BALANCE) {
            return 0;
        }
        return MONTHLY_FEE;
    }

    /**
     * Retrieves the profile type of the checking account.
     *
     * @return The type of the account, which is "Checking".
     */
    public String getProfileType() {
        return "Checking";
    }

    /**
     * Provides a string representation of the checking account.
     *
     * @return A string representation of the account in the format "Profile(C)".
     */
    @Override
    public String toString() {
        return this.holder.toString() + "(C)";
    }

    /**
     * Provides a string representation of the checking account without the account type.
     *
     * @return A string representation of the account without the type.
     */
    @Override
    public String toStringNoType() {
        return this.holder.toString();
    }
}