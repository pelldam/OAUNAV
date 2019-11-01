package food.com.groupproject.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import food.com.groupproject.R;
import food.com.groupproject.sharepref;

import static android.os.Build.VERSION.SDK_INT;

public class selectionactivity extends AppCompatActivity {
    Button visitor,student;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }
        setContentView(R.layout.selectionpage);

    visitor=findViewById(R.id.visitor);
    student=findViewById(R.id.student);
    visitor.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
directionactivity("visitor");
        }
    });
    student.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            directionactivity("student");

        }
    });

    }

    void directionactivity(String name){

        Intent intent=new Intent(this,directionactivity.class);
        intent.putExtra("name",name);

        startActivity(intent);

    }
}
