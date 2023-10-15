package P2;

public class MoneyMarket extends Savings {


    private static final double ANNUAL_INTEREST_RATE = 0.045;
    private static final double FEE = 10.0;
    private static final double MINIMUM_DEPOSIT = 2000;
    private static final double WITHDRAWAL_THRESHOLD = 3;
    private int withdrawal;

    /**
     * Constructor for an account.
     *
     * @param holder  The profile of the account holder.
     * @param balance The starting balance of the account.
     */
    public MoneyMarket(Profile holder, double balance, boolean isLoyal) {
        super(holder, balance, isLoyal);
        this.withdrawal = 0;
    }

    public MoneyMarket(Profile holder) {
        super(holder);
        this.withdrawal = 0;
    }

    public MoneyMarket(Profile holder, double balance) {
        super(holder, balance);
        this.withdrawal = 0;
    }


    public double monthlyInterest() {
        return ((ANNUAL_INTEREST_RATE + BONUS) / MONTH);
    }

    public double monthlyFee() {
        if (balance >= MINIMUM_DEPOSIT) {
            return 0;
        }
        return MONTHLY_FEE;
    }

    public void updateLoyaltyStatus() {
        if (this.balance < MINIMUM_DEPOSIT) {
            this.isLoyal = false;
        }
    }



    public double withdrawalCount() {
        if (withdrawal > WITHDRAWAL_THRESHOLD) {
            return FEE;
        }
        return withdrawal;
    }




    public String getProfileType() {
        return "Money Market";
    }



    @Override
    public String toString() {
        return this.holder.toString() + "(MM)";
    }

    public void resetWithdrawals() {
        withdrawal = 0;
    }
}
