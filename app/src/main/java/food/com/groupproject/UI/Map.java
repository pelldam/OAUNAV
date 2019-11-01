package food.com.groupproject.UI;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import food.com.groupproject.Detailkeeper;
import food.com.groupproject.FragmentAdapter;
import food.com.groupproject.R;

public class Map extends AppCompatActivity {
Toolbar mtoolbar;
ViewPager vpager;
TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setContentView(R.layout.mainmap);
    mtoolbar=findViewById(R.id.toolbar);

    mtoolbar.setTitleTextColor(Color.WHITE);
    mtoolbar.setTitle(Detailkeeper.Detail.getInstance().getLocationName());
        setSupportActionBar(mtoolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    tabLayout=findViewById(R.id.tabs);
    vpager=findViewById(R.id.vpager);
        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager());
        vpager.setAdapter(fragmentAdapter);
        TabLayout.Tab direction=tabLayout.newTab();
        TabLayout.Tab image=tabLayout.newTab();

        direction.setText("Directions");
        image.setText("Location Image");
        direction.isSelected();

        image.isSelected();
        tabLayout.addTab(direction,0);
        tabLayout.addTab(image,1);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        vpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


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

}
