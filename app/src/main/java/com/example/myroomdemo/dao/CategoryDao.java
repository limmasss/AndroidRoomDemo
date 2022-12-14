package com.example.myroomdemo.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.myroomdemo.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {

  @Query("SELECT * FROM categories")
  List<Category> findAll();

  @Query("SELECT * FROM categories WHERE name in (:names)")
  List<Category> findByNamesIn(List<String> names);
}
