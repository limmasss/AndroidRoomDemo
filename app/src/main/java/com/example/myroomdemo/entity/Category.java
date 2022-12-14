package com.example.myroomdemo.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {

  @PrimaryKey @NonNull private int _id;

  @NonNull private String name;

  public Category() {}

  public Category(int _id, String name) {
    this._id = _id;
    this.name = name;
  }

  public int get_id() {
    return _id;
  }

  public void set_id(int _id) {
    this._id = _id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Category{" + "_id=" + _id + ", name='" + name + '\'' + '}';
  }
}
