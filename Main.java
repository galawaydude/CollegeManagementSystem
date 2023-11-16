import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManagementSystem collegeSystem = new ManagementSystem();
        collegeSystem.loadData();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (true) {
            System.out.println("College Management System Menu:");
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
            System.out.println("14. Exit");
            System.out.print("Enter your choice: ");
            collegeSystem.saveData();
            int choice = scanner.nextInt();
            scanner.nextLine();

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
                    // see, if you want to add the option, of assigning teachers, while the section
                    // is getting created or late, the code is not very hard, i can do it later.
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
                    System.out.println("Exiting College Management System.");
                    scanner.close();
                    exit = true;

                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");

                    if (exit) {
                        continue;
                    }
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
}
