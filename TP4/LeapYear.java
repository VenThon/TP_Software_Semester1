import java.util.Scanner;
public class LeapYear {
    private int year;

    public LeapYear(int year) {
        this.year = year;
    }
    public LeapYear(){}

    public int getYear() {
        return this.year;
    }
    public void setYear(int Year) {
        this.year = Year;
    }
    public boolean isValid(){
        if(this.year<0){
            return false;
        }
        return true;
    }
    public boolean isleap(){
        if(!isValid()) return false;
        if(year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0){
                return true;
            }else{
                return false;
            }
            }else{
                return true;
            }
       }else{
            return false;
       }
    }
    public static void main(String[] args) {
        try (Scanner h = new Scanner(System.in)) {
            System.out.print("Please Input Years: ");
            int y = h.nextInt();
            LeapYear newyYear= new LeapYear(y);
            //System.out.println(newyYear.getYear());
            if(!newyYear.isValid()){
                System.out.println(+newyYear.getYear()+" invalid year!");
            }else if(newyYear.isleap()){
                System.out.println(+newyYear.getYear()+" It is a leap year!");
            }else{
                System.out.println(+newyYear.getYear()+" It is not a Leap year!!");
            }
        }

    }
    

}
