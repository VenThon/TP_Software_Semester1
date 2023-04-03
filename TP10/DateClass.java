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

public class DateClass {
    private Date StartDate;
    private Date EndDate;
    private int NumberDate;
    private String Opreration;
    private Date ChangeDate;

    public int getNumberDate(){
        return this.NumberDate;
    }

    public void setNumberDate(int NumberDate){
        this.NumberDate = NumberDate;
    }

    public Date getStartDate() {
        return this.StartDate;
    }
    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return this.EndDate;
    }

    public void setEndDate(Date EndDate) {
       if(getStartDate().compareTo(EndDate)<0){
            this.EndDate = EndDate;
       }
    }
    public String getOpreration() {
        return Opreration;
    }

    public void setOpreration(String opreration) {
        Opreration = opreration;
    }

    public Date getChangeDate() {
        return ChangeDate;
    }

    public void setChangeDate(Date changeDate) {
        ChangeDate = changeDate;
    }
    
    public void subtract(Date startday,Date endday) {
        Calendar d1=Calendar.getInstance();
        Calendar d2=Calendar.getInstance();
        d1.setTime(StartDate);
        d2.setTime(EndDate);
        int daysBetween = (int) ChronoUnit.DAYS.between(d1.toInstant(), d2.toInstant());
        if(daysBetween<0){
            NumberDate=daysBetween-daysBetween;
        }else{
            NumberDate=daysBetween;
        }
        setOpreration("subtraction");
    }

    public void increaseDate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input start date by format(dd/mm/yyyy):");
        try {
            setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine()));
        } catch (Exception err) {
            System.out.println("Input wrong format");
        }
        System.out.println("Enter number of day: ");
        int numberday = sc.nextInt();
        if(!String.valueOf(numberday).equals(null)){
            Calendar cal = Calendar.getInstance();
            cal.setTime(StartDate);
            cal.add(Calendar.DAY_OF_MONTH,numberday);
            EndDate=cal.getTime();

            setNumberDate(numberday);
            setEndDate(EndDate);
            setOpreration("increment");
            createDateBaseAndTable();
            insertDateToDataBase(StartDate, EndDate, numberday);
            System.out.println("stored data");
        }else{
            System.out.println("Input only inter number");
        }
        
    } 

    public void getData() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("use `i4GIC-C`");

            ResultSet result =  stmt.executeQuery("select * from Dateutil");
            while (result.next()) {
                System.out.println("{id:"+result.getString("DateId")+
                                    ",startdate:"+result.getString("date_start")+
                                    ",enddate:"+result.getString("end_date")+
                                    ",numberdays:"+result.getInt("n_days")+
                                    ",Operation type: "+result.getString("operation_type")+
                                    ",Change Date: "+result.getString("changedate")+
                                    "}");
            }            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputDate() throws Exception{
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Input start date by format(dd/mm/yyyy):");
        try {
            setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine()));
        } catch (Exception err) {
            System.out.println("Input wrong format");
        }
        System.out.println("Input end date by formart(dd/mm/yyyy):");
        try {
            setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine()));
            
        } catch (Exception err) {
            System.out.println("Input wrong format");
        }
        subtract(getStartDate(), getEndDate());
        createDateBaseAndTable();
        insertDateToDataBase(getStartDate(), getEndDate(), getNumberDate());

    } 
    public void createDateBaseAndTable(){
        Statement stmt = null;
        Connection conn = null;
        try {
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("connecting Database");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
            stmt = conn.createStatement();
            stmt.executeUpdate("create database IF NOT EXISTS `i4GIC-C`;");
            stmt.executeUpdate("use `i4GIC-C`");
            stmt.executeUpdate("create table IF NOT EXISTS `Dateutil`(DateId int not null auto_increment primary key,"+
            "date_start DATE, end_date DATE,n_days int,operation_type VARCHAR(30),changedate DATETIME);");
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } 
    public void insertDateToDataBase(Date Startdate,Date Enddate,int numberdays){
        SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
        Connection conn = null;
        if(!String.valueOf(numberdays).equals(null)||!String.valueOf(Startdate).equals(null)){
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("use `i4GIC-C`");
                stmt.executeUpdate("insert into `Dateutil`(date_start, end_date,n_days,operation_type,changedate) values("+
                                                                                                                                "'"+dbFormat.format(Startdate)+"',"+
                                                                                                                                "'"+dbFormat.format(Enddate)+"',"+
                                                                                                                                NumberDate+"," + 
                                                                                                                                "'"+Opreration+"',"+
                                                                                                                                "NOW());");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public String toString() {
        return "{" +
            " StartDate='" + getStartDate() + "'" +
            ", EndDate='" + getEndDate() + "'" +
            ", NumberDate='" + getNumberDate() + "'" +
            "}";
    }

}
class MainDate{
    public static void main(String[] args) throws Exception{
        int choice ;
        DateClass dateUtil = new DateClass();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Input of Date");
            System.out.println("2.Retrive and display");
            System.out.println("3.Increase date with date number and display");
            System.out.println("4.Exit");
            System.out.print("Enter your choice option: ");
            choice = Integer.parseInt(sc.nextLine());
            if(choice==1){
                dateUtil.inputDate();
            }else if(choice==2){
                dateUtil.getData();
            }
            else if(choice==3){
                dateUtil.increaseDate();
            }else if(choice==4){
                 System.out.println("Good bye!");
                break;
            }else{
                 System.out.println("Wrong input. Try Again");
             }
        }
    }
}


