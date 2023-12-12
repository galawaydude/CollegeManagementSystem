
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

enum UserRole {
    CREATOR,
    ADMIN,
    TEACHER,
    STUDENT
}

public class ManagementSystem implements Serializable {
    private pavan_dynamic_array students;
    private pavan_dynamic_array teachers;
    private pavan_dynamic_array courses;
    private pavan_dynamic_array sections;
    private pavan_dynamic_array admins;

    public ManagementSystem() {
        students = new pavan_dynamic_array(1);
        teachers = new pavan_dynamic_array(1);
        courses = new pavan_dynamic_array(1);
        sections = new pavan_dynamic_array(1);
        admins = new pavan_dynamic_array(1);
    }


    public void saveData() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));// see why you have to encapsulate this
            out.writeObject(students);
            out.writeObject(teachers);
            out.writeObject(courses);
            out.writeObject(sections);
            out.writeObject(admins);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadData() {
        try {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"));
            students = (pavan_dynamic_array) in.readObject();
            teachers = (pavan_dynamic_array) in.readObject();
            courses = (pavan_dynamic_array) in.readObject();
            sections = (pavan_dynamic_array) in.readObject();
            admins = (pavan_dynamic_array) in.readObject();
            System.out.println("Data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
    public void FileStateDeleter(String filename) {
        try (FileOutputStream writer = new FileOutputStream(filename)) {
            writer.write(("").getBytes());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveStateToFile(String filename, Class<?> classItBelongsTo) { 
        try{ 
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            if (classItBelongsTo.equals(Student.class)) {
                saveStudents(writer);
            } else if (classItBelongsTo.equals(Teacher.class)) {
                saveTeachers(writer);
            } else if (classItBelongsTo.equals(Course.class)) {
                saveCourses(writer);
            } else if (classItBelongsTo.equals(Section.class)) {
                saveSections(writer);
            } else {
                System.out.println("Class not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveStudents(BufferedWriter writer) {
        try{
        writer.write("Students:");
        writer.newLine();
        for (int i = 0; i < students.size(); i++) {
            Student student = (Student) students.get(i);
            writer.write("Name: " + student.getName());
            writer.newLine();
            writer.write("Roll No: " + student.getRollNo());
            writer.newLine();
            writer.write("Section: " + student.getSection());
            writer.newLine();
            writer.write("Courses Enrolled in: ");

            pavan_dynamic_array enrolledCourses = student.getEnrolledCourses();
            for (int j = 0; j < enrolledCourses.size(); j++) {
                Course course = (Course) enrolledCourses.get(j);
                writer.write(course.getName() + ", ");
            }
            writer.newLine();
            writer.newLine();
        }
        }catch(IOException e){
            System.out.println("error saving data");
        }
    }

    private void saveTeachers(BufferedWriter writer) {
        try{
        writer.write("Teachers: ");
        writer.newLine();

        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = (Teacher) teachers.get(i);
            writer.write("Name: " + teacher.getName());
            writer.newLine();
            writer.write("Roll No: " + teacher.getRollNo());
            writer.newLine();
            writer.write("Sections teaching: ");
            pavan_dynamic_array sectionsTaught = teacher.getSections();
            for (int j = 0; j < sectionsTaught.size(); j++) {
                Section section = (Section) sectionsTaught.get(j);
                writer.write(section.getName() + ", ");
            }
            writer.newLine();
            writer.write("Courses Teaching: ");
            pavan_dynamic_array coursesTaught = teacher.getCourses();
            for (int k = 0; k < coursesTaught.size(); k++) {
                Course course = (Course) coursesTaught.get(k);
                writer.write(course.getName() + ", ");
            }
            writer.newLine();
            writer.newLine();
        }
    }catch(IOException e){
        System.out.println("error saving data");
    }
    }

    private void saveCourses(BufferedWriter writer){
        try{
        writer.write("Courses: ");
        writer.newLine();
        for (int i = 0; i < courses.size(); i++) {
            Course course = (Course) courses.get(i);
            writer.write("Name: " + course.getName());
            writer.newLine();
            writer.write("Teachers Teaching: ");
            pavan_dynamic_array teachersTeaching = course.getTeachers();
            for (int j = 0; j < teachersTeaching.size(); j++) {
                Teacher teacher = (Teacher) teachersTeaching.get(j);
                writer.write(teacher.getName() + ", ");
            }

            writer.newLine();
            writer.write("Students Enrolled: ");
            pavan_dynamic_array studentsEnrolled = course.getStudents();
            for (int j = 0; j < studentsEnrolled.size(); j++) {
                Student student = (Student) studentsEnrolled.get(j);
                writer.write(student.getName() + ", ");
            }
            writer.newLine();
            writer.newLine();
        }
    }catch(IOException e){
        System.out.println("error saving code");
    }
    }

    private void saveSections(BufferedWriter writer){
        try{
        writer.write("Sections:");
        writer.newLine();
        for (int i = 0; i < sections.size(); i++) {
            Section section = (Section) sections.get(i);
            writer.write("Name: " + section.getName());
            writer.newLine();
            writer.write("Teachers Teaching: ");
            pavan_dynamic_array teachersTeachingSection = section.getTeachers();
            for (int j = 0; j < teachersTeachingSection.size(); j++) {
                Teacher teacher = (Teacher) teachersTeachingSection.get(j);
                writer.write(teacher.getName() + ", ");
            }
            writer.newLine();
            writer.write("Students in Section: ");
            pavan_dynamic_array studentsInSection = section.getStudents();
            for (int j = 0; j < studentsInSection.size(); j++) {
                Student student = (Student) studentsInSection.get(j);
                writer.write(student.getName() + ", ");
            }
            writer.newLine();
            writer.newLine();
        }
    }catch(IOException e){
        System.out.println("error saving data");
    }
    }

    public Student authenticateStudent(String username, String password) {
        for (int i = 0; i < students.size(); i++) {
            Student student = (Student) students.get(i);
            if (student.getName().equals(username) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    public Teacher authenticateTeacher(String username, String password) {
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = (Teacher) teachers.get(i);
            if (teacher.getName().equals(username) && teacher.getPassword().equals(password)) {
                return teacher;
            }
        }
        return null;
    }

    public Admin authenticateAdmin(String username, String password) {
        for (int i = 0; i < admins.size(); i++) {
            Admin admin = (Admin) admins.get(i);
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public pavan_dynamic_array getAdmins() {
        return admins;
    }

    public pavan_dynamic_array getStudents() {
        return students;
    }

    public pavan_dynamic_array getTeachers() {
        return teachers;
    }

    public pavan_dynamic_array getCourses() {
        return courses;
    }

    public pavan_dynamic_array getSections() {
        return sections;
    }

    public pavan_dynamic_array getTeachersOfSection(String section) {
        pavan_dynamic_array teachersOfStudent = new pavan_dynamic_array(1);
        for (int i = 0; i < students.size(); i++) {
            Student student = (Student) students.get(i);
            if (student.getSection().equals(section)) {
                pavan_dynamic_array enrolledCourses = student.getEnrolledCourses();
                for (int j = 0; j < enrolledCourses.size(); j++) {
                    Course course = (Course) enrolledCourses.get(j);
                    pavan_dynamic_array courseTeachers = course.getTeachers();
                    for (int k = 0; k < courseTeachers.size(); k++) {
                        Teacher teacher = (Teacher) courseTeachers.get(k);
                        if (!teachersOfStudent.contains(teacher)) {
                            teachersOfStudent.add(teacher);
                        }
                    }
                }
            }
        }
        return teachersOfStudent;
    }

    //-----------------------------alternate way
     public pavan_dynamic_array getTeacherofSec(String section) {
        pavan_dynamic_array TeacherofSec = new pavan_dynamic_array(1);
        for (int i = 0; i < sections.size(); i++) {
            Teacher section = (Section) sections.get(i);
            if (section.getName().equals(section)) {
                TeacherofSec.addAll(section.getTeachers());
            }
        }
        return coursesTaughtByTeacher;
    }
  //-----------------------------      


    public pavan_dynamic_array getCoursesTaughtByTeacher(String teacherRollNo) {
        pavan_dynamic_array coursesTaughtByTeacher = new pavan_dynamic_array(1);
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = (Teacher) teachers.get(i);
            if (teacher.getRollNo().equals(teacherRollNo)) {
                coursesTaughtByTeacher.addAll(teacher.getCourses());
            }
        }
        return coursesTaughtByTeacher;
    }

    public pavan_dynamic_array getTeachersOfCourse(String courseName) {
        pavan_dynamic_array teachersOfCourse = new pavan_dynamic_array(1);
        for (int i = 0; i < courses.size(); i++) {
            Course course = (Course) courses.get(i);
            if (course.getName().equals(courseName)) {
                teachersOfCourse.addAll(course.getTeachers());
            }
        }
        return teachersOfCourse;
    }

    public Section getSectionByName(String sectionName) {
        pavan_dynamic_array sections = getSections();
        for (int i = 0; i < sections.size(); i++) {
            Section section = (Section) sections.get(i);
            if (section.getName().equals(sectionName)) {
                return section;
            }
        }
        return null;
    }

    public Course getCourseByName(String courseName) {
        pavan_dynamic_array courses = getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course course = (Course) courses.get(i);
            if (course.getName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }

    public void StudentDeleter(String RollNo){
        System.out.println("You are deleting a student");
        for(int i = 0; i < students.size(); i++){
            Student student = (Student) students.get(i);
            if(student.getRollNo().equals(RollNo)){
                students.remove(student);
                System.out.println("Student has been successfully deleted");
                return;
            }
        }
    }

    public void TeacherDeleter(String rollNo){
        System.out.println("You are deleting a teacher");
        for(int i = 0; i < teachers.size(); i++){
            Teacher teacher = (Teacher) teachers.get(i);
            if(teacher.getRollNo().equals(rollNo)){
                teachers.remove(teacher);
                System.out.println("Teacher has been successfully deleted");
                return;
            }
        }
    }

    public void SectionDeleter(String Name){
        System.out.println("You are deleting a section");
        for(int i = 0; i < sections.size(); i++){
            Section section = (Section) sections.get(i);
            if(section.getName().equals(Name)){
                sections.remove(section);
                System.out.println("Section has been successfully deleted");
                return;
            }
        }
    }
    public void CourseDeleter(String Name){
        System.out.println("You are deleting a course");
        for(int i = 0; i < courses.size(); i++){
            Course course = (Course) courses.get(i);
            if (course.getName().equals(Name)){
                courses.remove(course);
                System.out.println("Course has been successfully deleted");
            }
        }
    }
}
