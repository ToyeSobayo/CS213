package P2;

/**
 * Represents a savings account.
 * Extends the Account class and adds functionality specific to savings accounts.
 * Tracks whether the account holder is a loyal customer or not.
 * Calculates monthly interest and fees for the account based on the loyalty status.
 * Provides methods to retrieve the profile type and a string representation of the account.
 * Allows for setting the account balance and checking the loyalty status.
 *
 * @author [Toye Sobayo]
 */
public abstract class Account implements Comparable<Account> {

    protected Profile holder;
    protected double balance;
    protected int MONTH = 12;

    /**
     * Constructor for an account.
     *
     * @param holder The profile of the account holder.
     * @param balance The starting balance of the account.
     */
    public Account(Profile holder, double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    /**
     * Constructor for an account with just a profile.
     *
     * @param holder The profile of the account holder.
     */
    public Account(Profile holder) {
        this.holder = holder;
    }

    /**
     * Abstract method to calculate the monthly interest.
     * This method needs to be implemented by subclasses.
     *
     * @return The monthly interest for the account.
     */
    public abstract double monthlyInterest();

    /**
     * Abstract method to calculate the monthly fee.
     * This method needs to be implemented by subclasses.
     *
     * @return The monthly fee for the account.
     */
    public abstract double monthlyFee();

    /**
     * Abstract method to convert the account to a string representation.
     *
     * @return A string representation of the account.
     */
    public abstract String toString();

    /**
     * Abstract method to convert the account to a string representation without the account type.
     *
     * @return A string representation of the account without the type information.
     */
    public abstract String toStringNoType();

    /**
     * Abstract method to get the profile type of the account.
     *
     * @return The profile type of the account.
     */
    public abstract String getProfileType();

    /**
     * Compares this account to another account.
     *
     * @param otherAccount The other account to compare to.
     * @return A negative integer, zero, or a positive integer if this account is less than, equal to, or greater than the specified account respectively.
     */

    @Override
    public  int compareTo(Account otherAccount) {
        // Implementation
        return this.holder.compareTo(otherAccount.holder);

    }

    /**
     * Retrieves the balance of the account.
     *
     * @return The balance of the account.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Sets the balance of the account.
     *
     * @param value The value to add to the balance.
     */
    public void setBalance(double value) {
        this.balance += value;
    }






}
