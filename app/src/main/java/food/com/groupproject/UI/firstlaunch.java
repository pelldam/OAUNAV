package food.com.groupproject.UI;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import food.com.groupproject.R;
import food.com.groupproject.RoomDatabase.AppDatabase;
import food.com.groupproject.RoomDatabase.Item;
import food.com.groupproject.RoomDatabase.itemDAO;
import food.com.groupproject.sharepref;

import static android.os.Build.VERSION.SDK_INT;

public class firstlaunch extends AppCompatActivity {

    TextView next;
    sharepref pref;
    AppDatabase database;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }

    setContentView(R.layout.firstscreen);

pref=new sharepref(this);
        next=findViewById(R.id.next);
        database=AppDatabase.getInstnace(this);

        next.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               savedata();
                pref.setFirstopen(true);

                Intent intent=new Intent(getApplicationContext(),selectionactivity.class);
                startActivity(intent);
                return false;
            }
        });
    }


    public  void  savedata(){
        itemDAO  Itemdao=database.getItemDAO();
        List<Item> locationlist=new ArrayList<>();
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        //start of lectures threatres
        locationlist.add(new Item("AUD2",4.52386,7.51834,R.drawable.aud2,"theatre"));
        locationlist.add(new Item("AUD1",4.52413,7.52061,R.drawable.aud1,"theatre"));
        locationlist.add(new Item("AMPHIT LT",4.52173,7.51920,R.drawable.amphi,"theatre"));
        locationlist.add(new Item("ODLT2",4.52180,7.51909,R.drawable.odlt,"theatre"));
        locationlist.add(new Item("ODLT1",4.52193,7.51880,R.drawable.odlt,"theatre"));
        locationlist.add(new Item("WHITE HOUSE",4.52095,7.51990,R.drawable.white_house,"theatre"));
        locationlist.add(new Item("YELLOW HOUSE ",4.52040,7.51923,0,"theatre"));
        locationlist.add(new Item("PIT THEATRE",4.52116,7.52082,R.drawable.pit_theatre,"theatre"));
        locationlist.add(new Item("ICAN LT",4.52020,7.52167,R.drawable.ican,"theatre"));
        locationlist.add(new Item("ADMIN EXTENSION",4.51975,7.52172,R.drawable.admin_extension,"theatre"));
        locationlist.add(new Item("1000 SLT",4.51975,4.51954,R.drawable.onesetters,"theatre"));
        locationlist.add(new Item("SPIDER",4.52929,7.52384,R.drawable.spider,"theatre"));
        locationlist.add(new Item("SSLT",4.52192,7.52137,0,"theatre"));
        locationlist.add(new Item("FIRST BANK L T",4.52389,7.52162,R.drawable.first_lt,"theatre"));
        locationlist.add(new Item("AJOSE LT",4.52646,7.52152,R.drawable.ajoselt,"theatre"));
        locationlist.add(new Item("AGRIC LT",4.51427,7.52123,0,"theatre"));
        locationlist.add(new Item("CHE LT",4.52841,7.51943,R.drawable.che_lt,"theatre"));
        locationlist.add(new Item("POST GRADUATE LT",4.52956,7.51652,R.drawable.pglt,"theatre"));
        locationlist.add(new Item("HSLT (A,B,C)",4.52503,7.51652,R.drawable.hslt_area,"theatre"));
        locationlist.add(new Item("BOO (A,B,C)",4.52538,7.51903,R.drawable.boo_area,"theatre"));

        //ends of lectures threatre
        //start of faculty/department
        locationlist.add(new Item("HISTORY",4.52362,7.51972,0,"faculty/department"));
        locationlist.add(new Item("FACULTY OF LAW",4.52302,7.51772,0,"faculty/department"));
        locationlist.add(new Item("MUSIC",4.52146,7.52145,0,"faculty/department"));
        locationlist.add(new Item("MECH. ENGR",4.52929,7.52384,R.drawable.spider,"faculty/department"));
        locationlist.add(new Item("EDUCATION",4.52373,7.51960,R.drawable.fac_edu,"faculty/department"));

        locationlist.add(new Item("FOOD SCI AND TECH",4.52761,7.51976,R.drawable.fst,"faculty/department"));
        locationlist.add(new Item("TECHNOLOGY",4.52871,7.51841,R.drawable.fac_tech,"faculty/department"));
        locationlist.add(new Item("PHARMACY",4.52697,7.51688,R.drawable.faculty_pharmacy,"faculty/department"));

        //end of faculty/department`

        //start of popular place

        locationlist.add(new Item("H.O. LIBRARY",4.52322,7.51973,R.drawable.hol,"popular places"));
        locationlist.add(new Item("MOTION GROUND",4.52286,7.51868,R.drawable.motion_ground,"popular places"));
        locationlist.add(new Item("ODUDUWA HALL",4.52226,7.51849,R.drawable.oduduwa,"popular places"));
        locationlist.add(new Item("BUS STOP2",4.52212,7.51763,0,"popular places"));
        locationlist.add(new Item("BUS STOP1",4.52320,7.51716,0,"popular places"));
        locationlist.add(new Item("SUB",4.52068,7.51846,R.drawable.sub,"popular places"));
        locationlist.add(new Item("SENATE BUILDING",4.52402,7.51840,R.drawable.senate,"popular places"));
        locationlist.add(new Item("BUS STOP2",4.52212,7.51763,0,"popular places"));
        locationlist.add(new Item("CAMPUS PHARMACY",4.52093,7.51871,0,"popular places"));
        locationlist.add(new Item("INS. OF CULTURAL STUDIES",4.52060,7.52167,R.drawable.idns,"popular places"));
        locationlist.add(new Item("UNIVERSITY ZOO",4.52440,7.52396,0,"popular places"));
        locationlist.add(new Item("BIOLOGICAL GARDEN",4.52517,7.52467,0,"popular places"));
        locationlist.add(new Item("GEOLOGY MOSQUE",4.52082,7.52126,0,"popular places"));
        locationlist.add(new Item("MORE HALL OF RESIDENCE",4.51794,7.51984,0,"popular places"));
        locationlist.add(new Item("AWO HALL",4.51639,7.52107,R.drawable.awo,"popular places"));
        locationlist.add(new Item("ETF BUKATERIA",4.51476,7.51734,R.drawable.etf_bu,"popular places"));
        //`end of popular place

        for (Item item:locationlist){


            Itemdao.insert(item);
        }
        progressDialog.dismiss();
    }

    @Override
    protected void onPause() {
        super.onPause();
    finish();
    }
}

