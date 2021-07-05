package com.example.culture.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.culture.DetailActivity;
import com.example.culture.Food;
import com.example.culture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.culture.MainActivity.BASEURL;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.ViewHolder> {
  private List<Food> mFoodsData = new ArrayList<>();
  private Context mContext;

  public FoodsAdapter(Context context, ArrayList<Food> foodsData) {
    this.mFoodsData = foodsData;
    this.mContext = context;
  }
  public void addData(List<Food> foodList){
    if(foodList==null)
      return;
    this.mFoodsData.addAll(foodList);
    this.notifyDataSetChanged();
  }

  public void removeItem(Food food){
    mFoodsData.remove(food);
    this.notifyDataSetChanged();
  }

  @Override
  public FoodsAdapter.ViewHolder onCreateViewHolder(
    ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext()).
      inflate(R.layout.list_item, parent, false));
  }

  @Override
  public void onBindViewHolder(ViewHolder holder,int position) {
    Food food = mFoodsData.get(position);
    holder.mTitleText.setText(food.getTitle());
    holder.mDetailText.setText(food.getDetail());
    holder.mInfoText.setText(food.getInfo());
    Glide.with(mContext).load(food.getImageResource()).into(holder.mFoodsImage);
  }

  @Override
  public int getItemCount() {
    return mFoodsData.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {

    private final TextView mTitleText;
    private final TextView mInfoText;
    private final TextView mDetailText;
    private final ImageView mFoodsImage;

    ViewHolder(View itemView) {
      super(itemView);

      mTitleText = itemView.findViewById(R.id.title);
      mInfoText = itemView.findViewById(R.id.info);
      mDetailText = itemView.findViewById(R.id.street_detail);
      mFoodsImage = itemView.findViewById(R.id.foodsImage);

      itemView.setOnClickListener(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
      Food currentFood = mFoodsData.get(getAdapterPosition());
      Intent detailIntent = new Intent(mContext, DetailActivity.class);
      detailIntent.putExtra("title", currentFood.getTitle());
      detailIntent.putExtra("detail", currentFood.getDetail());
      mContext.startActivity(detailIntent);
    }
  }
}
