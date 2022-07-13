package com.example.project_tra_sua.Home.gio_hang;

import static com.example.project_tra_sua.Home.Home.chipNavigationBar;
import static com.example.project_tra_sua.Home.Home.currencyVN;
import static com.example.project_tra_sua.Home.Home.idtk;
import static com.example.project_tra_sua.xuly.dia;
import static com.example.project_tra_sua.xuly.kiemtra_tt;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_tra_sua.API.API;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.donhang;
import com.example.project_tra_sua.models.giohang;
import com.example.project_tra_sua.models.taikhoan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class don_hang extends Fragment {
    RecyclerView list_item;
    ImageView image;
    LinearLayout layout1;
    giohangadapter giohangadapter;
    TextView ttt,ten,sdt,diachi,sua,loinhan;
    Dialog dialog;
    public don_hang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_don_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipNavigationBar.setVisibility(View.GONE);
        list_item=view.findViewById(R.id.list_item);
        ttt=view.findViewById(R.id.ttt);
        ten=view.findViewById(R.id.ten);
        sdt=view.findViewById(R.id.sdt);
        diachi=view.findViewById(R.id.diachi);
        sua=view.findViewById(R.id.sua);
        image=view.findViewById(R.id.image);
        layout1=view.findViewById(R.id.layout1);
        loinhan=view.findViewById(R.id.loinhan);
        dialog=new Dialog(getContext());
        dia(dialog);
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundl=new Bundle();
                bundl.putInt("ttcn",1);
                Navigation.findNavController(view).navigate(R.id.action_don_hang_to_thong_tin_ca_nhan,bundl);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipNavigationBar.setVisibility(View.VISIBLE);
                Navigation.findNavController(view).navigate(R.id.action_don_hang_to_gio_hang2);
            }
        });
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                if (diachi.getText().toString().equals("Địa chỉ: Thêm địa chỉ")){
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Vui lòng thêm địa chỉ", Toast.LENGTH_SHORT).show();
                }else {
                    API.API.getallsp(idtk,"không").enqueue(new Callback<List<giohang>>() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onResponse(Call<List<giohang>> call, Response<List<giohang>> response) {
                            if (response.isSuccessful()) {
                                dialog.dismiss();
                                ArrayList<giohang> arrayList = (ArrayList<giohang>) response.body();
                                for (giohang d:arrayList){
                                    String ngaygio= String.valueOf(java.time.LocalDateTime.now());
                                    donhang donhang=new donhang(0,d.getIdgh(),idtk,loinhan.getText().toString(),ngaygio,"Chờ duyệt","");
                                    API.API.Postdonhang(donhang).enqueue(new Callback<com.example.project_tra_sua.models.donhang>() {
                                        @Override
                                        public void onResponse(Call<com.example.project_tra_sua.models.donhang> call, Response<com.example.project_tra_sua.models.donhang> response) {
                                            if (response.isSuccessful()){

                                                giohang giohang=new giohang(d.getIdgh(),d.getIdtk(),d.getIdsp(),d.getSl(),d.getChon(),d.getGiax1(),d.getTongtien(),"đơn hàng");
                                                API.API.Putgiohang(d.getIdgh(),giohang).enqueue(new Callback<com.example.project_tra_sua.models.giohang>() {
                                                    @Override
                                                    public void onResponse(Call<com.example.project_tra_sua.models.giohang> call, Response<com.example.project_tra_sua.models.giohang> response) {

                                                    }

                                                    @Override
                                                    public void onFailure(Call<com.example.project_tra_sua.models.giohang> call, Throwable t) {

                                                    }
                                                });

                                            }else {
                                                dialog.dismiss();
                                                Toast.makeText(getContext(), "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<com.example.project_tra_sua.models.donhang> call, Throwable t) {
                                            dialog.dismiss();
                                            Log.e("lỗi đặt hàng",t.getMessage());
                                        }
                                    });
                                }
                                chipNavigationBar.setVisibility(View.VISIBLE);
                                Toast.makeText(getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.action_don_hang_to_trang_chu);
                                chipNavigationBar.setItemSelected(R.id.home, true);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<giohang>> call, Throwable t) {

                        }
                    });
                }


            }
        });
        getdata();
    }

    private void getdata() {
        API.API.gettt(idtk).enqueue(new Callback<taikhoan>() {
            @Override
            public void onResponse(Call<taikhoan> call, Response<taikhoan> response) {
                if (response.isSuccessful()){
                    ten.setText("Họ tên: "+response.body().getHoten());
                    kiemtra_tt(getContext(),response.body().getSdt(),sdt,response.body().getSdt(),"Thêm số điện thoại","SĐT: ");
                    kiemtra_tt(getContext(),response.body().getDiachi(),diachi,response.body().getDiachi(),"Thêm địa chỉ","Địa chỉ: ");
                }
            }

            @Override
            public void onFailure(Call<taikhoan> call, Throwable t) {

            }
        });
        API.API.getallsp(idtk,"không").enqueue(new Callback<List<giohang>>() {
            @Override
            public void onResponse(Call<List<giohang>> call, Response<List<giohang>> response) {
                if (response.isSuccessful()) {
                    ArrayList<giohang> arrayList = (ArrayList<giohang>) response.body();
                    giohangadapter = new giohangadapter(arrayList, don_hang.this,1);
                    list_item.setLayoutManager(new LinearLayoutManager(getContext()));
                    list_item.setAdapter(giohangadapter);
                    giohangadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<giohang>> call, Throwable t) {

            }
        });
        API.API.tongsl(idtk,"không").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String sl=response.body();
                API.API.tongtien(idtk,"không").enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            ttt.setText("x "+sl+" = "+currencyVN.format(Integer.parseInt(response.body())));
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }
}