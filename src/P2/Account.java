package P2;

public abstract class Account implements Comparable<Account> {

    protected Profile holder;
    protected  double balance;

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
    public  abstract double monthlyFee();

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

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double value) {
        this.balance += value;
    }




}
