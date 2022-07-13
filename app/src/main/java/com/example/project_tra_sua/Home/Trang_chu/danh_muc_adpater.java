package com.example.project_tra_sua.Home.Trang_chu;

import android.os.Bundle;
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
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.danh_muc;

import java.util.List;

public class danh_muc_adpater extends RecyclerView.Adapter<danh_muc_adpater.danh_muc_viewhodler> {
    List<danh_muc>list;
    Fragment fragment;

    public danh_muc_adpater(List<danh_muc> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public danh_muc_viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhmuc,parent,false);
        return new danh_muc_viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull danh_muc_viewhodler holder, int position) {
        danh_muc danh_muc=list.get(position);
        Glide.with(fragment.getContext()).load(danh_muc.getHinhanh()).error(R.drawable.tra_sua).into(holder.imageView);
        holder.txt_name.setText(danh_muc.getTendm());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putInt("iddm",danh_muc.getIddm());
                bundle.putString("tendm",danh_muc.getTendm());
                NavHostFragment.findNavController(fragment).navigate(R.id.action_trang_chu_to_danhmuc_sp,bundle);
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

    public class danh_muc_viewhodler extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_name;
        public danh_muc_viewhodler(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.profile_image);
            txt_name=itemView.findViewById(R.id.txt_name);

        }
    }
}
