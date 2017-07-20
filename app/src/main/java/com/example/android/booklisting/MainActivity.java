package com.example.android.booklisting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=search+";
    BookListAdapter bookadapter;
    List<BookList> booklist;
    RecyclerView rvList;
    EditText etSearch;
    ImageButton searchButton;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search();
        booklist = new ArrayList<>();
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvList.setLayoutManager(layoutManager);
    }

    void search() {
        etSearch = (EditText) findViewById(R.id.et_search);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booklist.clear();
                String userSearch = String.valueOf(etSearch.getText());
                String formatUserInput = userSearch.trim().replaceAll("\\s+", "+");
                url = baseUrl + formatUserInput;
                FetchData(url);
            }
        });
    }

    void FetchData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(JSONObject response) {
                String title = "";
                String id;
                String preview = "";
                try {
                    JSONArray item = response.getJSONArray("items");
                    for (int i = 0; i < item.length(); i++) {
                        Log.e("Item", String.valueOf(item.length()));
                        JSONObject volumeInfo = item.getJSONObject(i).getJSONObject("volumeInfo");
                        title = volumeInfo.getString("title");
                        preview = volumeInfo.getString("previewLink");
                        JSONArray authors = volumeInfo.getJSONArray("authors");
                        String author = "";
                        for (int k = 0; k < authors.length(); k++) {
                            author += authors.getString(k);
                            if (k != authors.length() - 1) {
                                author += " , ";
                            }
                        }
                        try {
                            JSONObject image = volumeInfo.getJSONObject("imageLinks");
                            id = image.getString("thumbnail");
                            Log.e("Image", id);
                        } catch (Exception e) {
                            id = "";
                        }
                        booklist.add(new BookList(id, title, author, preview));
                    }

                    bookadapter = new BookListAdapter(booklist, MainActivity.this);
                    rvList.setAdapter(bookadapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
