package com.example.myroomdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myroomdemo.R;
import com.example.myroomdemo.entity.Category;

import java.util.List;

public class CategoriesRecyclerAdapter
    extends RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryViewHolder> {

  List<Category> categories;

  public CategoriesRecyclerAdapter(List<Category> categories) {
    this.categories = categories;
  }

  @NonNull
  @Override
  public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new CategoryViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
    Category category = categories.get(position);
    holder.name.setText(category.getName());
  }

  @Override
  public int getItemCount() {
    return categories.size();
  }

  public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private Button name;

    public CategoryViewHolder(@NonNull View itemView) {
      super(itemView);
      name = itemView.findViewById(R.id.nameProduct);
    }
  }
}
