package food.com.groupproject.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Item.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private  static String LOG=AppDatabase.class.getSimpleName();
    private  static final   Object LOCK=new Object();
     private  static final String Database_name="Locationdb";
     private  static AppDatabase  instnace;
     private static RoomDatabase roomdatabase;
     public static  AppDatabase getInstnace(Context context ){

         if (instnace==null){



             synchronized (LOCK){



                 instnace=Room.databaseBuilder(context,AppDatabase.class,AppDatabase.Database_name)
                         .allowMainThreadQueries().build();
             }
         }

return instnace;
     }
    public  abstract itemDAO getItemDAO();
}
