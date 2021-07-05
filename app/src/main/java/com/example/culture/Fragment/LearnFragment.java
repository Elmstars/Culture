package com.example.culture.Fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.culture.Adapter.StreetAdapter;
import com.example.culture.R;
import com.example.culture.Street;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LearnFragment extends Fragment {
  private RecyclerView RecyclerView;
  private ArrayList<Street> mStreetData;
  private StreetAdapter Adapter;
  public LearnFragment() {
  }

  @Override
  @NonNull
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.learn, container, false);
    RecyclerView = view.findViewById(R.id.recyclerView2);
    RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mStreetData = new ArrayList<>();
    Adapter = new StreetAdapter(getActivity(), mStreetData);
    RecyclerView.setAdapter(Adapter);
    initializeData();
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
        Collections.swap(mStreetData, from, to);
        Adapter.notifyItemMoved(from, to);
        return true;
      }

      @Override
      public void onSwiped(RecyclerView.ViewHolder viewHolder,
                           int direction) {
        // Remove the item from the dataset.
        mStreetData.remove(viewHolder.getAdapterPosition());
        // Notify the adapter.
        Adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
      }
    });
    helper.attachToRecyclerView(RecyclerView);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  public static LearnFragment newInstance() {
    LearnFragment fragment = new LearnFragment();
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

  private void initializeData() {
    String[] streetList = getResources()
      .getStringArray(R.array.street_title);
    String[] streetDetail = getResources()
      .getStringArray(R.array.street_detail);
    TypedArray streetImageResources = getResources()
      .obtainTypedArray(R.array.street_image);

    mStreetData.clear();

    for (int i = 0; i < streetList.length; i++) {
      mStreetData.add(new Street(streetList[i], streetDetail[i],
        streetImageResources.getResourceId(i, 0)));
    }

    streetImageResources.recycle();

    Adapter.notifyDataSetChanged();
  }
}
