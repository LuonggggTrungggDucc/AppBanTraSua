package com.example.project_tra_sua.Home.Tai_khoan.dondamua;

import static com.example.project_tra_sua.Home.Home.chipNavigationBar;
import static com.example.project_tra_sua.xuly.on_back1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project_tra_sua.Home.Home;
import com.example.project_tra_sua.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class donhangdamua extends Fragment {
    TabLayout tab;
    ViewPager2 viewPager2;
    dondamuaviewpager dondamuaviewpager;
    public donhangdamua() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donhangdamua, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab=view.findViewById(R.id.tab);
        viewPager2=view.findViewById(R.id.view);
        dondamuaviewpager=new dondamuaviewpager(this);
        viewPager2.setAdapter(dondamuaviewpager);
        chipNavigationBar.setVisibility(View.GONE);
        Home home= (Home) getActivity();
        on_back1(home,this,R.id.action_donhangdamua_to_tai_khoan2);
        new TabLayoutMediator(tab, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Chờ xác nhận ");

                        break;
                    case 1:
                        tab.setText("Đang giao");

                        break;
                    case 2:
                        tab.setText("Đã giao");

                        break;
                    case 3:
                        tab.setText("Đã hủy");
                        break;
                }

            }
        }).attach();
    }
}