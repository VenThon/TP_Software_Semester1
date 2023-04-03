import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
public class PopularStore {
    Connection conn;
    Statement stmt;
    Scanner sc = new Scanner(System.in);
    public Connection getConn() {
        return this.conn;
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return this.stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    private Deque<CustomerOrder> customerOrder = new LinkedList<>();

    public Deque<CustomerOrder> getCustomerOrder() {
        return this.customerOrder;
    }

    public void setCustomerOrder(Deque<CustomerOrder> customerOrder) {
        this.customerOrder = customerOrder;
    }
    public void insertDatabse(CustomerOrder cus){
        try {
            getStmt().executeUpdate("insert into `Customers`(`start_serving`,`end_serving`,`status`,`food`,`price`,`duration`) values("
            +cus.getStartServe()+",DATETIME '"+cus.getServDate()+"',"+cus.getCustomer().getStatus().toString()+","+cus.getFood()+","+cus.getPrice()+",DATETIME '"+cus.getDurationServe()+"')");
        } catch (SQLException cerr) {
            System.err.println(cerr.getMessage());
        }

    }
    public void updateDB(CustomerOrder customer){
        try {
            getStmt().executeUpdate("update `Customer` set `food`="+customer.getFood()+",`status`="+customer.getCustomer().getStatus().toString()+",`start_serving`=DATETIME '"+customer.getStartServe()+"',end_serving=DATETIME '"+customer.getServDate()+"',duration=DATETIME '"+customer.getDurationServe()+
            "'where Customerno="+customer.getCustomer().getNumber()+";");
        } catch (SQLException e) {
            System.err.println();
        }
    }
    public void displayCustomerHistory(){
        for (CustomerOrder customerOrder2 : customerOrder) {
           System.out.println(customerOrder2.toString()); 
        }
        if(customerOrder.isEmpty()){
            System.out.println("There is no record");
        }

    }
    public void loadData() throws Exception{
        try {
            ResultSet result =  getStmt().executeQuery("select * from Customers");
            while (result.next()) {
                CustomerOrder customer = new CustomerOrder();
                Customer customer2 = new Customer();
                customer2.setNumber(result.getInt("Customerno"));
                customer2.setStatus(OrderStatus.valueOf(result.getString("status").toUpperCase()));
                customer.setCustomer(customer2);
                customer.setFood(result.getString("food"));
                Date duration = new SimpleDateFormat("yyyy-MM-dd H:m:s").parse(result.getString("duration"));
                customer.setDurationServe(duration);
                customer.setStartServe(new SimpleDateFormat("yyyy-MM-dd H:m:s").parse(result.getString("start_serving")));
                customer.setServDate(new SimpleDateFormat("yyyy-MM-dd H:m:s").parse(result.getString("end_serving")));
                customer.setPrice(result.getDouble("price"));
                customerOrder.add(customer);
            }
            if(!result.next()){
                System.out.println("There is no data in record");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateCustomerInList(OrderStatus status){
        
        System.out.println("Search user by id:");
        int id = Integer.parseInt(sc.nextLine());
        CustomerOrder p =gCustomerOrder(id);
        if(p!=null){   
            p.getCustomer().setStatus(status);
        }else{
            System.out.println("There is no customer's id:"+id);
        }
    }
    public CustomerOrder gCustomerOrder(int id){
        for (CustomerOrder customerOrder2 : customerOrder) {
            if(customerOrder2.getCustomer().getNumber()==id){
                return customerOrder2;
            }
        }
        return null;
    }
    public void readMenu(){
        System.out.println("1.Add customer to waiting list (queue) for serving");
        System.out.println("2. Display list of customers in queue");
        System.out.println("3.List all served customers (orders history)");
        System.out.println("4.Get customers count");
        System.out.println("5.Exit ");
        System.out.print("Enter the choice:");
    }
    public void closeDatabase(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void createDatabse(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
            Statement statement= conn.createStatement();
            setStmt(statement);
            setConn(conn);
            getStmt().executeUpdate("create database if not exists `i4GIC-C`;");
            getStmt().executeUpdate("use `i4GIC-C`;");
            getStmt().executeUpdate("create table if not exists `Customers`(Customerno Integer auto_increment primary key,"+
            "start_serving DATETIME,end_serving DATETIME,status varchar(100),food varchar(100),price decimal(13,2),duration DATETIME);");
        } catch (Exception e) {
            System.out.println("Cant connect to database");
        }
    }
}
class MainPopularStore{
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int choice ;
        PopularStore store = new PopularStore();
        CustomerQueue customer =new CustomerQueue();
        while (true) {
            store.createDatabse();
            store.readMenu();
            choice = Integer.parseInt(sc.nextLine());
            if(choice==1){
                customer.addNewCustomer();
            }else if(choice==2){
                customer.displayCustomerInQueue();
            }else if(choice==3){
                store.displayCustomerHistory();
            }else if(choice==5){
                customer.getCustomerCount();
            }
            else if(choice==5){
                System.out.println("Exit");
                store.closeDatabase();
                break;

            }
        }
    }
}
