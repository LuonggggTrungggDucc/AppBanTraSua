package com.example.project_tra_sua.Home.Tai_khoan.dondamua;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class dondamuaviewpager extends FragmentStateAdapter {
    public dondamuaviewpager(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new choxacnhan();

            case 1:
                return new danggiao();

            case 2:
                return new dagiao();

            case 3:
                return new dahuy();

            default:
                return null;

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
