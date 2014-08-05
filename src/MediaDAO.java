import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MediaDAO {

    private ArrayList<MediaItem> itemList;
    private String userid = ""; // Insert MySQL userid
    private String password = ""; // Insert MySQL password
    static String url = "jdbc:mysql://localhost:3306/Mysql";
    private Connection con;
    
    public MediaDAO() {
        itemList = new ArrayList<MediaItem>();
        getConnection();
    }
    
    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        try {
            con = DriverManager.getConnection(url, userid, password);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return con;
    }
    
    @SuppressWarnings("unused")
	public ArrayList<MediaItem> searchItem(String name)
    {
        try {
            String sql = "SELECT * FROM mediamanager.mediaitem WHERE Name like '%"+name+"%'";

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);

            String iname, comments, mediaType, genre, purLoc, purDate;
            int year, id, mediaTypeId, genreId, purInfoId;
            double purPrice, curValue;

            while(rs.next())
            {
                id = rs.getInt("ID");
                iname = rs.getString("Name");
                comments = rs.getString("Comments");
                mediaTypeId = rs.getInt("MediaTypeID");
                genreId = rs.getInt("GenreID");
                year = rs.getInt("Year");
                curValue = rs.getDouble("CurrentValue");
                
                mediaType = getMediaTypeNameById(mediaTypeId);
                genre = getGenreNameById(genreId);
                purLoc = getPurLocById(id);
                purDate = getPurDateById(id);
                purPrice = getPurPriceById(id);
                        
                
                /*
                String mediaTypeQ = "SELECT * FROM mediamanager.mediatype WHERE ID = " + mediaTypeId;
                Statement ts = con.createStatement();
                ResultSet trs = ts.executeQuery(mediaTypeQ);
                mediaType = trs.getString("MediaTypeDescription");
                
                String genreQ = "SELECT * FROM mediamanager.genre WHERE ID = " + genreId;
                Statement gs = con.createStatement();
                ResultSet grs = gs.executeQuery(genreQ);
                genre = grs.getString("GenreDescription");
                
                String purInfoQ = "SELECT * FROM mediamanager.purchaseinfomediaitem WHERE MediaItemID = " + id;
                Statement pis = con.createStatement();
                ResultSet pirs = pis.executeQuery(purInfoQ);
                purDate = pirs.getString("PurchaseDate");
                purPrice = pirs.getDouble("PurchasePrice");
                purInfoId = pirs.getInt("PurchaseInfoID");
                
                String purLocQ = "SELECT * FROM mediamanager.purchaseinfo WHERE ID = " + purInfoId;
                Statement pls = con.createStatement();
                ResultSet plrs = pls.executeQuery(purLocQ);
                purLoc = plrs.getString("PurchaseLocation");
                */

                
                MediaItem item = new MediaItem(id, name, comments, mediaType, genre, 
                        purLoc, year, purPrice, curValue, purDate);

                itemList.add(item);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        return itemList;
    }
    
    public String getMediaTypeNameById(int id) {
        String name = "";
        try {
            String sql = "SELECT * FROM mediamanager.mediatype WHERE ID = " + id;

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
                name = rs.getString("MediaTypeDescription");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return name;
    }
    
    public int getMediaTypeIdByName(String type) {
        int id = -1;
        try {
            String sql = "SELECT * FROM mediamanager.mediatype WHERE MediaTypeDescription like '%"+type+"%'";

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()){
                id = rs.getInt("ID");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return id;
    }
    
    public String getGenreNameById(int id) {
        String genre = "";
        try {
            String sql = "SELECT * FROM mediamanager.genre WHERE ID = " + id;

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
                genre = rs.getString("GenreDescription");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return genre;
        
    }
    
    public int getGenreIdByName(String genre) {
        int id = -1;
        try {
            String sql = "SELECT * FROM mediamanager.genre WHERE GenreDescription like '%"+genre+"%'";

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
                id = rs.getInt("ID");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return id;
        
    }
    
    public String getPurLocById(int id) {
        String purLoc = "";
        int locId = -1;
        try {
            String sql = "SELECT * FROM mediamanager.purchaseinfomediaitem WHERE MediaItemID = " + id;

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
                locId = rs.getInt("PurchaseInfoID");
            }
            
            if (locId != -1) {
                String sql2 = "SELECT * FROM mediamanager.purchaseinfo WHERE ID = " + locId;
            
                Statement s2 = con.createStatement();

                ResultSet rs2 = s2.executeQuery(sql2);
            
                if (rs2.next()) {
                    purLoc = rs2.getString("PurchaseLocation");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return purLoc;
    }
    
    public String getPurDateById(int id) {
        String purDate = "";
        try {
            String sql = "SELECT * FROM mediamanager.purchaseinfomediaitem WHERE MediaItemID = " + id;

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
                purDate = rs.getString("PurchaseDate");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return purDate;
    }
    
    public double getPurPriceById(int id) {
        double purPrice = -1.0;
        try {
            String sql = "SELECT * FROM mediamanager.purchaseinfomediaitem WHERE MediaItemID = " + id;

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
                purPrice = rs.getDouble("PurchasePrice");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return purPrice;
    }
    
    public void saveItem(MediaItem item){
        
        int mediaTypeId = getMediaTypeIdByName(item.getMediaType());
        int genreId = getGenreIdByName(item.getGenre());
        
        if (mediaTypeId == -1) {
            try {
                String mtsql = "INSERT INTO mediamanager.mediatype (MediaTypeDescription) VALUES (?)";
                PreparedStatement mtps = con.prepareStatement(mtsql);
                mtps.setString(1, item.getMediaType());
                mtps.executeUpdate();
            }
            catch(Exception e){
                System.out.println(e);
            }
            mediaTypeId = getMediaTypeIdByName(item.getMediaType());
        }
        
        if (genreId == -1) {
            try {
                String gsql = "INSERT INTO mediamanager.genre (GenreDescription) VALUES (?)";
                PreparedStatement gps = con.prepareStatement(gsql);
                gps.setString(1, item.getGenre());
                gps.executeUpdate();
            }
            catch(Exception e){
                System.out.println(e);
            }
            genreId = getGenreIdByName(item.getGenre());
        }
        
        try
        {
            String sql = "INSERT INTO mediamanager.mediaitem(GenreID, MediaTypeID, " +
                    "Name, Year, Comments, CurrentValue) VALUES (?,?,?,?,?,?) ";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, genreId);
            ps.setInt(2, mediaTypeId);
            ps.setString(3, item.getName());
            ps.setInt(4, item.getYear());
            ps.setString(5, item.getComments());
            ps.setDouble(6, item.getCurValue());
            
            ps.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void updateItem(MediaItem person)
    {/*
        try
        {
            String sql = "UPDATE addressbook.Person SET name = ?, address=? , " +
                    "phone=? , email=? where id=?";

            // Create a Prepared statement
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1 , item.);
            ps.setString(2 , item.));
            ps.setInt(3 , item.);
            ps.setString(4 , item.);
            ps.setString(5 , item.
            ps.setInt(6 , item.);
            

            ps.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }*/
    }

    public int removeItem(String name){
        /*
        int no = 0;
        try{
            String sql = "DELETE FROM mediamanager.mediaitem WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            no = ps.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return no; */
        return 0;
    }
}
