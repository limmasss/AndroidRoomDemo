package com.example.myroomdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myroomdemo.R;
import com.example.myroomdemo.entity.Product;

import java.util.List;

public class ProductsRecyclerAdapter
    extends RecyclerView.Adapter<ProductsRecyclerAdapter.ProductViewHolder> {

  List<Product> products;

  public ProductsRecyclerAdapter(List<Product> products) {
    this.products = products;
  }

  @NonNull
  @Override
  public ProductsRecyclerAdapter.ProductViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType) {
    return new ProductViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
  }

  @Override
  public void onBindViewHolder(
      @NonNull ProductsRecyclerAdapter.ProductViewHolder holder, int position) {
    Product product = products.get(position);
    holder.name.setText(product.getName());
    holder._id.setText(String.valueOf(product.get_id()));
    holder.categoryName.setText(product.getCategoryName());
    holder.price.setText(String.valueOf(product.getPrice()));
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  public class ProductViewHolder extends RecyclerView.ViewHolder {

    private TextView _id;
    private TextView name;
    private TextView categoryName;
    private TextView price;

    public ProductViewHolder(@NonNull View itemView) {
      super(itemView);
      _id = itemView.findViewById(R.id._idProduct);
      name = itemView.findViewById(R.id.nameProduct);
      categoryName = itemView.findViewById(R.id.categoryName);
      price = itemView.findViewById(R.id.price);
    }
  }
}
