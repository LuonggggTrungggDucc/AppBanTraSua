package com.example.project_tra_sua.log_in;

import static com.example.project_tra_sua.xuly.dia;
import static com.example.project_tra_sua.xuly.on_back;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dang_ky extends Fragment {
    ImageView image;
    EditText hoten, email, mk;
    Button btndk;
    TextView dangnhap;
    Dialog dialog;

    public Dang_ky() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dang_ky, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        onclick();
    }

    private void onclick() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dang_ky_to_welcome);
            }
        });
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                String email1 = email.getText().toString().trim();
                String hoten1 = hoten.getText().toString().trim();
                String mk1 = mk.getText().toString().trim();
                if (hoten1.isEmpty() && hoten1.isEmpty() && mk1.isEmpty()) {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Vui lòng ko để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    API.API.kiemtra(email1, mk1).enqueue(new Callback<List<taikhoan>>() {
                        @Override
                        public void onResponse(Call<List<taikhoan>> call, Response<List<taikhoan>> response) {
                            if (!response.isSuccessful()) {
                                taikhoan taikhoan = new taikhoan(0, email1, hoten1, mk1,"Thêm số điện thoại","Thêm địa chỉ");
                                API.API.post_tk(taikhoan).enqueue(new Callback<com.example.project_tra_sua.models.taikhoan>() {
                                    @Override
                                    public void onResponse(Call<com.example.project_tra_sua.models.taikhoan> call, Response<com.example.project_tra_sua.models.taikhoan> response) {
                                        if (response.isSuccessful()) {
                                            dialog.dismiss();
                                            Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(view).navigate(R.id.action_dang_ky_to_dang_nhap);



                                        } else {
                                            dialog.dismiss();
                                            Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<com.example.project_tra_sua.models.taikhoan> call, Throwable t) {
                                        dialog.dismiss();
                                        Log.e("lỗi đăng ký", "" + t);
                                    }
                                });
                            } else {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Tài khoản tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<taikhoan>> call, Throwable t) {
                            dialog.dismiss();
                            Log.e("lỗi đăng ký", "" + t);
                        }
                    });
                }


            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dang_ky_to_dang_nhap);
            }
        });
    }

    private void anhxa(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();
        image = view.findViewById(R.id.image);
        hoten = view.findViewById(R.id.hoten);
        email = view.findViewById(R.id.email);
        mk = view.findViewById(R.id.mk);
        btndk = view.findViewById(R.id.btndk);
        dangnhap = view.findViewById(R.id.dangnhap);
        dialog = new Dialog(getContext());
        dia(dialog);
        on_back(mainActivity, this, R.id.action_dang_ky_to_welcome);
    }
}