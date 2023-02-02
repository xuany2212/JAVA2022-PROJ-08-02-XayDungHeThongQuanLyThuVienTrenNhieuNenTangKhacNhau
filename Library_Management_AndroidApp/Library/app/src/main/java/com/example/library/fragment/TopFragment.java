package com.example.library.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.library.R;
import com.example.library.adapter.TopAdapter;
import com.example.library.dao.RevenueDAO;
import com.example.library.model.Top;

import java.util.ArrayList;

public class TopFragment extends Fragment {
    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        lv = v.findViewById(R.id.lvTop);
        RevenueDAO revenueDAO = new RevenueDAO(getActivity());
        list = (ArrayList<Top>) revenueDAO.getTop();
        adapter = new TopAdapter(getActivity(), list);
        lv.setAdapter(adapter);
        return v;
    }
}
