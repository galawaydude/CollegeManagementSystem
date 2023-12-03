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
        url = "jdbc:postgresql://localhost:5432/JavaProject";
        username = "postgres";
        password = "%Fortress123&";
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
        }
    }

    public String getCreatorUsername() {
        String creatorUsername = " ";
        String stuffToExecute = "SELECT username FROM public.users WHERE role = 'creator'";
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
        String stuffToExecute = "SELECT password FROM public.users WHERE role = 'creator'";
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
    }

    public void StudentCourse(String RollNo, String CourseName) {
        String stuffToExecute = "INSERT INTO STUDENT_COURSE (student_rollNo, course_name) VALUES (?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, RollNo);
            pst.setString(2, CourseName);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void TeacherCourse(String RollNo, String CourseName) {
        String stuffToExecute = "INSERT INTO TEACHER_COURSE (teacher_rollNo, course_Name) VALUES (?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, RollNo);
            pst.setString(2, CourseName);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void TeacherSection(String RollNo, String SectionName) {
        String stuffToExecute = "INSERT INTO TEACHER_SECTION (teacher_rollNo, section_name) VALUES (?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, RollNo);
            pst.setString(2, SectionName);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
    }

    public void updateCredentials(String StudentName, String newPassword, String column) {
        String stuffToExecute = "UPDATE users SET " + column + " = ? WHERE username = ?";
        try {
            PreparedStatement pst = con.prepareStatement(stuffToExecute);
            pst.setString(1, newPassword);
            pst.setString(2, StudentName);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
                System.out.println("---------------------");
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
}
