package pnj.ac.id.gameuasapi.Network;

import pnj.ac.id.gameuasapi.Model.DataResponse;
import pnj.ac.id.gameuasapi.Model.ErrorMessage;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("game")
    Call<DataResponse> getData();
    @FormUrlEncoded
    @POST("game")
    Call<ErrorMessage> tambahGame (@Field("game_title") String game_title,
                                   @Field("game_genre") String game_genre,
                                   @Field("price") String price,
                                   @Field("release_year") String release_year,
                                   @Field("developer") String developer,
                                   @Field("mode") String mode
    );
    @FormUrlEncoded
    @PUT("game/{id}")
    Call<ErrorMessage> ubahGame (@Path("id") Integer id,
                                @Field("game_title") String game_title,
                                @Field("game_genre") String game_genre,
                                @Field("price") String price,
                                @Field("release_year") String release_year,
                                @Field("developer") String developer,
                                @Field("mode") String mode
    );

    @DELETE("game/{id}")
    Call<ErrorMessage> hapusGame (@Path("id") Integer id);
}
