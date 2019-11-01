package food.com.groupproject.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface itemDAO {
@Insert
    public  void  insert( Item... items);
@Update
    public  void  update(Item... items);
@Delete
    public  void  delete(Item item);
@Query("SELECT * FROM Locations WHERE category =:category ")
    public List<Item> getlistbycategory(String category);

@Query("SELECT * FROM Locations")
    public  List<Item> getlist();


}

