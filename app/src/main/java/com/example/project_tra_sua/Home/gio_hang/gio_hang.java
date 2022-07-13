package com.example.project_tra_sua.Home.gio_hang;

import static com.example.project_tra_sua.Home.Home.chipNavigationBar;
import static com.example.project_tra_sua.Home.Home.currencyVN;
import static com.example.project_tra_sua.Home.Home.idtk;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_tra_sua.API.API;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.giohang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gio_hang extends Fragment {

    RecyclerView list_item;
    TextView sl, tongtien;
    LinearLayout layout_giohang, layout1;
    giohangadapter giohangadapter;
    Handler handler = new Handler();
    Runnable runnable;
    LinearLayout dathang;
    Button muasp;
    public gio_hang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gio_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_item = view.findViewById(R.id.list_item);
        layout1 = view.findViewById(R.id.layout1);
        sl = view.findViewById(R.id.sl);
        tongtien = view.findViewById(R.id.tongtien);
        dathang = view.findViewById(R.id.dathang);
        muasp = view.findViewById(R.id.muasp);
        layout_giohang = view.findViewById(R.id.layout_giohang);
        getdata();
        getdata1();
        dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_gio_hang2_to_don_hang);
            }
        });
        muasp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipNavigationBar.setItemSelected(R.id.home, true);
            }
        });
    }

    private void getdata1() {
        API.API.tongtien(idtk,"không").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    layout1.setVisibility(View.VISIBLE);
                    layout_giohang.setVisibility(View.GONE);
                    tongtien.setText(currencyVN.format(Integer.parseInt(response.body())));
                }else {
                    layout1.setVisibility(View.GONE);
                    layout_giohang.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void getdata() {
        API.API.getallsp(idtk,"không").enqueue(new Callback<List<giohang>>() {
            @Override
            public void onResponse(Call<List<giohang>> call, Response<List<giohang>> response) {
                if (response.isSuccessful()) {
                    sl.setText("" + response.body().size());
                    ArrayList<giohang> arrayList = (ArrayList<giohang>) response.body();
                    giohangadapter = new giohangadapter(arrayList, gio_hang.this,0);
                    list_item.setLayoutManager(new LinearLayoutManager(getContext()));
                    list_item.setAdapter(giohangadapter);
                    giohangadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<giohang>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed( runnable = new Runnable() {
            public void run() {
                getdata1();
                API.API.getallsp(idtk,"không").enqueue(new Callback<List<giohang>>() {
                    @Override
                    public void onResponse(Call<List<giohang>> call, Response<List<giohang>> response) {
                        if (response.isSuccessful()) {
                            if (response.body().size()< Integer.parseInt(sl.getText().toString())){
                                getdata();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<giohang>> call, Throwable t) {

                    }
                });
                handler.postDelayed(runnable, 2000);
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}