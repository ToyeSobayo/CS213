package P2;

/**
 * Represents a college checking account, which is a type of checking account.
 * Contains information and methods specific to a college checking account.
 * Inherits from the Checking class.
 * Constants for interest rate and fee for College Checking accounts are included.
 *
 * @author [Toye Sobayo]
 */
public class CollegeChecking extends Checking {

    private static final double INTEREST_RATE_COLLEGE_CHECKING = 0.01;
    private static final double FEE_COLLEGE_CHECKING = 0;

    private Campus campus;

    /**
     * Constructor for a college checking account.
     *
     * @param holder  The profile of the account holder.
     * @param balance The starting balance of the account.
     * @param campus  The campus associated with the college checking account.
     */
    public CollegeChecking(Profile holder, double balance, Campus campus) {
        super(holder, balance);
        this.campus = campus;
    }

    /**
     * Alternate constructor for a college checking account without a specific balance.
     *
     * @param holder The profile of the account holder.
     */
    public CollegeChecking(Profile holder) {
        super(holder);
    }

    /**
     * Alternate constructor for a college checking account with a specific balance.
     *
     * @param holder  The profile of the account holder.
     * @param balance The starting balance of the account.
     */
    public CollegeChecking(Profile holder, double balance) {
        super(holder, balance);
    }

    /**
     * Calculates the monthly interest for the college checking account.
     *
     * @return The monthly interest.
     */
    @Override
    public double monthlyInterest() {
        return balance * INTEREST_RATE_COLLEGE_CHECKING / MONTH;
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

    /**
     * Retrieves the campus associated with the college checking account.
     *
     * @return The campus of the account.
     */
    public String getCampus() {
        return this.campus.toString();
    }

    /**
     * Retrieves the profile type of the college checking account.
     *
     * @return The type of the account, which is "College Checking".
     */
    @Override
    public String getProfileType() {
        return "College Checking";
    }

    /**
     * Provides a string representation of the college checking account.
     *
     * @return A string representation of the account in the format "Profile(CC)".
     */
    @Override
    public String toString() {
        return this.holder.toString() + "(CC)";
    }

    /**
     * Provides a string representation of the college checking account without the account type.
     *
     * @return A string representation of the account without the type.
     */
    @Override
    public String toStringNoType() {
        return this.holder.toString();
    }
}
