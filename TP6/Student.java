
import java.util.*;
import java.util.Scanner;
class ClassStudent{
    private int id;
    private String name;
    private String sex;

    ClassStudent(int id, String name, String sex){
        this.id=id;
        this.name=name;
        this.sex=sex;
    }

    public int getByid(){
        return id;
    }
    public String getByname(){
        return name;
    }
    public String getBysex(){
        return sex;
    }

    public String toString(){
        return name+" "+sex+" "+id;
    }
}
public class Student{
    public static void main(String[] args) {
        List<ClassStudent> c = new ArrayList<ClassStudent>();
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        int ch;
        do{
            System.out.println("===============MENU================");
            System.out.println("1. Add new student");
            System.out.println("2. List students");
            System.out.println("3. Remove student by id");
            System.out.println("4. Update student information by id");
            System.out.println("5. Quit");
            System.out.println("====================================");
            System.out.print("Enter your choice: ");
            ch =s.nextInt();
            System.out.println("\n");
            switch(ch){
                case 1:
                    System.out.println("Add information new student");
                    System.out.print("Enter your id: ");
                    int id=s.nextInt(); 
                    System.out.print("Enter your name: ");
                    String name=s1.nextLine();
                    System.out.print("Enter your sex: ");
                    String sex=s1.nextLine();
                    c.add(new ClassStudent(id, name, sex));
                    System.out.println("\n");
                    break;
                case 2:
                    System.out.println("===================");
                    System.out.println("Show List all information student.");
                    Iterator<ClassStudent>i=c.iterator();
                    while(i.hasNext()){
                        ClassStudent e = i.next();
                        System.out.println(e);
                    }
                    System.out.println("===================");
                    System.out.println("\n");
                    break;
                case 3:
                    Boolean found;
                    found = false;
                    System.out.println("Remove information student");
                    System.out.print("Enter id you want remove: ");
                    id=s.nextInt();
                    System.out.println("===================");
                    i=c.iterator();
                    while(i.hasNext()){
                        ClassStudent e=i.next();
                        if(e.getByid()==id){
                            i.remove();
                            found=true;
                        }
                    }
                    if(!found){
                        System.out.println("Not found");
                    }else{
                        System.out.println("It is deleted Successful");
                    }
                    System.out.println("===================");
                    System.out.println("\n");
                    break;
                case 4:
                    found=false;
                    System.out.println("Update information");
                    System.out.print("Enter id you want to update: ");
                     id =s.nextInt();
                    ListIterator<ClassStudent> li=c.listIterator();
                    while(li.hasNext()){
                        ClassStudent e=li.next();
                        if(e.getByid()==id){
                            System.out.print("Enter new name: ");
                            name=s1.nextLine();
                            System.out.print("Enter new sex: ");
                            sex=s1.nextLine();
                            li.set(new ClassStudent(id, name, sex));
                            found = true;
                        }
                    }
                    System.out.println("===================");
                    if(!found){
                        System.out.println("Not found");
                    }else{
                        System.out.println("Is updated successful");
                    }
                    System.out.println("===================");
                    break;
                case 5:
                    System.out.println("Quit");
                    System.out.println("\n");
                    break;
            }       
        }while(ch !=0);
    }
}