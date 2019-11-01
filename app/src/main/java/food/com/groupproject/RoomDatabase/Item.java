package food.com.groupproject.RoomDatabase;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Locations")
public class Item {
@PrimaryKey(autoGenerate = true)
    @NonNull private int id;
    private  String location_name;
    private  double  lat;
    private  double lon;
    private int image_url;
    private String category;

    public Item(String location_name,double lon,double lat,int image_url,String category){

        this.location_name=location_name;
        this.lat=lat;
        this.lon=lon;
        this.image_url=image_url;
        this.category=category;
    }
    @Ignore

    public Item(int id,String location_name,double lon,double lat,int image_url,String category){
        this.id=id;
        this.location_name=location_name;
        this.lat=lat;
        this.lon=lon;
        this.image_url=image_url;
        this.category=category;
    }


    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
