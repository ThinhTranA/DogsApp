package com.visualsharp.dogsapp.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DogDao {
    @Insert
    List<Integer> insertAll(DogBreed... dogs);

    @Query("SELECT * FROM dogbreed")
    List<DogBreed> getAllDogs();

    @Query("SELECT * FROM dogbreed WHERE uuui = :dogId")
    DogBreed getDog(int dogId);

    @Query("DELETE FROM dogbreed ")
    void deleteAllDogs();
}
