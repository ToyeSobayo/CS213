package P2;

public class Savings extends Account {

    private static final double ANNUAL_INTEREST_RATES = 0.04;
    private static final double LOYAL_CUSTOMER_INTEREST_RATES = 0.0425;
    protected boolean isLoyal;
    protected static final double MONTHLY_FEE = 25;
    private static final double FEE_WAIVER = 500;
    protected static final double BONUS = 0.025;



    /**
     * Constructor for an account.
     *
     * @param holder  The profile of the account holder.
     * @param balance The starting balance of the account.
     */
    public Savings(Profile holder, double balance, boolean isLOYAL) {
        super(holder, balance);
        this.isLoyal = isLOYAL;
    }

    public Savings(Profile holder) {
        super(holder);
    }

    public boolean loyaltyStatus() {
        return this.isLoyal;
    }
    public Savings(Profile holder, double balance) {
        super(holder, balance);
    }




    @Override
    public double monthlyInterest() {
        double rate = isLoyal ? LOYAL_CUSTOMER_INTEREST_RATES : ANNUAL_INTEREST_RATES;
        return balance * rate / MONTH;
    }

    @Override
    public double monthlyFee() {
        if (balance >= FEE_WAIVER) {
            return 0;
        }
        return MONTHLY_FEE;
    }


    public String getProfileType() {
        return "Savings";
    }
    @Override
    public String toString() {
        return this.holder.toString() + "(S)";
    }

    @Override
    public String toStringNoType() {
        return this.holder.toString();
    }


}
