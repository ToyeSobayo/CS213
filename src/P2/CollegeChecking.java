package P2;

public class CollegeChecking extends Checking {

    private static final double INTEREST_RATE_COLLEGE_CHECKING = 0.01;
    private static final double FEE_COLLEGE_CHECKING = 0;

    // Enum to represent different campuses?
    public enum Campus {
        NEW_BRUNSWICK, NEWARK, CAMDEN
    }

    private Campus campus;

    /**
     * Constructor for College checking account.
     *
     * @param holder  The profile of the account holder.
     * @param balance The starting balance of the account.
     */
    public CollegeChecking(Profile holder, double balance, Campus campus) {
        super(holder, balance);
        this.campus = campus;
    }

    /**
     * Calculates the monthly interest for the college checking account.
     *
     * @return The monthly interest.
     */

    @Override
    public double monthlyInterest() {
        // get rid of 12
        return balance * INTEREST_RATE_COLLEGE_CHECKING / 12;
    }
    /**
     * Calculates the monthly fee for the college checking account.
     *
     * @return The monthly fee.
     */
    @Override
    public double monthlyFee() {
        return FEE_COLLEGE_CHECKING;
    }

    // toString()


}
