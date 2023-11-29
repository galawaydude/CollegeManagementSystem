import java.io.Serializable;

public class Teacher implements Serializable{
    private static int temp = 100;
    private String name;
    private String rollNo;
    private String EmailAddress = "";
    private String PhoneNo = "";
    private String DateOfBirth = "";
    public  String Password;
    private pavan_dynamic_array courses;
    private pavan_dynamic_array sections;

    public Teacher(String name) {
        this.name = name;
        this.rollNo = generateTeacherRollNumber();
        this.EmailAddress = generateEmailId();
        this.Password = "Password";  
        courses = new pavan_dynamic_array(2);
        sections = new pavan_dynamic_array(2);
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

    public String getRollNo() {
        return rollNo;
    }

    public pavan_dynamic_array getCourses() {
        return courses;
    }
    public String getPassword(){
        return Password;
    }

    public pavan_dynamic_array getSections() {
        return sections;
    }

    public void setPassword(String newPassword){
        this.Password = newPassword;
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
    public void assignToSection(Section section){
        sections.add(section);
        section.addTeacher(this);
    }
    public void assignToCourse(Course course) {
        courses.add(course);
        course.addTeacher(this);
    }

    private String generateTeacherRollNumber() {
        String rollNumber =  "BML" + 2022 + "tch" + temp;
        temp++;
        return rollNumber;
    }

    private String generateEmailId(){
        String emailid = this.name + "@bmu.edu.in";
        return emailid;
    }
    public void displayTeacherInfo() {
        System.out.println("Teacher Name: " + name);
        System.out.println("Roll Number: " + rollNo);
        System.out.println("Sections Taught: ");
        for (int i = 0; i < sections.size(); i++) {
            Section section = (Section) sections.get(i);
            System.out.println(section.getName());
        }
    }
}
