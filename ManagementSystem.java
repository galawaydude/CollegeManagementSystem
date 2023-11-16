
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ManagementSystem implements Serializable {
    private pavan_dynamic_array students;
    private pavan_dynamic_array teachers;
    private pavan_dynamic_array courses;
    private pavan_dynamic_array sections;

    public ManagementSystem() {
        students = new pavan_dynamic_array(1);
        teachers = new pavan_dynamic_array(1);
        courses = new pavan_dynamic_array(1);
        sections = new pavan_dynamic_array(1);
    }



    
    public void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            out.writeObject(students);
            out.writeObject(teachers);
            out.writeObject(courses);
            out.writeObject(sections);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"))) {
            students = (pavan_dynamic_array) in.readObject();
            teachers = (pavan_dynamic_array) in.readObject();
            courses = (pavan_dynamic_array) in.readObject();
            sections = (pavan_dynamic_array) in.readObject();
            System.out.println("Data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
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

    public void addSection(Section section){
        sections.add(section);
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

    public pavan_dynamic_array getSections(){
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
}
