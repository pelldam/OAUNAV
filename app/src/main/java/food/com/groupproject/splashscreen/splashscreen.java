package food.com.groupproject.splashscreen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import food.com.groupproject.R;
import food.com.groupproject.UI.firstlaunch;
import food.com.groupproject.UI.selectionactivity;
import food.com.groupproject.sharepref;

import static android.os.Build.VERSION.SDK_INT;

public class splashscreen extends AppCompatActivity {
private sharepref  pref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

setContentView(R.layout.splashscreen);
        if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }
pref=new sharepref(this);
Thread thread=new Thread(new Runnable() {
    @Override
    public void run() {
        try{

            Thread.sleep(5000);
        }catch (Exception e){


            e.printStackTrace();
        }

        finally {
         nextactivity();
        }
    }
});

thread.start();
    }


    void nextactivity(){

        if (pref.getFirstopen()) {
            Intent intent=new Intent(this,selectionactivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this,firstlaunch.class);

            startActivity(intent);

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    finish();
    }
}
