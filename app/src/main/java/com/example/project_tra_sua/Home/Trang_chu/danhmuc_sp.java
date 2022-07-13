package com.example.project_tra_sua.Home.Trang_chu;

import static com.example.project_tra_sua.Home.Home.chipNavigationBar;
import static com.example.project_tra_sua.xuly.on_back1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_tra_sua.API.API;
import com.example.project_tra_sua.Home.Home;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.san_pham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class danhmuc_sp extends Fragment {

    RecyclerView recy_2;
    TextView ten_danhmuc;
    sp_adapter sp_adapter;
    ArrayList<san_pham>arrayList;
    int iddm;
    ImageView image;
    public danhmuc_sp() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_danhmuc_sp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recy_2=view.findViewById(R.id.recy_2);
        ten_danhmuc=view.findViewById(R.id.ten_danhmuc);
        image=view.findViewById(R.id.img_back);
        chipNavigationBar.setVisibility(View.GONE);
        iddm=getArguments().getInt("iddm");
        ten_danhmuc.setText(getArguments().getString("tendm"));
        getData();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipNavigationBar.setVisibility(View.VISIBLE);
                Navigation.findNavController(view).navigate(R.id.action_danhmuc_sp_to_trang_chu);
            }
        });
        Home home= (Home) getActivity();
        on_back1(home,this, R.id.action_danhmuc_sp_to_trang_chu);
    }

    private void getData() {
        API.API.Get_all_dm(iddm).enqueue(new Callback<List<san_pham>>() {
            @Override
            public void onResponse(Call<List<san_pham>> call, Response<List<san_pham>> response) {
                arrayList= (ArrayList<san_pham>) response.body();
                recy_2.setLayoutManager(new GridLayoutManager(getContext(),2));
                sp_adapter=new sp_adapter(arrayList,danhmuc_sp.this,1);
                recy_2.setAdapter(sp_adapter);
            }

            @Override
            public void onFailure(Call<List<san_pham>> call, Throwable t) {
                Log.e("loi",""+t);
            }
        });
    }
}