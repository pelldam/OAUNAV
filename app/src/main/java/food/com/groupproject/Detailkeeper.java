package food.com.groupproject;

import com.google.android.gms.maps.model.LatLng;

public class Detailkeeper {

    LatLng originlatlng;
    int imagelocation;
    String locationName;
    public  LatLng getLatlng(){

        return  originlatlng;
    }
    public  int getImagelocation(){

        return imagelocation;
    }
    public void setOriginlatlng(LatLng latLng){


        originlatlng=latLng;

    }
    public  void  setImagelocation( int imagelocation){

        this.imagelocation=imagelocation;
    }
    public void setlocationName(String locationName){


        this.locationName=locationName;
    }
    public String  getLocationName(){

        return  locationName;
    }

    public static class Detail{
       static Detailkeeper keeper=new Detailkeeper();
        public static Detailkeeper getInstance(){
            return keeper;

        }
    }
}
