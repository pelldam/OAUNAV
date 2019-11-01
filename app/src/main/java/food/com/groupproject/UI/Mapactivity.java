package food.com.groupproject.UI;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import food.com.groupproject.Detailkeeper;
import food.com.groupproject.DirectionsJSONParser;
import food.com.groupproject.R;

public class Mapactivity extends Fragment implements OnMapReadyCallback, LocationListener,
        GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,DirectionCallback {

    private GoogleMap mMap;
    Marker mCurrlocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationrequest;
Location  mLastLocation;
int MY_LOCATION_REQUEST_CODE=100;
double dest_lon;
double dest_lat;
LatLng dest;
ProgressDialog progressDialog;
int numberoftimelocationupdate=1;
Route mroute;
    boolean locationon;
    SupportMapFragment mapFragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
public static   Fragment getInstance(){

        Mapactivity map=new Mapactivity();
        return  map;
}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view=inflater.inflate(R.layout.mapfragment,container,false);
       mapFragment=(SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
    if (mapFragment==null){

        mapFragment=SupportMapFragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.map,mapFragment).commit();
    }

        dest=Detailkeeper.Detail.getInstance().getLatlng();
        //Toast.makeText(getActivity(),""+dest.latitude+" "+dest.longitude,Toast.LENGTH_SHORT).show();

progressDialog=new ProgressDialog(getActivity());
progressDialog.setMessage("Please wait..Getting direction");
progressDialog.setIndeterminate(true);





    return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
  mLocationrequest=new LocationRequest();
  mLocationrequest.setInterval(1000);
  mLocationrequest.setFastestInterval(1000);
  mLocationrequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
  if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){


      LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationrequest,this);
//      Toast.makeText(getActivity(),"request for location",Toast.LENGTH_SHORT).show();
  }else {
      requestforlocationpermission();
      Toast.makeText(getActivity(),"permission not granted",Toast.LENGTH_SHORT).show();


  }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
mLastLocation=location;
if (mCurrlocationMarker!=null){

    mCurrlocationMarker.remove();
}
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

      //  Toast.makeText(getApplicationContext(),""+location.getLatitude()+" "+location.getLongitude(),Toast.LENGTH_SHORT).show();

        MarkerOptions markerOptions=new MarkerOptions();

        markerOptions.position(latLng);


        markerOptions.title("Current Position");

        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        mCurrlocationMarker=mMap.addMarker(markerOptions);


     /*   if (mGoogleApiClient!=null)

            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);

 */  /* String url=downloadurl(latLng,dest_latlng);
    DownloadTask downloadTask=new DownloadTask();

    downloadTask.execute(url);
*/
 if (numberoftimelocationupdate==1) {
     requestDirection(latLng, dest);
     mMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
     mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

     mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
 }
        numberoftimelocationupdate=numberoftimelocationupdate+1;
 }

    @Override
    public void onMapReady(GoogleMap googleMap) {
mMap=googleMap;
if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M)
{
if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)

{

    final AlertDialog.Builder mbuilder=new AlertDialog.Builder(getActivity());
    final String  action= Settings.ACTION_LOCATION_SOURCE_SETTINGS;
    final String message=getString(R.string.app_name) +"  will like  use  GPS Location.to continue enable you GPS Location";

    LocationSettingsRequest.Builder builder=new LocationSettingsRequest.Builder();
    SettingsClient client=LocationServices.getSettingsClient(getActivity());
    Task<LocationSettingsResponse> task=client.checkLocationSettings(builder.build());
    task.addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                    if(locationSettingsResponse.getLocationSettingsStates().isGpsUsable()) {

                        buildGoogleApiCLient();
                        mMap.setMyLocationEnabled(true);


                    }else {
                        mbuilder.setMessage(message)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        getActivity().startActivity(new Intent(action));
                                        dialogInterface.dismiss();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        getActivity().finish();
                                    }
                                });
                        mbuilder.create().show();

                    }
                }

            }


    );

    task.addOnFailureListener(getActivity(), new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            if (e instanceof ResolvableApiException){

                try {


                    ResolvableApiException resolvable=(ResolvableApiException)e;
                    resolvable.startResolutionForResult(getActivity(),90);



                }catch (IntentSender.SendIntentException senderintent){



                }
            }
        }
    });
    }


else {
    requestforlocationpermission();
}
}else {

    buildGoogleApiCLient();
    mMap.setMyLocationEnabled(true);

}
    }

    protected  synchronized  void  buildGoogleApiCLient(){


        mGoogleApiClient=new GoogleApiClient.Builder(getContext()).addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

 mGoogleApiClient.connect();
    }

    private  void requestforlocationpermission(){
        if (Build.VERSION.SDK_INT>=23) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {

showMessageOkcancel(" You have  to give this app permission to read your current location", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_LOCATION_REQUEST_CODE);
    }
});

            }else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_LOCATION_REQUEST_CODE);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode==MY_LOCATION_REQUEST_CODE){

        if (permissions.length==1 && permissions[0].contentEquals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0]==PackageManager.PERMISSION_GRANTED)

        {
            if (getActivity().checkCallingPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            mMap.setMyLocationEnabled(true);
        }else {

requestforlocationpermission();
        }
    }

    }
    public  void showMessageOkcancel(String message, DialogInterface.OnClickListener oklistener){

        new AlertDialog.Builder(getActivity()).setMessage(message).setNegativeButton("Cancel",null).setPositiveButton("Ok",oklistener)
                .create().show();

    }

    private class  DownloadTask extends AsyncTask{

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
      ParserTask parserTask=new ParserTask();

      parserTask.execute(o.toString());

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String data= "";
            try{
                data=downloadUrl(objects[0].toString());


            }catch (Exception e){


                Log.d("error", e.toString());

            }
            return data;
        }
    }
