import java.io.Serializable;

public class Student  implements Serializable {
    private static int  temp = 100;
    private String name;
    private String rollNo;
    private String section;
    private String EmailAddress = ""; // this is not being printed properly
    private String PhoneNo = "";
    private String DateOfBirth = "";
    private String Password;
    private pavan_dynamic_array enrolledCourses;

    public Student(String name) {
        this.name = name;
        this.rollNo = generateStudentRollNumber();
        this.EmailAddress = generateEmailId();
        this.Password = "Password";
        enrolledCourses = new pavan_dynamic_array(2);
    }

    public String getPhoneNo(){
        return PhoneNo;
    }

    public String getDOB(){
        return DateOfBirth;
    }
    public String getEmailID(){
        return EmailAddress;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String newPassword){
        this.Password = newPassword;
    }
    public String getPassword(){
        return Password;
    }

    public void setName(String newName){
        this.name = newName;
    }

      public void setEmailAddress(String newEmailAddress){
        this.EmailAddress = newEmailAddress;
    }

      public void setPhoneNo(String newPhoneNo){
        this.PhoneNo = newPhoneNo;
    }
      public void setDateOfBirth(String newDataOfBirth){
        this.DateOfBirth = newDataOfBirth;
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
        emailid = emailid.replaceAll("\\s", "");
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
