package P2;
/**
 * Represents a checking account.
 *
 * @author [Toye Sobayo]
 */
public class Checking extends Account {
    // Constants for interest rate and fee for Checking accounts
    private static final double INTEREST = 0.01;
    private static final double MONTHLY_FEE = 12;
    private  static final double FEE_WAIVER_BALANCE = 1000;




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
     * Calculates the monthly interest for the checking account.
     *
     * @return The monthly interest.
     */

    @Override
    public double monthlyInterest() {
        return balance * INTEREST / 12;
    }

    @Override
    public double monthlyFee() {
        if (balance >= FEE_WAIVER_BALANCE) {
            return 0;
        }
        return MONTHLY_FEE;
    }

    public String getProfileType() {
        return "Checking";
    }

    @Override
    public String toString() {
        return this.holder.toString() + "(C)";
    }

}
