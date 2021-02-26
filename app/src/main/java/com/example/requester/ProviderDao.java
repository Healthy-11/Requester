package com.example.requester;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

    @Dao
    public interface ProviderDao {
        @Insert(onConflict = REPLACE)
        void insert(Provider provider);

        @Delete
        void delete(Provider provider);

        @Update
        void update(Provider... provider);

        @Query("SELECT * FROM provider")
        List<Provider> getAll();

        @Query("SELECT * FROM provider WHERE id = :providerId")
        Provider getProvider(int providerId);
}
