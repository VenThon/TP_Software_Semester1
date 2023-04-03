import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CountryORM extends ORM<Country> {
    public CountryORM() {
        // super();
        tableName = "countries";
    }

    // public Country add(Country t) {
    // try (var stmt = connection.createStatement()) {
    // var sql = "INSERT INTO " + tableName + "VALUE(NULL,'" + t.getCountry() +
    // "')";
    // stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
    // var rs = stmt.getGeneratedKeys();
    // rs.next();
    // t.setId(rs.getInt(1));
    // return t;
    // } catch (SQLException e) {
    // // TODO: handle exception
    // e.printStackTrace();
    // }
    // return null;
    // }

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
        CountryORM orm = new CountryORM();
        // add
        // Country c = new Country(0, "Korea");
        // orm.add(c);
        // System.out.println("New added country id: " + c.getId());
        // System.out.println("ID: " + c.getId() + "; Name: " + c.getCountry());
        // listAll
        // for (var c2 : orm.listAll()) {
        // System.out.println("ID: " + c2.getId() + "; Name: " + c2.getCountry());
        // }
        // update
        // Country c = new Country(1, "Laos");
        // orm.update(c);
        // remove from datebase
        orm.remove(12);

        // list by row query

        // for (var ct : orm.rawQueryList("SELECT * FROM countries WHERE id = 2;")) {
        // System.out.println("ID: " + ct.getId() + "; Country: " + ct.getCountry());
        // }
    }
}
