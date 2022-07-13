package com.example.project_tra_sua.Home.Trang_chu;

import static com.example.project_tra_sua.Home.Home.chipNavigationBar;
import static com.example.project_tra_sua.xuly.on_back1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_tra_sua.API.API;
import com.example.project_tra_sua.Home.Home;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.san_pham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Timkiem extends Fragment {

    EditText timkiem;
    TextView tb;
    RecyclerView recy_1;
    ImageView image;
    sp_adapter sp_adapter;
    ArrayList<san_pham> arrayList=new ArrayList<>();
    public Timkiem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timkiem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipNavigationBar.setVisibility(View.GONE);
        timkiem=view.findViewById(R.id.timkiem);
        tb=view.findViewById(R.id.tb);
        recy_1=view.findViewById(R.id.recy_1);
        image=view.findViewById(R.id.image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipNavigationBar.setVisibility(View.VISIBLE);
                Navigation.findNavController(view).navigate(R.id.action_timkiem2_to_trang_chu);
            }
        });
        Home home= (Home) getActivity();
        on_back1(home,this, R.id.action_timkiem2_to_trang_chu);
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getdata(timkiem.getText().toString().trim());
            }
        });

    }

    private void getdata(String tikiem) {
        API.API.gettimkiem(tikiem).enqueue(new Callback<List<san_pham>>() {
            @Override
            public void onResponse(Call<List<san_pham>> call, Response<List<san_pham>> response) {
                if (response.isSuccessful()){
                    tb.setVisibility(View.GONE);
                    //xóa dữu liệu ở arraylist
                    arrayList.clear();
                    // add dữu liệu mới vào arraylist
                    arrayList= (ArrayList<san_pham>) response.body();
                    sp_adapter=new sp_adapter(arrayList,Timkiem.this,2);
                    recy_1.setLayoutManager(new GridLayoutManager(getContext(),2));
                    // set dữu liệu hiển thị trên recy_1
                    recy_1.setAdapter(sp_adapter);
                    // tự động load lại dữu liệu hiển thị
                    recy_1.post(new Runnable() {
                        @Override
                        public void run() {
                            sp_adapter.notifyDataSetChanged();
                        }
                    });
                }else {
                    arrayList.clear();
                    sp_adapter=new sp_adapter(arrayList,Timkiem.this,2);
                    recy_1.setAdapter(sp_adapter);
                    recy_1.post(new Runnable() {
                        @Override
                        public void run() {
                            sp_adapter.notifyDataSetChanged();
                        }
                    });

                    tb.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<san_pham>> call, Throwable t) {
                Log.e("Lỗi tìm kiếm",""+t);
            }
        });
    }
}