package food.com.groupproject;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectionsJSONParser  {

    public List<List<HashMap<String,String>>> parse(JSONObject jsonObject){

List<List<HashMap<String,String>>> route=new ArrayList<>();

        JSONArray  jRoute=null;
        JSONArray jLeg=null;
        JSONArray jStep=null;

        try{

jRoute=jsonObject.getJSONArray("routes");

for (int i=0;i<jRoute.length();i++) {

List path=new ArrayList<HashMap<String,String>>();
    jLeg = ((JSONObject) jRoute.get(i)).getJSONArray("legs");


    for (int k = 0; k < jLeg.length(); k++) {

        jStep = ((JSONObject) jLeg.get(k)).getJSONArray("steps");

        for (int j = 0; j < jStep.length(); j++) {


            String polyline = "";
            polyline = (String) ((JSONObject) ((JSONObject) jStep.get(j)).get("polyline")).get("points");
            List list = decode(polyline);

            /** Traversing all points */
            for (int l = 0; l < list.size(); l++) {
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("lat", Double.toString(((LatLng) list.get(l)).latitude));
                hm.put("lng", Double.toString(((LatLng) list.get(l)).longitude));
                path.add(hm);

            }
        }

route.add(path);
    }
}
        }catch (Exception ex){
ex.printStackTrace();

        }

return route;
    }
    
    
    private List  decode(String encode){
        List poly=new ArrayList();
        int index=0,len=encode.length();
        int lat=0,lng=0;

        while (index<len){

            int b,shift=0,result=0;

            do{
           b=encode.charAt(index++)-63;
           result |=(b &0x1f) << shift;
           shift=+5;
            }while (b>=0x20);
            int dlat=((result & 1)!=0 ?~(result>>1):(result >> 1 ));
            lat=+dlat;
            shift=0;
            result =0;

            do {

                b = encode.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift = +5;
            }while (b>=0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
            }

        }






    

