package com.example.project_tra_sua.Home.Tai_khoan;

import static com.example.project_tra_sua.Home.Home.chipNavigationBar;
import static com.example.project_tra_sua.Home.Home.idtk;
import static com.example.project_tra_sua.xuly.dia;
import static com.example.project_tra_sua.xuly.on_back1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.project_tra_sua.API.API;
import com.example.project_tra_sua.Home.Home;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.taikhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Thong_tin_ca_nhan extends Fragment {

    EditText ten, email, sdt, diachi;
    ImageView image;
    Button btndn;
    String mk;
    Dialog dialog;

    public Thong_tin_ca_nhan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_tin_ca_nhan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipNavigationBar.setVisibility(View.GONE);

        ten = view.findViewById(R.id.ten);
        image = view.findViewById(R.id.image);
        email = view.findViewById(R.id.email);
        sdt = view.findViewById(R.id.sdt);
        diachi = view.findViewById(R.id.diachi);
        btndn = view.findViewById(R.id.btndn);
        dialog = new Dialog(getContext());
        dia(dialog);
        Home home = (Home) getActivity();
        if (getArguments().getInt("ttcn")==1){
            on_back1(home, this, R.id.action_thong_tin_ca_nhan_to_don_hang);
        }else {
            on_back1(home, this, R.id.action_thong_tin_ca_nhan_to_tai_khoan2);
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getInt("ttcn")==1){
                    chipNavigationBar.setVisibility(View.VISIBLE);
                    Navigation.findNavController(view).navigate(R.id.action_thong_tin_ca_nhan_to_don_hang);
                }else {
                    chipNavigationBar.setVisibility(View.VISIBLE);
                    Navigation.findNavController(view).navigate(R.id.action_thong_tin_ca_nhan_to_tai_khoan2);
                }

            }
        });
        getdata();
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                String sdt1 = sdt.getText().toString();
                String email1 = diachi.getText().toString();
                if (sdt1.isEmpty()) {
                    sdt1 = "Thêm số điện thoại";
                }
                if (email1.isEmpty()) {
                    email1 = "Thêm địa chỉ";
                }
                taikhoan taikhoan = new taikhoan(idtk, email.getText().toString(), ten.getText().toString(), mk, sdt1, email1);
                API.API.Puttk(idtk, taikhoan).enqueue(new Callback<com.example.project_tra_sua.models.taikhoan>() {
                    @Override
                    public void onResponse(Call<com.example.project_tra_sua.models.taikhoan> call, Response<com.example.project_tra_sua.models.taikhoan> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            if (getArguments().getInt("ttcn")==1){
                                chipNavigationBar.setVisibility(View.VISIBLE);
                                Navigation.findNavController(view).navigate(R.id.action_thong_tin_ca_nhan_to_don_hang);
                            }else {
                                chipNavigationBar.setVisibility(View.VISIBLE);
                                Navigation.findNavController(view).navigate(R.id.action_thong_tin_ca_nhan_to_tai_khoan2);
                            }

                        } else {
                            Toast.makeText(home, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.project_tra_sua.models.taikhoan> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void getdata() {
        API.API.gettt(idtk).enqueue(new Callback<taikhoan>() {
            @Override
            public void onResponse(Call<taikhoan> call, Response<taikhoan> response) {
                if (response.isSuccessful()) {
                    taikhoan taikhoan = response.body();
                    ten.setText(taikhoan.getHoten());
                    email.setText(taikhoan.getEmail());
                    mk = taikhoan.getMk();
                    if (taikhoan.getSdt().equals("Thêm số điện thoại")) {
                        sdt.setHint(taikhoan.getSdt());
                    } else {
                        sdt.setText(taikhoan.getSdt());
                    }
                    if (taikhoan.getDiachi().equals("Thêm địa chỉ")) {
                        diachi.setHint(taikhoan.getDiachi());
                    } else {
                        diachi.setText(taikhoan.getDiachi());
                    }
                }
            }

            @Override
            public void onFailure(Call<taikhoan> call, Throwable t) {

            }
        });
    }
}