package com.pdm2018.amazon2.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pdm2018.amazon2.database.DAO.ProductDAO;
import com.pdm2018.amazon2.database.entities.Product;

@Database(entities = {Product.class}, exportSchema = false, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;
    private static final String DB_NAME = "forever33.db";

    public static synchronized AppDB getInstance(Context context) {
        if (instance == null) {
            instance = createDB(context);
        }
        return instance;
    }

    private static AppDB createDB(Context context) {
        return Room
                .databaseBuilder(context, AppDB.class, DB_NAME)
                .addCallback(addCallback)
                .build();
    }

    public abstract ProductDAO productDAO();

    private static Callback addCallback=
            new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                new Insert(instance).execute();
            }
        };

    public static class Insert extends AsyncTask<Void, Void, Void>{

        private final ProductDAO productDAO;

        Insert(AppDB appDB) {
            this.productDAO = appDB.productDAO();
        }

        @Override
        protected Void doInBackground(Void ... voids) {
            productDAO.insertProduct(new Product(0, "HOLA", "category", "genero", "description", 12.56));
            return null;
        }
    }
}
