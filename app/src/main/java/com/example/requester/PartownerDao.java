package com.example.requester;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PartownerDao {
    @Insert(onConflict = REPLACE)
    void insert(Partowner partowner);

    @Delete
    void delete(Partowner partowner);

    @Update
    void update(Partowner... partowner);

    @Query("SELECT * FROM partowner")
    List<Partowner> getAll();

    @Query("SELECT * FROM partowner WHERE id = :partownerId")
    Partowner getPartowner(int partownerId);
}
