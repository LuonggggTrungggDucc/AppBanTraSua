package com.example.project_tra_sua.Home.gio_hang;

import static com.example.project_tra_sua.Home.Home.currencyVN;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_tra_sua.API.API;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.giohang;
import com.example.project_tra_sua.models.san_pham;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class giohangadapter extends RecyclerView.Adapter<giohangadapter.giohangviewhodler> {
    List<giohang> list;
    Fragment fragment;
    int sl;
    int sl1;
    int tongtien;
    int vitri;

    public giohangadapter(List<giohang> list, Fragment fragment,int vitri) {
        this.list = list;
        this.fragment = fragment;
        this.vitri = vitri;

    }

    @NonNull
    @Override
    public giohangviewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.item_gio_hang, parent, false);
        return new giohangviewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull giohangviewhodler holder, int position) {
        giohang giohang = list.get(position);


            holder.chitiet.setText(giohang.getChon());
            holder.tienx1.setText(currencyVN.format(Integer.parseInt(giohang.getGiax1())));
            holder.sl.setText(giohang.getSl()+"");
            sl = giohang.getSl();
            sl1=giohang.getSl();
            if (vitri==1){
                holder.layout.setVisibility(View.GONE);
                holder.delete.setVisibility(View.GONE);
            }
            API.API.getchitiet(giohang.getIdsp()).enqueue(new Callback<san_pham>() {
                @Override
                public void onResponse(Call<san_pham> call, Response<san_pham> response) {
                    san_pham san_pham = response.body();
                    holder.tensp.setText(san_pham.getTensp());
                    Glide.with(fragment.getContext()).load(san_pham.getHinhanh()).error(R.drawable.img1).into(holder.img);
                }

                @Override
                public void onFailure(Call<san_pham> call, Throwable t) {

                }
            });
            holder.cong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sl = sl + 1;
                    sl1 = sl;
                    holder.sl.setText(sl + "");
                    tongtien = Integer.parseInt(giohang.getGiax1()) * sl;
                    put(tongtien, giohang,sl);
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
                    builder.setMessage("Bạn có muốn xóa sản phẩm này ko")
                            .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    API.API.Deletegiohang(giohang.getIdgh()).enqueue(new Callback<com.example.project_tra_sua.models.giohang>() {
                                        @Override
                                        public void onResponse(Call<com.example.project_tra_sua.models.giohang> call, Response<com.example.project_tra_sua.models.giohang> response) {

                                        }

                                        @Override
                                        public void onFailure(Call<com.example.project_tra_sua.models.giohang> call, Throwable t) {

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
            holder.tru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sl1 = sl1 - 1;
                    if (sl > 1) {
                        sl = sl - 1;
                        holder.sl.setText(sl + "");
                        tongtien = Integer.parseInt(giohang.getGiax1()) * sl;
                        put(tongtien, giohang,sl);
                    }
                    if (sl1 <= 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
                        builder.setMessage("Bạn có muốn xóa sản phẩm này ko")
                                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        API.API.Deletegiohang(giohang.getIdgh()).enqueue(new Callback<com.example.project_tra_sua.models.giohang>() {
                                            @Override
                                            public void onResponse(Call<com.example.project_tra_sua.models.giohang> call, Response<com.example.project_tra_sua.models.giohang> response) {

                                            }

                                            @Override
                                            public void onFailure(Call<com.example.project_tra_sua.models.giohang> call, Throwable t) {

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
                }
            });


    }

    private void put(int tongtien, giohang giohang,int sl) {
        giohang giohang1 = new giohang(giohang.getIdgh(), giohang.getIdtk(), giohang.getIdsp(), sl, giohang.getChon(), giohang.getGiax1(), tongtien, giohang.getGhichu());
        API.API.Putgiohang(giohang.getIdgh(), giohang1).enqueue(new Callback<com.example.project_tra_sua.models.giohang>() {
            @Override
            public void onResponse(Call<com.example.project_tra_sua.models.giohang> call, Response<com.example.project_tra_sua.models.giohang> response) {

            }

            @Override
            public void onFailure(Call<com.example.project_tra_sua.models.giohang> call, Throwable t) {

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

    public class giohangviewhodler extends RecyclerView.ViewHolder {
        ImageView img, cong, tru,delete;
        TextView tensp, chitiet, tienx1, sl;
        LinearLayout layout;

        public giohangviewhodler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            cong = itemView.findViewById(R.id.cong);
            tru = itemView.findViewById(R.id.tru);
            tensp = itemView.findViewById(R.id.tensp);
            chitiet = itemView.findViewById(R.id.chitiet);
            tienx1 = itemView.findViewById(R.id.tienx1);
            sl = itemView.findViewById(R.id.sl);
            delete = itemView.findViewById(R.id.delete);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
