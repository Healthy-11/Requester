package com.example.requester;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Condo.class, Partowner.class, Provider.class}, version = 4, exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {


    private static DatabaseManager database;

    private static String DATABASE_NAME = "database";

    public  synchronized static DatabaseManager getInstance(Context context){

        if (database == null){
            database = androidx.room.Room.databaseBuilder(context.getApplicationContext()
                    , DatabaseManager.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }

    public abstract CondoDao condoDao();
    public abstract PartownerDao partownerDao();
    public abstract ProviderDao providerDao();

}
