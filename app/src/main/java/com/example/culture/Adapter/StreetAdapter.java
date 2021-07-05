package com.example.culture.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.culture.DetailNew;
import com.example.culture.R;
import com.example.culture.Street;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class StreetAdapter extends RecyclerView.Adapter<StreetAdapter.ViewHolder> {
  private ArrayList<Street> mStreetData;
  private Context Context;

  public StreetAdapter(Context context, ArrayList<Street> streetData) {
    this.mStreetData = streetData;
    this.Context = context;
  }

  @Override
  public StreetAdapter.ViewHolder onCreateViewHolder(
    ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext()).
      inflate(R.layout.street_item, parent, false));
  }

  @Override
  public void onBindViewHolder(ViewHolder holder,
                               int position) {
    Street currentStreet = mStreetData.get(position);
    holder.bindTo(currentStreet);
  }

  @Override
  public int getItemCount() {
    return mStreetData.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {

    // Member Variables for the TextViews
    private final TextView TitleText;
    private final TextView DetailText;
    private ImageView mStreetImage;

    ViewHolder(View itemView) {
      super(itemView);

      // Initialize the views.
      TitleText = itemView.findViewById(R.id.street_title);
      DetailText = itemView.findViewById(R.id.street_detail);
      mStreetImage = itemView.findViewById(R.id.streetImage);

      // Set the OnClickListener to the entire view.
      itemView.setOnClickListener(this);
    }

    void bindTo(Street currentStreet) {
      TitleText.setText(currentStreet.getTitle());
      DetailText.setText(currentStreet.getDetail());
      Glide.with(Context).load(
        currentStreet.getImageResource()).into(mStreetImage);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
      Street currentStreet = mStreetData.get(getAdapterPosition());
      Intent detailNewIntent = new Intent(Context, DetailNew.class);
      detailNewIntent.putExtra("title", currentStreet.getTitle());
      detailNewIntent.putExtra("detail", currentStreet.getDetail());
      Context.startActivity(detailNewIntent);
    }
  }

}
