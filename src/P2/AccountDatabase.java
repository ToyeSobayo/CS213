package P2;

import java.text.DecimalFormat;

/**
 * Represents a database for managing accounts, providing operations for handling account information.
 * Enables adding, removal, and retrieval, as well as sorting and tracking of accounts
 * @author [Sean Thomas]
 */
public class AccountDatabase {

    private Account [] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array

    /**
     * Initializes an AccountDatabase with a default capacity of 4 accounts.
     */
    public AccountDatabase() {
        this.accounts = new Account[4];
        this.numAcct = 0;
    }

    /**
     * Searches for an account in the array.
     *
     * @param account The account to search for.
     * @return The index of the account if found, otherwise -1.
     */
    private int find(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if (this.accounts[i].getClass() == account.getClass() & this.accounts[i].compareTo(account) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Increases the capacity of the accounts array by 4.
     */
    private void grow() {
        Account[] newAccounts = new Account[this.accounts.length + 4];

        for (int i = 0; i < this.numAcct; i++) {
            newAccounts[i] = this.accounts[i];
        }

        this.accounts = newAccounts;
    }

    /**
     * Retrieves the number of accounts in the database.
     *
     * @return The number of accounts.
     */
    public int getNumAcct() {
        return this.numAcct;
    }

    /**
     * Checks if the given account exists in the database.
     *
     * @param account The account to check for.
     * @return true if the account exists, otherwise false.
     */
    public boolean contains(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if (account instanceof Checking) {
                if ((this.accounts[i] instanceof Checking
                        || this.accounts[i] instanceof CollegeChecking)
                        && this.accounts[i].compareTo(account) == 0) {
                    return true;
                }
            }

            else if (account instanceof CollegeChecking) {
                if ((this.accounts[i] instanceof CollegeChecking
                        || this.accounts[i] instanceof Checking)
                        && this.accounts[i].compareTo(account) == 0) {
                    return true;
                }
            }

            else {
                if (this.accounts[i].getClass() == account.getClass()
                        && this.accounts[i].compareTo(account) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the given account exists exactly as it is in the database.
     *
     * @param account The account to check for.
     * @return true if the exact account exists, otherwise false.
     */
    public boolean containsExact(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if (this.accounts[i].getClass() == account.getClass()
                    && this.accounts[i].compareTo(account) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new account to the database.
     *
     * @param account The account to add.
     * @return true if the account was added, otherwise false.
     */
    public boolean open(Account account) {
        if (contains(account)) {
            return false;
        }

        this.accounts[this.numAcct] = account;
        this.numAcct += 1;

        if (this.numAcct == this.accounts.length) {
            grow();
        }

        return true;
    }

    /**
     * Removes the given account from the database.
     *
     * @param account The account to remove.
     * @return true if the account was successfully removed, otherwise false.
     */
    public boolean close(Account account) {
        if (!contains(account)) {
            return false;
        }
        int index = find(account);

        if (index == -1) {
            return false;
        }

        for (int i = index; i < this.numAcct - 1; i++) {
            this.accounts[i] = this.accounts[i+1];
        }

        this.numAcct -= 1;

        return true;

    }

    /**
     * Withdraws a certain amount from the specified account.
     *
     * @param account The account to withdraw from.
     * @return true if the withdrawal was successful, otherwise false.
     */
    public boolean withdraw(Account account) {
        int index = find(account);

        if (index < 0) {
            return false;
        }

        Account selectedAccount = this.accounts[index];
        if (selectedAccount.getBalance() < account.getBalance()) {
            return false;
        }

        selectedAccount.setBalance(-account.getBalance());
        if (selectedAccount instanceof MoneyMarket) {
            ((MoneyMarket) selectedAccount).incrementWithdrawal();
            ((MoneyMarket) selectedAccount).updateLoyaltyStatus();
        }

        return true;
    }

    /**
     * Deposits a certain amount into the specified account.
     *
     * @param account The account to deposit into.
     */
    public void deposit(Account account) {
        int index = find(account);

        Account selectedAccount = this.accounts[index];
        selectedAccount.setBalance(account.getBalance());
        if (selectedAccount instanceof MoneyMarket) {
            ((MoneyMarket) selectedAccount).updateLoyaltyStatus();
        }
    }

    /**
     * Determines if two accounts should be swapped, helper method for bubble sort.
     *
     * @param a1 The first account.
     * @param a2 The second account.
     * @return true if the accounts should be swapped, otherwise false.
     */
    private boolean shouldSwap(Account a1, Account a2) {
        //type comparison
        String profileType1 = a1.getProfileType();
        String profileType2 = a2.getProfileType();
        int typeComparison = profileType1.compareToIgnoreCase(profileType2);
        if(typeComparison != 0) {
            return typeComparison > 0;
        }
        //last name comparison
        Profile profile1 = a1.holder;
        Profile profile2 = a2.holder;

        int lastNameComparison = profile1.getlname().compareToIgnoreCase(profile2.getlname());
        if (lastNameComparison != 0) {
            return lastNameComparison > 0;
        }

        int firstNameComparison = profile1.getfname().compareToIgnoreCase(profile2.getfname());
        if (firstNameComparison != 0) {
            return  firstNameComparison > 0;
        }

        int dobComparison = profile1.getDob().compareTo(profile2.getDob());
        return dobComparison > 0;
    }

    /**
     * Prints the accounts sorted by account type and profile.
     */
    public void printSorted(){
        // Bubble sort
        for (int i = 0; i < numAcct - 1; i++) {
            for (int j = 0; j < numAcct - i - 1; j++) {
                if (shouldSwap(accounts[j], accounts[j + 1])) {
                    // Swap accounts[j + 1] and accounts[j]
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                }
            }
        }

        System.out.println("\n*Accounts sorted by account type and profile.");

        DecimalFormat df = new DecimalFormat("$#,##0.00");

        for (int i = 0; i < numAcct; i++) {
            Account currentAccount = accounts[i];

            if (currentAccount instanceof CollegeChecking) {
                System.out.println(currentAccount.getProfileType() + "::" + currentAccount.toStringNoType() + "::Balance "
                        + df.format(currentAccount.getBalance()) + "::" + ((CollegeChecking) currentAccount).getCampus());
            }

            else if (currentAccount instanceof Checking) {
                System.out.println(currentAccount.getProfileType() + "::" + currentAccount.toStringNoType() + "::Balance "
                        + df.format(currentAccount.getBalance()));
            }

            else if (currentAccount instanceof MoneyMarket) {
                System.out.println(currentAccount.getProfileType() + "::Savings::" + currentAccount.toStringNoType() + "::Balance "
                        + df.format(currentAccount.getBalance()) +
                        (((MoneyMarket) currentAccount).isLoyal ? "::is loyal" : "") +
                        "::withdrawal: " + (int)((MoneyMarket) currentAccount).withdrawalCount());
            }
            else if (currentAccount instanceof Savings) {
                System.out.println(currentAccount.getProfileType() + "::" + currentAccount.toStringNoType() + "::Balance "
                        + df.format(currentAccount.getBalance()) +
                        (((Savings) currentAccount).isLoyal ? "::is loyal" : ""));
            }
        }
        System.out.println("*end of list.\n");
    }

    /**
     * Prints the accounts with their fees and monthly interests.
     */
    public void printFeesAndInterests() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        // Bubble sort
        for (int i = 0; i < numAcct - 1; i++) {
            for (int j = 0; j < numAcct - i - 1; j++) {
                if (shouldSwap(accounts[j], accounts[j + 1])) {
                    // Swap accounts[j + 1] and accounts[j]
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                }
            }
        }
        System.out.println("*list of accounts with fee and monthly interest");

    for (int i = 0; i < numAcct; i++) {
        Account currentAccount = accounts[i];
        double montlyInterest = currentAccount.monthlyInterest();
        double monthlyFee = currentAccount.monthlyFee();

        // Display fees and interests with 2 decimal places
        String formattedInterest = df.format(montlyInterest);
        String formattedFee = df.format(monthlyFee);

        if (currentAccount instanceof CollegeChecking) {
            System.out.println(currentAccount.getProfileType() + "::" + currentAccount.toStringNoType() +
                    "::Balance " + df.format(currentAccount.getBalance()) + "::" +
                    ((CollegeChecking) currentAccount).getCampus() +
                    "::fee " + formattedFee + "::monthly interest " + formattedInterest);
        } else if (currentAccount instanceof Checking) {
            System.out.println(currentAccount.getProfileType() + "::" + currentAccount.toStringNoType() +
                    "::Balance " + df.format(currentAccount.getBalance()) +
                    "::fee " + formattedFee + "::monthly interest " + formattedInterest);
        } else if (currentAccount instanceof MoneyMarket) {
            System.out.println(currentAccount.getProfileType() + "::Savings::" + currentAccount.toStringNoType() +
                    "::Balance " + df.format(currentAccount.getBalance()) +
                    (((MoneyMarket) currentAccount).isLoyal ? "::is loyal" : "") +
                    "::withdrawal: " + (int) ((MoneyMarket) currentAccount).withdrawalCount() +
                    "::fee " + formattedFee + "::monthly interest " + formattedInterest);
        } else if (currentAccount instanceof Savings) {
            System.out.println(currentAccount.getProfileType() + "::" + currentAccount.toStringNoType() +
                    "::Balance " + df.format(currentAccount.getBalance()) +
                    (((Savings) currentAccount).loyaltyStatus() ? "::is loyal" : "") +
                    "::fee " + formattedFee + "::monthly interest " + formattedInterest);
        }
    }
    System.out.println("*end of list.");

    }

    /**
     * Prints the accounts with applied fees and interests.
     */
    public void printUpdatedBalances() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        System.out.println("*list of accounts with fees and interests applied.");
        for (int i = 0; i < numAcct; i++) {
            Account currentAccount = accounts[i];
            double monthlyInterest = currentAccount.monthlyInterest();
            double monthlyFee = currentAccount.monthlyFee();

            currentAccount.setBalance(-monthlyFee);
            currentAccount.setBalance(monthlyInterest);

            if (currentAccount instanceof CollegeChecking) {
                System.out.println(currentAccount.getProfileType() + "::" + currentAccount.toStringNoType() + "::Balance "
                        + df.format(currentAccount.getBalance()) + "::" + ((CollegeChecking) currentAccount).getCampus());
            }

            else if (currentAccount instanceof Checking) {
                System.out.println(currentAccount.getProfileType() + "::" + currentAccount.toStringNoType() + "::Balance "
                        + df.format(currentAccount.getBalance()));
            }

            else if (currentAccount instanceof MoneyMarket) {
                // resetting the number of Withdrawls for MMS
                ((MoneyMarket) currentAccount).resetWithdrawals();

                System.out.println(currentAccount.getProfileType() + "::Savings::" + currentAccount.toStringNoType() + "::Balance "
                        + df.format(currentAccount.getBalance()) +
                        (((MoneyMarket) currentAccount).isLoyal ? "::is loyal" : "") +
                        "::withdrawal: " + (int)((MoneyMarket) currentAccount).withdrawalCount());
            }

            else if (currentAccount instanceof Savings) {
                System.out.println(currentAccount.getProfileType() + "::" + currentAccount.toStringNoType() + "::Balance "
                        + df.format(currentAccount.getBalance()) +(
                        ((Savings) currentAccount).loyaltyStatus() ? "::is loyal" : ""));
            }
        }
        System.out.println("*end of list.");
    }
}
