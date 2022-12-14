package com.example.myroomdemo;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myroomdemo.adapter.CategoriesRecyclerAdapter;
import com.example.myroomdemo.adapter.ProductsRecyclerAdapter;
import com.example.myroomdemo.dao.CategoryDao;
import com.example.myroomdemo.dao.ProductDao;
import com.example.myroomdemo.entity.Category;
import com.example.myroomdemo.entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

  RecyclerView productsView;
  RecyclerView categoriesView;
  DatabaseHelper databaseHelper;
  SQLiteDatabase db;
  Cursor cursor;
  ProductsRecyclerAdapter productAdapter;
  CategoriesRecyclerAdapter categoriesRecyclerAdapter;
  CategoryDao categoryDao;
  ProductDao productDao;
  RoomDemoDB roomDb;

  List<Product> products = new ArrayList<>();
  List<Category> categories = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
    showCategories();
    showProductsForCategories();
  }

  @SuppressLint("MissingInflatedId")
  private void init() {
    setContentView(R.layout.activity_main);
    productsView = findViewById(R.id.productsView);
    categoriesView = findViewById(R.id.categoriesView);
    databaseHelper = new DatabaseHelper(this);
    databaseHelper.create_db();
    db = databaseHelper.getReadableDatabase();
    roomDb = RoomDemoDB.create(this, false);
    categoryDao = roomDb.categoryDao();
    productDao = roomDb.productDao();
  }

  private void showCategories(String... categories) {
    Thread thread;
    LinearLayoutManager layout =
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    if (categories == null || categories.length == 0) {
      thread =
          new Thread(
              () -> {
                List<Category> allCategories = categoryDao.findAll();
                if (this.categories.isEmpty()) {
                  this.categories.addAll(allCategories);
                }
                categoriesRecyclerAdapter = new CategoriesRecyclerAdapter(this.categories);
                categoriesView.setLayoutManager(layout);
                categoriesView.setAdapter(categoriesRecyclerAdapter);
              });
      thread.start();
    } else {
      // TODO
    }
  }

  public void showProductsForCategories(String... categories) {
    if (categories == null || categories.length == 0) {
      cursor = db.rawQuery("SELECT * FROM products", null);
      List<Product> products = new ArrayList<>();
      while (cursor.moveToNext()) {
        products.add(
            new Product(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3)));
      }
      if (this.products.isEmpty()) {
        this.products.addAll(products);
      }
      productAdapter = new ProductsRecyclerAdapter(this.products);
    } else {
      //  TODO переделать на обращение к бд
      //  Добавление вызова метода в отдельный поток не помогает
      //  избавиться от исключения
      productAdapter = new ProductsRecyclerAdapter(productDao.findByCategoryName(categories[0]));
      productAdapter =
          new ProductsRecyclerAdapter(
              this.products.stream()
                  .filter(product -> Arrays.asList(categories).contains(product.getCategoryName()))
                  .collect(Collectors.toList()));
    }
    LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    productsView.setLayoutManager(layout);
    productsView.setAdapter(productAdapter);
    cursor.close();
  }

  public void onClearFilterClick(View view) {
    showCategories();
    showProductsForCategories();
  }

  public void onItemSelectedListener(View view) {
    Button categoryButton = (Button) view;
    String categoryName = categoryButton.getText().toString();
    showCategories(categoryName);
    showProductsForCategories(categoryName);
  }
}
