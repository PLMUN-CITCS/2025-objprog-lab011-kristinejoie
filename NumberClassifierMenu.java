import java.util.Scanner;

public class NumberClassifierMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    classifyNumber(scanner);
                    break;
                case 2:
                    printMultiplicationTable(scanner);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (running && shouldReturnToMenu(scanner)) {
                continue;
            } else if (running) {
                running = false;
                System.out.println("Exiting the program. Goodbye!");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("=== Number Classifier Menu ===");
        System.out.println("1. Classify a number as positive, negative, or zero");
        System.out.println("2. Print a multiplication table for a given number");
        System.out.println("3. Exit");
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.print("Enter your choice (1-3): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); 
            System.out.print("Enter your choice (1-3): ");
        }
        return scanner.nextInt();
    }

    private static void classifyNumber(Scanner scanner) {
        System.out.print("Enter a number to classify: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); 
            System.out.print("Enter a number to classify: ");
        }
        int number = scanner.nextInt();

        if (number > 0) {
            System.out.println("The number " + number + " is positive.");
        } else if (number < 0) {
            System.out.println("The number " + number + " is negative.");
        } else {
            System.out.println("The number " + number + " is zero.");
        }

        if (number % 2 == 0) {
            System.out.println("It is an even number.");
        } else {
            System.out.println("It is an odd number.");
        }
    }

    private static void printMultiplicationTable(Scanner scanner) {
        System.out.print("Enter a number to display its multiplication table: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); 
            System.out.print("Enter a number to display its multiplication table: ");
        }
        int number = scanner.nextInt();

        System.out.println("Multiplication Table for " + number + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
    }

    private static boolean shouldReturnToMenu(Scanner scanner) {
        System.out.print("Do you want to return to the menu? (Y/N): ");
        String input = scanner.next().trim().toUpperCase();
        return input.equals("Y");
    }
}