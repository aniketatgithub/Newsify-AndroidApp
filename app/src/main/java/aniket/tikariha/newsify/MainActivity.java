package aniket.tikariha.newsify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CustomAdapter.NewsItemClicked {
    ArrayList<News> numbers;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchdata();
        customAdapter = new CustomAdapter(this);
        recyclerView.setAdapter(customAdapter);

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(MainActivity.this, myAppIntro.class));
            Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_LONG)
                    .show();
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();



    }
    void fetchdata(){
        Log.d("tag", "onErrorResponse: called");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "https://saurav.tech/NewsAPI/top-headlines/category/technology/in.json", null, new Response.Listener<JSONObject>() {

                    public void onResponse(JSONObject response) {
                        try {Log.d("tag", "onErrorResponse: noice");
                            JSONArray jsonArray = response.getJSONArray("articles");
                            ArrayList<News> newsArray = new ArrayList<News>();
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject news = jsonArray.getJSONObject(i);
                                News newss = new News(news.getString("title"),news.getString("author") , news.getString("url"),news.getString("urlToImage"),news.getString("description"));
                                newsArray.add(newss);
                            }
                            customAdapter.updateNews(newsArray);
                        } catch (JSONException e) {
                            Log.d("tag", "onErrorResponse: notnoice");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("tag", "onErrorResponse: errorrr");
                    }
                });
// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    public void onItemClicked(News item) {
        //Toast.makeText(this, "noice", Toast.LENGTH_SHORT).show();

      CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
      CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}
