package cinema;

import java.util.Scanner;

public class Cinema {

    private static int boughtTicket = 0;

    private static void instruction() {
        System.out.println("\n" + """
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""");
    }

    private static void buyTicket(int numberOfRows, int seatsInRow, char[][] cinema) {
        Scanner scanner = new Scanner(System.in);
        int chooseRowNumber = 0;
        int chooseSeatNumber = 0;

        while (true) {
            System.out.println("\nEnter a row number:");
            chooseRowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            chooseSeatNumber = scanner.nextInt();

            if (chooseRowNumber < 1 || chooseRowNumber > numberOfRows || chooseSeatNumber < 1 || chooseSeatNumber > seatsInRow) {
                System.out.println("\nWrong input !");
            } else if (cinema[chooseRowNumber][chooseSeatNumber] == 'B') {
                System.out.println("That ticket has already been purchased!\n");
            } else {
                break;
            }
        }

        int ticketPrice = calculateTicketPrice(numberOfRows, seatsInRow, chooseRowNumber);

        System.out.printf("%nTicket price: $%d%n", ticketPrice);
        cinema[chooseRowNumber][chooseSeatNumber] = 'B'; // Mark the seat as booked
        boughtTicket++;
    }

    private static int calculateTicketPrice(int numberOfRows, int seatsInRow, int chooseRowNumber) {
        int totalSeats = numberOfRows * seatsInRow;
        int frontHalfRows = numberOfRows / 2;

        if (totalSeats <= 60 || chooseRowNumber <= frontHalfRows) {
            return 10;
        } else {
            return 8;
        }
    }

    private static void statistics(int boughtTickets, int numberOfRows, int seatsInRow, char[][] cinema) {
        double percentage = (double) boughtTickets / (numberOfRows * seatsInRow) * 100;
        int currentIncome = 0;

        for (int i = 1; i <= numberOfRows; i++) {
            for (int j = 1; j <= seatsInRow; j++) {
                if (cinema[i][j] == 'B') {
                    currentIncome += calculateTicketPrice(numberOfRows, seatsInRow, i);
                }
            }
        }

        int totalIncome;

        if (numberOfRows * seatsInRow <= 60) {
            totalIncome = numberOfRows * seatsInRow * 10;
        } else {
            int frontHalfRows = numberOfRows / 2;
            int backHalfRows = numberOfRows - frontHalfRows;
            totalIncome = (frontHalfRows * seatsInRow * 10) + (backHalfRows * seatsInRow * 8);
        }

        System.out.printf("""
            \nNumber of purchased tickets: %d
            Percentage: %.2f%%
            Current income: $%d
            Total income: $%d%n""", boughtTickets, percentage, currentIncome, totalIncome);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();

        char[][] cinema = new char[numberOfRows + 1][seatsInRow + 1];
        for (int i = 0; i <= numberOfRows; i++) {
            for (int j = 0; j <= seatsInRow; j++) {
                cinema[i][j] = 'S'; // Initialize all seats as empty
            }
        }

        while (true) {
            instruction();
            int choose = scanner.nextInt();

            if (choose == 1) {
                System.out.print("\nCinema:\n  ");
                for (int i = 0; i < numberOfRows + 1; i++) {
                    if (i == 0) {
                        for (int j = 1; j <= seatsInRow; j++) {
                            System.out.print(j + " ");
                        }
                    } else {
                        System.out.printf("%d ", i);
                        for (int j = 1; j <= seatsInRow; j++) {
                            System.out.print(cinema[i][j] + " ");
                        }
                    }
                    System.out.println();
                }
            } else if (choose == 2) {
                buyTicket(numberOfRows, seatsInRow, cinema);
            } else if (choose == 3) {
                statistics(boughtTicket, numberOfRows, seatsInRow, cinema);
            } else if (choose == 0) {
                break;
            }
        }
    }
}
