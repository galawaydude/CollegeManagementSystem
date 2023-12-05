import java.util.Scanner;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ManagementSystem collegeSystem = new ManagementSystem();
    static jdbcStuff jdbcstuff = new jdbcStuff();

    public static void main(String[] args) {
        boolean exit = false;
        collegeSystem.loadData();
        while (true) {
            LoginMenu();
            int choice = -1;
            while (true) {
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Please enter a valid integer \n");
                    scanner.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    authenticateAndDisplayMenu(UserRole.CREATOR);
                    break;
                case 2:
                    authenticateAndDisplayMenu(UserRole.ADMIN);
                    break;
                case 3:
                    authenticateAndDisplayMenu(UserRole.TEACHER);
                    break;
                case 4:
                    authenticateAndDisplayMenu(UserRole.STUDENT);
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            if (exit == true) {
                return;
            }
        }
    }

    private static void authenticateAndDisplayMenu(UserRole role) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("\n");
        switch (role) {
            case CREATOR:
                String creatorUsername = jdbcstuff.getCreatorUsername();
                String creatorPassword = jdbcstuff.getCreatorPassword();
                if (username.equals(creatorUsername) && password.equals(creatorPassword)) {
                    System.out.println(
                            "________________________________________________________________________________________________");
                    System.out.println("authentication successful.");
                    CreatorControls();
                } else {
                    System.out.println(
                            "________________________________________________________________________________________________");
                    System.out.println("authentication failed. Invalid username or password.");
                }
                break;

            case ADMIN:
                Admin authenticatedAdmin = collegeSystem.authenticateAdmin(username, password);
                if (authenticatedAdmin != null) {
                    System.out.println(
                            "________________________________________________________________________________________________");
                    System.out.println("authentication successful.");
                    AdminControls();
                } else {
                    System.out.println(
                            "________________________________________________________________________________________________");
                    System.out.println("authentication failed. Invalid username or password.");
                }
                break;

            case TEACHER:
                Teacher authenticatedTeacher = collegeSystem.authenticateTeacher(username, password);
                if (authenticatedTeacher != null) {
                    System.out.println(
                            "________________________________________________________________________________________________");
                    System.out.println("authentication successful.");
                    TeacherControls(authenticatedTeacher);
                } else {
                    System.out.println(
                            "________________________________________________________________________________________________");
                    System.out.println("authentication failed. Invalid username or password.");
                }
                break;

            case STUDENT:
                Student authenticatedStudent = collegeSystem.authenticateStudent(username, password);
                if (authenticatedStudent != null) {
                    System.out.println(
                            "________________________________________________________________________________________________");
                    System.out.println("authentication successful.");
                    StudentControls(authenticatedStudent);
                } else {
                    System.out.println(
                            "________________________________________________________________________________________________");
                    System.out.println("authentication failed. Invalid username or password.");
                }
                break;
        }
    }

    private static void LoginMenu() {
        System.out.println(
                "________________________________________________________________________________________________");
        System.out.println();
        System.out.println("Login ");
        System.out.println("_____");

        System.out.println("1. Creator ");
        System.out.println("2. Admin");
        System.out.println("3. Teacher");
        System.out.println("4. Student");
        System.out.println("5. Exit");
    }

    private static void CreatorMenu() {
        System.out.println("Creator Menu ");
        System.out.println("____________");
        System.out.println();
        System.out.println("0. Creator Options");
        System.out.println("1. Entity Creation Options");
        System.out.println("2. Entity Assignment Options");
        System.out.println("3. Entity Display Options");
        System.out.println("4. Entity Deletion Options");
        System.out.println("5. Additional Options");
        System.out.println("6. Logout");

        collegeSystem.saveData();
    }

    private static void AdminMenu() {
        System.out.println("Admin Menu");
        System.out.println("__________");
        System.out.println();
        System.out.println("1. Entity Creation Options");
        System.out.println("2. Entity Assignment Options");
        System.out.println("3. Entity Display Options");
        System.out.println("4. Additional Options");
        System.out.println("5. Logout");

        collegeSystem.saveData();
    }

    private static void StudentMenu() {
        System.out.println("Peronal Menu");
        System.out.println("__________");
        System.out.println();
        System.out.println("1. Display My Courses");
        System.out.println("2. Display My Teachers");
        System.out.println("3. Set Personal Information");
        System.out.println("4. Display Personal Information");
        System.out.println("5. Change login credentials");
        System.out.println("6. logout");

        collegeSystem.saveData();
    }

    private static void TeacherMenu() {
        System.out.println("Peronal Menu");
        System.out.println("____________");
        System.out.println();
        System.out.println("1. Display My Courses");
        System.out.println("2. Display My Sections");
        System.out.println("3. Set Personal Information");
        System.out.println("4. Display Personal Information");// this shit comes from database;
        System.out.println("5. Change login credentials");
        System.out.println("6. logout");
        collegeSystem.saveData();
    }

    private static void CreatorOptions() {
        System.out.println("Creator Options");
        System.out.println("_______________");
        System.out.println();
        System.out.println(" 1. Save the contents of the progam to txt files, only you can do this btw");
        System.out.println(" 2. Create Admin");
        System.out.println(" 3. Display Asmins");
        System.out.println(" 4. Display The information of a Teacher");
        System.out.println(" 5. Display The information of a Student");
        System.out.println(" 0. Previous Page");
        collegeSystem.saveData();
    }

    private static void EntityCreationOptions() {
        System.out.println("Entity Creation Options");
        System.out.println("_______________________");
        System.out.println();
        System.out.println(" 1. Add Student");
        System.out.println(" 2. Add Teacher");
        System.out.println(" 3. Add Course");
        System.out.println(" 4. Add Section");
        System.out.println(" 0. Previous Page");
        collegeSystem.saveData();
    }

    private static void EntityAssignmentOptions() {
        System.out.println("Entity Assignment Options");
        System.out.println("_________________________");
        System.out.println();
        System.out.println(" 1. Enroll Student in Course");
        System.out.println(" 2. Assign Teacher to Course");
        System.out.println(" 3. Assign Teacher to Section");
        System.out.println(" 0. Previous Page");
        collegeSystem.saveData();
    }

    private static void EntityDisplayOptions() {
        System.out.println("Entity Display Options");
        System.out.println("______________________");
        System.out.println();
        System.out.println(" 1. Display Students");
        System.out.println(" 2. Display Teachers");
        System.out.println(" 3. Display Courses");
        System.out.println(" 4. Display Sections");
        System.out.println(" 0. Previous Page");
    }

    private static void EntityDeletionOptions() {
        System.out.println("Entity Deletion Options");
        System.out.println("_______________________");
        System.out.println();
        System.out.println("1. Delete Student");
        System.out.println("2. Delete Teacher");
        System.out.println("3. Delete Section");
        System.out.println("4. Delete Course");
        System.out.println("0. Previous Page");

    }

    private static void AdditionalOptions() {
        System.out.println("Additional Options(for advanced query operations)");
        System.out.println("_________________________________________________");
        System.out.println();
        System.out.println(" 1. display all students of a section");
        System.out.println(" 2. display all the teachers of a course");
        System.out.println(" 3. display all courses of a student");
        System.out.println(" 4. display all teacher of a section");
        System.out.println(" 5. display all teachers not teaching");
        System.out.println(" 6. display teachers teaching multiple sections");
        System.out.println(" 0. Previous Page");
    }

    private static void CreatorControls() {
        System.out.println("Logged in Successfully \n");
        System.out.println("Welcome Creator (pavan(I wrote the whole program)) ");
        while (true) {
            System.out.println();
            CreatorMenu();
            int choice = -1;
            while (true) {
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Please enter a valid integer \n");
                    scanner.nextLine();
                }
            }
            if (choice == 0) {
                while (true) {
                    CreatorOptions();
                    System.out.print("Enter your choice: ");
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        StuffSaver();
                    }
                    if (choiceAgain == 2) {
                        AdminCreator();
                    }
                    if (choiceAgain == 3) {
                        DisplayAdmins();
                    }
                    if (choiceAgain == 4) {
                        TeacherStalker();
                    }
                    if (choiceAgain == 5) {
                        StudentStalker();
                    }
                    if (choiceAgain == 0) {
                        break;
                    }
                }
            }
            if (choice == 1) {
                while (true) {
                    EntityCreationOptions();
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        while (true) {
                            collegeSystem.saveData();
                            String stuff;
                            StudentCreator();
                            System.out.println("If you want to add more students enter y, else n");
                            stuff = scanner.nextLine();
                            while (true) {
                                if (stuff.equalsIgnoreCase("n")) {
                                    break;
                                }
                                if (stuff.equalsIgnoreCase("y")) {
                                    break;
                                }
                                System.out.println("Not what i was expecting, try again ");
                                stuff = scanner.nextLine();
                            }
                            if (stuff.equalsIgnoreCase("n")) {
                                break;
                            }
                            if (stuff.equalsIgnoreCase("y")) {

                            }
                        }
                    }
                    if (choiceAgain == 2) {
                        while (true) {
                            collegeSystem.saveData();
                            String stuff;
                            TeacherCreator();
                            System.out.println("If you want to add more teachers enter y, else n");
                            stuff = scanner.nextLine();
                            while (true) {
                                if (stuff.equalsIgnoreCase("n")) {
                                    break;
                                }
                                if (stuff.equalsIgnoreCase("y")) {
                                    break;
                                }
                                System.out.println("Not what i was expecting, try again ");
                                stuff = scanner.nextLine();
                            }
                            if (stuff.equalsIgnoreCase("n")) {
                                break;
                            }
                            if (stuff.equalsIgnoreCase("y")) {

                            }
                        }
                    }
                    if (choiceAgain == 3) {
                        while (true) {
                            collegeSystem.saveData();
                            String stuff;
                            CourseCreator();
                            System.out.println("If you want to add more courses enter y, else n");
                            stuff = scanner.nextLine();
                            while (true) {
                                if (stuff.equalsIgnoreCase("n")) {
                                    break;
                                }
                                if (stuff.equalsIgnoreCase("y")) {
                                    break;
                                }
                                System.out.println("Not what i was expecting, try again ");
                                stuff = scanner.nextLine();
                            }
                            if (stuff.equalsIgnoreCase("n")) {
                                break;
                            }
                            if (stuff.equalsIgnoreCase("y")) {

                            }

                        }
                    }
                    if (choiceAgain == 4) {
                        while (true) {
                            collegeSystem.saveData();
                            String stuff;
                            SectionCreator();
                            System.out.println("If you want to add more sections enter y, else n");
                            stuff = scanner.nextLine();
                            while (true) {
                                if (stuff.equalsIgnoreCase("n")) {
                                    break;
                                }
                                if (stuff.equalsIgnoreCase("y")) {
                                    break;

                                }

                                System.out.println("Not what i was expecting, try again ");
                                stuff = scanner.nextLine();
                            }
                            if (stuff.equalsIgnoreCase("n")) {
                                break;
                            } else if (stuff.equalsIgnoreCase("y")) {

                            }
                        }
                    }
                    if (choiceAgain == 0) {
                        break;
                    }
                }
            }
            if (choice == 2) {
                while (true) {
                    EntityAssignmentOptions();
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        StudentCourse();
                    }
                    if (choiceAgain == 2) {
                        TeacherCourse();
                    }
                    if (choiceAgain == 3) {
                        TeacherSection();
                    }
                    if (choiceAgain == 0) {
                        break;
                    }
                }
            }
            if (choice == 3) {
                while (true) {
                    EntityDisplayOptions();
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        StudentDisplay();
                    }
                    if (choiceAgain == 2) {
                        TeacherDisplay();
                    }
                    if (choiceAgain == 3) {
                        CourseDisplay();
                    }
                    if (choiceAgain == 4) {
                        SectionDisplay();
                    }
                    if (choiceAgain == 0) {
                        break;
                    }
                }
            }
            if (choice == 6) {
                System.out.println("Logging out");
                return;
            }

            if (choice == 4) {
                while (true) {
                    EntityDeletionOptions();
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        StudentDeleter();
                    }
                    if (choiceAgain == 2) {
                        TeacherDeleter();
                    }
                    if (choiceAgain == 3) {
                        SectionDeleter();
                    }
                    if (choiceAgain == 4) {
                        CourseDeleter();
                    }
                    if (choiceAgain == 0) {
                        break;
                    }
                }
            }
            if (choice == 5) {
                while (true) {
                    AdditionalOptions();
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        System.out.print("Enter the name of the section: ");
                        String sectionName = scanner.nextLine();
                        jdbcstuff.displayStudentsOfSection(sectionName);
                    }
                    if (choiceAgain == 2) {
                        System.out.print("Enter the name of the course: ");
                        String courseName = scanner.nextLine();
                        jdbcstuff.displayTeachersOfCourse(courseName);
                    }
                    if (choiceAgain == 3) {
                        System.out.print("Enter the name of the student: ");
                        String studentName = scanner.nextLine();
                        jdbcstuff.displayCoursesOfStudent(studentName);
                    }
                    if (choiceAgain == 4) {
                        System.out.println("Enter the name of the section");
                        String sectionName = scanner.nextLine();
                        jdbcstuff.displayTeachersOfSection(sectionName);
                    }
                    if (choiceAgain == 5) {
                        jdbcstuff.displayCourselessTeachers();
                    }
                    if (choiceAgain == 6) {
                        jdbcstuff.displayTeachersTeachingMultipleSections();
                    }
                    if (choiceAgain == 0) {
                        break;
                    }

                }
            }
        }
    }

    private static void AdminControls() {
        System.out.println("Logged in Successfully \n");
        System.out.println("Welcome Admin");
        while (true) {
            System.out.println();
            AdminMenu();
            int choice = -1;
            while (true) {
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Please enter a valid integer \n");
                    scanner.nextLine();
                }
            }
            if (choice == 1) {
                while (true) {
                    EntityCreationOptions();
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        while (true) {
                            collegeSystem.saveData();
                            String stuff;
                            StudentCreator();
                            System.out.println("If you want to add more students enter y, else n");
                            stuff = scanner.nextLine();
                            while (true) {
                                if (stuff.equalsIgnoreCase("n")) {
                                    break;
                                }
                                if (stuff.equalsIgnoreCase("y")) {
                                    break;
                                }
                                System.out.println("Not what i was expecting, try again ");
                                stuff = scanner.nextLine();
                            }
                            if (stuff.equalsIgnoreCase("n")) {
                                break;
                            }
                            if (stuff.equalsIgnoreCase("y")) {

                            }
                        }
                    }
                    if (choiceAgain == 2) {
                        while (true) {
                            collegeSystem.saveData();
                            String stuff;
                            TeacherCreator();
                            System.out.println("If you want to add more teachers enter y, else n");
                            stuff = scanner.nextLine();
                            while (true) {
                                if (stuff.equalsIgnoreCase("n")) {
                                    break;
                                }
                                if (stuff.equalsIgnoreCase("y")) {
                                    break;
                                }
                                System.out.println("Not what i was expecting, try again ");
                                stuff = scanner.nextLine();
                            }
                            if (stuff.equalsIgnoreCase("n")) {
                                break;
                            }
                            if (stuff.equalsIgnoreCase("y")) {

                            }
                        }
                    }
                    if (choiceAgain == 3) {
                        while (true) {
                            collegeSystem.saveData();
                            String stuff;
                            CourseCreator();
                            System.out.println("If you want to add more courses enter y, else n");
                            stuff = scanner.nextLine();
                            while (true) {
                                if (stuff.equalsIgnoreCase("n")) {
                                    break;
                                }
                                if (stuff.equalsIgnoreCase("y")) {
                                    break;
                                }
                                System.out.println("Not what i was expecting, try again ");
                                stuff = scanner.nextLine();
                            }
                            if (stuff.equalsIgnoreCase("n")) {
                                break;
                            }
                            if (stuff.equalsIgnoreCase("y")) {

                            }

                        }
                    }
                    if (choiceAgain == 4) {
                        while (true) {
                            collegeSystem.saveData();
                            String stuff;
                            SectionCreator();
                            System.out.println("If you want to add more sections enter y, else n");
                            stuff = scanner.nextLine();
                            while (true) {
                                if (stuff.equalsIgnoreCase("n")) {
                                    break;
                                }
                                if (stuff.equalsIgnoreCase("y")) {
                                    break;

                                }

                                System.out.println("Not what i was expecting, try again ");
                                stuff = scanner.nextLine();
                            }
                            if (stuff.equalsIgnoreCase("n")) {
                                break;
                            } else if (stuff.equalsIgnoreCase("y")) {

                            }
                        }
                    }
                    if (choiceAgain == 0) {
                        break;
                    }
                }
            }
            if (choice == 2) {
                while (true) {
                    EntityAssignmentOptions();
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        StudentCourse();
                    }
                    if (choiceAgain == 2) {
                        TeacherCourse();
                    }
                    if (choiceAgain == 3) {
                        TeacherSection();
                    }
                    if (choiceAgain == 0) {
                        break;

                    }
                }
            }
            if (choice == 3) {
                while (true) {
                    EntityDisplayOptions();
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        StudentDisplay();
                    }
                    if (choiceAgain == 2) {
                        TeacherDisplay();
                    }
                    if (choiceAgain == 3) {
                        CourseDisplay();
                    }
                    if (choiceAgain == 4) {
                        SectionDisplay();
                    }
                    if (choiceAgain == 0) {
                        break;
                    }
                }
            }
            if (choice == 5) {
                System.out.println("Logging out");
                System.out.println("\n");
                return;
            }
            if (choice == 4) {
                while (true) {
                    AdditionalOptions();
                    int choiceAgain = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            choiceAgain = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    if (choiceAgain == 1) {
                        System.out.print("Enter the name of the section: ");
                        String sectionName = scanner.nextLine();
                        jdbcstuff.displayStudentsOfSection(sectionName);
                    }
                    if (choiceAgain == 2) {
                        System.out.print("Enter the name of the course: ");
                        String courseName = scanner.nextLine();
                        jdbcstuff.displayTeachersOfCourse(courseName);
                    }
                    if (choiceAgain == 3) {
                        System.out.print("Enter the name of the student: ");
                        String studentName = scanner.nextLine();
                        jdbcstuff.displayCoursesOfStudent(studentName);
                    }
                    if (choiceAgain == 4) {
                        System.out.println("Enter the name of the section");
                        String sectionName = scanner.nextLine();
                        jdbcstuff.displayTeachersOfSection(sectionName);
                    }
                    if (choiceAgain == 5) {
                        jdbcstuff.displayCourselessTeachers();
                    }
                    if (choiceAgain == 6) {
                        jdbcstuff.displayTeachersTeachingMultipleSections();
                    }
                    if (choiceAgain == 0) {
                        break;
                    }
                }

            }
        }
    }

    private static void TeacherControls(Teacher teacher) {
        System.out.println("Logged in Successfully \n");
        System.out.println("Welcome " + teacher.getName());
        while (true) {
            System.out.println();
            TeacherMenu();
            int choice = -1;
            while (true) {
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Please enter a valid integer \n");
                    scanner.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    System.out.println("This are all the Courses you are teaching: ");
                    pavan_dynamic_array teacherCourses = teacher.getCourses();
                    for (int i = 0; i < teacherCourses.size(); i++) {
                        Course course = (Course) teacherCourses.get(i);
                        System.out.println(course.getName());
                    }
                    break;
                case 2:
                    System.out.println("This are all your Sections you are teaching: ");
                    pavan_dynamic_array teacherSections = teacher.getSections();
                    for (int i = 0; i < teacherSections.size(); i++) {
                        Section section = (Section) teacherSections.get(i);
                        System.out.println(section.getName());
                    }
                    break;
                case 3:
                    System.out.println("1. Change Name");
                    System.out.println("2. EmailAddress");
                    System.out.println("3. PhoneNo");
                    System.out.println("4. DateOfBirth");
                    System.out.println("Which information do you want to change: ");
                    int teacherChoice = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            teacherChoice = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    TeacherStuff(teacherChoice, teacher);
                    break;
                case 4:
                    System.out.println("This is you personal Information");
                    System.out.println("Name: " + teacher.getName());
                    System.out.println("RollNo: " + teacher.getRollNo());
                    System.out.println("PhoneNo: " + teacher.getPhoneNo());
                    System.out.println("EmailAddress: " + teacher.getEmailID());
                    System.out.println("DateOfBirth: " + teacher.getDOB());
                    break;
                case 5:
                    System.out.println("Password change Menu");
                    String newPassword = scanner.nextLine();
                    while (newPassword.length() < 5) {
                        System.out.println("too small, try again");
                        newPassword = scanner.nextLine();
                    }
                    teacher.setPassword(newPassword);
                    System.out.println("Password Successfully changed");
                    jdbcstuff.updateCredentials(teacher.getName(), newPassword, "password");
                    break;
                case 6:
                    System.out.println("Logging out");
                    System.out.println("\n");
                    return;
            }
        }
    }

    private static void StudentControls(Student student) {
        System.out.println("logged in successfully \n");
        System.out.println("Welcome " + student.getName());
        while (true) {
            System.out.println();
            StudentMenu();
            int choice = -1;
            while (true) {
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Please enter a valid integer \n");
                    scanner.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    System.out.println("This are all the Courses you are enrolled in: ");
                    pavan_dynamic_array studentCourses = student.getEnrolledCourses();
                    for (int i = 0; i < studentCourses.size(); i++) {
                        Course course = (Course) studentCourses.get(i);
                        System.out.println(course.getName());
                    }
                    break;
                case 2:
                    System.out.println("This are all the teachers who are teaching you");
                    String studentSection = student.getSection();
                    pavan_dynamic_array stuff = collegeSystem.getSections();

                    Section sectionIwant = null;

                    for (int i = 0; i < stuff.size(); i++) {
                        Section section = (Section) stuff.get(i);
                        if (section.getName().equals(studentSection)) {
                            sectionIwant = section;
                        }
                        if (sectionIwant != null) {
                            pavan_dynamic_array teachersOfThisSection = sectionIwant.getTeachers();
                            for (i = 0; i < teachersOfThisSection.size(); i++) {
                                Teacher teacher = (Teacher) teachersOfThisSection.get(i);
                                System.out.println(teacher.getName());
                            }
                        }
                        if (sectionIwant == null) {
                            System.out.println("");
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("1. Change Name");
                    System.out.println("2. EmailAddress");
                    System.out.println("3. PhoneNo");
                    System.out.println("4. DateOfBirth");
                    System.out.println("Which information do you want to change: ");
                    int studentChoice = -1;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        try {
                            studentChoice = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter a valid integer \n");
                            scanner.nextLine();
                        }
                    }
                    StudentStuff(studentChoice, student);
                case 4:
                    System.out.println();
                    System.out.println("This is you personal Information");
                    System.out.println("Name: " + student.getName());
                    System.out.println("RollNo: " + student.getRollNo());
                    System.out.println("PhoneNo: " + student.getPhoneNo());
                    System.out.println("EmailAddress: " + student.getEmailID());
                    System.out.println("DateOfBirth: " + student.getDOB());
                    break;
                case 5:
                    System.out.println("Presently you can only change your password, deal with it later");
                    System.out.print("Enter New Password: ");
                    String newPassword = scanner.nextLine();
                    while (newPassword.length() < 5) {
                        System.out.println("Too small, Enter again");
                        newPassword = scanner.nextLine();
                    }
                    student.setPassword(newPassword);
                    System.out.println("Password Successfully changed");
                    jdbcstuff.updateCredentials(student.getName(), newPassword, "password");
                    break;
                case 6:
                    System.out.println("Logging out now");
                    return;
            }
        }
    }

    private static void TeacherStuff(int teacherChoice, Teacher teacher) {
        if (teacherChoice == 1) {
            System.out.println("You have opted to set your name");
            System.out.print("Enter the new name: ");
            String newName = scanner.nextLine();
            teacher.setName(newName);
            jdbcstuff.updateTeacherStuff(teacher.getRollNo(), newName, "name");
        }
        if (teacherChoice == 2) {
            System.out.println("You have opted to set your EmailAddress");
            System.out.print("Enter the EmailAddress: ");
            String newEmailAddress = scanner.nextLine();
            teacher.setEmailAddress(newEmailAddress);
            jdbcstuff.updateTeacherStuff(teacher.getRollNo(), newEmailAddress, "emailAddress");
        }
        if (teacherChoice == 3) {
            System.out.println("You have opted to set your phoneNo");
            System.out.print("Enter the phoneNo: ");
            String newPhoneNo = scanner.nextLine();
            teacher.setPhoneNo(newPhoneNo);
            jdbcstuff.updateTeacherStuff(teacher.getRollNo(), newPhoneNo, "phoneNo");
        }
        if (teacherChoice == 4) {
            System.out.println("You have opted to set your DateOfBirth");
            System.out.print("Enter the DateOfBirth: ");
            String newDOB = scanner.nextLine();
            teacher.setDateOfBirth(newDOB);
            jdbcstuff.updateTeacherStuff(teacher.getRollNo(), newDOB, "dateOfBirth");
        }
        return;
    }

    private static void StudentStuff(int studentChoice, Student student) {
        if (studentChoice == 1) {
            System.out.println("You have opted to set your name");
            System.out.print("Enter the new name: ");
            String newName = scanner.nextLine();
            student.setName(newName);
            jdbcstuff.updateStudentStuff(student.getRollNo(), newName, "name");
        }
        if (studentChoice == 2) {
            System.out.println("You have opted to set your EmailAddress");
            System.out.print("Enter the EmailAddress: ");
            String newEmailAddress = scanner.nextLine();
            student.setEmailAddress(newEmailAddress);
            jdbcstuff.updateStudentStuff(student.getRollNo(), newEmailAddress, "emailAddress");

        }
        if (studentChoice == 3) {
            System.out.println("You have opted to set your phoneNo");
            System.out.print("Enter the phoneNo: ");
            String newPhoneNo = scanner.nextLine();
            student.setPhoneNo(newPhoneNo);
            jdbcstuff.updateStudentStuff(student.getRollNo(), newPhoneNo, "phoneNo");
        }
        if (studentChoice == 4) {
            System.out.println("You have opted to set your DateOfBirth");
            System.out.print("Enter the DateOfBirth: ");
            String newDOB = scanner.nextLine();
            student.setDateOfBirth(newDOB);
            jdbcstuff.updateStudentStuff(student.getRollNo(), newDOB, "dateOfBirth");

        }
        return;
    }

    private static Student getStudentByRollNo(String rollNo) {
        pavan_dynamic_array students = collegeSystem.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student student = (Student) students.get(i);
            if (student.getRollNo().equals(rollNo)) {
                return student;
            }
        }
        return null;
    }

    private static Teacher getTeacherByRollNo(String rollNo) {
        pavan_dynamic_array teachers = collegeSystem.getTeachers();
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = (Teacher) teachers.get(i);
            if (teacher.getRollNo().equals(rollNo)) {
                return teacher;
            }
        }
        return null;
    }

    private static Course getCourseByName(String courseName) {
        pavan_dynamic_array courses = collegeSystem.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course course = (Course) courses.get(i);
            if (course.getName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }

    private static Section getSectionByName(String sectionNameToAssign) {
        pavan_dynamic_array sections = collegeSystem.getSections();
        for (int i = 0; i < sections.size(); i++) {
            Section section = (Section) sections.get(i);
            if (section.getName().equals(sectionNameToAssign)) {
                return section;
            }
        }
        return null;
    }

    // private static Student getStudentByName(String StudentName) {
    // pavan_dynamic_array students = collegeSystem.getStudents();
    // for (int i = 0; i < students.size(); i++) {
    // Student student = (Student) students.get(i);
    // if (student.getName().equals(StudentName)) {
    // return student;
    // }
    // }
    // return null;
    // }

    // private static Teacher getTeacherByName(String teacherName) {
    // pavan_dynamic_array teachers = collegeSystem.getTeachers();
    // for (int i = 0; i < teachers.size(); i++) {
    // Teacher teacher = (Teacher) teachers.get(i);
    // if (teacher.getName().equals(teacherName)) {
    // return teacher;
    // }
    // }
    // return null;
    // }

    private static void StuffSaver() {
        System.out.println("Saving data into text files");
        collegeSystem.FileStateDeleter("Students.txt");
        collegeSystem.saveStateToFile("Students.txt", Student.class);
        collegeSystem.FileStateDeleter("Teachers.txt");
        collegeSystem.saveStateToFile("Teachers.txt", Teacher.class);
        collegeSystem.FileStateDeleter("Sections.txt");
        collegeSystem.saveStateToFile("Sections.txt", Section.class);
        collegeSystem.FileStateDeleter("Courses.txt");
        collegeSystem.saveStateToFile("Courses.txt", Course.class);
    }

    private static void AdminCreator() {
        System.out.print("Enter Admin Name: ");
        String adminName = scanner.nextLine();
        pavan_dynamic_array AdminArray = collegeSystem.getAdmins();
        for (int i = 0; i < AdminArray.size(); i++) {
            Admin admin = (Admin) AdminArray.get(i);
            if (admin.getUsername().equals(adminName)) {
                System.out.println("This Admin already exists");
                return;
            }
        }
        System.out.print("Enter the Admin Password: ");
        String adminPassword = scanner.nextLine();
        Admin admin = new Admin(adminName, adminPassword);
        collegeSystem.addAdmin(admin);
        System.out.println(" \n Admin added successfully");
        jdbcstuff.insertUser(adminName, adminPassword, "admin");
    }

    private static void DisplayAdmins() {
        System.out.println();
        System.out.println("This is a list of all the admins");
        pavan_dynamic_array AdminArray = collegeSystem.getAdmins();
        for (int i = 0; i < AdminArray.size(); i++) {
            Admin Admin = (Admin) AdminArray.get(i);
            System.out.println("Name: " + Admin.getUsername());
        }
    }

    private static void TeacherStalker() {
        System.out.println();
        TeacherDisplayAgain();
        System.out.println();
        boolean finder = false;
        System.out.print("Enter the rollNo of the teacher whose information you want: ");
        String teacherToSearch = scanner.nextLine();
        pavan_dynamic_array teacherArrayAgain = collegeSystem.getTeachers();
        for (int i = 0; i < teacherArrayAgain.size(); i++) {
            Teacher teacherAgain = (Teacher) teacherArrayAgain.get(i);
            if (teacherAgain.getRollNo().equals(teacherToSearch)) {
                finder = true;
                System.out.println("Name: " + teacherAgain.getName());
                System.out.println("RollNo" + teacherAgain.getRollNo());
                System.out.println("PhoneNo" + teacherAgain.getPhoneNo());
                System.out.println("EmailAddress" + teacherAgain.getEmailID());
                System.out.println("DateOfBirth" + teacherAgain.getDOB());
                System.out.println("Password: " + teacherAgain.getPassword());
                return;
            }
        }
        if (finder == false) {
            System.out.println("Teacher not found");
        }
    }

    private static void StudentStalker() {
        System.out.println();
        StudentDisplayAgain();
        System.out.println();
        boolean finder = false;
        System.out.print("Enter the rollNo of the student whose information you want: ");
        String studentToSearch = scanner.nextLine();
        pavan_dynamic_array studentArrayAgain = collegeSystem.getStudents();
        for (int i = 0; i < studentArrayAgain.size(); i++) {
            Student studentAgain = (Student) studentArrayAgain.get(i);
            if (studentAgain.getRollNo().equals(studentToSearch)) {
                finder = true;
                System.out.println("Name: " + studentAgain.getName());
                System.out.println("RollNo" + studentAgain.getRollNo());
                System.out.println("PhoneNo" + studentAgain.getPhoneNo());
                System.out.println("EmailAddress" + studentAgain.getEmailID());
                System.out.println("DateOfBirth" + studentAgain.getDOB());
                System.out.println("Password: " + studentAgain.getPassword());
            }
        }
        if (finder = false) {
            System.out.println("Student not found");
        }

    }

    private static void StudentCreator() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        pavan_dynamic_array Students = collegeSystem.getStudents();
        for (int i = 0; i < Students.size(); i++) {
            Student Student = (Student) Students.get(i);
            while (Student.getName().equals(studentName)) {
                System.out.println(
                        "There is already a student with the similar name, if there is a different student with the same name, enter yes, and continue, else, if its a mistake enter no");
                String response = scanner.nextLine();
                if (response.equals("yes")) {
                    break;
                } else {
                    System.out.println("Enter Student name again");
                    studentName = scanner.nextLine();
                }
            }
        }
        Student student = new Student(studentName);
        int counter = 0;
        System.out.print("Enter student section: ");
        while (true) {
            String Section = scanner.nextLine();
            Section SectionToEnroll = getSectionByName(Section);
            if (SectionToEnroll != null) {
                student.enrollInSection(SectionToEnroll);
                System.out.println("Student has been successfully added to the section");
                break;
            } else {
                System.out.println("Section not found, Enter Section again");
                counter++;
            }
            if (counter > 3) {
                System.out.println("Student Creation Failed");
                return;
            }
        }
        collegeSystem.addStudent(student);
        System.out.println(" \n Student added successfully!");
        jdbcstuff.insertStudent(studentName, student.getRollNo(), student.getSection(), student.getEmailID());
        jdbcstuff.insertUser(studentName, student.getPassword(), "student");
        return;
    }

    private static void TeacherCreator() {
        System.out.print("Enter teacher name: ");
        String teacherName = scanner.nextLine();

        pavan_dynamic_array Teachers = collegeSystem.getTeachers();
        for (int i = 0; i < Teachers.size(); i++) {
            Teacher Teacher = (Teacher) Teachers.get(i);
            while (Teacher.getName().equals(teacherName)) {
                System.out.println(
                        "There is already a teacher with the similar name, if there is a different teacher with the same name, enter yes, and continue, else, if its a mistake enter no");
                String response = scanner.nextLine();
                if (response.equals("yes")) {
                    break;
                } else {
                    System.out.println("Enter teacher name again");
                    teacherName = scanner.nextLine();
                }
            }
        }
        Teacher teacher = new Teacher(teacherName);
        collegeSystem.addTeacher(teacher);
        System.out.println(" \n Teacher added successfully!");
        jdbcstuff.insertTeacher(teacherName, teacher.getRollNo(), teacher.getEmailID());
        jdbcstuff.insertUser(teacherName, teacher.getPassword(), "teacher");
    }

    private static void CourseCreator() {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();

        pavan_dynamic_array Courses = collegeSystem.getCourses();
        for (int i = 0; i < Courses.size(); i++) {
            Course Course = (Course) Courses.get(i);
            while (Course.getName().equals(courseName)) {
                System.out.println("This course already exists, cannot create duplicate values");
                System.out.println("Enter a new course");
                courseName = scanner.nextLine();
            }
        }
        Course course = new Course(courseName);
        collegeSystem.addCourse(course);
        System.out.println(" \n Course added successfully!");
        jdbcstuff.insertCourse(courseName);
        return;
    }

    private static void SectionCreator() {
        System.out.println("Enter section name: ");
        String sectionName = scanner.nextLine();

        pavan_dynamic_array Sections = collegeSystem.getSections();
        for (int i = 0; i < Sections.size(); i++) {
            Section Section = (Section) Sections.get(i);
            while (Section.getName().equals(sectionName)) {
                System.out.println("This section already exists, cannot create duplicate sections");
                sectionName = scanner.nextLine();
            }
        }
        Section section = new Section(sectionName);
        collegeSystem.addSection(section);
        System.out.println(" \n Section Created successfully");
        jdbcstuff.insertSection(sectionName);
        return;
    }

    private static void StudentCourse() {
        StudentDisplayAgain();
        System.out.println("");
        System.out.print("Enter student roll number: ");
        String studentRollNo = scanner.nextLine();
        String stuff;
        int counter = 0;
        while (true) {
            collegeSystem.saveData();
            Student studentToEnroll = getStudentByRollNo(studentRollNo);
            if (studentToEnroll == null) {
                System.out.println("Student don't exit");
                return;
            }
            System.out.print("Enter course name: ");
            String courseNameToEnroll = scanner.nextLine();
            Course courseToEnroll = getCourseByName(courseNameToEnroll);

            if (courseToEnroll != null) {
                if (jdbcstuff.StudentCourse(studentRollNo, courseNameToEnroll)) {
                    studentToEnroll.enrollInCourse(courseToEnroll);
                    System.out.println("\n Student enrolled in the course successfully!");
                } else {
                    System.out.println("Error enrolling student to course. check the logs.");
                    System.out.println("Database said not to do the above operation");

                }
            } else {
                System.out.println("\n  course not found. Please check the inputs.");
                counter++;
            }
            if (counter > 3) {
                System.out.println("Course assignment failed");
                break;
            }

            System.out.println("if you want to assign more courses enter y, else n");
            stuff = scanner.nextLine();
            while (true) {
                if (stuff.equalsIgnoreCase("y")) {
                    break;
                }
                if (stuff.equalsIgnoreCase("n")) {
                    break;
                }
                System.out.println("Not what i was expecting, try again ");
                stuff = scanner.nextLine();
            }
            if (stuff.equalsIgnoreCase("n")) {
                break;
            }
            if (stuff.equalsIgnoreCase("y")) {

            }
        }
    }

    private static void TeacherCourse() {
        TeacherDisplayAgain();
        System.out.println();
        System.out.print("Enter teacher roll number: ");
        String teacherRollNoToAssign = scanner.nextLine();
        String stuff;
        int counter = 0;
        while (true) {
            collegeSystem.saveData();
            Teacher teacherToAssign = getTeacherByRollNo(teacherRollNoToAssign);
            if (teacherToAssign == null) {
                System.out.println("Teacher don't exist");
                return;
            }
            System.out.print("Enter course name: ");
            String courseNameToAssign = scanner.nextLine();
            Course courseToAssign = getCourseByName(courseNameToAssign);

            if (courseToAssign != null) {
                if (jdbcstuff.TeacherCourse(teacherRollNoToAssign, courseNameToAssign)) {
                    teacherToAssign.assignToCourse(courseToAssign);
                    System.out.println("\nTeacher assigned to the course successfully!");
                } else {
                    System.out.println("Error assigning teacher to course. Please check the logs.");
                    System.out.println("Database said not to do the above operation");
                }
            } else {
                System.out.println("\n  course not found. Please check the inputs.");
                counter++;
            }
            if (counter > 3) {
                System.out.println("Course assignemnt failed");
                break;
            }

            System.out.println("if you want to assign more courses enter y, else n");
            stuff = scanner.nextLine();
            while (true) {
                if (stuff.equalsIgnoreCase("n")) {
                    break;
                }
                if (stuff.equalsIgnoreCase("y")) {
                    break;
                }
                System.out.println("Not what i was expecting, try again ");
                stuff = scanner.nextLine();
            }
            if (stuff.equalsIgnoreCase("n")) {
                break;
            }
            if (stuff.equalsIgnoreCase("y")) {

            }
        }

    }

    private static void TeacherSection() {
        TeacherDisplayAgain();
        System.out.println();
        System.out.println("Enter Teacher RollNo");
        String teacherRollNoToAssignToSection = scanner.nextLine();
        String stuff;
        int counter = 0;
        while (true) {
            collegeSystem.saveData();
            Teacher teachertoAssign = getTeacherByRollNo(teacherRollNoToAssignToSection);
            if (teachertoAssign == null) {
                System.out.println("Teacher don't exist");
                return;
            }
            System.out.println("Enter Section Name");
            String sectionNameToAssign = scanner.nextLine();
            Section sectionToAssign = getSectionByName(sectionNameToAssign);

            if (sectionToAssign != null) {
                if (jdbcstuff.TeacherSection(teacherRollNoToAssignToSection, sectionNameToAssign)) {
                    teachertoAssign.assignToSection(sectionToAssign);
                    System.out.println("\n Teacher assigned to the section successfully");
                } else {
                    System.out.println("Error assigning teacher to course. Please check the logs.");
                    System.out.println("Database said not to do the above operation");
                }

            } else {
                System.out.println("\n Section not found, Please check the inputs are try again later");
                counter++;
            }
            if (counter > 3) {
                System.out.println("Section assignment failed");
                break;
            }

            System.out.println("if you want to assign more sections enter y, else n");
            stuff = scanner.nextLine();
            while (true) {
                if (stuff.equalsIgnoreCase("n")) {
                    break;
                }
                if (stuff.equalsIgnoreCase("y")) {
                    break;
                }
                System.out.println("Not what i was expecting, try again ");
                stuff = scanner.nextLine();
            }
            if (stuff.equalsIgnoreCase("n")) {
                break;
            }
            if (stuff.equalsIgnoreCase("y")) {

            }
        }
    }

    private static void StudentDisplay() {
        System.out.println("List of Students:");
        pavan_dynamic_array students = collegeSystem.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student Student = (Student) students.get(i);
            Student.displayStudentInfo();
            System.out.println();
        }
    }

    private static void StudentDisplayAgain() {
        System.out.println("List of Students:");
        pavan_dynamic_array students = collegeSystem.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student Student = (Student) students.get(i);
            System.out.println("Name: " + Student.getName());
            System.out.println("Roll Number: " + Student.getRollNo());
            System.out.println();
        }
    }

    private static void TeacherDisplay() {
        System.out.println("List of Teachers:");
        pavan_dynamic_array teachers = collegeSystem.getTeachers();
        for (int i = 0; i < teachers.size(); i++) {
            Teacher Teacher = (Teacher) teachers.get(i);
            Teacher.displayTeacherInfo();
            System.out.println();
        }
    }

    private static void TeacherDisplayAgain() {
        System.out.println("List of Teachers:");
        pavan_dynamic_array teachers = collegeSystem.getTeachers();
        for (int i = 0; i < teachers.size(); i++) {
            Teacher Teacher = (Teacher) teachers.get(i);
            System.out.println("Name: " + Teacher.getName());
            System.out.println("RollNo: " + Teacher.getRollNo());
        }
    }

    private static void CourseDisplay() {
        pavan_dynamic_array courses = collegeSystem.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course Course = (Course) courses.get(i);
            System.out.println("Course Name: " + Course.getName());
            System.out.println("Teachers: " + Course.getTeachers().size());
            System.out.println("No. Enrolled Students: " + Course.getStudents().size());
            System.out.println();
        }
    }

    private static void SectionDisplay() {
        System.out.println("List of Sections:");
        pavan_dynamic_array sections = collegeSystem.getSections();
        for (int i = 0; i < sections.size(); i++) {
            Section Section = (Section) sections.get(i);
            System.out.println("Section Name: " + Section.getName());
            System.out.println("Teachers: " + Section.getTeachers().size());
            System.out.println("No. Enrolled Students: " + Section.getStudents().size());
            System.out.println();
        }
    }

    private static void StudentDeleter() {
        StudentDisplayAgain();
        System.out.println();
        System.out.println("Enter the rollNo of the student you want to delete");
        String rollNo = scanner.nextLine();
        Student studentToDelete = getStudentByRollNo(rollNo);
        if (studentToDelete == null) {
            System.out.println("Student don't exist");
            return;
        }
        if (jdbcstuff.deleteStudent(rollNo)) {
            collegeSystem.StudentDeleter(rollNo);
            System.out.println("Student deleted successfully");
            return;
        } else {
            System.out.println("Database said to not do it");
            return;
        }
    }

    private static void TeacherDeleter() {
        TeacherDisplayAgain();
        System.out.println();
        System.out.println("Enter the rollNo of the teacher you want to delete");
        String rollNo = scanner.nextLine();
        Teacher teacherToDelete = getTeacherByRollNo(rollNo);
        if (teacherToDelete == null) {
            System.out.println("Teacher don't exist");
            return;
        }
        if (jdbcstuff.deleteTeacher(rollNo)) {
            collegeSystem.TeacherDeleter(rollNo);
            System.out.println("Teacher deleted Succeffully");
        } else {
            System.out.println("Database said to not do it");
            return;
        }
    }

    private static void SectionDeleter() {
        SectionDisplay();
        System.out.println();
        System.out.println("Enter the Name of the Section you want to delete");
        String Name = scanner.nextLine();
        Section sectionToDelete = getSectionByName(Name);
        if (sectionToDelete == null) {
            System.out.println("Section don't exist");
            return;
        }
        if (jdbcstuff.deleteSection(Name)) {
            collegeSystem.SectionDeleter(Name);
            System.out.println("Section deleted succeffully");
        } else {
            System.out.println("Database said to not do it");
            return;
        }
    }

    private static void CourseDeleter() {
        CourseDisplay();
        System.out.println();
        System.out.println("Enter the Name of the Course you want to delete");
        String Name = scanner.nextLine();
        Course courseToDelete = getCourseByName(Name);
        if (courseToDelete == null) {
            System.out.println("Course don't exist");
            return;
        }
        if (jdbcstuff.deleteCourse(Name)) {
            collegeSystem.CourseDeleter(Name);
            System.out.println("Course deleter");
        } else {
            System.out.println("Database said to not do it");
            return;
        }
    }
}