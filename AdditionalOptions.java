import java.util.Scanner;

public class AdditionalOptions {
    private ManagementSystem collegeSystem;

    public AdditionalOptions(ManagementSystem collegeSystem) {
        this.collegeSystem = collegeSystem;
    }

    public void displayOptionsMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Additional Options Menu:");
                System.out.println("1. Display Courses of a Teacher");
                System.out.println("2. Display Teachers of a Course");
                System.out.println("3. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Enter the teacher's roll number: ");
                        String teacherRollNo = scanner.nextLine();
                        pavan_dynamic_array coursesTaughtByTeacher = collegeSystem
                                .getCoursesTaughtByTeacher(teacherRollNo);
                        if (coursesTaughtByTeacher.size() == 0) {
                            System.out.println("No courses found for the given teacher.");
                        } else {
                            System.out.println("Courses Taught by Teacher with Roll Number " + teacherRollNo + ":");
                            for (int i = 0; i < coursesTaughtByTeacher.size(); i++) {
                                Course course = (Course) coursesTaughtByTeacher.get(i);
                                System.out.println("Course Name: " + course.getName());
                                System.out.println();
                            }
                        }
                        break;

                    case 2:
                        System.out.print("Enter the course name: ");
                        String courseName = scanner.nextLine();
                        pavan_dynamic_array teachersOfCourse = collegeSystem.getTeachersOfCourse(courseName);
                        if (teachersOfCourse.size() == 0) {
                            System.out.println("No teachers found for the given course.");
                        } else {
                            System.out.println("Teachers of Course " + courseName + ":");
                            for (int i = 0; i < teachersOfCourse.size(); i++) {
                                Teacher teacher = (Teacher) teachersOfCourse.get(i);
                                System.out.println("Teacher Name: " + teacher.getName());
                                System.out.println("Teacher Roll Number: " + teacher.getRollNo());
                                System.out.println();
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Returning to Main Menu.");
                        try {
                            return;
                        } catch (Exception e) {

                        } finally {
                            System.out.println(
                                    "To solve this exception, i would need to create another class to manage both the menu's, will implement that for endsem along with other features");
                        }

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
    }
}
