import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModifyDataBase {
    public static void main(String[] args) {
        try {
            String jdbcUrl  = "jdbc:mysql://localhost:3306/assignment10";
            String username = "root";
            String password = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            /***********************************************************************************************************/
            /* Requirement 1 */
            System.out.println("Requirement 1: ");
            ResultSet resultStudent = statement.executeQuery("SELECT COUNT(*) FROM Student");
            resultStudent.next();
            int studentCount = resultStudent.getInt(1);

            ResultSet resultEnrollment = statement.executeQuery("SELECT COUNT(*) FROM Enrollment");
            resultEnrollment.next();
            int enrollmentCount = resultEnrollment.getInt(1);

            ResultSet resultCourse = statement.executeQuery("SELECT COUNT(*) FROM Course");
            resultCourse.next();
            int courseCount = resultCourse.getInt(1);

            /* Check if table has data or not */
            if (studentCount == 0 && enrollmentCount == 0 && courseCount == 0) {
                statement.executeUpdate("INSERT INTO Student " + "VALUES (1, 'Long', 'Hai', '12/12/2023')");
                statement.executeUpdate("INSERT INTO Enrollment " + "VALUES (1, 'English', 'ABC', '5')");
                statement.executeUpdate("INSERT INTO Course " + "VALUES (1, 'English', 'Yes')");
            }
            else {
            	System.out.println("");
            }

            ResultSet resultStudent_ = statement.executeQuery("SELECT * FROM Student");
            while (resultStudent_.next()) {
            	System.out.println("Before change data ");
                System.out.println(resultStudent_.getInt(1) + " " + resultStudent_.getString(2) + " " + resultStudent_.getString(3) + " " + resultStudent_.getString(4));
            }

            ResultSet resultEnrollment_ = statement.executeQuery("SELECT * FROM Enrollment");
            while (resultEnrollment_.next()) {
                System.out.println(resultEnrollment_.getInt(1) + " " + resultEnrollment_.getString(2) + " " + resultEnrollment_.getString(3) + " " + resultEnrollment_.getString(4));
            }

            ResultSet resultCourse_ = statement.executeQuery("SELECT * FROM Course");
            while (resultCourse_.next()) {
                System.out.println(resultCourse_.getInt(1) + " " + resultCourse_.getString(2) + " " + resultCourse_.getString(3));
            }

            /***********************************************************************************************************/
            /* Requirement 2 */
            System.out.println("\n" + "Requirement 2: ");
            int studentId = 1;
            String newEnrollmentDate = "2023-12-17";

            String updateQuery = "UPDATE Student SET EnrollmentDate = '" + newEnrollmentDate + "' WHERE id = " + studentId;
            statement.executeUpdate(updateQuery);

            ResultSet resultStudent__ = statement.executeQuery("SELECT * FROM Student");
            while (resultStudent__.next()) {
            	System.out.println("After change data ");
            	System.out.println("Updated EnrollmentDate for Student with ID " + studentId);
                System.out.println(resultStudent__.getInt(1) + " " + resultStudent__.getString(2) + " " + resultStudent__.getString(3) + " " + resultStudent__.getString(4));
            }

            /***********************************************************************************************************/
            /* Requirement 3 */
            System.out.println("\n" + "Requirement 3: ");
            connection.setAutoCommit(false);
            statement.executeUpdate("INSERT INTO Course VALUES (2, 'Math', 'No')");
            System.out.println("Added a new row to Course.");
            ResultSet resultCourse__ = statement.executeQuery("SELECT * FROM Course");
            while (resultCourse__.next()) {
                System.out.println(resultCourse__.getInt(1) + " " + resultCourse__.getString(2) + " " + resultCourse__.getString(3));
            }

            connection.rollback();
            System.out.println("After rolled back data in Course.");
            ResultSet resultCourse___ = statement.executeQuery("SELECT * FROM Course");
            while (resultCourse___.next()) {
                System.out.println(resultCourse___.getInt(1) + " " + resultCourse___.getString(2) + " " + resultCourse___.getString(3));
            }

            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
