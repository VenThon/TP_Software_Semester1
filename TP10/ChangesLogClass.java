import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class ChangesLogClass {
    public static final SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
    public String toString(){
        String Atlast5row = " ";
        SimpleDateFormat dbFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic-c?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery("(SELECT * FROM dateutil ORDER BY dateid DESC LIMIT 5) ORDER BY dateid ASC;");
            System.out.println("Connected to DB.");
            System.out.println("Containing last 5 changes in date history");
            System.out.println("ID\tdate start\tdate end\tn days\toperation type\tchanged date");
            while (rs.next()) {
                Atlast5row += rs.getInt("dateId")
                        + "\t" + dbFormat.format(rs.getDate("date_start"))
                        + "\t" + dbFormat.format(rs.getDate("end_date"))
                        + "\t" + rs.getInt("n_days")
                        + "\t" + rs.getString("operation_type")
                        + "\t" + rs.getString("changedate") + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Atlast5row;
    }

    public void changeByDate(Date searchDate) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic-c?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(
                    "SELECT * FROM dateutil WHERE changedate LIKE '" + dbFormat.format(searchDate) + "%'");
            System.out.println("Connected to DB.");
            System.out.println("Listing changes by date");
            System.out.println("Your search date is " + dbFormat.format(searchDate));
            while (result.next()) {
                System.out.println("ID: " + result.getInt("dateId"));
                System.out.println("Date Start: " + result.getString("date_start"));
                System.out.println("Date End: " + result.getString("end_date"));
                System.out.println("Number days: " + result.getInt("n_days"));
                System.out.println("Operation type: " + result.getString("operation_type"));
                System.out.println("Change date: " + result.getString("changedate"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeByWeek(Date searchDate) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic-c?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM dateutil WHERE YEARWEEK(`changedate`, 1) = YEARWEEK('"
                    + dbFormat.format(searchDate) + "', 1);");
            System.out.println("Connected to DB.");
            System.out.println("Listing changes by week");
            System.out.println("Your search date is " + dbFormat.format(searchDate));
            while (result.next()) {
                System.out.println("ID: " + result.getInt("dateId"));
                System.out.println("Date Start: " + result.getString("date_start"));
                System.out.println("Date End: " + result.getString("end_date"));
                System.out.println("Number days: " + result.getInt("n_days"));
                System.out.println("Operation type: " + result.getString("operation_type"));
                System.out.println("Change date: " + result.getString("changedate"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeByDateRange(Date searchDateStart, Date searchDateEnd) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic-c?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt
                    .executeQuery("SELECT * FROM dateutil WHERE changedate >= '" + dbFormat.format(searchDateStart)
                            + "' " + "AND changedate <= '" + dbFormat.format(searchDateEnd) + "'");
            System.out.println("Connected to DB.");
            System.out.println("Listing changes by date range");
            while (result.next()) {
                System.out.println("ID: " + result.getInt("dateId"));
                System.out.println("Date Start: " + result.getString("date_start"));
                System.out.println("Date End: " + result.getString("end_date"));
                System.out.println("Number days: " + result.getInt("n_days"));
                System.out.println("Operation type: " + result.getString("operation_type"));
                System.out.println("Change date: " + result.getString("changedate"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllChange() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic-c?user=root&password=")) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM dateutil");
            System.out.println("Connected to DB.");
            System.out.println("List all changes");
            while (result.next()) {
                System.out.println("ID: " + result.getInt("dateId"));
                System.out.println("Date Start: " + result.getString("date_start"));
                System.out.println("Date End: " + result.getString("end_date"));
                System.out.println("Number days: " + result.getInt("n_days"));
                System.out.println("Operation type: " + result.getString("operation_type"));
                System.out.println("Change date: " + result.getString("changedate"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

class TestChangesLogClass{
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

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
       ChangesLogClass clc = new ChangesLogClass();
       int choice;
    //    Date searchDate = inputDateToOperate();
    //    Date searchDateStart = inputDateToOperate();
    //    Date searchDateEnd = inputDateToOperate();
       while (true) {
        System.out.println("1.Override toString() method eturns ast 5 changes");
        System.out.println("2.Listing changes by date");
        System.out.println("3.Listing changes by week");
        System.out.println("4.Listing changes by date range");
        System.out.println("5.List all changes");
        System.out.println("6.Exit");
        System.out.print("Enter your choice option: ");
        choice = Integer.parseInt(sc.nextLine());
        if(choice==1){
            System.out.println(clc.toString());
        }else if(choice==2){
            Date searchDate = inputDateToOperate();
            clc.changeByDate(searchDate);
        }
        else if(choice==3){
            Date searchDate = inputDateToOperate();
            clc.changeByWeek(searchDate);
        }else if(choice==4){
            System.out.println("Enter date to change start: ");
            Date searchDateStart = inputDateToOperate();
            System.out.println("Enter date to change end:");
            Date searchDateEnd = inputDateToOperate();
        }else if(choice==5){
            clc.getAllChange();
        }else if(choice==6){
             System.out.println("Good bye!");
            break;
        }else{
             System.out.println("Wrong input. Try Again");
         }
    }
    }
}
