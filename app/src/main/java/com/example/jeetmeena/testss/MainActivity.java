package com.example.jeetmeena.testss;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity   {
  TextView hello;
  RequestQueue requestQueue;
  String urlGet,urlPost,urlPut;
  static  final String tagCancel="CancelTag";
  Button  getButton,postButton,putButton;
    JsonObjectRequest jsonObjectRequest;
  ArrayList<JsonData> arrayList;
  String[] listOfPopularSearch;
  RecyclerView itemListRecycler;
  RecyclerView.Adapter mAdapter;
  RecyclerView.LayoutManager mLayoutManager;
  StaggeredGridLayoutManager staggeredGridLayoutManager;
  ImageView imageView;
    HashMap<Integer, Bitmap> imagesBitmap;
  int[] imageRes;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
                requestQueue= Volley.newRequestQueue(this);
            itemListRecycler=findViewById(R.id.itemList_recycler);

              listOfPopularSearch=new String[]{"Art","Food","Fun","Family","Halloween","Kissing",
                    "Design","Mockup","Success","Yoga","Vintage","Mountains","River","Sport",
                    "Friends","Adventure","Couple","Sad","Nature","Love","dark"};
            imageRes=new int[]{R.drawable.art,R.drawable.food,R.drawable.famliy,R.drawable.halloween1,R.drawable.kissing,R.drawable.design,
                    R.drawable.mockup,R.drawable.succeses,R.drawable.yoga,R.drawable.vintage,R.drawable.mountains,R.drawable.river1,R.drawable.sport
                  ,R.drawable.friends,R.drawable.adventure1,R.drawable.couple,R.drawable.sad2,R.drawable.nature,R.drawable.love1,R.drawable.dark};


            //creatImageBitmap();
            mLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
            staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,1);
            arrayList=new ArrayList<>();
            //https://simplifiedcoding.net/demos/view-flipper/heroes.php;  JSONArray arrayData=jsonData.getJSONArray("heroes");
            //http://dummy.restapiexample.com/api/v1/employees
            //http://httpbin.org/get?param1=hello

            itemListRecycler.setLayoutManager(staggeredGridLayoutManager);
            mAdapter=new PopulerItemList(listOfPopularSearch);
            itemListRecycler.setAdapter(mAdapter);
            //getPopuleraSearch("art",1);
          //  getPopuleraSearch("food",2);
           // getPopuleraSearch("fun",3);
            //ItemListFragment itemListFragment=new ItemListFragment();
            //FragmentManager fragmentManager=getSupportFragmentManager();
            //fragmentManager.beginTransaction().attach(itemListFragment);


    }







    public void setGet(){
        urlGet="https://api.pexels.com/v1/search?query=example+query&per_page=15&page=1";
                //"http://httpbin.org/get?param1=hello";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlGet, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "g "+response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject arge=response.getJSONObject("args");
                    hello.setText(arge.getString("param1"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "That did't work", Toast.LENGTH_SHORT).show();
            }
        });

        jsonObjectRequest.setTag(tagCancel);
        requestQueue.add(jsonObjectRequest);
    }

    /*
    function prepareUrl(directory, query, perPage, page) {

    var search = query ? "query=" + (query ? encodeURIComponent(query) : "") : "";

    return directory + "?" + search +

        "&per_page=" + (perPage && !isNaN(perPage) ? +perPage : 10) +

        "&page=" + (page && !isNaN(page) ? +page : 1);

    }
    */

    public void creatImageBitmap(){

         imagesBitmap=new HashMap<>();
        for(int i=0;i<imageRes.length;i++){
            imagesBitmap.put(i, BitmapFactory.decodeResource(Resources.getSystem(),imageRes[i]));


        }
    }

    public void setPost(){
        urlPost="https://api.pexels.com/v1/search?&per_page=15&page=1&query=technology";
        //https://api.pexels.com/v1/search?query=example+query&per_page=15&page=1
                //"http://httpbin.org/post";
       // String strig="https://api.pexels.com/v1/search?"+"query=food"+"&per_page=15&page=1";
        //    var search = query ? "query=" + (query ? encodeURIComponent(query) : "") : "";
        jsonObjectRequest  = new JsonObjectRequest(Request.Method.GET, urlPost, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "po "+response, Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "That did't work"+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<>();
                headers.put("Content-Type","application/json; charset=UTF-8");
                headers.put("Authorization","563492ad6f91700001000001bfba11288c6e4da9999f57a81fea1c64");

                return headers;
            }
        };
        jsonObjectRequest.setTag(tagCancel);
        requestQueue.add(jsonObjectRequest );
    }
    public void setput(){
        urlPut="http://httpbin.org/put";
         jsonObjectRequest  =new JsonObjectRequest(Request.Method.PUT,urlPut, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "pu "+response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "That did't work", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()   {
                Map<String,String> dataMap=new HashMap<>();
                dataMap.put("name","Jeetpal Meena");
                dataMap.put("domain", "http://itsalif.info");
              //  dataMap.put("email","jeetpalmeena143@gmail.com");
            //    dataMap.put("password","unknown");
                return dataMap ;
            }
        };
        jsonObjectRequest .setTag(tagCancel);
        requestQueue.add(jsonObjectRequest );
    }
    public void cancelReq(String tag){
        if(requestQueue!=null){
            requestQueue.cancelAll(tag);
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public ArrayList<String> getPopuleraSearch(String urlQuery, final int off){
        //urlPost="https://api.pexels.com/v1/search?&per_page=15&page=1&query=technology";
        //https://api.pexels.com/v1/search?query=example+query&per_page=15&page=1
        //"http://httpbin.org/post";
        final ArrayList<String> arrayList=new ArrayList<>();

        String strig="https://api.pexels.com/v1/search?"+"query="+urlQuery+"&per_page=15&page=1";
        //    var search = query ? "query=" + (query ? encodeURIComponent(query) : "") : "";
        jsonObjectRequest  = new JsonObjectRequest(Request.Method.GET, strig, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
              //  Toast.makeText(MainActivity.this, "po "+object3, Toast.LENGTH_LONG).show();

                try {
                   JSONArray object1=  response.getJSONArray("photos");
                    for(int i=0;i<object1.length();i++){
                        JSONObject object2=object1.getJSONObject(i);
                        JSONObject object3=object2.getJSONObject("src");
                        arrayList.add(i,object3.getString("original"));
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
                getData(arrayList,off);
               // Toast.makeText(MainActivity.this, "po "+arrayList, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "That did't work"+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<>();
                headers.put("Content-Type","application/json; charset=UTF-8");
                headers.put("Authorization","563492ad6f91700001000001bfba11288c6e4da9999f57a81fea1c64");

                return headers;
            }
        };
        jsonObjectRequest.setTag(tagCancel);
        requestQueue.add(jsonObjectRequest );

        return arrayList;

    }
    public void getData(ArrayList<String> s,int off){

      switch (off){
          case 1:

             // artRecycler.setLayoutManager(mLayoutManager);
            //  mAdapter=new ArtAdapter(s);
            // artRecycler.setAdapter(mAdapter);
              break;
          case 2:
             // foodRecycler.setLayoutManager(mLayoutManager);
             // mAdapter=new ArtAdapter(s);
              //foodRecycler.setAdapter(mAdapter);
              break;
          case 3:
              //funRecycler.setLayoutManager(mLayoutManager);
              //mAdapter=new ArtAdapter(s);
              //funRecycler.setAdapter(mAdapter);
              break;
          case 4:
              break;
      }

    }

    private class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.MyViewHolder> {
        ArrayList<String> dataList;
        public ArtAdapter(ArrayList<String> s) {
            dataList=s;
        }


        @NonNull
        @Override
        public ArtAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_view_item,null);
            MyViewHolder myViewHolder=new MyViewHolder(v);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ArtAdapter.MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }
    }


    private class PopulerItemList extends RecyclerView.Adapter<PopulerItemList.ItemViewHolder> {
        String[] itemPopulerList;
        public PopulerItemList(String[] listOfPopularSearch) {
            itemPopulerList=listOfPopularSearch;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_view_item,null);
            ItemViewHolder itemViewHolder=new ItemViewHolder(view);
            return  itemViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
                  holder.title.setText(itemPopulerList[position]);
                 // holder.imageView2.setImageBitmap(imagesBitmap.get(position));
        }

        @Override
        public int getItemCount() {
            return itemPopulerList.length;
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView2,imageView3;
            ImageButton download,faviret;
            TextView title;
            public ItemViewHolder(View itemView) {
                super(itemView);
                imageView2=itemView.findViewById(R.id.imageView2);
                imageView3=itemView.findViewById(R.id.imageView3);
                download=itemView.findViewById(R.id.download);
                faviret=itemView.findViewById(R.id.faviret);
                //title=itemView.findViewById(R.id.testText);

            }
        }
    }
}
