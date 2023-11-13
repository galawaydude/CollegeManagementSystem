public class Teacher {
    private static int temp = 100;
    private String name;
    private String rollNo;
    private pavan_dynamic_array courses;
    private pavan_dynamic_array sections;

    public Teacher(String name) {
        this.name = name;
        this.rollNo = generateTeacherRollNumber();
        courses = new pavan_dynamic_array(2);
        sections = new pavan_dynamic_array(2);
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

    public pavan_dynamic_array getSections() {
        return sections;
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
    public void setSection(String section) {
        sections.add(section);
    }
    public void displayTeacherInfo() {
        System.out.println("Teacher Name: " + name);
        System.out.println("Roll Number: " + rollNo);
        System.out.println("Sections Taught: ");
        for (int i = 0; i < sections.size(); i++) {
            String section = (String) sections.get(i);
            System.out.println("- " + section);
        }
    }
}