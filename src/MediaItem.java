

public class MediaItem {
    private String name, comments, mediaType, genre, purLoc, purDate;
    private int year, id;
    private double purPrice, curValue;
    
    public MediaItem() {
        name = "";
        comments = "";
        mediaType = "";
        genre = "";
        purLoc = "";
        
        year = 0;
        id = 0;
        purPrice = 0.0;
        curValue = 0.0;
        purDate = "";
    }
    
    public MediaItem(int id, String name, String comments, String mediaType, String genre, 
            String purLoc, int year, double purPrice, double curValue, String purDate) {
        this.id = id;
        this.name = name;
        this.comments = comments;
        this.mediaType = mediaType;
        this.genre = genre;
        this.purLoc = purLoc;
        this.year = year;
        this.purPrice = purPrice;
        this.curValue = curValue;
        this.purDate = purDate;
    }
    
    public MediaItem(String name, String comments, String mediaType, String genre, 
            String purLoc, int year, double purPrice, double curValue, String purDate) {
        this.name = name;
        this.comments = comments;
        this.mediaType = mediaType;
        this.genre = genre;
        this.purLoc = purLoc;
        this.year = year;
        this.purPrice = purPrice;
        this.curValue = curValue;
        this.purDate = purDate;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getComments() {
        return comments;
    }

    
    public void setComments(String comments) {
        this.comments = comments;
    }

    
    public String getMediaType() {
        return mediaType;
    }

    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    
    public String getGenre() {
        return genre;
    }

    
    public void setGenre(String genre) {
        this.genre = genre;
    }

    
    public String getPurLoc() {
        return purLoc;
    }

    
    public void setPurLoc(String purLoc) {
        this.purLoc = purLoc;
    }

    
    public String getPurDate() {
        return purDate;
    }

    
    public void setPurDate(String purDate) {
        this.purDate = purDate;
    }

    
    public int getYear() {
        return year;
    }

    
    public void setYear(int year) {
        this.year = year;
    }

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    
    public double getPurPrice() {
        return purPrice;
    }

    
    public void setPurPrice(double purPrice) {
        this.purPrice = purPrice;
    }

    
    public double getCurValue() {
        return curValue;
    }

    
    public void setCurValue(double curValue) {
        this.curValue = curValue;
    }
}
