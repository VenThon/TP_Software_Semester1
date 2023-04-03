public class MyClass{
    int age;
    String name;
    static final String IdPrefix="e";
    public MyClass(int age, String name){
        this.age=age;
        this.name=name;
    }

    @Override
    public String toString(){
        return "Name: " +name+"\nAge: "+age;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof MyClass){
            MyClass m = (MyClass)obj;
            return age == m.age && name.equals(m.name);
        }
        return false;
    }
    public boolean oldEquals(Object obj){
        return super.equals(obj);
    }
    public static String  getInfo(MyClass m){
        return "Name: "+ m.name +"\nAge: "+m.age;
    }
    public static void main(String[] args) {
        MyClass mc = new MyClass(20, "Pov");
        System.out.println(MyClass.getInfo(mc));
        System.out.println("Every student ID starts with " +MyClass.IdPrefix);
        System.out.println("Pi= "+Math.);
        // MyClass mc2 = new MyClass(20, "Pov");
        // System.out.println(mc.toString());
        // System.out.println("mc == m2? "+mc.equals(mc2));
    }
}