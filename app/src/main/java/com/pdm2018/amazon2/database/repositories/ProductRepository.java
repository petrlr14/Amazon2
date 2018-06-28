package com.pdm2018.amazon2.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pdm2018.amazon2.database.AppDB;
import com.pdm2018.amazon2.database.DAO.ProductDAO;
import com.pdm2018.amazon2.database.entities.Product;

import java.util.List;

public class ProductRepository {

    private ProductDAO productDAO;

    public ProductRepository(Application application) {
        AppDB db = AppDB.getInstance(application);
        this.productDAO = db.productDAO();
    }

    public void insert(Product product) {
        productDAO.insertProduct(product);
    }

    public LiveData<List<Product>> getAllProducts() {
        return productDAO.getProducts();
    }

    private static class InsertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDAO productDAO;

        public InsertAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }


        @Override
        protected Void doInBackground(Product... products) {
            productDAO.insertProduct(products[0]);
            return null;
        }
    }

}
