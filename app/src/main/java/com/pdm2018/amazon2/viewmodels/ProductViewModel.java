package com.pdm2018.amazon2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pdm2018.amazon2.database.entities.Product;
import com.pdm2018.amazon2.database.repositories.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository productRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
    }

    public void insertProduct(Product product) {
        productRepository.insert(product);
    }

    public LiveData<List<Product>> getAllProducts() {
        return productRepository.getAllProducts();
    }
}
