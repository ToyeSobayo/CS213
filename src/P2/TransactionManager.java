package P2;


import java.util.Scanner;

public class TransactionManager {

    private AccountDatabase db = new AccountDatabase();
    private Scanner scanner = new Scanner(System.in);

    public void run() {

        System.out.println("Transaction Manager is running\n");

        while (scanner.hasNext()) {
            String command = scanner.next();

            switch (command) {
                case "O":
                    String input = scanner.nextLine();
                    verifyThenOpen(input);

                case "C":
                    break;

                case "D":
                    break;

                case "W":
                    break;

                case "P":
                    db.printSorted();

                case "PI":
                    break;

                case "UB":
                    break;

                case "Q":
                    System.out.println("Transaction manager is terminated.");

                default:
                    System.out.println("Invalid command!");
                    break;

            }
        }

    }

    private void verifyThenOpen(String input) {
        String[] accountParts = input.strip().split("\\s+");

        String type = accountParts[0];

        if (type.equals("C")) {
            if (accountParts.length < 5) {
                System.out.println("Missing data for opening an account.");
                return;
            }

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
                }
            }

            catch (NumberFormatException e){
                System.out.println("Not a valid amount.");
                return;
            }

            Date dob = new Date(month, day, year);

            if (!dob.isValid()) {
                System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
                return;
            }

            if (dob.isFuture()) {
                System.out.println("DOB invalid: " + dob.toString() + " cannot be today or a future day");
                return;
            }

            if (dob.underSixteen()) {
                System.out.println("DOB invalid: " + dob.toString() + " under 16.");
                return;
            }

            Profile profile = new Profile(fname, lname, dob);

            Account account = new Checking(profile, balance);

            if (db.open(account)) {
                System.out.println(account.toString() + " opened.");
            }

            else {
                System.out.println(account.toString() + " is already in the database.");
            }

        }

        else if (type.equals("CC")) {
            if (accountParts.length < 6) {
                System.out.println("Missing data for opening an account.");
                return;
            }

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
                    return;
                }
            }

            catch (NumberFormatException e){
                System.out.println("Not a valid amount.");
                return;
            }

            int cc = Integer.parseInt(accountParts[5]);

            Campus campus = findCampus(cc);

            if (campus == null) {
                System.out.println("Invalid campus code.");
                return;
            }

            Date dob = new Date(month, day, year);

            if (!dob.isValid()) {
                System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
                return;
            }

            if (dob.isFuture()) {
                System.out.println("DOB invalid: " + dob.toString() + " cannot be today or a future day.");
                return;
            }

            if (dob.underSixteen()) {
                System.out.println("DOB invalid: " + dob.toString() + " under 16.");
                return;
            }

            if (dob.overTwentyFour()) {
                System.out.println("DOB invalid: " + dob.toString() + " over 24.");
                return;
            }

            Profile profile = new Profile(fname, lname, dob);

            Account account = new CollegeChecking(profile, balance, campus);

            if (db.open(account)) {
                System.out.println(account.toString() + " opened.");
            }

            else {
                System.out.println(account.toString() + " is already in the database.");
            }


        }

        else if (type.equals("S")) {
            if (accountParts.length < 6) {
                System.out.println("Missing data for opening an account.");
            }

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
                    return;
                }
            }

            catch (NumberFormatException e){
                System.out.println("Not a valid amount.");
                return;
            }

            int loyaltyCode = Integer.parseInt(accountParts[5]);

            boolean isLoyal = loyaltyCode == 1;

            Date dob = new Date(month, day, year);

            if (!dob.isValid()) {
                System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
                return;
            }

            if (dob.isFuture()) {
                System.out.println("DOB invalid: " + dob.toString() + " cannot be today or a future day.");
                return;
            }

            if (dob.underSixteen()) {
                System.out.println("DOB invalid: " + dob.toString() + " under 16.");
                return;
            }


            Profile profile = new Profile(fname, lname, dob);

            Account account = new Savings(profile, balance, isLoyal);

            if (db.open(account)) {
                System.out.println(account.toString() + " opened.");
            }

            else {
                System.out.println(account.toString() + " is already in the database.");
            }
        }

        else if (type.equals("MM")) {
            if (accountParts.length < 5) {
                System.out.println("Missing data for opening an account.");
                return;
            }

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
                    return;
                }

                if (balance < 2000) {
                    System.out.println("Minimum of $2000 to open a Money Market account.");
                    return;
                }
            }

            catch (NumberFormatException e){
                System.out.println("Not a valid amount.");
                return;
            }

            Date dob = new Date(month, day, year);
            if (!dob.isValid()) {
                System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
                return;
            }

            if (dob.isFuture()) {
                System.out.println("DOB invalid: " + dob.toString() + " cannot be today or a future day.");
                return;
            }

            if (dob.underSixteen()) {
                System.out.println("DOB invalid: " + dob.toString() + " under 16.");
                return;
            }

            Profile profile = new Profile(fname, lname, dob);

            Account account = new MoneyMarket(profile, balance, true);

            if (db.open(account)) {
                System.out.println(account.toString() + " opened.");
            }

            else {
                System.out.println(account.toString() + " is already in the database.");
            }
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
