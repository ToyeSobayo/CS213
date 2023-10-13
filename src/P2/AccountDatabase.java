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
            if (this.accounts[i].compareTo(account) > 0) {
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

    public boolean contains(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if (this.accounts[i].compareTo(account) == 0) {
                return true;
            }
        }

        return false;
    } //overload if necessary

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

        for (int i = index; i < this.numAcct - 1; i++) {
            this.accounts[i] = this.accounts[i+1];
        }

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

    public void printSorted(){

    } //sort by account type and profile

    public void printFeesAndInterests() {

    } //calculate interests/fees

    public void printUpdatedBalances() {

    } //apply the interests/fees

}
