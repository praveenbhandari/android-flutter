package com.example.country.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class},version = 3,exportSchema = false)
public abstract class country_db extends RoomDatabase {

    public abstract doaaa ddoa();
    private  static country_db INSTANCE;
    public static country_db getdbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),country_db.class, "country")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
    return INSTANCE;
    }


}
