import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DateUtil {
    private Date date_start;
    private Date date_end;
    private int numberDate;
    private String operation_type;
    private String changed_date;

    public static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    // public static final SimpleDateFormat dbFormat = new
    // SimpleDateFormat("yyyy-MM-dd");

    public DateUtil() {

    }

    public String getOperation_type() {
        return this.operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public String getChanged_date() {
        return this.changed_date;
    }

    public void setChanged_date(String changed_date) {
        this.changed_date = changed_date;
    }

    public Date getDate_start() {
        return this.date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return this.date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getNumberDate() {
        return this.numberDate;
    }

    public void setnumberDate(int numberDate) {
        this.numberDate = numberDate;
    }

    public void inputDate() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a start date(dd/MM/yyyy): ");
        try {
            setDate_start(new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine()));
        } catch (Exception err) {
            System.out.println("You have been input wrong format!");
        }
        System.out.print("Enter a end date(dd/MM/yyyy): ");
        try {
            setDate_end(new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine()));
        } catch (Exception err) {
            System.out.println("You have been input wrong format!");
        }
    }

    public void subtractDate() {
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d1.setTime(date_start);
        d2.setTime(date_end);
        int daysBetween = (int) ChronoUnit.DAYS.between(d1.toInstant(), d2.toInstant());
        if (daysBetween < 0) {
            numberDate = daysBetween - daysBetween;
        } else {
            numberDate = daysBetween;
        }
        setOperation_type("Subtraction");
    }

    public void increaseDate() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a start date(dd/MM/yyyy): ");
        try {
            setDate_start(new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine()));
        } catch (Exception err) {
            System.out.println("You have been input wrong format!");
        }
        System.out.print("Enter number of days for increase: ");
        int numberday = sc.nextInt();
        if (!String.valueOf(numberday).equals(null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date_start);
            cal.add(Calendar.DAY_OF_MONTH, numberday);
            date_end = cal.getTime();

            setnumberDate(numberday);
            setDate_end(date_end);
            setOperation_type("Increment");
            creatDatabaseAndTable();
            insertDataToDatabase();
            System.out.println("All of information are stored into Database:");
        } else {
            System.out.println("You need to input only integer of number of days!");
        }
    }

    public void getData() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4gic", "root", null);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM dateutil");
            System.out.println("Connected to Database.");
            System.out.println("\nAll of information from Database:");
            System.out.println("_________________________________");
            while (result.next()) {
                System.out.println("ID: " + result.getInt("dateid"));
                System.out.println("Date Start: " + result.getString("date_start"));
                System.out.println("Date End: " + result.getString("end_date"));
                System.out.println("Number days: " + result.getInt("n_day"));
                System.out.println("Operation type: " + result.getString("operation_type"));
                System.out.println("Change date: " + result.getString("changed_date"));
            }
        } catch (SQLException e) {
            System.out.println("I can't select from the Database!");
        }
    }

    public void creatDatabaseAndTable() {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            stmt = conn.createStatement();
            stmt.executeUpdate("create database IF NOT EXISTS `i4gic`;");
            stmt.executeUpdate("use `i4gic`");
            stmt.executeUpdate("create table IF NOT EXISTS `Dateutil`(DateId int not null auto_increment primary key,"
                    + "date_start DATE, end_date DATE, n_day int,operation_type VARCHAR(30),changed_date DATETIME);");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDataToDatabase() {
        Connection conn = null;
        SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("use `i4gic`");
            stmt.executeUpdate(
                    "INSERT INTO dateutil(date_start, end_date, n_day, operation_type, changed_date) VALUES( " +
                            "'" + dbFormat.format(date_start) + "', " +
                            "'" + dbFormat.format(date_end) + "', " +
                            numberDate + ", " +
                            "'" + operation_type + "', " +
                            "NOW());");
            System.out.println("Connected to Database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "StartDate= " + getDate_start() +
                "\nEndDate= " + getDate_end() +
                "\nNumberDate= " + getNumberDate() +
                "\nOperation type= " + getOperation_type() +
                "\nChange date= " + getChanged_date();
    }
}

class MainDate {
    public static void main(String[] args) throws Exception {
        int choice;
        DateUtil dateUtil = new DateUtil();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n________________________________________________");
            System.out.println("\n1. Subtract date with start date and end date");
            System.out.println("2. Increase date with start date and number of day");
            System.out.println("3. Display all data from Database");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                dateUtil.inputDate();
                dateUtil.subtractDate();
                dateUtil.creatDatabaseAndTable();
                dateUtil.insertDataToDatabase();
                System.out.println("All of information are stored into Database:");
            } else if (choice == 2) {
                dateUtil.increaseDate();
            } else if (choice == 3) {
                dateUtil.getData();
            } else {
                System.out.println("Good luck!");
                break;
            }
        }
    }
}