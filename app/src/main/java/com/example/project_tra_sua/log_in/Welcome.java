package com.example.project_tra_sua.log_in;

import static com.example.project_tra_sua.xuly.on_back_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.project_tra_sua.MainActivity;
import com.example.project_tra_sua.R;

public class Welcome extends Fragment {
    Button btndn;
    TextView dangky;
    public Welcome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        onclick();
    }

    private void onclick() {
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_welcome_to_dang_nhap);
            }
        });
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_welcome_to_dang_ky);
            }
        });
    }

    private void anhxa(View view) {
        btndn=view.findViewById(R.id.btndn);
        dangky=view.findViewById(R.id.dangky);
        MainActivity m= (MainActivity) getActivity();
        on_back_home(m);
    }
}