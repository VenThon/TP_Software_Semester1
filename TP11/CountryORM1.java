import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CountryORM1 extends ORM<Country> {
    public CountryORM1() {
        // super();
        tableName = "countries";
    }


    public ArrayList<Country> listAll() {
        ArrayList<Country> ar = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from " + tableName);
            while (rs.next()) {
                ar.add(new Country(rs.getInt("id"), rs.getString(2)));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return ar;
    }

    public Country add(Country t) {
        try (var stmt = connection.prepareStatement("INSERT INTO " + tableName
                + " VALUES(NULL,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, t.getCountry());
            stmt.execute();
            var rs = stmt.getGeneratedKeys();
            if (rs.next())
                t.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    // delete

    public int count(int id) {
        int count = -1;
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select Count(*) from " + tableName + " WHERE id = " + id + ";");
            rs.next();
            count = rs.getInt(1);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count;
    }

    public boolean remove(int id) {
        if (count(id) == 1) {
            try (var stmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ? ;")) {
                stmt.setInt(1, id);
                stmt.execute();
                System.out.println("Deleted!");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't");
        }
        return false;
    }

    // update by id
    public void update(Country c) {
        try (var stmt = connection.prepareStatement("UPDATE " + tableName + " SET country = ?" + " WHERE id = ?")) {
            stmt.setString(1, c.getCountry());
            stmt.setInt(2, c.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // List
    public ArrayList<Country> rawQueryList(String query) {
        ArrayList<Country> ar = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ar.add(new Country(rs.getInt("id"), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ar;
    }

    public static void main(String[] args) {
        CountryORM1 orm = new CountryORM1();
        
        Scanner sc = new Scanner(System.in);
        String option;
        while(2>0){
            System.out.println("\n\n===========Menu========");
        System.out.println("1. Add country");
        System.out.println("2. List all country and Id");
        System.out.println("3. Update country");
        System.out.println("4. Remove frome database");
        System.out.println("5. List by row query");
        System.out.println("0. Exit");
        System.out.print("Enter option you want: ");
        option=sc.nextLine();
        System.out.println("========================\n");
        if(option.equals("1")){
                System.out.print("Enter a country: ");
                String co = sc.nextLine(); 
                Country c = new Country(0,co );
                orm.add(c);
                System.out.println("New added country id: " + c.getId());
                System.out.println("ID: " + c.getId() + "; Name: " + c.getCountry());
        }else if(option.equals("2")){
                for (var c2 : orm.listAll()) {
                    System.out.println("ID: " + c2.getId() + "; Name: " + c2.getCountry());
                }
        }else if(option.equals("3")){
                System.out.print("Enter country to update: ");
                String st = sc.nextLine();
                System.out.print("Enter id of country to update: ");//for input id to update you can put id in database(heidi)
                int num = sc.nextInt();
                Country c = new Country(num, st);
                orm.update(c);

        }else if(option.equals("4")){
                System.out.print("Enter id to remove: ");//for remove id you can check data in heidi and then choose in want to remove
                int num = sc.nextInt();
                orm.remove(num);
            
        }else if(option.equals("5")){
            for (var ct : orm.rawQueryList("SELECT * FROM countries WHERE id = 2;")) {
                System.out.println("ID: " + ct.getId() + "; Country: " + ct.getCountry());
            }
        }
        }
    }
}
