import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
public class CustomerQueue {
    private Scanner sc = new Scanner(System.in);
    private Deque<Customer> customers= new LinkedList<>();

    
    public Deque<Customer> getCustomers() {
        return this.customers;
    }
    public void setCustomers(Deque<Customer> customers) {
        this.customers = customers;
    }
    public int getCustomerCount(){
        return  customers.size();
    }
    public void addNewCustomer(){
        if(getCustomerCount()<=100){
            Customer customer =new Customer();
            customer.dataInput();
            customers.add(customer);
        }else{
            System.out.println("Please give them waiting or update some status");
        }
    }
    // public void removeCustomer(CustomerOrder customerO){
    //     Customer customer = customers.getFirst();
    //     OrderStatus WAITING_FOR_FOOD=OrderStatus.valueOf("ORDERING");
    //     customer.setStatus(WAITING_FOR_FOOD);
    //     customerO.setCustomer(customer);
    //     customers.removeFirst();
    // }
    public void serveCustomer(CustomerOrder customerO){
        Customer customer = customerO.getCustomer();
        OrderStatus WAITING_FOR_FOOD=OrderStatus.valueOf("WAITING_FOR_FOOD");
        customer.setStatus(WAITING_FOR_FOOD);
    }

    public void displayCustomerInQueue(){
        for (Customer customer : customers) {
            System.out.println("Customer{No:"+customer.getNumber()+",Date:"+customer.getDate_enter().getTime()+",Status:"+customer.getStatus().toString());
        }
    }
}
