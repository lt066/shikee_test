package com.example.administrator.shikee_test.myAdaper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shikee_test.R;
import com.example.administrator.shikee_test.volley.ImageCacheManager;

/**
 * Created by lt413 on 2016/2/15.
 */
public class MyListAdapter extends ArrayAdapter<Item_data>
{
    private int mResource;

    public MyListAdapter(Context context, int resource) {
        super(context, resource);
        mResource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item_data item_data = getItem(position);
        View item_view = LayoutInflater.from(getContext()).inflate(mResource, null);
        TextView albnm_text = (TextView) item_view.findViewById(R.id.albnm);
        TextView msg_text = (TextView) item_view.findViewById(R.id.msg);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.img_list_item);
        albnm_text.setText(item_data.getAlbnm());
        msg_text.setText(item_data.getMsg());
        CacheImage(imageView,item_data.getImgUrl());

        return super.getView(position, convertView, parent);
    }


    public void CacheImage(ImageView view,String url){
        Bitmap defaultImage= BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.img_default);
        Bitmap errorImage= BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.img_error);
        ImageCacheManager.loadImage(getContext(), url, view, defaultImage, errorImage);
    }
}
