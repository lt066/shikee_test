package com.example.administrator.shikee_test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.shikee_test.R;
import com.example.administrator.shikee_test.another.MyDialogFragment2;
import com.example.administrator.shikee_test.myAdaper.Item_data;
import com.example.administrator.shikee_test.myAdaper.MyListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lt on 2016/2/15.
 */
public class First  extends Fragment{

    private View view;
    private PullToRefreshListView mPullToRefreshListView;
    private MyListAdapter myListAdaper;
    private Item_data mItem_data;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private final String initUrl = "http://www.duitang.com/album/1733789/masn/p/1/24/";
    private String[] mimg_url;
    private String[] malbnm;
    private String[] mmsg;
    private MyDialogFragment2 myDialogFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.first_layout,container,false);

        //PullToRefreshListView设置



        return view;
    }

    private void init()
    {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.first_listview);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        myListAdaper = new MyListAdapter(getActivity(),R.layout.listview_item);

        mRequestQueue = Volley.newRequestQueue(getActivity());
        stringRequest(initUrl);

        myDialogFragment = new MyDialogFragment2();

    }

    private void stringRequest(String url)
    {
        mStringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    json_data(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        mStringRequest.setTag("initRequest");

        mRequestQueue.add(mStringRequest);
    }


    private void json_data(String json) throws JSONException {
        JSONObject jsonObject= new JSONObject(json);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray blogs = data.getJSONArray("blogs");
        JSONObject[] json_array= new JSONObject[blogs.length()];
        mimg_url= new String[blogs.length()];
        malbnm= new String[blogs.length()];
        mmsg= new String[blogs.length()];
        for(int i =0;i<blogs.length();i++)
        {
            json_array[i]=blogs.getJSONObject(i);
            mimg_url[i]= json_array[i].getString("isrc");
            malbnm[i]= json_array[i].getString("albnm");
            mmsg[i]= json_array[i].getString("msg");
        }

    }


    class MyThread extends Thread
    {
        @Override
        public void run() {
            super.run();
            mRequestQueue.start();
        }
    }




}
