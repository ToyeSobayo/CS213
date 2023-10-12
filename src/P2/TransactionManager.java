package P2;

import java.util.Scanner;

public class TransactionManager {

    private AccountDatabase db = new AccountDatabase();

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Transaction Manager is running\n");

        while (scanner.hasNext()) {
            String command = scanner.next();

            switch (command) {
                case "O":
                    String type = scanner.next();

                    if (type.equals("C")) {
                        String tokens = scanner.nextLine();
                        System.out.println(tokens);
                    }

                    else if (type.equals("CC")) {

                    }

                    else if (type.equals("S")) {

                    }

                    else if (type.equals("MM")) {

                    }

                case "C":
                    break;

                case "D":
                    break;

                case "W":
                    break;

                case "P":
                    System.out.println("print");
                    break;

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
}
