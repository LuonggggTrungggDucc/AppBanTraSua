package com.example.project_tra_sua.Home.Trang_chu;

import static com.example.project_tra_sua.Home.Home.currencyVN;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_tra_sua.API.gettien;
import com.example.project_tra_sua.API.quantilist;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.chon;

import java.util.ArrayList;
import java.util.List;

public class chon_adpater extends RecyclerView.Adapter<chon_adpater.chonloai_viewholder> {
    List<chon> list;
    String loai;

    int selectedPos = -1;
    RecyclerView recyclerView;
    quantilist quantilist;
    gettien gettien;
    ArrayList<String> chontopping = new ArrayList<>();

    int tong=0;
    public chon_adpater(List<chon> list, String loai, RecyclerView recyclerView, quantilist quantilist, gettien gettien) {
        this.list = list;
        this.loai = loai;
        this.recyclerView = recyclerView;

        this.quantilist = quantilist;
        this.gettien = gettien;

    }

    @NonNull
    @Override
    public chonloai_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chon, parent, false);
        return new chonloai_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chonloai_viewholder holder, int position) {
        chon chon = list.get(position);

        if (chon.getThe_loai().equals(loai)) {
            if (!chon.getThe_loai().equals("Chọn topping")) {
                holder.checkBox.setVisibility(View.GONE);
                holder.radioButton.setVisibility(View.VISIBLE);
                holder.txt_tien.setVisibility(View.GONE);
                holder.radioButton.setText(chon.getTen());
                holder.radioButton.setChecked(position == selectedPos);
                holder.txt_tien1.setVisibility(View.GONE);
                if (chon.getThe_loai().equals("Chọn size")){
                    holder.txt_tien1.setVisibility(View.VISIBLE);
                    holder.txt_tien1.setText(currencyVN.format(Integer.parseInt(chon.getTien())));
                }
                holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            selectedPos = holder.getAdapterPosition();
                            gettien.test(holder.radioButton.getText().toString());
                            gettien.test1(chon.getTien());
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    notifyDataSetChanged();
                                }
                            });
                        }
                    }
                });
            } else {
                holder.checkBox.setVisibility(View.VISIBLE);
                holder.radioButton.setVisibility(View.GONE);
                holder.txt_tien.setVisibility(View.VISIBLE);
                holder.checkBox.setText(chon.getTen());
                holder.txt_tien.setText(currencyVN.format(Integer.parseInt(chon.getTien())));

                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (holder.checkBox.isChecked()) {
                            chontopping.add(holder.checkBox.getText().toString());
                            tong= tong+Integer.parseInt(chon.getTien());
                            Log.e("tong",""+tong);
                        } else {
                            chontopping.remove(holder.checkBox.getText().toString());
                            tong= tong-Integer.parseInt(chon.getTien());
                            Log.e("tong",""+tong);
                        }

                        quantilist.onlist(chontopping);
                        quantilist.onlist1(tong);

                    }
                });


            }
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class chonloai_viewholder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        RadioButton radioButton;
        TextView txt_tien;
        TextView txt_tien1;

        public chonloai_viewholder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            radioButton = itemView.findViewById(R.id.radioButton);
            txt_tien = itemView.findViewById(R.id.txt_tien);
            txt_tien1 = itemView.findViewById(R.id.txt_tien1);
        }
    }
}
