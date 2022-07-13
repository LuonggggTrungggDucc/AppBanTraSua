package com.example.project_tra_sua.Home.Tai_khoan;

import static com.example.project_tra_sua.Home.Home.idtk;
import static com.example.project_tra_sua.xuly.kiemtra_tt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.project_tra_sua.API.API;
import com.example.project_tra_sua.MainActivity;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.taikhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tai_khoan extends Fragment {

    TextView ten, email, sdt, diachi, phanhoi;
    LinearLayout layout1, layout2, layout3;
    ImageView dx;

    public Tai_khoan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tai_khoan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ten = view.findViewById(R.id.ten);
        email = view.findViewById(R.id.email);
        sdt = view.findViewById(R.id.sdt);
        diachi = view.findViewById(R.id.diachi);
        layout1 = view.findViewById(R.id.layout1);
        layout2 = view.findViewById(R.id.layout2);
        layout3 = view.findViewById(R.id.layout3);
        phanhoi = view.findViewById(R.id.phanhoi);
        dx = view.findViewById(R.id.dx);
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Chức năng đang bảo trì !", Toast.LENGTH_SHORT).show();
            }
        });
        phanhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Chức năng đang bảo trì !", Toast.LENGTH_SHORT).show();
            }
        });
        dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("ttcn", 0);
                Navigation.findNavController(view).navigate(R.id.action_tai_khoan2_to_thong_tin_ca_nhan, bundle);
            }
        });
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_tai_khoan2_to_donhangdamua);
            }
        });

        getdata();
    }

    private void getdata() {
        API.API.gettt(idtk).enqueue(new Callback<taikhoan>() {
            @Override
            public void onResponse(Call<taikhoan> call, Response<taikhoan> response) {
                if (response.isSuccessful()) {
                    ten.setText("Họ tên: " + response.body().getHoten());
                    email.setText("Email: " + response.body().getEmail());
                    kiemtra_tt(getContext(), response.body().getSdt(), sdt, response.body().getSdt(), "Thêm số điện thoại", "SĐT: ");
                    kiemtra_tt(getContext(), response.body().getDiachi(), diachi, response.body().getDiachi(), "Thêm địa chỉ", "Địa chỉ: ");
                }
            }

            @Override
            public void onFailure(Call<taikhoan> call, Throwable t) {

            }
        });
    }
}