import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserORM extends ORM<User> {
    public UserORM() {
        tableName = "users";
    }

    // list All
    public ArrayList<User> listAll() {
        ArrayList<User> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()) {
                arr.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        new Role(rs.getInt(5), null), rs.getShort(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    // list by row query
    public ArrayList<User> listByRow(int id) {
        ArrayList<User> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id + " ;");
            while (rs.next()) {
                arr.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        new Role(rs.getInt(5), null), rs.getShort(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    // add to DB
    public User add(User u) {
        try (var stmt = connection.prepareStatement("INSERT INTO " + tableName + " VALUES(NULL,?,?,?,?,?,?);",
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPass());
            stmt.setString(3, u.getEmail());
            stmt.setInt(4, u.getRole().getId());
            stmt.setShort(5, u.getDiscount());
            stmt.setString(6, u.getAvatar());
            stmt.execute();
            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                u.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return u;
    }

    // delete in DB
    public void remove(int id) {
        try (var stmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update in DB by id
    public void update(User u) {
        try (var stmt = connection.prepareStatement("UPDATE " + tableName +
                " SET username = ? , pass = ? , email = ? , roleid = ? , discount = ? , avatar = ? WHERE id = ? ;")) {
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPass());
            stmt.setString(3, u.getEmail());
            stmt.setInt(4, u.getRole().getId());
            stmt.setShort(5, u.getDiscount());
            stmt.setString(6, u.getAvatar());
            stmt.setInt(7, u.getId());
            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserORM orm = new UserORM();

        // add to DB
        Role r = new Role(1, null);
        User u = new User(0, "venthon", "1234", "venthon@gmail.com", r, (short) 15,
                "pic.png");
        orm.add(u);

        // update in DB by ID
        // Role r = new Role(1, null);
        // User u = new User(1, "vysing", "1234", "sing@gmail.com", r, (short) 10,
        // "sing.png");
        // orm.update(u);

        // list All
        // for (var u1 : orm.listAll()) {
        // System.out.println("ID: " + u1.getId());
        // System.out.println("Username: " + u1.getUsername());
        // System.out.println("Password: " + u1.getPass());
        // System.out.println("Eamil: " + u1.getEmail());
        // System.out.println("RoleID: " + u1.getRole().getId());
        // System.out.println("Discount: " + u1.getDiscount() + " %");
        // System.out.println("Avatar: " + u1.getAvatar());
        // }

        // list by id
        // for (var u1 : orm.listByRow(2)) {
        //     System.out.println("ID: " + u1.getId());
        //     System.out.println("Username: " + u1.getUsername());
        //     System.out.println("Password: " + u1.getPass());
        //     System.out.println("Eamil: " + u1.getEmail());
        //     System.out.println("RoleID: " + u1.getRole().getId());
        //     System.out.println("Discount: " + u1.getDiscount() + " %");
        //     System.out.println("Avatar: " + u1.getAvatar());
        // }

    }
}