private  class ParserTask extends AsyncTask<String,Integer,List<List<HashMap<String,String>>>>{

    @Override
    protected void onPostExecute(List<List<HashMap<String,String>>> lists) {
        super.onPostExecute(lists);
        ArrayList points=null;
        if (lists==null|| lists.isEmpty()){

            Toast.makeText(getContext(),"result is null or empty",Toast.LENGTH_SHORT).show();
        }else {

            Toast.makeText(getContext(),"result is  not empty",Toast.LENGTH_SHORT).show();
        }
        PolylineOptions polylineOptions=null;
        MarkerOptions markerOptions=new MarkerOptions();

        for (int i=0;i<lists.size();i++){
            points=new ArrayList();
            polylineOptions=new PolylineOptions();
            List<HashMap<String,String>> path=lists.get(i);

            for (int j=0; j<path.size();j++){

                HashMap<String,String> point=path.get(j);
                double lat=Double.parseDouble(point.get("lat"));
                Double lon=Double.parseDouble(point.get("lng"));

                LatLng position=new LatLng(lat,lon);

                points.add(position);

            }


polylineOptions.addAll(points);
polylineOptions.width(12);
polylineOptions.color(Color.RED);
polylineOptions.geodesic(true);

        }
if(polylineOptions!=null) {
    mMap.addPolyline(polylineOptions);
}else {

            Toast.makeText(getContext(),"polyline is null",Toast.LENGTH_SHORT).show();
}
        }

    @Override
    protected List<List<HashMap<String,String>>> doInBackground(String... strings) {

        JSONObject jsonObject;
        List<List<HashMap<String,String>>> route=null;
        try{
            jsonObject=new JSONObject(strings[0]);
            DirectionsJSONParser  parser=new DirectionsJSONParser();
route=parser.parse(jsonObject);

        }catch (Exception e){

Toast.makeText(getContext(),"exception"+e.toString(),Toast.LENGTH_SHORT).show();
        }
        return route;
    }
}
    private String downloadurl(LatLng origin, LatLng dest){

        String str_orign="origin="+origin.latitude+","+origin.longitude;
        String str_dest="destination="+dest.latitude+","+dest.longitude;
        String mode="mode=walking";
        String  sensor="sensor=false";
        String parameter=str_orign +"&"+str_dest+"&"+ sensor+"&"+mode;
        String output="json";
        String url="https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameter+"&key=AIzaSyDK_LYFGBLB-ruMpT1tpUfJciwNJkrkdbI";


        return url;

    }

    private void requestDirection(LatLng origin, LatLng destination){
        GoogleDirection.withServerKey(getResources().getString(R.string.map_key))
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.WALKING)
                 .execute(this);
progressDialog.show();
   }
    private  String downloadUrl(String strUrl) throws IOException{


        String data="";
        InputStream inputStream=null;
        HttpURLConnection httpURLConnection=null;

        try{

            URL url=new URL(strUrl);
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            inputStream=httpURLConnection.getInputStream();
            BufferedReader  br=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb=new StringBuffer();
            String line="";
            while ((line=br.readLine())!=null){
                sb.append(line);

            }
            data=sb.toString();
        }catch (Exception e){

            Log.d("Exception",e.toString());
            Toast.makeText(getContext(),"Exception "+e.toString(),Toast.LENGTH_SHORT).show();
        }finally {
            inputStream.close();
            httpURLConnection.disconnect();
        }
        Log.i("data",data);
        return data;
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
progressDialog.dismiss();

        if (direction.isOK()){
            MarkerOptions markerOptions1=new MarkerOptions();
            markerOptions1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            markerOptions1.title("Destination Location");
            markerOptions1.position(dest);
            mMap.addMarker(markerOptions1);
            Route route=direction.getRouteList().get(0);

            ArrayList<LatLng> directionpositionList=route.getLegList().get(0).getDirectionPoint();

            mMap.addPolyline(DirectionConverter.createPolyline(getActivity(),directionpositionList,5,Color.RED));

setCameraWithCoordinationBounds(route);

        }

    }

    @Override
    public void onDirectionFailure(Throwable t) {
Toast.makeText(getContext(),""+t.getMessage(),Toast.LENGTH_SHORT).show();
    progressDialog.dismiss();
    }
private void setCameraWithCoordinationBounds(Route route) {

        LatLng southwest=route.getBound().getSouthwestCoordination().getCoordination();
        LatLng northeast=route.getBound().getNortheastCoordination().getCoordination();
    LatLngBounds latLngBounds=new LatLngBounds(southwest,northeast);
    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds,100));



    }

private boolean checkifgpsison(){


    return locationon;
    }
}
