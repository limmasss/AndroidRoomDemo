package com.example.myroomdemo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myroomdemo.dao.CategoryDao;
import com.example.myroomdemo.dao.ProductDao;
import com.example.myroomdemo.entity.Category;
import com.example.myroomdemo.entity.Product;

@Database(
    entities = {Category.class, Product.class},
    version = 1)
public abstract class RoomDemoDB extends RoomDatabase {

  abstract CategoryDao categoryDao();

  abstract ProductDao productDao();

  private static final String DB_NAME = "room.db";
  private static volatile RoomDemoDB INSTANCE = null;

  static synchronized RoomDemoDB get(Context ctxt) {

    if (INSTANCE == null) {
      INSTANCE = create(ctxt, false);
    }
    return (INSTANCE);
  }

  static RoomDemoDB create(Context ctxt, boolean memoryOnly) {
    Builder<RoomDemoDB> b;
    if (memoryOnly) {
      b = Room.inMemoryDatabaseBuilder(ctxt.getApplicationContext(), RoomDemoDB.class);
    } else {
      b = Room.databaseBuilder(ctxt.getApplicationContext(), RoomDemoDB.class, DB_NAME);
    }
    return (b.build());
  }
}
