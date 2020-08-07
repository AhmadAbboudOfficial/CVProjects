package com.example.books;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class BookUtil {

    private static final String TAG = "BookUtil";


    public static ArrayList<BookItem> fetchBooks(String l){

        if (TextUtils.isEmpty(l)){
            return null;
        }

        //url handling
        URL url = createUrl(l);
        String json = makeHttpRequest(url);
        ArrayList<BookItem> Books = fetchItemsFromJson(json);

        return Books;

    }


    private static URL createUrl(String url1){
        URL url = null;
        try {
            url = new URL(url1);
        }catch (MalformedURLException e){

        }
        return url;
    }

    private static String makeHttpRequest(URL url){
        String json = "";
        if (url == null){
            return json;
        }

        HttpURLConnection urlConnection = null;
        InputStream IS = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(1500);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            //success
            if (urlConnection.getResponseCode() == 200){
                IS = urlConnection.getInputStream();
                json = readFromStream(IS);
            }else{
                Log.e(TAG,"Failed to get response");
            }
            IS.close();
        }catch (IOException e){
            Log.e(TAG,"Failed to read url");
        }finally {
            if (urlConnection == null){
                urlConnection.disconnect();
            }

        }
        return json;
    }

    private static String readFromStream(InputStream IS){
        StringBuilder build = new StringBuilder();
        if (IS != null){
            InputStreamReader ISR = new InputStreamReader(IS, Charset.forName("UTF-8"));
            BufferedReader bf = new BufferedReader(ISR);
            try {
                String line = bf.readLine();
                while (line != null){
                    build.append(line);
                    line = bf.readLine();
                }
            } catch (IOException e) {

            }

        }
        return build.toString();
    }

    private static ArrayList<BookItem> fetchItemsFromJson(String json){

        if (TextUtils.isEmpty(json)){
            return null;
        }

        ArrayList<BookItem> books = new ArrayList<>();

        try {
            JSONObject all = new JSONObject(json);
            JSONArray items = all.getJSONArray("items");
            for(int i=0;i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                //preview link
                String previewLink = volumeInfo.getString("previewLink");
                //......
                String title = volumeInfo.getString("title");
                JSONArray authors = volumeInfo.getJSONArray("authors");
                String author = authors.getString(0);//there are authors
                String description = volumeInfo.getString("description");
                String PageCount = volumeInfo.getString("pageCount");
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String ImageUrl = imageLinks.getString("smallThumbnail");
                Bitmap bm = getImageBitMap(ImageUrl);
                BookItem item1 = new BookItem(title,"By "+author,description,PageCount,bm);
                //preview link
                item1.setPreviewLink(previewLink);
                books.add(item1);
            }
        }catch (JSONException e){
            Log.e(TAG,"Failed to fetch items from json");
        }

        return books;
    }

    /**
     *
     * @param l book image url in json format as string
     * @return image bit map
     */
    public static Bitmap getImageBitMap(String l){
        URL url = createUrl(l);

        Bitmap bm = null;
        try {
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream IS = connection.getInputStream();
            bm = BitmapFactory.decodeStream(new BufferedInputStream(IS));
        } catch (IOException e) {
            Log.e(TAG,"Failed to get image bitmap");
        }
        return bm;
    }

}
