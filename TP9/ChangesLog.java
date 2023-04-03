import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class ChangesLog {
    public static final SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String toString() {
        String last5row = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery("(SELECT * FROM dateutil ORDER BY dateid DESC LIMIT 5) ORDER BY dateid ASC;");
            System.out.println("Connected to DB.");
            System.out.println("Containing last 5 changes in date history");
            System.out.println("ID\tdate start\tdate end\tn days\toperation type\tchanged date");
            while (rs.next()) {
                last5row += rs.getInt("dateid")
                        + "\t" + dateFormat.format(rs.getDate("date_start"))
                        + "\t" + dateFormat.format(rs.getDate("end_date"))
                        + "\t" + rs.getInt("n_day")
                        + "\t" + rs.getString("operation_type")
                        + "\t" + rs.getString("changed_date") + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return last5row;
    }

    public void changeByDate(Date searchDate) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(
                    "SELECT * FROM dateutil WHERE changed_date LIKE '" + dbFormat.format(searchDate) + "%'");
            System.out.println("Connected to DB.");
            System.out.println("Listing changes by date");
            System.out.println("Your search date is " + dbFormat.format(searchDate));
            while (result.next()) {
                System.out.println("ID: " + result.getInt("dateid"));
                System.out.println("Date Start: " + result.getString("date_start"));
                System.out.println("Date End: " + result.getString("end_date"));
                System.out.println("Number days: " + result.getInt("n_day"));
                System.out.println("Operation type: " + result.getString("operation_type"));
                System.out.println("Change date: " + result.getString("changed_date"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public void changeByWeek(Date searchDate) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM dateutil WHERE YEARWEEK(`changed_date`, 1) = YEARWEEK('"
                    + dbFormat.format(searchDate) + "', 1);");
            System.out.println("Connected to DB.");
            System.out.println("Listing changes by week");
            System.out.println("Your search date is " + dbFormat.format(searchDate));
            while (result.next()) {
                System.out.println("ID: " + result.getInt("dateid"));
                System.out.println("Date Start: " + result.getString("date_start"));
                System.out.println("Date End: " + result.getString("end_date"));
                System.out.println("Number days: " + result.getInt("n_day"));
                System.out.println("Operation type: " + result.getString("operation_type"));
                System.out.println("Change date: " + result.getString("changed_date"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public void changeByDateRange(Date searchDateStart, Date searchDateEnd) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt
                    .executeQuery("SELECT * FROM dateutil WHERE changed_date >= '" + dbFormat.format(searchDateStart)
                            + "' " + "AND changed_date <= '" + dbFormat.format(searchDateEnd) + "'");
            System.out.println("Connected to DB.");
            System.out.println("Listing changes by date range");
            while (result.next()) {
                System.out.println("ID: " + result.getInt("dateid"));
                System.out.println("Date Start: " + result.getString("date_start"));
                System.out.println("Date End: " + result.getString("end_date"));
                System.out.println("Number days: " + result.getInt("n_day"));
                System.out.println("Operation type: " + result.getString("operation_type"));
                System.out.println("Change date: " + result.getString("changed_date"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public void getAllChange() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM dateutil");
            System.out.println("Connected to DB.");
            System.out.println("List all changes");
            while (result.next()) {
                System.out.println("ID: " + result.getInt("dateid"));
                System.out.println("Date Start: " + result.getString("date_start"));
                System.out.println("Date End: " + result.getString("end_date"));
                System.out.println("Number days: " + result.getInt("n_day"));
                System.out.println("Operation type: " + result.getString("operation_type"));
                System.out.println("Change date: " + result.getString("changed_date"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

}

class MainChangesLog {
    public static Date inputDateToOperate() {
        Scanner sc = new Scanner(System.in);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Enter a date to operate(dd/MM/yyyy): ");
        try {
            date = format.parse(sc.nextLine());
        } catch (Exception err) {
            System.out.println("You have been input wrong format!");
        }
        return date;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ChangesLog logs = new ChangesLog();
        while (true) {
            System.out.println("""
                    \n\t\tChange Log
                    \t\t__________
                    1. Returns a last 5 changes in date history
                    2. Listing changes by date
                    3. Listing changes by week
                    4. Listing changes by date range
                    5. List all changes
                    6. Quit
                    """);
            System.out.print("Enter your option: ");
            int op = sc.nextInt();
            if (op == 1) {
                System.out.println(logs.toString());
            } else if (op == 2) {
                Date searchDate = inputDateToOperate();
                logs.changeByDate(searchDate);
            } else if (op == 3) {
                Date searchDate = inputDateToOperate();
                logs.changeByWeek(searchDate);
            } else if (op == 4) {
                System.out.println("Input date of changes from.");
                Date searchDateStart = inputDateToOperate();
                System.out.println("Input date of changes until.");
                Date searchDateEnd = inputDateToOperate();
                logs.changeByDateRange(searchDateStart, searchDateEnd);
            } else if (op == 5) {
                logs.getAllChange();
            } else {
                break;
            }
        }
    }
}
