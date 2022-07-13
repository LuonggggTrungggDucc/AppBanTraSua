package com.example.project_tra_sua.Home.Trang_chu;

import static com.example.project_tra_sua.Home.Home.currencyVN;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.san_pham;

import java.util.List;

public class sp_adapter extends RecyclerView.Adapter<sp_adapter.sp_viewholder> {
    List<san_pham>list;
    Fragment fragment;
    int vitri;

    public sp_adapter(List<san_pham> list, Fragment fragment, int vitri) {
        this.list = list;
        this.fragment = fragment;
        this.vitri = vitri;
    }

    @NonNull
    @Override
    public sp_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(fragment.getContext()).inflate(R.layout.item_sp,parent,false);
        return new sp_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sp_viewholder holder, int position) {
        san_pham san_pham=list.get(position);
        Glide.with(fragment.getContext()).load(san_pham.getHinhanh()) .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).error(R.drawable.img1).into(holder.imageView);
        holder.txt_name.setText(san_pham.getTensp());
        holder.txt_giatien.setText(currencyVN.format(Integer.parseInt(san_pham.getGiatien())));
        Bundle bundle=new Bundle();
        bundle.putInt("idsp",san_pham.getIdsp());
        holder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (vitri){
                    case 0:
                        bundle.putInt("vitri",0);
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_trang_chu_to_ct_sp,bundle);
                        break;
                    case 1:
                        bundle.putInt("vitri",1);
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_danhmuc_sp_to_ct_sp,bundle);
                        break;
                    case 2:
                        bundle.putInt("vitri",0);
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_timkiem2_to_ct_sp,bundle);
                        break;
                }

            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (vitri){
                    case 0:
                        try {
                            bundle.putInt("vitri",0);
                            NavHostFragment.findNavController(fragment).navigate(R.id.action_trang_chu_to_ct_sp,bundle);
                        }catch (Exception e){
                            Log.e("lỗi ctsp",""+e);
                        }

                        break;
                    case 1:
                        bundle.putInt("vitri",0);
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_danhmuc_sp_to_ct_sp,bundle);
                        break;
                    case 2:
                        bundle.putInt("vitri",0);
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_timkiem2_to_ct_sp,bundle);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class sp_viewholder extends RecyclerView.ViewHolder{
        ImageView imageView,img_add;
        TextView txt_name,txt_giatien;
        public sp_viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            img_add=itemView.findViewById(R.id.img_add);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_giatien=itemView.findViewById(R.id.txt_giatien);
        }
    }
}
