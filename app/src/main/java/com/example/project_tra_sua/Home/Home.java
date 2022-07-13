package com.example.project_tra_sua.Home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.example.project_tra_sua.API.API;
import com.example.project_tra_sua.R;
import com.example.project_tra_sua.models.giohang;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
    public static ChipNavigationBar chipNavigationBar;
    public static NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    public static int idtk;
    Handler handler = new Handler();
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        chipNavigationBar = findViewById(R.id.menu);
        chipNavigationBar.setItemSelected(R.id.home, true);
        bottommenu();
        idtk=getIntent().getIntExtra("idtk",0);
        Log.e("idtk",""+idtk);
       getdata();
    }

    private void getdata() {
        API.API.getallsp(idtk,"không").enqueue(new Callback<List<giohang>>() {
            @Override
            public void onResponse(Call<List<giohang>> call, Response<List<giohang>> response) {
                if (response.isSuccessful()) {
                    chipNavigationBar.showBadge(R.id.gio_hang, response.body().size());
                } else {
                    chipNavigationBar.dismissBadge(R.id.gio_hang);
                }

            }

            @Override
            public void onFailure(Call<List<giohang>> call, Throwable t) {

            }
        });
    }

    private void bottommenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        Navigation.findNavController(Home.this, R.id.nav_host_fragment_content_main).navigate(R.id.trang_chu);
                        break;
                    case R.id.gio_hang:
                        Navigation.findNavController(Home.this, R.id.nav_host_fragment_content_main).navigate(R.id.gio_hang2);
                        break;
                    case R.id.tai_khoan:
                        Navigation.findNavController(Home.this, R.id.nav_host_fragment_content_main).navigate(R.id.tai_khoan2);
                        break;

                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                getdata();
                handler.postDelayed(runnable, 3000);
            }
        }, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}