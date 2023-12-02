import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static private boolean CreatorLogged = false;
    static private boolean AdminLogged = false;
    static ManagementSystem collegeSystem = new ManagementSystem();

    public static void main(String[] args) {
        collegeSystem.loadData();

        while (true) {
            LoginMenu();
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            scanner.nextLine();

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
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void authenticateAndDisplayMenu(UserRole role) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        switch (role) {
            case CREATOR:
                if (username.equals("Pavan") && password.equals("%Fortress123&")) {
                    System.out.println("Creator authentication successful.");
                    PavanControls();
                } else {
                    System.out.println("Creator authentication failed. Invalid username or password.");
                }
                break;

            case ADMIN:
                Admin authenticatedAdmin = collegeSystem.authenticateAdmin(username, password);
                if (authenticatedAdmin != null) {
                    System.out.println("Admin authentication successful.");
                    AdminControls();
                } else {
                    System.out.println("Admin authentication failed. Invalid username or password.");
                }
                break;

            case TEACHER:
                Teacher authenticatedTeacher = collegeSystem.authenticateTeacher(username, password);
                if (authenticatedTeacher != null) {
                    System.out.println("Teacher authentication successful.");
                    TeacherControls(authenticatedTeacher);
                } else {
                    System.out.println("Teacher authentication failed. Invalid username or password.");
                }
                break;

            case STUDENT:
                Student authenticatedStudent = collegeSystem.authenticateStudent(username, password);
                if (authenticatedStudent != null) {
                    System.out.println("Student authentication successful.");
                    StudentControls(authenticatedStudent);
                } else {
                    System.out.println("Student authentication failed. Invalid username or password.");
                }
                break;
        }
    }

    private static void LoginMenu() {
        System.out.println("LOGIN PAGE \n");
        System.out.println("LOGIN AS: ");
        System.out.println("1. Creator ");
        System.out.println("2. Admin");
        System.out.println("3. Teacher");
        System.out.println("4. Student");
        System.out.println("5. Exit");
    }

    private static void PavanMenu() {
        System.out.println("-4. Save the contents of the progam to txt files, only you can do this btw");
        System.out.println("-3. Display Asmins");
        System.out.println("-2. Display The information of a Teacher");
        System.out.println("-1. Display The information of a Student");
        System.out.println("0. Add Admin");
        System.out.println("1. Add Student");
        System.out.println("2. Add Teacher");
        System.out.println("3. Add Course");
        System.out.println("4. add Section");
        System.out.println("5. Enroll Student in Course");
        System.out.println("6. Assign Teacher to Course");
        System.out.println("7. Assign Teacher to Section");
        System.out.println("8. Assign Student to Section");
        System.out.println("9. Additional Options");
        System.out.println("10. Display Students");
        System.out.println("11. Display Teachers");
        System.out.println("12. Display Courses");
        System.out.println("13. Display Sections");
        System.out.println("14. logout");
        collegeSystem.saveData();
    }

    private static void AdminMenu() {
        System.out.println("1. Add Student");
        System.out.println("2. Add Teacher");
        System.out.println("3. Add Course");
        System.out.println("4. add Section");
        System.out.println("5. Enroll Student in Course");
        System.out.println("6. Assign Teacher to Course");
        System.out.println("7. Assign Teacher to Section");
        System.out.println("8. Assign Student to Section");
        System.out.println("9. Additional Options");
        System.out.println("10. Display Students");
        System.out.println("11. Display Teachers");
        System.out.println("12. Display Courses");
        System.out.println("13 Display Sections");
        System.out.println("14. logout");
        collegeSystem.saveData();

    }

    private static void TeacherMenu() {
        System.out.println("1. Display My Courses");
        System.out.println("2. Display My Sections");
        System.out.println("3. Set Personal Information");
        System.out.println("4. Display Personal Information");
        System.out.println("5. Change Password");
        System.out.println("6. logout");
        collegeSystem.saveData();
    }

    private static void StudentMenu() {
        System.out.println("1. Display My Courses");
        System.out.println("2. Display My Teachers");
        System.out.println("3. Set Personal Information");
        System.out.println("4. Display Personal Information");
        System.out.println("5. Change login credentials");
        System.out.println("6. logout");
        collegeSystem.saveData();
    }

    private static void InfoChangeMenu(){
        System.out.println("1. Change my EmailAddress");
        System.out.println("2. Change my Name");
        System.out.println("3. Change my PhoneNumber");
    }

    static void functions(int choice) {
        while (true) {
            switch (choice) {
                case 1:
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
                    System.out.print("Enter student section: ");
                    while (true) {
                        String Section = scanner.nextLine();
                        Section SectionToEnroll = getSectionByName(collegeSystem, Section);
                        if (SectionToEnroll != null) {
                            student.enrollInSection(SectionToEnroll);
                            System.out.println("Student has been successfully added to the section");
                            break;
                        } else {
                            System.out.println("Section not found, Enter Section again");
                        }
                    }
                    collegeSystem.addStudent(student);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
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

                    while (true) {
                        System.out.print("Assign a section *This is mandatory(or enter 'over' to finish): ");
                        String sectionNameToAssign = scanner.nextLine();
                        if (sectionNameToAssign.equals("over")) {
                            break;
                        }
                        Section sectionToAssign = getSectionByName(collegeSystem, sectionNameToAssign);
                        if (sectionToAssign != null) {
                            teacher.assignToSection(sectionToAssign);
                            System.out.println("Teacher Assigned to Section successfull");
                        } else {
                            System.out.println("Section not found, please check the section name again ");
                        }

                    }
                    while (true) {
                        System.out.print("Assign a course (or enter 'over' to finish): ");
                        String courseNameToAssign = scanner.nextLine();
                        if (courseNameToAssign.equals("over")) {
                            break;
                        }

                        Course courseToAssign = getCourseByName(collegeSystem, courseNameToAssign);
                        if (courseToAssign != null) {
                            teacher.assignToCourse(courseToAssign);
                            System.out.println("Teacher assigned to the course successfully!");
                        } else {
                            System.out.println("Course not found. Please check the course name.");
                        }
                    }
                    collegeSystem.addTeacher(teacher);
                    System.out.println("Teacher added successfully!");
                    break;

                case 3:
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
                    while (true) {
                        System.out.print("Assign a teacher (or enter 'over' to finish): ");
                        String teacherNameToAssign = scanner.nextLine();
                        if (teacherNameToAssign.equals("over")) {
                            break;
                        }

                        Teacher teacherToAssign = getTeacherByName(collegeSystem, teacherNameToAssign);
                        if (teacherToAssign != null) {
                            course.addTeacher(teacherToAssign);
                            System.out.println("Teacher assigned to the course successfully!");
                        } else {
                            System.out.println("Teacher not found. Please check the teacher name.");
                        }
                    }

                    collegeSystem.addCourse(course);
                    System.out.println("Course added successfully!");
                    break;

                case 4:
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
                    System.out.println("Section Created successfully");
                    break;
                case 5:
                    System.out.print("Enter student roll number: ");
                    String studentRollNo = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseNameToEnroll = scanner.nextLine();
                    Student studentToEnroll = getStudentByRollNo(collegeSystem, studentRollNo);
                    Course courseToEnroll = getCourseByName(collegeSystem, courseNameToEnroll);

                    if (studentToEnroll != null && courseToEnroll != null) {
                        studentToEnroll.enrollInCourse(courseToEnroll);
                        System.out.println("Student enrolled in the course successfully!");
                    } else {
                        System.out.println("Student or course not found. Please check the inputs.");
                    }
                    break;

                case 6:
                    System.out.print("Enter teacher name: ");
                    String teacherNameToAssign = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseNameToAssign = scanner.nextLine();

                    Teacher teacherToAssign = getTeacherByName(collegeSystem, teacherNameToAssign);
                    Course courseToAssign = getCourseByName(collegeSystem, courseNameToAssign);

                    if (teacherToAssign != null && courseToAssign != null) {
                        teacherToAssign.assignToCourse(courseToAssign);
                        System.out.println("Teacher assigned to the course successfully!");
                    } else {
                        System.out.println("Teacher or course not found. Please check the inputs.");
                    }
                    break;

                case 7:
                    System.out.println("Enter Teacher Name");
                    String teacherNameToAssignToSection = scanner.nextLine();
                    System.out.println("Enter Section Name");
                    String sectionNameToAssign = scanner.nextLine();

                    Teacher teachertoAssign = getTeacherByName(collegeSystem, teacherNameToAssignToSection);
                    Section sectionToAssign = getSectionByName(collegeSystem, sectionNameToAssign);

                    if (teachertoAssign != null && sectionToAssign != null) {
                        teachertoAssign.assignToSection(sectionToAssign);
                        System.out.println("Teacher assigned to the section successfully");
                    } else {
                        System.out.println("Teacher or Section not found, Please check the inputs are try again later");
                    }
                    break;

                case 8:
                    System.out.println("Enter Student Name");
                    String studentNameToAssignToSection = scanner.nextLine();
                    System.out.println("Enter Section Name");
                    String studentSection = scanner.nextLine();

                    Student StudenttoAssign = getStudentByName(collegeSystem, studentNameToAssignToSection);
                    Section sectionToAssignStudent = getSectionByName(collegeSystem, studentSection);

                    if (StudenttoAssign != null && sectionToAssignStudent != null) {
                        StudenttoAssign.enrollInSection(sectionToAssignStudent);
                        System.out.println("Student assigned to the section successfully");
                    } else {
                        System.out.println("Student or Section not found, Please check the inputs are try again later");
                    }
                    break;
                case 9:
                    AdditionalOptions additionalOptions = new AdditionalOptions(collegeSystem);
                    try {
                        additionalOptions.displayOptionsMenu();
                    } catch (Exception e) {

                    } finally {
                        System.out.println("I will implement this later, in the big file");
                    }
                    break;

                case 10:
                    System.out.println("List of Students:");
                    pavan_dynamic_array students = collegeSystem.getStudents();
                    for (int i = 0; i < students.size(); i++) {
                        Student Student = (Student) students.get(i);
                        System.out.println("Name: " + Student.getName());
                        System.out.println("Roll Number: " + Student.getRollNo());
                        System.out.println("Section: " + Student.getSection());
                        System.out.println("Enrolled Courses: " + Student.getEnrolledCourses().size());
                        System.out.println();
                    }
                    break;

                case 11:
                    System.out.println("List of Teachers:");
                    pavan_dynamic_array teachers = collegeSystem.getTeachers();
                    for (int i = 0; i < teachers.size(); i++) {
                        Teacher Teacher = (Teacher) teachers.get(i);
                        Teacher.displayTeacherInfo();
                        System.out.println("Assigned Courses: " + Teacher.getCourses().size());
                        System.out.println();
                    }
                    break;
                case 12:
                    System.out.println("List of Courses:");
                    pavan_dynamic_array courses = collegeSystem.getCourses();
                    for (int i = 0; i < courses.size(); i++) {
                        Course Course = (Course) courses.get(i);
                        System.out.println("Course Name: " + Course.getName());
                        System.out.println("Teachers: " + Course.getTeachers().size());
                        System.out.println("Enrolled Students: " + Course.getStudents().size());
                        System.out.println();
                    }
                    break;

                case 13:
                    System.out.println("List of Sections:");
                    pavan_dynamic_array sections = collegeSystem.getSections();
                    for (int i = 0; i < sections.size(); i++) {
                        Section Section = (Section) sections.get(i);
                        System.out.println("Section Name: " + Section.getName());
                        System.out.println("Teachers: " + Section.getTeachers().size());
                        System.out.println("Enrolled Students: " + Section.getStudents().size());
                        System.out.println();
                    }
                    break;
                case 14:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            if (CreatorLogged) {
                PavanMenu();
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 0) {
                    System.out.print("Enter Admin Name: ");
                    String adminName = scanner.nextLine();
                    System.out.print("Enter the Admin Password: ");
                    String adminPassword = scanner.nextLine();

                    Admin admin = new Admin(adminName, adminPassword);
                    collegeSystem.addAdmin(admin);
                    System.out.println("Admin added successfully");
                } else {
                    continue;
                }
            }
            if (AdminLogged) {
                AdminMenu();
                choice = scanner.nextInt();
                scanner.nextLine();
                continue;
            }
        }
    }

    private static void PavanControls() {
        System.out.println("Welcome Creator, This is the Super Menu");
        CreatorLogged = true;
        boolean exit = false;
        while (true) {
            PavanMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                System.out.print("Enter Admin Name: ");
                String adminName = scanner.nextLine();
                System.out.print("Enter the Admin Password: ");
                String adminPassword = scanner.nextLine();

                Admin admin = new Admin(adminName, adminPassword);
                collegeSystem.addAdmin(admin);
                System.out.println("Admin added successfully");

            }
            else if(choice == -2){
                boolean finder = false;
                System.out.println("Enter the rollNo of the teacher whose information you want");
                String teacherToSearch = scanner.nextLine();
                pavan_dynamic_array teacherArrayAgain = collegeSystem.getTeachers();
                for(int i = 0; i < teacherArrayAgain.size(); i++){
                    Teacher teacherAgain = (Teacher) teacherArrayAgain.get(i);
                    if(teacherAgain.getRollNo().equals(teacherToSearch)){
                        finder = true;
                        System.out.println("Name: " + teacherAgain.getName());
                        System.out.println("RollNo" + teacherAgain.getRollNo());
                        System.out.println("PhoneNo" + teacherAgain.getPhoneNo());
                        System.out.println("EmailAddress" + teacherAgain.getEmailID());
                        System.out.println("DateOfBirth" + teacherAgain.getDOB());
                    }
                }
                if(finder = false){
                    System.out.println("Teacher not found");
                } 
            } 
            else if(choice == -1){
                boolean finder = false;
                System.out.println("Enter the rollNo of the student whose information you want");
                String studentToSearch = scanner.nextLine();
                pavan_dynamic_array studentArrayAgain = collegeSystem.getStudents();
                for(int i = 0; i < studentArrayAgain.size(); i++){
                    Student studentAgain = (Student) studentArrayAgain.get(i);
                    if(studentAgain.getRollNo().equals(studentToSearch)){
                        finder = true;
                        System.out.println("Name: " + studentAgain.getName());
                        System.out.println("RollNo" + studentAgain.getRollNo());
                        System.out.println("PhoneNo" + studentAgain.getPhoneNo());
                        System.out.println("EmailAddress" + studentAgain.getEmailID());
                        System.out.println("DateOfBirth" + studentAgain.getDOB());
                    }
                }
                if(finder = false){
                    System.out.println("Student not found");
                } 

            }
            else if(choice == -3){
                System.out.println("This is a list of all the admins");

                pavan_dynamic_array AdminArray = collegeSystem.getAdmins();
                for(int i = 0; i < AdminArray.size(); i++){
                    Admin Admin = (Admin) AdminArray.get(i);
                    System.out.println("Name: " + Admin.getUsername());
                }
            }

            else if(choice == -4){
                System.out.println("Saving data into text files");
                collegeSystem.saveStateToFile("Students.txt", Student.class);
                collegeSystem.saveStateToFile("Teachers.txt", Teacher.class);
                collegeSystem.saveStateToFile("Sections.txt", Section.class);
                collegeSystem.saveStateToFile("Courses.txt", Course.class);
            }
            else {
                functions(choice);
                System.out.print("Are you sure you want to log out y/n");
                String response = scanner.nextLine();
                if (response.equals("y")) {
                    CreatorLogged = false;
                    return;
                } else {
                    continue;
                }
            }
        }
    }

    private static void AdminControls() {
        AdminLogged = true;
        System.out.println("Welcome Admin, This is Menu");
        boolean exit = false;
        while (true) {
            AdminMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            functions(choice);
            System.out.print("Are you sure you want to log out y/n");
            String response = scanner.nextLine();
            if (response.equals("y")) {
                AdminLogged = false;
                return;
            } else {
                continue;
            }
        }
    }

    private static void TeacherControls(Teacher teacher) {
        System.out.println("Logged in successfully");
        while (true) {
            TeacherMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
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
                    System.out.println("Which information do you want to change");
                    System.out.println("1. Change Name");
                    System.out.println("2. EmailAddress");
                    System.out.println("3. PhoneNo");
                    System.out.println("4. DateOfBirth");
                    int teacherChoice = scanner.nextInt();
                    scanner.nextLine();
                    TeacherStuff(teacherChoice, teacher);

                    break;

                case 4:
                    System.out.println("This is you personal Information");
                    System.out.println("Name: " + teacher.getName());
                    System.out.println("RollNo" + teacher.getRollNo());
                    System.out.println("PhoneNo" + teacher.getPhoneNo());
                    System.out.println("EmailAddress" + teacher.getEmailID());
                    System.out.println("DateOfBirth" + teacher.getDOB());
                    break;
                case 5:
                    System.out.println("Password change Menu");
                    String newPassword = scanner.nextLine();
                    teacher.setPassword(newPassword);
                    System.out.println("Password Successfully changed");
                    break;

                case 6:
                    System.out.println("Logging out now");
                    return;
            }
        }

    }

    private static void StudentControls(Student student) {
        System.out.println("Logged in successfully");
        while (true) {
            StudentMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
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

                        pavan_dynamic_array teachersOfThisSection = sectionIwant.getTeachers();
                        for (i = 0; i < teachersOfThisSection.size(); i++) {
                            Teacher teacher = (Teacher) teachersOfThisSection.get(i);
                            System.out.println(teacher.getName());
                        }
                    }
                    break;
                 case 3:
                    System.out.println("Which information do you want to change");
                    System.out.println("1. Change Name");
                    System.out.println("2. EmailAddress");
                    System.out.println("3. PhoneNo");
                    System.out.println("4. DateOfBirth");
                    int studentChoice = scanner.nextInt();
                    scanner.nextLine();
                    StudentStuff(studentChoice, student);
                case 4:
                    System.out.println("This is you personal Information");
                    System.out.println("Name: " + student.getName());
                    System.out.println("RollNo" + student.getRollNo());
                    System.out.println("PhoneNo" + student.getPhoneNo());
                    System.out.println("EmailAddress" + student.getEmailID());
                    System.out.println("DateOfBirth" + student.getDOB());
                    break;

                case 5:
                    System.out.println("Presently you can only change your password, deal with it later");
                    String newPassword = scanner.nextLine();
                    student.setPassword(newPassword);
                    System.out.println("Password Successfully changed");
                    break;

                case 6:
                    System.out.println("Logging out now");
                    return;
            }
        }
    }
    private static Student getStudentByRollNo(ManagementSystem collegeSystem, String rollNo) {
        pavan_dynamic_array students = collegeSystem.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student student = (Student) students.get(i);
            if (student.getRollNo().equals(rollNo)) {
                return student;
            }
        }
        return null;
    }

    private static Course getCourseByName(ManagementSystem collegeSystem, String courseName) {
        pavan_dynamic_array courses = collegeSystem.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course course = (Course) courses.get(i);
            if (course.getName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }

    private static Section getSectionByName(ManagementSystem collegeSystem, String sectionNameToAssign) {
        pavan_dynamic_array sections = collegeSystem.getSections();
        for (int i = 0; i < sections.size(); i++) {
            Section section = (Section) sections.get(i);
            if (section.getName().equals(sectionNameToAssign)) {
                return section;
            }
        }
        return null;
    }

    private static Student getStudentByName(ManagementSystem collegeSystem, String StudentName) {
        pavan_dynamic_array students = collegeSystem.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student student = (Student) students.get(i);
            if (student.getName().equals(StudentName)) {
                return student;
            }
        }
        return null;
    }

    private static Teacher getTeacherByName(ManagementSystem collegeSystem, String teacherName) {
        pavan_dynamic_array teachers = collegeSystem.getTeachers();
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = (Teacher) teachers.get(i);
            if (teacher.getName().equals(teacherName)) {
                return teacher;
            }
        }
        return null;
    }

    private static void TeacherStuff(int teacherChoice, Teacher teacher){
        if(teacherChoice == 1){
            System.out.println("You have opted to set your name");
            System.out.println("Enter the new name");
            String newName = scanner.nextLine();
            teacher.setName(newName);
        }
         if(teacherChoice == 2){
            System.out.println("You have opted to set your EmailAddress");
            System.out.println("Enter the EmailAddress");
            String newEmailAddress = scanner.nextLine();
            teacher.setEmailAddress(newEmailAddress);
        }
         if(teacherChoice == 3){
            System.out.println("You have opted to set your phoneNo");
            System.out.println("Enter the phoneNo");
            String newPhoneNo = scanner.nextLine();
            teacher.setPhoneNo(newPhoneNo);
        }
         if(teacherChoice == 4){
            System.out.println("You have opted to set your DateOfBirth");
            System.out.println("Enter the DateOfBirth");
            String newDOB = scanner.nextLine();
            teacher.setDateOfBirth(newDOB);
        }
        return;
    }

        private static void StudentStuff(int studentChoice, Student student){
        if(studentChoice == 1){
            System.out.println("You have opted to set your name");
            System.out.println("Enter the new name");
            String newName = scanner.nextLine();
            student.setName(newName);
        }
         if(studentChoice == 2){
            System.out.println("You have opted to set your EmailAddress");
            System.out.println("Enter the EmailAddress");
            String newEmailAddress = scanner.nextLine();
            student.setEmailAddress(newEmailAddress);
        }
         if(studentChoice == 3){
            System.out.println("You have opted to set your phoneNo");
            System.out.println("Enter the phoneNo");
            String newPhoneNo = scanner.nextLine();
            student.setPhoneNo(newPhoneNo);
        }
         if(studentChoice == 4){
            System.out.println("You have opted to set your DateOfBirth");
            System.out.println("Enter the DateOfBirth");
            String newDOB = scanner.nextLine();
            student.setDateOfBirth(newDOB);
        }
        return;
    }
    
}
