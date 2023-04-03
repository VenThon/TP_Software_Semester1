import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Customer {
    private Integer number = null;//auto incremented
    private Date date_enter= null;// date and time of entering into queue
    private OrderStatus status = OrderStatus.NONE;

    public Customer(){

    }

    public Customer(Integer number, Date date_enter, OrderStatus status) {
        this.number = number;
        this.date_enter = date_enter;
        this.status = status;
    }
    public Customer(Integer number, Date date_enter) {
        this(number, date_enter, OrderStatus.NONE);
    }

    public Customer(Integer number) {
        this(number, null, OrderStatus.NONE);
    }
    public void dataInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number: ");
        setNumber(Integer.parseInt(sc.nextLine()));
        System.out.println("Input date(dd/MM/yyyy H:m:s): ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:m:s");
        try {
            setDate_enter(dateFormat.parse(sc.nextLine()));
        } catch (ParseException e) {
            System.err.println("Invalid date and time format.");
        }
        setStatus(OrderStatus.valueOf("WAITING_FOR_ORDERING"));
    }
    public void loadData(Connection conn, int number) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `customers` WHERE number = "+number);
        if(rs.next()){
            setNumber(number);
            setDate_enter(rs.getDate("date_enter"));
            setStatus(OrderStatus.valueOf(rs.getString("status")));
        }
    }
    public String toString() {
        return "Customer{" +
                "number=" + number +
                ", date_enter=" + date_enter +
                ", status=" + status +
                '}';
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate_enter() {
        return date_enter;
    }

    public void setDate_enter(Date date_enter) {
        this.date_enter = date_enter;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
enum OrderStatus{NONE, WAITING_TO_ORDER, WAITING_FOR_FOOD,WAITING_FOR_ORDERING, ORDERING}
