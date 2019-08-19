package com.example.bilibili_improve;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bookstoreFragment extends Fragment {

    private ImageView image;
    private boolean temp = true;  //
    View view2;

    private ListView haoyouListview;
    private List<Map<String, Object>> dataList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_bookstore, container, false);
        view2 = inflater.inflate(R.layout.simple_item, null);

        initlist(view, inflater, container);

        return view;

    }

    private void initlist(View view, LayoutInflater inflater, ViewGroup container) {

        haoyouListview = (ListView) view.findViewById(R.id.haoyou_list);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.simple_item, new String[]{"pic", "text"}, new int[]{R.id.tv_name, R.id.tv_age});
        haoyouListview.setAdapter(simpleAdapter);

    }

    private List<? extends Map<String, ?>> getData() {

        dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("pic", R.mipmap.ic_launcher);
        map1.put("text", "个人中心");
        dataList.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("pic", R.mipmap.ic_launcher);
        map2.put("text", "宝贝中心");
        dataList.add(map2);

        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("text", "爱你全家a ");
            dataList.add(map);
        }
        return dataList;
    }

    private void Data() {
        if (temp) {
            image.setSelected(temp);
            temp = false;
        } else {
            image.setSelected(temp);
            temp = true;
        }
    }
}

