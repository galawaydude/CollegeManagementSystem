import java.io.Serializable;

public class Section implements Serializable {
    private String name;
    private pavan_dynamic_array teachers;
    private pavan_dynamic_array students;

    public Section(String name) {
        this.name = name;
        teachers = new pavan_dynamic_array(1);
        students = new pavan_dynamic_array(1);
    }

    public String getName() {
        return name;
    }

    public pavan_dynamic_array getTeachers() {
        return teachers;
    }

    public pavan_dynamic_array getStudents() {
        return students;
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void displayStudentInfo(){
        System.out.println("Section Name" + name);
        System.out.println("Teachers: ");
        for(int i = 0; i < teachers.size(); i++){
            Teacher teacher = (Teacher) teachers.get(i);
            System.out.println("- " + teacher.getName());
        }
        System.out.println("Enrolled Students: ");
        for(int i = 0; i < students.size(); i++){
            Student student = (Student) students.get(i);
            System.out.println("- " + student.getName());
        }
    }
}