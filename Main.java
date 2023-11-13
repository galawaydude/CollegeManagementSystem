import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManagementSystem collegeSystem = new ManagementSystem();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (true) {
            System.out.println("College Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Course");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. Assign Teacher to Course");
            System.out.println("6. Additional Options");
            System.out.println("7. Display Students");
            System.out.println("8. Display Teachers");
            System.out.println("9. Display Courses");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter student section: ");
                    String studentSection = scanner.nextLine();
                    pavan_dynamic_array Students = collegeSystem.getStudents();
                    for (int i = 0; i < Students.size(); i++) {
                        Student Student = (Student) Students.get(i);
                        while (Student.getName().equals(studentName)) {
                            System.out.println("There is already a student with the similar name, if there is a different student with the same name, enter yes, and continue, else, if its a mistake enter no");
                            String response = scanner.nextLine();
                            if (response.equals("yes")) {
                                break;
                            }
                            else{
                                System.out.println("Enter teacher name again");
                                studentName = scanner.nextLine();
                            }
                        }
                    }


                    Student student = new Student(studentName, studentSection);
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
                            System.out.println("There is already a teacher with the similar name, if there is a different teacher with the same name, enter yes, and continue, else, if its a mistake enter no");
                            String response = scanner.nextLine();
                            if (response.equals("yes")) {
                                break;
                            }
                            else{
                                System.out.println("Enter teacher name again");
                                teacherName = scanner.nextLine();
                            }
                        }
                    }
                    Teacher teacher = new Teacher(teacherName);

                    while (true) {
                        System.out.print("Assign a section *This is mandatory(or enter 'over' to finish): ");
                        String teacherSection = scanner.nextLine();
                        if (teacherSection.equals("over")) {
                            break;
                        }

                        teacher.setSection(teacherSection);
                        System.out.println("Section assigned to the teacher successfully!");
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

                case 5:
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

                case 6:
                    AdditionalOptions additionalOptions = new AdditionalOptions(collegeSystem);
                    try{
                    additionalOptions.displayOptionsMenu();
                    }catch(Exception e){

                    }finally{
                        System.out.println("I will implement this later, in the big file");
                    }
                    break;

                case 7:
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

                case 8:
                    System.out.println("List of Teachers:");
                    pavan_dynamic_array teachers = collegeSystem.getTeachers();
                    for (int i = 0; i < teachers.size(); i++) {
                        Teacher Teacher = (Teacher) teachers.get(i);
                        Teacher.displayTeacherInfo();
                        System.out.println("Assigned Courses: " + Teacher.getCourses().size());
                        System.out.println();
                    }
                    break;
                case 9:
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
                case 10:
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
