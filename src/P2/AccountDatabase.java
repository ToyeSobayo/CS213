package P2;

public class AccountDatabase {


    private Account [] accounts; //list of various types of accounts


    private int numAcct; //number of accounts in the array

    public AccountDatabase() {
        this.accounts = new Account[4];
        this.numAcct = 0;
    }

    private int find(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if (this.accounts[i].getClass() == account.getClass() & this.accounts[i].compareTo(account) == 0) {
                return i;
            }
        }
        return -1;
    } //search for an account in the array

    private void grow() {
        Account[] newAccounts = new Account[this.accounts.length + 4];

        for (int i = 0; i < this.numAcct; i++) {
            newAccounts[i] = this.accounts[i];
        }

        this.accounts = newAccounts;
    } //increase the capacity by 4

    public int getNumAcct() {
        return this.numAcct;
    }
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
    } //overload if necessary

    public boolean containsExact(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if (this.accounts[i].getClass() == account.getClass()
                    && this.accounts[i].compareTo(account) == 0) {
                return true;
            }
        }
        return false;
    }

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
    } //add a new account

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

    } //remove the given account

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

        return true;
    } //false if insufficient fund

    public void deposit(Account account) {
        int index = find(account);

        Account selectedAccount = this.accounts[index];
        selectedAccount.setBalance(account.getBalance());
    }

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
        // print the sorted accounts
        System.out.println("\n*Accounts sorted by account type and profile.");
        for (int i = 0; i < numAcct; i++) {
            Account currentAccount = accounts[i];
            String accountInfo = currentAccount.getClass().getSimpleName() + "::" + currentAccount.toString() + " :: Balance " + String.format("%.2s", currentAccount.getBalance());

            if (currentAccount instanceof  CollegeChecking) {
                CollegeChecking collegeAccount = (CollegeChecking) currentAccount;
                accountInfo += "::" + collegeAccount.getCampus();
            } else if (currentAccount instanceof  MoneyMarket) {
                MoneyMarket moneyMarketAccount = (MoneyMarket) currentAccount;
                accountInfo += "::" + (moneyMarketAccount.loyaltyStatus() ? "is loyal" : "not loyal") + ":: withdrawal: " + moneyMarketAccount.withdrawalCount();
            } else if (currentAccount instanceof Savings ){
                Savings savingsAccount = (Savings) currentAccount;
                accountInfo += savingsAccount.loyaltyStatus() ? "::is loyal" : "";
            }
            System.out.println(accounts[i].getClass() + "::" + accounts[i].toString() + ":: Balance " + accounts[i].getBalance());
        }
        System.out.println("*end of list.\n");
    }

    //sort by account type and profile

    public void printFeesAndInterests() {
        System.out.println("*list of accounts with fee and monthly interest");
    for (int i = 0; i < numAcct; i++) {
        Account currentAccount = accounts[i];
        double montlyInterest = currentAccount.monthlyInterest();
        double monthlyFee = currentAccount.monthlyFee();

        // Display fees and interests with 2 decimal places
        String formattedInterest = String.format("% 2f", montlyInterest);
        String formattedFee = String.format(".2f", monthlyFee);

        String accountInfo = currentAccount.toString();

        // Add with fee and monthly interest
        System.out.println(accountInfo + ":: fee $" + formattedFee + "::monthly interest $" + formattedInterest);
    }
    System.out.println("*end of list.");

    } //calculate interests/fees

    public void printUpdatedBalances() {
        for (int i = 0; i < numAcct; i++) {
            Account currentAccount = accounts[i];
            double monthlyInterest = currentAccount.monthlyInterest();
            double monthlyFee = currentAccount.monthlyFee();

            currentAccount.setBalance(-monthlyFee);
            currentAccount.setBalance(monthlyInterest);

            if (currentAccount instanceof MoneyMarket) {
                // resetting the number of Withdrawls for MMS
                ((MoneyMarket) currentAccount).resetWithdrawals();
            }
        }

        //Update balance by applying fees and interests

    } //apply the interests/fees

}
