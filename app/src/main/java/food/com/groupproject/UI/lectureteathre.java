package food.com.groupproject.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import food.com.groupproject.Adapters.recycleradapter;
import food.com.groupproject.Detailkeeper;
import food.com.groupproject.R;
import food.com.groupproject.RecyclerViewListener;
import food.com.groupproject.RoomDatabase.AppDatabase;
import food.com.groupproject.RoomDatabase.Item;

public class lectureteathre extends AppCompatActivity  implements RecyclerViewListener.ClickListener {
    RecyclerView recyclerView;
    Toolbar mtoolbar;
    recycleradapter madpter;
    ArrayList<Item> item;
    AppDatabase mdatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lecturetreathre);
        recyclerView = findViewById(R.id.recylcer);
        mtoolbar = findViewById(R.id.toolbar);
        Bundle bundle = getIntent().getExtras();
        String category = bundle.getString("category");
        mtoolbar.setTitle(category.toUpperCase());
        mtoolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mdatabase = AppDatabase.getInstnace(this);
        item = new ArrayList<>();
        item.addAll(mdatabase.getItemDAO().getlistbycategory(category));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        madpter = new recycleradapter(this, item,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(madpter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view, int position) {
        Item mitem=item.get(position);
        //Toast.makeText(getApplicationContext(),"onclick on "+mitem.getLocation_name(),Toast.LENGTH_SHORT).show();
        LatLng latLng=new LatLng(mitem.getLat(),mitem.getLon());
        Detailkeeper.Detail.getInstance().setOriginlatlng(latLng);
        Detailkeeper.Detail.getInstance().setImagelocation(mitem.getImage_url());
        Detailkeeper.Detail.getInstance().setlocationName(mitem.getLocation_name());
        Intent intent=new Intent(getApplicationContext(),Map.class);
        startActivity(intent);
    }
}
