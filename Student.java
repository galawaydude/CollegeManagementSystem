import java.io.Serializable;

public class Student  implements Serializable {
    private static int  temp = 100;
    private String name;
    private String rollNo;
    private String section;
    private String EmailAddress;
    private String Password;
    private pavan_dynamic_array enrolledCourses;

    public Student(String name) {
        this.name = name;
        this.rollNo = generateStudentRollNumber();
        this.EmailAddress = generateEmailId();
        this.Password = "Password";
        enrolledCourses = new pavan_dynamic_array(2);
    }

    public String getName() {
        return name;
    }

    public String getPassword(){
        return Password;
    }


    public String getRollNo() {
        return rollNo;
    }

    public String getSection() {
        return section;
    }

    public pavan_dynamic_array getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInSection(Section section){
        this.section = section.getName();
        section.addStudent(this);
    }
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
        course.addStudent(this);
    }

    private String generateEmailId(){
        String emailid = this.name + "@bmu.edu.in";
        return emailid;
    }

    public void displayStudentInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("Roll Number: " + rollNo);
        System.out.println("Section: " + section);
        System.out.println("Enrolled Courses: ");
        for (int i = 0; i < enrolledCourses.size(); i++) {
            Course course = (Course) enrolledCourses.get(i);
            System.out.println("- " + course.getName());
        }
    }

    private String generateStudentRollNumber() {
        String rollNumber =  "BML" + 2022 + "Stu" + temp;
        temp++;
        return rollNumber;
    }

}
