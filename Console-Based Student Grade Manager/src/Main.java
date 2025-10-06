// Importing Scanner for input
import java.util.Scanner;

// Making a public class Main
public class Main {
    // Starting Program Execution
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Declaring variables with data types
        String student_name;
        String ID;
        int number_of_courses;
        String[] subject_names;
        double[] marks;
        double total_grade_points = 0.0;
        double CGPA;
        // taking user input
        System.out.print("Student Name: ");
        student_name = in.nextLine();
        System.out.print("Student ID: ");
        ID = in.nextLine();
        System.out.print("Courses taken: ");
        number_of_courses = in.nextInt();
        //Initialize arrays *after* number_of_courses is known
        subject_names = new String[number_of_courses];
        marks = new double[number_of_courses];
        // to avoid buffer and handle next line
        in.nextLine();
        // using for loop for naming courses taken
        for (int x = 0; x < number_of_courses; x++) {
            System.out.print("Name of Subject " + (x + 1) + ": ");
            subject_names[x] = in.nextLine();
        }
        // using another for loop for getting grades of courses taken
        for (int x = 0; x < number_of_courses; x++) {
            System.out.print("Enter the marks for " + subject_names[x] + ": ");
            marks[x] = in.nextDouble();
        }
        // Display name, ID, number of courses taken
        System.out.println("\nAcademic Report\n");
        System.out.println("Student Name: " + student_name + "\n"
                + "Student ID: " + ID + "\n"
                + "Number of Courses Taken: " + number_of_courses);
        // using the final for loop for grading as per marks obtained
        for (int x = 0; x < number_of_courses; x++) {
            String grade;
            double grade_point; // Variable to hold the specific grade point
            double score = marks[x];

            // using conditional statements to assign Letter Grade and Grade Point
            if (score >= 90) {
                grade = "A+";
                grade_point = 4.00;
            } else if (score >= 85) {
                grade = "A";
                grade_point = 3.75;
            } else if (score >= 80) {
                grade = "B+";
                grade_point = 3.50;
            } else if (score >= 75) {
                grade = "B";
                grade_point = 3.25;
            } else if (score >= 70) {
                grade = "C+";
                grade_point = 3.00;
            } else if (score >= 65) {
                grade = "C";
                grade_point = 2.75;
            } else if (score >= 60) {
                grade = "D+";
                grade_point = 2.50;
            } else if (score >= 50) {
                grade = "D";
                grade_point = 2.25;
            }
            else {
                grade = "F";
                grade_point = 0.00; // F grade gets 0 points
            }

            // Accumulate the grade points for CGPA calculation
            total_grade_points += grade_point;
            // Displaying Marks with Grade Obtained
            System.out.println(subject_names[x] + ": " + score + " Grade: " + grade);
        }
        if (number_of_courses > 0) {
            CGPA = total_grade_points / number_of_courses;
        } else {
            CGPA = 0.0;
        }
        System.out.printf("\nTotal Grade Points: %.2f\n", total_grade_points);
        System.out.printf("CGPA: %.2f\n", CGPA); // Display CGPA formatted to two decimal places

        // closing the scanner
        in.close();
    }
}