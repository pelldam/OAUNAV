package food.com.groupproject.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import food.com.groupproject.R;

public class directionactivity extends AppCompatActivity {
    Toolbar toolbar;
    Button  teathre,popular,facu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   setContentView(R.layout.selectiondirection);
String name=getIntent().getStringExtra("name");
   toolbar=findViewById(R.id.toolbar);
setSupportActionBar(toolbar);


toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
teathre=findViewById(R.id.l_threatre);
popular=findViewById(R.id.popular_area);
facu=findViewById(R.id.fac);

if (name.contentEquals("visitor")){

    teathre.setVisibility(View.GONE);
}
teathre.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getApplicationContext(),lectureteathre.class);

        intent.putExtra("category","theatre");
        startActivity(intent);
    }
});
facu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent=new Intent(getApplicationContext(),lectureteathre.class);

        intent.putExtra("category","faculty/department");
        startActivity(intent);
    }
});

popular.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Intent intent=new Intent(getApplicationContext(),lectureteathre.class);

        intent.putExtra("category","popular places");
        startActivity(intent);
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
