import java.util.Scanner;
 class positive {
    private int num;
    public positive(int num) {
        this.num = num;
    }
    public void is_positive() {
        for (int i = 1; i <= num; i++) {
            if (i % 2 != 0) {
                System.out.printf("%d ", i);
            }
        }
        for (int i = num; i > 0; i--) {
            if (i % 2 == 0) {
                System.out.printf("%d ", i);
            }
        }
    }
}
public class SeUpandDown {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input positive number: ");
        int num = sc.nextInt();
        positive pn = new positive(num);
        if (num> 0) {
            pn.is_positive();

        } else {
            System.out.println("Input only positive number.");
            SeUpandDown.main(args);
        }
    }
}
