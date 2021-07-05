package com.example.culture;

import android.os.AsyncTask;
import android.util.Log;

import com.example.culture.Adapter.FoodsAdapter;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.culture.MainActivity.BASEURL;

public class FoodAsync extends AsyncTask<Integer,Food, List<Food>> {
  private FoodsAdapter adapter;
  public FoodAsync(FoodsAdapter adapter){
    this.adapter=adapter;
  }

  @Override
  protected List<Food> doInBackground(Integer... integers) {
    List<Food> foods = new ArrayList<>();
    OkHttpClient client=new OkHttpClient();
    Request request = new Request.Builder()
      .url(BASEURL + "/getMyCulture")
      .get()
      .build();
    Response response = null;
    try {
      response = client.newCall(request).execute();
      String responseData = response.body().string();
      Log.e("responseData",responseData);
      JSONObject jsonObject = new JSONObject(responseData);
      JSONArray jsonArray = jsonObject.getJSONArray("data");
      for(int i=0;i<jsonArray.length();i++){
        JSONObject object=jsonArray.getJSONObject(i);
        String title=object.getString("title");
        String image=object.getString("image");
        String detail=object.getString("detail");
        String info=object.getString("info");
        Food food=new Food(title,info,detail,image);
        foods.add(food);
      }
    } catch (IOException | JSONException e) {
      e.printStackTrace();
    }

    return foods;
  }
  @Override
  protected void onProgressUpdate(Food... values) {
    super.onProgressUpdate(values);
  }
  @Override
  protected void onPostExecute(List<Food> foods) {
    super.onPostExecute(foods);
    adapter.addData(foods);

  }
}
