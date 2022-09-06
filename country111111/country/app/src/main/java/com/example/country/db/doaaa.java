package com.example.country.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface doaaa {

    @Query("SELECT * FROM country ORDER BY c_name ASC")
  List<Country> getAll();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCountry(Country... countries);

    @Delete
    void deleteAll(Country country);

}
