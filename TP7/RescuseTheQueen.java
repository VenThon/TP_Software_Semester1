import java.util.Scanner;
public class RescuseTheQueen {
    Scanner sc = new Scanner(System.in);
    public void ReadQuestionAnswer(){
        while(2>0){
            System.out.println("You enter into the first room, here it is a lot of gold.\nWhether you will take it?");
            System.out.println("(A. Yes, B. No)");
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("A")||input.equalsIgnoreCase("Yes")){
                System.out.println("Gold remains to you, but you have ruined test. GAME is over!!!");
            }else if(input.equalsIgnoreCase("B")||input.equalsIgnoreCase("no")){
                System.out.println("Congratulation, you have passed the first test honor!");
                System.out.println("You pass in a following room. It is full of diamonds, whether you will take diamonds?\n(A. Yes, B. No)");
                input=sc.nextLine();
                if(input.equalsIgnoreCase("A")||input.equalsIgnoreCase("Yes")){
                    System.out.println("Diamonds remain to you, but you have ruined the second test");
                }else if(input.equalsIgnoreCase("B")||input.equalsIgnoreCase("no")){
                    System.out.println("Congratulation, you have passed the second test of honor!!!");
                    System.out.println("You enter into the third room. A person was attacking by a dragon! To move further, not paying to them of attention?\n(A. Yes, B. No");
                    input=sc.nextLine();
                    if(input.equalsIgnoreCase("B")||input.equalsIgnoreCase("no")){
                        System.out.println("Congratulation, you have pass all tests of honor. Princess gets to you!!!");
                        System.out.println("You Win!");
                        break;
                    }else if(input.equalsIgnoreCase("A")||input.equalsIgnoreCase("Yes")){
                        System.out.println("Game Over!");
                    }else{
                        System.out.println("There is no that operation. Please try again");

                    }
                }else{
                    System.out.println("There is no that operation. Please try again");

                }
            }else{
                System.out.println("There is no that operation. Please try again");
            }
        }
    }
        
class main{
public static void main(String[] args) {
    RescuseTheQueen queen = new RescuseTheQueen();
    queen.ReadQuestionAnswer();
}
}
}

