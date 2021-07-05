package com.example.culture.Fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.culture.Adapter.FoodsAdapter;
import com.example.culture.Food;
import com.example.culture.FoodAsync;
import com.example.culture.R;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private ArrayList<Food> mFoodsData;
  private FoodsAdapter mAdapter;

  public HomeFragment() {
  }

  @Override
  @NonNull
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.home, container, false);
    mRecyclerView = view.findViewById(R.id.recyclerView);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mFoodsData = new ArrayList<>();
    mAdapter = new FoodsAdapter(getActivity(), mFoodsData);
    mRecyclerView.setAdapter(mAdapter);
    //initializeData();
    new FoodAsync(mAdapter).execute();
    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
      .SimpleCallback(
      ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
      ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

      @Override
      public boolean onMove(RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder,
                            RecyclerView.ViewHolder target) {
        // Get the from and to positions.
        int from = viewHolder.getAdapterPosition();
        int to = target.getAdapterPosition();

        // Swap the items and notify the adapter.
        Collections.swap(mFoodsData, from, to);
        mAdapter.notifyItemMoved(from, to);
        return true;
      }

      @Override
      public void onSwiped(RecyclerView.ViewHolder viewHolder,
                           int direction) {
        // Remove the item from the dataset.
        mFoodsData.remove(viewHolder.getAdapterPosition());
        // Notify the adapter.
        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
      }
    });
    helper.attachToRecyclerView(mRecyclerView);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  public static HomeFragment newInstance() {
    HomeFragment fragment = new HomeFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

}
