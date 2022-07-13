package com.example.project_tra_sua;

import static com.example.project_tra_sua.Home.Home.chipNavigationBar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project_tra_sua.Home.Home;

public class xuly {
    public static void dia(Dialog dialog) {
        dialog.setContentView(R.layout.dialogloadding);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams w = window.getAttributes();
        w.gravity = Gravity.CENTER;
        window.setAttributes(w);
        dialog.setCancelable(false);

    }
    public static void kiemtra_tt(Context context, String a1, TextView a2, String a3, String a4,String a5) {
        if (a1.equals(a4)) {
            a2.setText(a5+a4);
            a2.setTextColor(context.getColor(R.color.teal_200));
        } else {
            a2.setText( a5+a3);
        }
    }
    public static void on_back(MainActivity mainActivity, Fragment context, int id) {
        mainActivity.getOnBackPressedDispatcher().addCallback(context, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(context).navigate(id);
            }
        });
    }
    public static void on_back1(Home mainActivity, Fragment context, int id) {
        mainActivity.getOnBackPressedDispatcher().addCallback(context, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                chipNavigationBar.setVisibility(View.VISIBLE);
                NavHostFragment.findNavController(context).navigate(id);
            }
        });
    }

   public static long backpreesedtime;

    public static void on_back_home(MainActivity mainActivity) {
        mainActivity.getOnBackPressedDispatcher().addCallback(mainActivity, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                if (backpreesedtime + 2000 > System.currentTimeMillis()) {
                    mainActivity.finish();
                } else {
                    Toast.makeText(mainActivity, "Nhấn lần nữa để thoát !", Toast.LENGTH_SHORT).show();
                }
                backpreesedtime = System.currentTimeMillis();
            }
        });
    }
    public static void on_back_home1(Home mainActivity) {
        mainActivity.getOnBackPressedDispatcher().addCallback(mainActivity, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                if (backpreesedtime + 2000 > System.currentTimeMillis()) {
                    mainActivity.finish();
                } else {
                    Toast.makeText(mainActivity, "Nhấn lần nữa để thoát !", Toast.LENGTH_SHORT).show();
                }
                backpreesedtime = System.currentTimeMillis();
            }
        });
    }
}
