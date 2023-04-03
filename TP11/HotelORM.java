import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelORM extends ORM<Hotel> {
    public HotelORM() {
        tableName = "hotels";
    }

    public ArrayList<Hotel> listAll() {
        ArrayList<Hotel> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()) {
                arr.add(new Hotel(rs.getInt(1), rs.getString(2), new Country(rs.getInt(3), null),
                        new City(rs.getInt(4), null, null), rs.getShort(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return arr;
    }

    public ArrayList<Hotel> listRow(int id) {
        ArrayList<Hotel> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id + ";");
            while (rs.next()) {
                arr.add(new Hotel(rs.getInt(1), rs.getString(2), new Country(rs.getInt(3), null),
                        new City(rs.getInt(4), null, null), rs.getShort(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return arr;
    }

    public Hotel add(Hotel h) {
        try (var stmt = connection.prepareStatement("INSERT INTO " + tableName + " VALUES(NULL,?,?,?,?,?,?);",
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, h.getHotel());
            stmt.setInt(2, h.getCountry().getId());
            stmt.setInt(3, h.getCity().getId());
            stmt.setShort(4, h.getStars());
            stmt.setDouble(5, h.getCost());
            stmt.setString(6, h.getInfo());
            stmt.execute();
            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                h.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return h;
    }

    // delete
    public void remove(int id) {
        try (var stmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update by id
    //
    public void update(Hotel h) {
        // Country country = new Country(NewCountryid, null);
        // City city = new City(NewCityid, null, country);

        try (var stmt = connection
                .prepareStatement(
                        "UPDATE " + tableName
                                + " SET hotel = ? , countryid = ? , cityid = ? , stars = ? , cost = ? , info = ? "
                                + " WHERE id = ?")) {
            stmt.setString(1, h.getHotel());
            stmt.setInt(2, h.getCountry().getId());
            stmt.setInt(3, h.getCity().getId());
            stmt.setShort(4, h.getStars());
            stmt.setDouble(5, h.getCost());
            stmt.setString(6, h.getInfo());
            stmt.setInt(7, h.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HotelORM orm = new HotelORM();

        //add data
        Country c = new Country(3, null);
        City ct = new City(4, null, c);
        Hotel h = new Hotel(0, "Phnom penh", c, ct, (short) 4, 50, "Resort");
        orm.add(h);

        //remove data
        // orm.remove(1);

        // for (var h : orm.listAll()) {
        // System.out.println("ID: " + h.getId());
        // System.out.println("Hotel: " + h.getHotel());
        // System.out.println("CountryID: " + h.getCountry().getId());
        // System.out.println("CityID: " + h.getCity().getId());
        // System.out.println("Stars: " + h.getStar());
        // System.out.println("Cost: " + h.getCost());
        // System.out.println("Info: " + h.getInfo());
        // }

        // for (var h : orm.listRow(2)) {
        // System.out.println("ID: " + h.getId());
        // System.out.println("Hotel: " + h.getHotel());
        // System.out.println("CountryID: " + h.getCountry().getId());
        // System.out.println("CityID: " + h.getCity(

        // Country c = new Country(4, null);
        // City ct = new City(5, null, c);
        // Hotel h = new Hotel(2, "JSS", c, ct, (short) 5, 99.9, "Breakfast");
        // orm.update(h);
        
        }
    }
