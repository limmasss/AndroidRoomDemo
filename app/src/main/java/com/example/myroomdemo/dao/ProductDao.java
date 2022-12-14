package com.example.myroomdemo.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.myroomdemo.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

  @Query("SELECT * FROM products WHERE categoryName = :categoryName")
  List<Product> findByCategoryName(String categoryName);
}
