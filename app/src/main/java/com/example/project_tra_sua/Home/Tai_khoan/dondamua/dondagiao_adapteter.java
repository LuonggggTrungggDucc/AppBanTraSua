package com.example.project_tra_sua.Home.Tai_khoan.dondamua;

import static com.example.project_tra_sua.Home.Home.currencyVN;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_tra_sua.API.API;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.donhang;
import com.example.project_tra_sua.models.giohang;
import com.example.project_tra_sua.models.san_pham;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dondagiao_adapteter extends RecyclerView.Adapter<dondagiao_adapteter.dondagiao_viewhodler> {
    List<donhang> list;
    Fragment fragment;

    public dondagiao_adapteter(List<donhang> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public dondagiao_viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.item_dongiaohang, parent, false);
        return new dondagiao_viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dondagiao_viewhodler holder, int position) {
        donhang donhang = list.get(position);
        holder.ngaygio.setText(donhang.getNgaygio());
        Bundle bundle = new Bundle();

        switch (donhang.getHangchinh()) {
            case "Chờ duyệt":
                holder.huydon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
                        builder.setMessage("Bạn có muốn hủy sản phẩm này ko")
                                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        donhang donhang1 = new donhang(donhang.getIddh(), donhang.getIdgh(), donhang.getIdtk(), donhang.getLoinhan(), donhang.getNgaygio(), "Đã hủy", "không");
                                        API.API.Putdonhang(donhang.getIddh(), donhang1).enqueue(new Callback<com.example.project_tra_sua.models.donhang>() {
                                            @Override
                                            public void onResponse(Call<com.example.project_tra_sua.models.donhang> call, Response<com.example.project_tra_sua.models.donhang> response) {
                                                if (response.isSuccessful()) {
                                                    Toast.makeText(fragment.getContext(), "Hủy thành công", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(fragment.getContext(), "Hủy thất bại", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<com.example.project_tra_sua.models.donhang> call, Throwable t) {

                                            }
                                        });
                                    }
                                })
                                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });

                        builder.create();
                        builder.show();

                    }
                });
                break;
            case "Đã hủy":
                holder.huydon.setText("Mua lại sản phẩm");
                holder.huydon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bundle.putInt("vitri", 0);
                        Navigation.findNavController(view).navigate(R.id.action_donhangdamua_to_ct_sp, bundle);
                    }
                });
                break;
            case "Đã giao":
                holder.huydon.setText("Mua lại sản phẩm");
                holder.huydon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bundle.putInt("vitri", 0);
                        Navigation.findNavController(view).navigate(R.id.action_donhangdamua_to_ct_sp, bundle);
                    }
                });
                break;
            case "Đang giao":
                holder.huydon.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        API.API.Getgiohang(donhang.getIdgh()).enqueue(new Callback<giohang>() {
            @Override
            public void onResponse(Call<giohang> call, Response<giohang> response) {
                holder.chitiet.setText(response.body().getChon());
                holder.tienx1.setText(currencyVN.format(Integer.parseInt(response.body().getGiax1())));
                API.API.getchitiet(response.body().getIdsp()).enqueue(new Callback<san_pham>() {
                    @Override
                    public void onResponse(Call<san_pham> call, Response<san_pham> response) {
                        san_pham san_pham = response.body();
                        holder.tensp.setText(san_pham.getTensp());
                        bundle.putInt("idsp", san_pham.getIdsp());
                        Glide.with(fragment.getContext()).load(san_pham.getHinhanh()).error(R.drawable.img1).into(holder.img);
                    }

                    @Override
                    public void onFailure(Call<san_pham> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<giohang> call, Throwable t) {

            }
        });


    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class dondagiao_viewhodler extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tensp, chitiet, tienx1, ngaygio;
        Button huydon;

        public dondagiao_viewhodler(@NonNull View itemView) {

            super(itemView);
            img = itemView.findViewById(R.id.image);

            tensp = itemView.findViewById(R.id.tensp);
            ngaygio = itemView.findViewById(R.id.ngaygio);
            chitiet = itemView.findViewById(R.id.chitiet);
            tienx1 = itemView.findViewById(R.id.tienx1);
            huydon = itemView.findViewById(R.id.huydon);
        }
    }
}
