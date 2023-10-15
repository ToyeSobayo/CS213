package P2;


import java.util.Scanner;

public class TransactionManager {

    private AccountDatabase db = new AccountDatabase();
    private Scanner scanner = new Scanner(System.in);

    public void run() {

        System.out.println("Transaction Manager is running\n");

        while (scanner.hasNext()) {
            String command = scanner.next();
            String input;

            switch (command) {
                case "O":
                    input = scanner.nextLine();
                    openAccount(input);
                    break;

                case "C":
                    input = scanner.nextLine();
                    Account account = verifyThenClose(input);

                    if (account == null) {
                        break;
                    }

                    String accountString = account.toString();
                    if (db.containsExact(account)) {
                        db.close(account);
                        System.out.println(account.toString() + " has been closed.");
                    }

                    else {
                        System.out.println(accountString + " is not in the database.");
                    }

                    break;

                case "D":
                    input = scanner.nextLine();
                    account = verifyThenDeposit(input);

                    if (account == null) {
                        break;
                    }

                    if (db.contains(account) && db.containsExact(account) ) {
                        db.deposit(account);
                        System.out.println(account.toString() + " Deposit - balance updated.");
                    }
                    else {
                        System.out.println(account.toString() + " is not in the database.");
                    }


                    break;

                case "W":
                    input = scanner.nextLine();
                    account = verifyThenDeposit(input);

                    if (account == null) {
                        break;
                    }

                    if (db.contains(account) && db.containsExact(account) ) {
                        db.withdraw(account);
                        System.out.println(account.toString() + " Withdraw - balance updated.");
                    }
                    else {
                        System.out.println(account.toString() + " is not in the database.");
                    }


                    break;

                case "P":
                    if (db.getNumAcct() > 0) {
                        db.printSorted();
                    }
                    else {
                       System.out.println("Account Database is empty!");
                    }
                    break;

                case "PI":
                    if (db.getNumAcct() > 0) {
                        db.printFeesAndInterests();
                    }
                    else {
                        System.out.println("Account Database is empty!");
                    }

                    break;

                case "UB":
                    if (db.getNumAcct() > 0) {
                        db.printUpdatedBalances();
                    }
                    else {
                        System.out.println("Account Database is empty!");
                    }

                    break;

                case "Q":
                    System.out.println("Transaction manager is terminated.");
                    System.exit(1);

                default:
                    System.out.println("Invalid command!");
                    scanner.nextLine();
                    break;

            }
        }

    }

    private Account verifyThenOpen(String input) {
        Account account = null;

        String[] accountParts = input.strip().split("\\s+");

        if (accountParts.length < 5) {
            System.out.println("Missing data for opening an account.");
            return null;
        }
        String type = accountParts[0];

        String fname = accountParts[1];
        String lname = accountParts[2];
        String date = accountParts[3];

        String[] dobParts = date.split("/");
        int month = Integer.parseInt(dobParts[0]);
        int day = Integer.parseInt(dobParts[1]);
        int year = Integer.parseInt(dobParts[2]);

        double balance;

        try {
            balance = Double.parseDouble(accountParts[4]);
            if (balance <= 0) {
                System.out.println("Initial deposit cannot be 0 or negative.");
                return null;
            }
        }

        catch (NumberFormatException e){
            System.out.println("Not a valid amount.");
            return null;
        }

        Date dob = new Date(month, day, year);

        if (!dob.isValid()) {
            System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
            return null;
        }

        if (dob.isFuture()) {
            System.out.println("DOB invalid: " + dob.toString() + " cannot be today or a future day.");
            return null;
        }

        if (dob.underSixteen()) {
            System.out.println("DOB invalid: " + dob.toString() + " under 16.");
            return null;
        }

        Profile profile = new Profile(fname, lname, dob);

        if (type.equals("C")) {
            account = new Checking(profile, balance);
        }

        else if (type.equals("CC")) {
            if (accountParts.length < 6) {
                System.out.println("Missing data for opening an account.");
                return null;
            }

            int cc = Integer.parseInt(accountParts[5]);

            Campus campus = findCampus(cc);

            if (campus == null) {
                System.out.println("Invalid campus code.");
                return null;
            }

            if (dob.overTwentyFour()) {
                System.out.println("DOB invalid: " + dob.toString() + " over 24.");
                return null;
            }

            account = new CollegeChecking(profile, balance, campus);


        }

        else if (type.equals("S")) {
            if (accountParts.length < 6) {
                System.out.println("Missing data for opening an account.");
            }

            int loyaltyCode = Integer.parseInt(accountParts[5]);

            boolean isLoyal = loyaltyCode == 1;

            account = new Savings(profile, balance, isLoyal);

        }

        else if (type.equals("MM")) {
            if (balance < 2000) {
                System.out.println("Minimum of $2000 to open a Money Market account.");
                return null;
            }

            account = new MoneyMarket(profile, balance, true);

        }

        return account;
    }

