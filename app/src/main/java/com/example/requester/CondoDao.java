package com.example.requester;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CondoDao {

    @Insert(onConflict = REPLACE)
    void insert(Condo condo);

    @Delete
    void delete(Condo condo);

    @Update
    void update(Condo... condo);

    @Query("SELECT * FROM condo")
    List<Condo> getAll();

    @Query("SELECT * FROM condo WHERE id = :condoId")
    Condo getCondo(int condoId);
}
