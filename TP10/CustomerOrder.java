import java.util.Calendar;
import java.util.Date;
public class CustomerOrder {
    private Customer customer;
    private String food;
    private double price;
    private Date durationServe;
    private Date startServe;
    public Date getStartServe() {
        return this.startServe;
    }

    public void setStartServe(Date startServe) {
        this.startServe = startServe;
    }
    private Date servDate;

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getFood() {
        return this.food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDurationServe() {
        return this.durationServe;
    }

    public void setDurationServe(Date durationServe) {
        this.durationServe = durationServe;
    }

    public Date getServDate() {
        return this.servDate;
    }

    public void setServDate(Date servDate) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(getStartServe());
        } catch (Exception e) {
        }
        cal.add(Calendar.MILLISECOND,-((int)servDate.getTime()));
        this.servDate = cal.getTime();
    }
    
    @Override
    public String toString(){
        return "Customer{No:"+getCustomer().getNumber()+",StartServe:"+getStartServe()+",End Serve:"+getServDate()+",Status"+customer.getStatus().toString()+
        ",Food:"+getFood()+",Pice:"+getPrice()+",Duration Serve:"+getDurationServe()+"}";
    }

}
