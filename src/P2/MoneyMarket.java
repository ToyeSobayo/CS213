package P2;

public class MoneyMarket extends Savings {

    private static final double MONEY_MARKET_SAVING_INTEREST_RATE= 0.0475;
    private static final double ANNNUAL_INTEREST_RATE = 0.045;
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
}
