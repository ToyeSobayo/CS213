package P2;

/**
 * Represents a money market account.
 * Extends the Savings class and adds functionality specific to money market accounts.
 * Tracks the number of withdrawals and updates the loyalty status accordingly.
 * Calculates monthly interest and fees for the account.
 * Allows for resetting the withdrawal count.
 *
 * @author [Toye Sobayo]
 */
public class MoneyMarket extends Savings {

    private static final double ANNUAL_INTEREST_RATE = 0.045;
    private static final double FEE = 10.0;
    private static final double MINIMUM_DEPOSIT = 2000;
    private static final double WITHDRAWAL_THRESHOLD = 3;
    private int withdrawal;

    /**
     * Constructor for a money market account.
     *
     * @param holder  The profile of the account holder.
     * @param balance The starting balance of the account.
     * @param isLoyal Indicates whether the account is loyal or not.
     */
    public MoneyMarket(Profile holder, double balance, boolean isLoyal) {
        super(holder, balance, isLoyal);
        this.withdrawal = 0;
    }

    /**
     * Constructor for a money market account with a specified holder.
     *
     * @param holder The profile of the account holder.
     */
    public MoneyMarket(Profile holder) {
        super(holder);
        this.withdrawal = 0;
    }

    /**
     * Constructor for a money market account with a specified holder and initial balance.
     *
     * @param holder  The profile of the account holder.
     * @param balance The starting balance of the account.
     */
    public MoneyMarket(Profile holder, double balance) {
        super(holder, balance);
        this.withdrawal = 0;
    }

    /**
     * Calculates the monthly interest for the money market account.
     *
     * @return The monthly interest.
     */
    public double monthlyInterest() {
        if (this.isLoyal) {
            return (((double)balance * (ANNUAL_INTEREST_RATE + BONUS)) / MONTH);
        }

        return ((double)balance * ANNUAL_INTEREST_RATE / MONTH);

    }

    /**
     * Calculates the monthly fee for the money market account.
     *
     * @return The monthly fee.
     */
    public double monthlyFee() {
        double fee = 0;

        if (balance < MINIMUM_DEPOSIT) {
            fee += MONTHLY_FEE;
        }
        if (this.withdrawal > WITHDRAWAL_THRESHOLD) {
            fee += FEE;
        }
        return fee;
    }

    /**
     * Updates the loyalty status based on the current balance.
     */
    public void updateLoyaltyStatus() {
        if (this.balance < MINIMUM_DEPOSIT) {
            this.isLoyal = false;
        }

        if (this.balance >= MINIMUM_DEPOSIT) {
            this.isLoyal = true;
        }
    }

    /**
     * Retrieves the number of withdrawals for the money market account.
     *
     * @return The number of withdrawals.
     */
    public double withdrawalCount() {
        return withdrawal;
    }

    /**
     * Increments the withdrawal count for the money market account.
     */
    public void incrementWithdrawal() {
        this.withdrawal += 1;

    }

    /**
     * Retrieves the profile type of the account.
     *
     * @return The profile type.
     */
    public String getProfileType() {
        return "Money Market";
    }

    /**
     * Returns the string representation of the money market account, including the account type.
     *
     * @return The string representation of the account.
     */
    @Override
    public String toString() {
        return this.holder.toString() + "(MM)";
    }

    /**
     * Returns the string representation of the money market account without the account type.
     *
     * @return The string representation of the account without the type.
     */
    @Override
    public String toStringNoType() {
        return this.holder.toString();
    }

    /**
     * Resets the withdrawal count for the money market account.
     */
    public void resetWithdrawals() {
        withdrawal = 0;
    }
}
