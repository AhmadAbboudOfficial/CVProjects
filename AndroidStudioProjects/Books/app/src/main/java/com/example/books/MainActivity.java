package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private BookAdapter adapter;
    private static String QUERY_URL = "https://www.googleapis.com/books/v1/volumes?q=java";
    private TextView EmptyStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        //empty state view
        EmptyStateView = findViewById(R.id.empty_view);
        listView.setEmptyView(EmptyStateView);

        //ArrayList<BookItem> list = BookUtil.fetchBooks(QUERY_URL);

        adapter = new BookAdapter(this,new ArrayList<BookItem>());


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookItem book = adapter.getItem(position);

                Intent i = new Intent(MainActivity.this,Main2Activity.class);

                i.putExtra("title",book.getTitle());
                i.putExtra("description",book.getDescription());
                i.putExtra("page count",book.getPageCount());
                i.putExtra("image",book.getImage());
                i.putExtra("previewLink",book.getPreviewLink());

                startActivity(i);
            }
        });
        //checking network connectivity
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            BooksAsyncTask task = new BooksAsyncTask();
            task.execute(QUERY_URL);
        }else{
            ProgressBar loading = findViewById(R.id.loading_indicator);
            loading.setVisibility(View.GONE);
            EmptyStateView.setText(R.string.no_internet_connection);
        }
    }

    private class BooksAsyncTask extends AsyncTask<String,Void,ArrayList<BookItem>>{

        @Override
        protected ArrayList<BookItem> doInBackground(String... url) {

            if (url.length < 1 || url[0] ==null) {
                return null;
            }
            ArrayList<BookItem> list = BookUtil.fetchBooks(url[0]);

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<BookItem> bookItems) {
            adapter.clear();

            if (!bookItems.isEmpty() || bookItems != null){
                adapter.addAll(bookItems);
            }
        }
    }
}
