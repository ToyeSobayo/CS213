package P2;

public class AccountDatabase {
    private Account [] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array

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
    } //increase the capacity by 4

    public boolean contains(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if (this.accounts[i].compareTo(account) > 0) {
                return true
            }
        }

        return false;
    } //overload if necessary

    public boolean open(Account account) {

    } //add a new account

    public boolean close(Account account) {

    } //remove the given account

    public boolean withdraw(Account account) {

    } //false if insufficient fund

    public void deposit(Account account) {

    }

    public void printSorted(){

    } //sort by account type and profile

    public void printFeesAndInterests() {

    } //calculate interests/fees

    public void printUpdatedBalances() {

    } //apply the interests/fees
}
