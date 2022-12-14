package com.example.myroomdemo.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {

  @PrimaryKey @NonNull private int _id;

  private String name;

  private String categoryName;

  private Float price;

  public Product() {}

  public Product(int _id, String name, String categoryName, Float price) {
    this._id = _id;
    this.name = name;
    this.categoryName = categoryName;
    this.price = price;
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

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }
}
