package com.pdm2018.amazon2.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.pdm2018.amazon2.models.Login;

@Database(entities = {Login.class}, version = 1)
    public abstract class Amazon2RoomDatabase extends RoomDatabase {

        private static Amazon2RoomDatabase INSTANCE;
        private static String DATABASE_NAME = "amazone2_database";

        public static Amazon2RoomDatabase getDatabase(final Context context){
            if(INSTANCE == null) {
                //If null sync the class (first call)
                synchronized (Amazon2RoomDatabase.class) {
                    if(INSTANCE == null) {
                        //Create database
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                Amazon2RoomDatabase.class, DATABASE_NAME)
                                .build();
                    }
                }
            }

            return INSTANCE;
        }

    }

