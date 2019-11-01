package food.com.groupproject;

import android.content.Context;
import android.content.SharedPreferences;

public class sharepref {
    private SharedPreferences preferences;
    private  SharedPreferences.Editor editor;
    private int Private_Mode=0;
    private String pref_name="oau map";
    private  final String  firstopen="App is first Lanuch";
    public  sharepref(Context context){
        preferences=context.getSharedPreferences(pref_name,Private_Mode);
        editor=preferences.edit();


    }

    public  void setFirstopen(boolean open){

        editor.putBoolean(firstopen,open);
        editor.commit();

    }

    public  boolean getFirstopen(){

        return preferences.getBoolean(firstopen,false);

    }

}