    private Account verifyThenClose(String input) {
        Account account = null;

        String[] accountParts = input.strip().split("\\s+");

        int TOKENS = 4;

        if (accountParts.length < TOKENS) {
            System.out.println("Missing data for closing an account.");
            return null;
        }
        String type = accountParts[0];

        String fname = accountParts[1];
        String lname = accountParts[2];
        String date = accountParts[3];

        String[] dobParts = date.split("/");
        int month = Integer.parseInt(dobParts[0]);
        int day = Integer.parseInt(dobParts[1]);
        int year = Integer.parseInt(dobParts[2]);

        Date dob = new Date(month, day, year);

        if (!dob.isValid()) {
            System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
            return null;
        }

        if (dob.isFuture()) {
            System.out.println("DOB invalid: " + dob.toString() + " cannot be today or a future day.");
            return null;
        }

        if (dob.underSixteen()) {
            System.out.println("DOB invalid: " + dob.toString() + " under 16.");
            return null;
        }

        Profile profile = new Profile(fname, lname, dob);

        if (type.equals("C")) {
            account = new Checking(profile);
        }

        else if (type.equals("CC")) {
            if (accountParts.length < TOKENS) {
                System.out.println("Missing data for closing an account.");
                return null;
            }

            if (dob.overTwentyFour()) {
                System.out.println("DOB invalid: " + dob.toString() + " over 24.");
                return null;
            }

            account = new CollegeChecking(profile);

        }

        else if (type.equals("S")) {
            if (accountParts.length < TOKENS) {
                System.out.println("3Missing data for closing an account.");
                return null;
            }

            account = new Savings(profile);

        }

        else if (type.equals("MM")) {
            account = new MoneyMarket(profile);
        }

        return account;
    }

    private Account verifyThenDeposit(String input) {
        Account account = null;

        String[] accountParts = input.strip().split("\\s+");

        if (accountParts.length < 5) {
            System.out.println("Missing data for opening an account.");
            return null;
        }
        String type = accountParts[0];

        String fname = accountParts[1];
        String lname = accountParts[2];
        String date = accountParts[3];

        String[] dobParts = date.split("/");
        int month = Integer.parseInt(dobParts[0]);
        int day = Integer.parseInt(dobParts[1]);
        int year = Integer.parseInt(dobParts[2]);

        double balance;

        try {
            balance = Double.parseDouble(accountParts[4]);
            if (balance <= 0) {
                System.out.println("Deposit - amount cannot be 0 or negative.");
                return null;
            }
        }

        catch (NumberFormatException e){
            System.out.println("Not a valid amount.");
            return null;
        }

        Date dob = new Date(month, day, year);

        if (!dob.isValid()) {
            System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
            return null;
        }

        if (dob.isFuture()) {
            System.out.println("DOB invalid: " + dob.toString() + " cannot be today or a future day.");
            return null;
        }

        if (dob.underSixteen()) {
            System.out.println("DOB invalid: " + dob.toString() + " under 16.");
            return null;
        }

        Profile profile = new Profile(fname, lname, dob);

        if (type.equals("C")) {
            account = new Checking(profile, balance);
        }

        else if (type.equals("CC")) {
            account = new CollegeChecking(profile, balance);
        }

        else if (type.equals("S")) {

            account = new Savings(profile, balance);

        }

        else if (type.equals("MM")) {

            account = new MoneyMarket(profile, balance);

        }

        return account;
    }

    private Account verifyThenWithdraw(String input) {
        Account account = null;

        String[] accountParts = input.strip().split("\\s+");

        if (accountParts.length < 5) {
            System.out.println("Missing data for opening an account.");
            return null;
        }
        String type = accountParts[0];

        String fname = accountParts[1];
        String lname = accountParts[2];
        String date = accountParts[3];

        String[] dobParts = date.split("/");
        int month = Integer.parseInt(dobParts[0]);
        int day = Integer.parseInt(dobParts[1]);
        int year = Integer.parseInt(dobParts[2]);

        double balance;

        try {
            balance = Double.parseDouble(accountParts[4]);
            if (balance <= 0) {
                System.out.println("Deposit - amount cannot be 0 or negative.");
                return null;
            }
        }

        catch (NumberFormatException e){
            System.out.println("Not a valid amount.");
            return null;
        }

        Date dob = new Date(month, day, year);

        if (!dob.isValid()) {
            System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
            return null;
        }

        if (dob.isFuture()) {
            System.out.println("DOB invalid: " + dob.toString() + " cannot be today or a future day.");
            return null;
        }

        if (dob.underSixteen()) {
            System.out.println("DOB invalid: " + dob.toString() + " under 16.");
            return null;
        }

        Profile profile = new Profile(fname, lname, dob);

        if (type.equals("C")) {
            account = new Checking(profile, balance);
        }

        else if (type.equals("CC")) {
            account = new CollegeChecking(profile, balance);
        }

        else if (type.equals("S")) {

            account = new Savings(profile, balance);

        }

        else if (type.equals("MM")) {

            account = new MoneyMarket(profile, balance);

        }

        return account;
    }

    private void openAccount(String input) {
        Account account = verifyThenOpen(input);

        if (account == null) {
            return;
        }

        if (db.open(account)) {
            System.out.println(account.toString() + " opened.");
        }

        else {
            System.out.println(account.toString() + " is already in the database.");
        }
    }

    private Campus findCampus(int campus) {
        for (Campus c : Campus.values()) {
            if (c.getCode() == campus) {
                return c;
            }
        }

        return null;
    }

}
