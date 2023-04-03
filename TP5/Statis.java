public class Statis {
    // public static void main(String[] args) {
    //     System.out.println(args.length);
    //     for(int i=0; i<args.length;i++){
    //         System.out.println(args[i]);
    //     }
    // }

    public static void main(String[] args) {
        if(args.length==1){
            if(args[0].equalsIgnoreCase("How old are you?")){
                System.out.println("I am...years old");
            }else if(args[0].equalsIgnoreCase("What is your name?")){
                System.out.println("My name is pov");
            }
        }
    }
}
