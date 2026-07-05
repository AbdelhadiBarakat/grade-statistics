import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeStatistics {

    // =========================
    // Overall Statistics (Global State)
    // =========================

    static int overallMin = Integer.MAX_VALUE;
    static int overallMax = Integer.MIN_VALUE;
    static int overallSum = 0;
    static int overallStudentCount = 0;

    public static void main(String[] args) {

        // Program entry point: handles input, processes sections, and prints results

        Scanner scanner = new Scanner(System.in);

        int sectionCount = getMinValidatedInput("Enter the number of sections: ", 1, scanner);

        for (int sectionNumber = 1; sectionNumber <= sectionCount; sectionNumber++) {

            processSection(sectionNumber, scanner);
        }

        printOverallResults();

        scanner.close();
    }

    // -----------------------------
    // Input Validation Methods
    // -----------------------------

    /**
     * Prompts the user until a valid whole number greater than or equal to
     * the specified minimum value is entered.
     *
     * @param prompt   message displayed to the user
     * @param minValue minimum acceptable value
     * @param scanner  Scanner used to read user input
     * @return a validated integer greater than or equal to minValue
     */
    static int getMinValidatedInput(
            String prompt,
            int minValue,
            Scanner scanner
    ) {

        int input;

        while (true) {
            try {
                System.out.print(prompt);

                input = scanner.nextInt();
                scanner.nextLine();

                if (input >= minValue) {
                    return input;
                }
                System.out.printf(
                        "Value must be at least %d.\n\n",
                        minValue
                );

            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input. Please enter a whole number.\n"
                );
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println(
                        "An unexpected error occurred. Please try again.\n"
                );
                scanner.nextLine();
            }
        }
    }

    /**
     * Prompts the user until a valid whole number within the specified range is entered.
     *
     * @param prompt   message displayed to the user
     * @param minValue minimum acceptable value
     * @param maxValue maximum acceptable value
     * @param scanner  Scanner used to read user input
     * @return a validated integer within the specified range
     */
    static int getRangeValidatedInput(
            String prompt,
            int minValue,
            int maxValue,
            Scanner scanner
    ) {

        int input;

        while (true) {
            try {
                System.out.print(prompt);

                input = scanner.nextInt();
                scanner.nextLine();

                if (input >= minValue && input <= maxValue) {
                    return input;
                }
                System.out.printf(
                        "Value must be between %d and %d.\n\n",
                        minValue,
                        maxValue
                );

            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input. Please enter a whole number.\n"
                );
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println(
                        "An unexpected error occurred. Please try again.\n"
                );
                scanner.nextLine();
            }
        }
    }

    // -----------------------------
    // Processing Logic
    // -----------------------------

    /**
     * Processes a single section of students by collecting their marks,
     * calculating section statistics, and updating overall program statistics.
     *
     * <p>The method performs the following steps:
     * <ul>
     *     <li>Prompts for the number of students in the section</li>
     *     <li>Iterates through each student and collects validated marks (0–100)</li>
     *     <li>Computes section sum, minimum mark, maximum mark, and average</li>
     *     <li>Updates overall statistics (min, max, sum, and student count)</li>
     *     <li>Outputs a formatted summary of the section results</li>
     * </ul>
     *
     * @param sectionNumber the ID/number of the section being processed
     * @param scanner the Scanner object used for user input
     */
    static void processSection(
            int sectionNumber,
            Scanner scanner
    ) {

        String studentCountPrompt = String.format(
                "Enter number of students in section %d: ",
                sectionNumber
        );

        int studentCount = getMinValidatedInput(studentCountPrompt, 1, scanner);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int studentNumber = 1; studentNumber <= studentCount; studentNumber++) {

            String studentMarkPrompt = String.format(
                    "Enter mark for student %d: ",
                    studentNumber
            );

            int mark = getRangeValidatedInput(studentMarkPrompt, 0, 100, scanner);

            min = Math.min(min, mark);
            max = Math.max(max, mark);
            sum += mark;
        }

        overallMin = Math.min(overallMin, min);
        overallMax = Math.max(overallMax, max);
        overallSum += sum;
        overallStudentCount += studentCount;

        double average = (double) sum / studentCount;

        System.out.printf(
                "Section %d - Lowest: %d, Highest: %d, Average: %.2f%n%n",
                sectionNumber,
                min,
                max,
                average
        );
    }

    // -----------------------------
    // Output / Reporting Methods
    // -----------------------------

    /**
     * Prints the overall statistics for all processed sections.
     *
     * <p>This method calculates the overall average from the accumulated sum
     * and total number of students, then displays a formatted report including:
     * the lowest mark, highest mark, and overall average.</p>
     *
     * <p>The output is formatted as a structured report using a text block
     * for consistent and readable console presentation.</p>
     */
    static void printOverallResults() {

        double average = (double) overallSum / overallStudentCount;

        String output = """
                        ================================
                                OVERALL RESULTS
                        ================================
                        
                        Lowest  : %d
                        Highest : %d
                        Average : %.2f
                        
                        ================================
                        """;
        System.out.printf(output, overallMin, overallMax, average);
    }
}