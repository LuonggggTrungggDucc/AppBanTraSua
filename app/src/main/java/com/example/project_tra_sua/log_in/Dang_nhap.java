package com.example.project_tra_sua.log_in;

import static com.example.project_tra_sua.xuly.dia;
import static com.example.project_tra_sua.xuly.on_back;

import android.app.Dialog;
import android.content.Intent;
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
import com.example.project_tra_sua.Home.Home;
import com.example.project_tra_sua.MainActivity;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.taikhoan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dang_nhap extends Fragment {

    ImageView image;
    Button btndn;
    EditText mk,email;
    TextView dk;
    Dialog dialog;
    public Dang_nhap() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dang_nhap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        onclick();
    }

    private void onclick() {
        MainActivity mainActivity= (MainActivity) getActivity();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dang_nhap_to_welcome);
            }
        });
        on_back(mainActivity,this, R.id.action_dang_nhap_to_welcome);
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dang_nhap_to_dang_ky);
            }
        });
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                String email1=email.getText().toString().trim();
                String mk1=mk.getText().toString().trim();
                if (email1.isEmpty()||mk1.isEmpty()){
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    API.API.kiemtra(email1,mk1).enqueue(new Callback<List<taikhoan>>() {
                        @Override
                        public void onResponse(Call<List<taikhoan>> call, Response<List<taikhoan>> response) {
                            if (response.isSuccessful()){
                                dialog.dismiss();
                                taikhoan taikhoan=response.body().get(0);
                                Intent intent=new Intent(getActivity(),Home.class);
                                intent.putExtra("idtk",taikhoan.getIdtk());
                                startActivity(intent);
                                getActivity().finish();
                            }else {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Tài khoản mật khẩu ko tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<taikhoan>> call, Throwable t) {
                            dialog.dismiss();
                            Log.e("Lỗi đăng nhập",""+t);
                        }
                    });
                }

            }
        });
    }

    private void anhxa(View view) {
        image=view.findViewById(R.id.image);
        btndn=view.findViewById(R.id.btndn);
        email=view.findViewById(R.id.email);
        mk=view.findViewById(R.id.mk);
        dk=view.findViewById(R.id.dk);
        dialog=new Dialog(getContext());
        dia(dialog);
    }
}