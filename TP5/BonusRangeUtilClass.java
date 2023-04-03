import java.util.Scanner;

public class BonusRangeUtilClass {
        Scanner sc = new Scanner(System.in);
        private int i;
        private int start;
        private int end;
        private int step;

        public void inputRange(){
            System.out.print("Enter a value of start: ");
            start = sc.nextInt();
            System.out.print("Enter a value of end: ");
            end = sc.nextInt();
            System.out.print("Enter a value of step: ");
            step = sc.nextInt();
        }

        public void findRange() {
            System.out.print("\nType integer: ");
            if(start < end){
                for (i = start; i <= end; i = i+ step){
                    System.out.printf("%d ", i);
                }
            }
            else {
                for (i = start; i >= end ; i = i - step){
                    System.out.printf("%d ", i);
                }
            }
        }

        public void convertToString() {
            System.out.printf("\nType a string: ");
            if (start < end) {
                for (i = start; i <= end; i = i + step) {
                    String strNum = "" + i;
                    System.out.printf("%s ", strNum);
                }
            }
            else {
                for (i = start; i >= end; i = i - step) {
                    String strNum = "" + i;
                    System.out.printf("%s ", strNum);
                }
            }
        }
    
}
class Range {
    public static void main(String[] args) {
        BonusRangeUtilClass r1 = new BonusRangeUtilClass();
        r1.inputRange();
        r1.findRange();
        r1.convertToString();
    } 
}
