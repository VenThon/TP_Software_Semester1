import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImagesORM extends ORM<Image> {
    public ImagesORM() {
        tableName = "images";
    }

    // listALL DB
    public ArrayList<Image> listAll() {
        ArrayList<Image> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + ";");
            while (rs.next()) {
                arr.add(new Image(rs.getInt(1), new Hotel(rs.getInt(2), null, null, null, (short) 0, 0, null),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return arr;
    }

    // listByRom DB
    public ArrayList<Image> listByRowQuery(int id) {
        ArrayList<Image> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id + " ;");
            while (rs.next()) {
                arr.add(new Image(rs.getInt(1), new Hotel(rs.getInt(2), null, null, null, (short) 0, 0, null),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return arr;
    }

    // add to DB
    public Image add(Image i) {
        try (var stmt = connection.prepareStatement("INSERT INTO " + tableName
                + " VALUES(NULL,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, i.getHotel().getId());
            stmt.setString(2, i.getImagepath());
            stmt.execute();
            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                i.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return i;
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

    // update in DB
    public void update(Image i) {
        try (var stmt = connection
                .prepareStatement("UPDATE " + tableName + " SET hotelid = ? , imagepath = ? " + " WHERE id = ?")) {
            stmt.setInt(1, i.getHotel().getId());
            stmt.setString(2, i.getImagepath());
            stmt.setInt(3, i.getId());
            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ImagesORM orm = new ImagesORM();

        // Add to Database
        Hotel h = new Hotel(1, null, null, null, (short) 0, 0, null);
        Image i = new Image(0, h, "pic.png");
        orm.add(i);

        // List ALL in table of DB
        // for (var i1 : orm.listAll()) {
        // System.out.println("ID: " + i1.getId());
        // System.out.println("HotelID: " + i1.getHotel().getId());
        // System.out.println("Image Path: " + i1.getImagepath());
        // }

        // List by rom in DB
        // for (var i1 : orm.listByRowQuery(1)) {
        // System.out.println("ID: " + i1.getId());
        // System.out.println("HotelID: " + i1.getHotel().getId());
        // System.out.println("Image Path: " + i1.getImagepath());
        // }


        // update to DB
        // Hotel h = new Hotel(2, null, null, null, (short) 0, 0, null);
        // Image i = new Image(1, h, "software.png");
        // orm.update(i);
    }
}
