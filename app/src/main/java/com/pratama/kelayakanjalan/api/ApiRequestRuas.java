package com.pratama.kelayakanjalan.api;

import com.pratama.kelayakanjalan.model.Responmodel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestRuas {

    @FormUrlEncoded
    @POST("insert_ruas.php")
    Call<Responmodel> sendRuas(@Field("pjl") String pjl, @Field("ppk") String ppk, @Field("pro") String pro,
                               @Field("nru") String nru, @Field("nor") String nor, @Field("pru") double pru,
                               @Field("dkm") String dkm, @Field("kkm") String kkm, @Field("dkt") String dkt,
                               @Field("kcp") double kcp, @Field("sjr") String sjr, @Field("sts") String sts,
                               @Field("fng") String fng, @Field("kpr") String kpr, @Field("kpg") String kpg,
                               @Field("mjl") String mjl);

    @GET("select_ruas.php")
    Call<Responmodel> selectRuas();

    @FormUrlEncoded
    @POST("insert_segmen.php")
    Call<Responmodel> sendSegmen(@Field("idr") int id_ru, @Field("seg") String seg,
                                 @Field("pseg") double pj_seg, @Field("dseg") String d_seg,
                                 @Field("kseg") String k_seg);

}
