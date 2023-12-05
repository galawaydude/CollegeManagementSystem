import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

;

public class jdbcStuff {
    private Connection con = null;
    private String url;
    private String password;
    private String username;

    jdbcStuff() {
        int counter = 0;
        credentials details[] = credentials.values();
        for (credentials i : details) {
            if (counter == 0) {
                url = i.getValue();
            }
            if (counter == 1) {
                password = i.getValue();
            }
            if (counter == 2) {
                username = i.getValue();
            }
            counter++;
        }
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
        }
    }

    public String getCreatorUsername() {
        String creatorUsername = " ";
        String stuffToExecute = "SELECT username FROM users WHERE role = 'creator'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(stuffToExecute);
            rs.next();
            creatorUsername = rs.getString(1); 
            return creatorUsername;
        } catch (SQLException e) {
            System.out.println("dude there is an error");
        }
        return creatorUsername;
    }

    public String getCreatorPassword() {
        String creatorPassword = " ";
        String stuffToExecute = "SELECT password FROM users WHERE role = 'creator'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(stuffToExecute);
            rs.next();
            creatorPassword = rs.getString(1);
            return creatorPassword;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creatorPassword;
    }

    public void insertUser(String username, String password, String role) {
        String stuffToExecute = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStudent(String name, String RollNo, String section, String emailAddress) {
        String stuffToExecute = "INSERT INTO STUDENT (name, rollNo, section, emailaddress) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, name);
            pst.setString(2, RollNo);
            pst.setString(3, section);
            pst.setString(4, emailAddress);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write("A new student was added " + "Name: " + name);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Writing info failed");
        }

    }

    public void insertTeacher(String name, String RollNo, String emailAddress) {
        String stuffToExecute = "INSERT INTO TEACHER (name, rollNo, emailaddress) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, name);
            pst.setString(2, RollNo);
            pst.setString(3, emailAddress);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write("A new Teacher was added " + "Name: " + name);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Writing info failed");
        }
    }

    public void insertCourse(String name) {
        String stuffToExecute = "INSERT INTO Course (name) VALUES (?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("dude, there has been an error");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write("A new course was added " + "Name: " + name);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Writing info failed");
        }
    }

    public void insertSection(String name) {
        String stuffToExecute = "INSERT INTO Section (name) VALUES (?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, name);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write("A new Section was added " + "Name: " + name);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Writing info failed");
        }
    }

    public boolean StudentCourse(String RollNo, String CourseName) {
        String stuffToExecute = "INSERT INTO STUDENT_COURSE (student_rollNo, course_name) VALUES (?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, RollNo);
            pst.setString(2, CourseName);
            pst.executeUpdate();
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                writer.write("Student was assigned a course" + "StudentRollNo: " + RollNo + "Course: " + CourseName);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Writing info failed");
            }
    
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    

    public boolean TeacherCourse(String RollNo, String CourseName) {
        String stuffToExecute = "INSERT INTO TEACHER_COURSE (teacher_rollNo, course_Name) VALUES (?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, RollNo);
            pst.setString(2, CourseName);
            pst.executeUpdate();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                writer.write("Teacher was assigned a course" + "TeacherRollNo: " + RollNo + "Course: " + CourseName);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Writing info failed");
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean TeacherSection(String RollNo, String SectionName) {
        String stuffToExecute = "INSERT INTO TEACHER_SECTION (teacher_rollNo, section_name) VALUES (?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, RollNo);
            pst.setString(2, SectionName);
            pst.executeUpdate();
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                writer.write("Teacher was assigned a section" + "TeacherRollNo: " + RollNo + "Section: " + SectionName);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Writing info failed");
            }
    
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public void updateTeacherStuff(String TeacherRollNo, String newInfo, String column) {
        String stuffToExecute = "UPDATE teacher SET " + column + " = ? WHERE rollno = ?";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, newInfo);
            pst.setString(2, TeacherRollNo);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write("Teacher information updated" + "TeacherRollNo: " + TeacherRollNo + column + "Changed to "
                    + "NewInfo " + newInfo);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Writing info failed");
        }
    }

    public void updateStudentStuff(String StudentRollNo, String newInfo, String column) {
        String stuffToExecute = "UPDATE student SET " + column + " = ? WHERE rollno = ?";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, newInfo);
            pst.setString(2, StudentRollNo);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write("Student information updated" + "StudentRollNo: " + StudentRollNo + column + "Changed to"
                    + "NewInfo " + newInfo);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Writing info failed");
        }
    }

    public void updateCredentials(String Name, String newPassword, String column) {
        String stuffToExecute = "UPDATE users SET " + column + " = ? WHERE username = ?";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, newPassword);
            pst.setString(2, Name);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write("UserCredentials updated" + "Name: " + Name + column + "Changed " + "NewInfo " + newPassword);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Writing info failed");
        }
    }

    public void displayStudentsOfSection(String sectionName) {
        String stuffToRun = "SELECT * FROM Student WHERE section = ?";

        try {
            PreparedStatement pst = con.prepareStatement(stuffToRun);
            pst.setString(1, sectionName);
            ResultSet rs = pst.executeQuery();

            System.out.println("Students in Section " + sectionName + ":");
            while (rs.next()) {
                String name = rs.getString("name");
                String rollNo = rs.getString("rollNo");
                String emailAddress = rs.getString("emailAddress");
                String phoneNo = rs.getString("phoneNo");
                String dateOfBirth = rs.getString("dateOfBirth");

                System.out.println("Name: " + name);
                System.out.println("Roll No: " + rollNo);
                System.out.println("Email Address: " + emailAddress);
                System.out.println("Phone No: " + phoneNo);
                System.out.println("Date of Birth: " + dateOfBirth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayTeachersOfCourse(String courseName) {
        String stuffToRun = "SELECT Teacher.* FROM Teacher " +
                "JOIN Teacher_Course ON Teacher.rollNo = Teacher_Course.teacher_rollNo " +
                "JOIN Course ON Teacher_Course.course_name = Course.name " +
                "WHERE Course.name = ?";

        try {
            PreparedStatement pst = con.prepareStatement(stuffToRun);
            pst.setString(1, courseName);
            ResultSet rs = pst.executeQuery();

            System.out.println("Teachers for Course " + courseName + ":");
            while (rs.next()) {
                String name = rs.getString("name");
                String rollNo = rs.getString("rollNo");
                String emailAddress = rs.getString("emailAddress");
                String phoneNo = rs.getString("phoneNo");
                String dateOfBirth = rs.getString("dateOfBirth");

                System.out.println("Name: " + name);
                System.out.println("Roll No: " + rollNo);
                System.out.println("Email Address: " + emailAddress);
                System.out.println("Phone No: " + phoneNo);
                System.out.println("Date of Birth: " + dateOfBirth);
                System.out.println("---------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void displayCoursesOfStudent(String studentRollNo) {
        String stuffToRun = "SELECT Course.* FROM Course " +
                "JOIN Student_Course ON Course.name = Student_Course.course_name " +
                "JOIN Student ON Student_Course.student_rollNo = Student.rollNo " +
                "WHERE Student.rollNo = ?";

        try {
            PreparedStatement pst = con.prepareStatement(stuffToRun);
            pst.setString(1, studentRollNo);
            ResultSet rs = pst.executeQuery();

            System.out.println("Courses for Student with Roll No " + studentRollNo + ":");
            while (rs.next()) {
                String name = rs.getString("name");

                System.out.println("Course Name: " + name);
                System.out.println("---------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayTeachersOfSection(String sectionName) {
        String stuffToRun = "SELECT Teacher.* FROM Teacher " +
                "JOIN teacher_section ON Teacher.rollNo = teacher_section.teacher_rollNo " +
                "JOIN Section ON teacher_section.section_name = Section.name " +
                "WHERE Section.name = ?";

        try {
            PreparedStatement pst = con.prepareStatement(stuffToRun);
            pst.setString(1, sectionName);
            ResultSet rs = pst.executeQuery();

            System.out.println("Teachers for Section " + sectionName + ":");
            if (!rs.next()) {
                System.out.println("No teachers found for the section.");
            } else {
                do {
                    String name = rs.getString("name");
                    String rollNo = rs.getString("rollNo");
                    String emailAddress = rs.getString("emailAddress");
                    String phoneNo = rs.getString("phoneNo");
                    String dateOfBirth = rs.getString("dateOfBirth");

                    System.out.println("Teacher Name: " + name);
                    System.out.println("Roll No: " + rollNo);
                    System.out.println("Email Address: " + emailAddress);
                    System.out.println("Phone No: " + phoneNo);
                    System.out.println("Date of Birth: " + dateOfBirth);
                    System.out.println("---------------------");
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayCourselessTeachers() {
        String stuffToRun = "SELECT Teacher.* FROM Teacher " +
                "LEFT JOIN teacher_course ON Teacher.rollno = teacher_course.teacher_rollno " +
                "WHERE teacher_course.teacher_rollno IS NULL";

        try {
            PreparedStatement pst = con.prepareStatement(stuffToRun);
            ResultSet rs = pst.executeQuery();

            System.out.println("Teachers with No Courses:");
            while (rs.next()) {
                String name = rs.getString("name");
                String rollNo = rs.getString("rollNo");
                String emailAddress = rs.getString("emailAddress");
                String phoneNo = rs.getString("phoneNo");
                String dateOfBirth = rs.getString("dateOfBirth");

                System.out.println("Teacher Name: " + name);
                System.out.println("Roll No: " + rollNo);
                System.out.println("Email Address: " + emailAddress);
                System.out.println("Phone No: " + phoneNo);
                System.out.println("Date of Birth: " + dateOfBirth);
                System.out.println("---------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayTeachersTeachingMultipleSections() {
        String stuffToRun = "SELECT Teacher.name, COUNT(teacher_section.id) AS sectionCount " +
                "FROM Teacher " +
                "JOIN teacher_section ON Teacher.rollno = teacher_section.teacher_rollno " +
                "GROUP BY Teacher.rollno " +
                "HAVING COUNT(teacher_section.id) > 1";

        try {
            PreparedStatement pst = con.prepareStatement(stuffToRun);
            ResultSet rs = pst.executeQuery();

            System.out.println("Teachers teaching multiple sections:");
            while (rs.next()) {
                String teacherName = rs.getString("name");
                int sectionCount = rs.getInt("sectionCount");
                System.out.println("Teacher Name: " + teacherName + ", Section Count: " + sectionCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteStudent(String rollNo) {
        String stuffToExecute = "DELETE FROM Student WHERE rollNo = ?";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, rollNo);
            pst.executeUpdate();
    
            System.out.println("Student with Roll No " + rollNo + " deleted successfully.");
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                writer.write("Student deleted" + "StudentRollNo: " + rollNo);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Writing info failed");
            }
    
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteTeacher(String rollNo) {
        String stuffToExecute = "DELETE FROM Teacher WHERE rollNo = ?";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, rollNo);
            pst.executeUpdate();
    
            System.out.println("Teacher with Roll No " + rollNo + " deleted successfully.");
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                writer.write("Teacher deleted" + "TeacherRollNo: " + rollNo);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Writing info failed");
            }
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    

    public boolean deleteSection(String name) {
        String stuffToExecute = "DELETE FROM Section WHERE name = ?";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, name);
            pst.executeUpdate();
    
            System.out.println("Section with Name " + name + " deleted successfully.");
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                writer.write("Section deleted" + "Section: " + name);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Writing info failed");
            }
    
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteCourse(String name) {
        String stuffToExecute = "DELETE FROM Course WHERE name = ?";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, name);
            pst.executeUpdate();
    
            System.out.println("Course with Name " + name + " deleted successfully.");
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                writer.write("Course deleted" + "Course: " + name);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Writing info failed");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    

}
