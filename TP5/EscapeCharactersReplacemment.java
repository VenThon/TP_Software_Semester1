import java.util.Scanner;
public class EscapeCharactersReplacemment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please enter a senten: ");
        String paragrap = sc.next();

        String replaSen =paragrap.replace("\n", "(new_line)");
        replaSen =replaSen.replace("\t", "(tab)");
        replaSen =replaSen.replace("\\\\", "(slash)");
        replaSen =replaSen.replace("//", "(comment_line)");
        replaSen =replaSen.replace(":)", "(smile)");
        System.out.printf(replaSen);
    }
}
