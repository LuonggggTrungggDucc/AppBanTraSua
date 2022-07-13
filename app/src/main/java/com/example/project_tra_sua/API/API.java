package com.example.project_tra_sua.API;

import com.example.project_tra_sua.models.chon;
import com.example.project_tra_sua.models.danh_muc;
import com.example.project_tra_sua.models.donhang;
import com.example.project_tra_sua.models.giohang;
import com.example.project_tra_sua.models.san_pham;
import com.example.project_tra_sua.models.taikhoan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    API API = new Retrofit.Builder().baseUrl("https://webapi-trasua.conveyor.cloud/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(API.class);

    @POST("api/tk/Posttk")
    Call<taikhoan> post_tk(@Body taikhoan taikhoan);

    @GET("api/tk/gettt/{id}")
    Call<taikhoan> gettt(@Path("id") int id);

    @GET("api/tk/kiemtra/{email}/{mk}")
    Call<List<taikhoan>> kiemtra(@Path("email") String email, @Path("mk") String mk);

    @PUT("api/tk/Puttk/{id}")
    Call<taikhoan> Puttk(@Path("id") int id, @Body taikhoan taikhoan);

    @GET("api/danhmuc/getall")
    Call<List<danh_muc>> Get_Danh_muc();

    @GET("api/san_pham/getall")
    Call<List<san_pham>> Get_san_pham();

    @GET("api/san_pham/getall_dm/{id}")
    Call<List<san_pham>> Get_all_dm(@Path("id") int id);

    @GET("api/san_pham/getchitiet/{id}")
    Call<san_pham> getchitiet(@Path("id") int id);
    @GET("api/giohang/Getgiohang/{id}")
    Call<giohang> Getgiohang(@Path("id") int id);

    @GET("api/san_pham/timkiem/{id}")
    Call<List<san_pham>> gettimkiem(@Path("id") String id);


    @GET("api/chon/Getall_chon/{id}")
    Call<List<chon>> Getall_chon(@Path("id") String id);


    @GET("api/giohang/getallsp/{id}/{ghichu}")
    Call<List<giohang>> getallsp(@Path("id") int id,@Path("ghichu") String ghichu);

    @GET("api/giohang/tongtien/{id}/{ghichu}")
    Call<String> tongtien(@Path("id") int id,@Path("ghichu") String ghichu);

    @GET("api/giohang/tongsl/{id}/{ghichu}")
    Call<String> tongsl(@Path("id") int id,@Path("ghichu") String ghichu);

    @DELETE("api/giohang/Deletegiohang/{id}")
    Call<giohang> Deletegiohang(@Path("id") int id);

    @POST("api/giohang/addgiohang")
    Call<giohang> addgiohang(@Body giohang giohang);

    @PUT("api/giohang/Putgiohang/{id}")
    Call<giohang> Putgiohang(@Path("id") int id, @Body giohang giohang);


    @POST("api/donhang/Postdonhang")
    Call<donhang> Postdonhang( @Body donhang donhang);
    @PUT("api/donhang/Putdonhang/{id}")
    Call<donhang> Putdonhang(@Path("id") int id , @Body donhang donhang);
    @GET("api/donhang/getadhllsp/{id}/{hanhchinh}")
    Call<List<donhang>> getadhllsp(@Path("id") int id , @Path("hanhchinh") String hanhchinh);
}
