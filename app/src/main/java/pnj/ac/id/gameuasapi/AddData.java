package pnj.ac.id.gameuasapi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pnj.ac.id.gameuasapi.Model.ErrorMessage;
import pnj.ac.id.gameuasapi.Network.APIInterface;
import pnj.ac.id.gameuasapi.Network.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddData extends AppCompatActivity {
    private static final String TAG = "Tambah Data";
    private APIInterface apiInterface;

    EditText etGameTitle, etGameGenre, etPrice, etDeveloper, etMode, etReleaseYear;
    Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game);

        initData();
        btnTambah.setOnClickListener(v -> {
            Call <ErrorMessage> dataCall = apiInterface.tambahGame(etGameTitle.getText().toString(), etGameGenre.getText().toString(), etPrice.getText().toString(), etPrice.getText().toString(), etReleaseYear.getText().toString(), etMode.getText().toString());
            dataCall.enqueue(new Callback<ErrorMessage>() {
                @Override
                public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                    Toast.makeText(AddData.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: " + response.raw());
                    onBackPressed();
                }

                @Override
                public void onFailure(Call<ErrorMessage> call, Throwable t) {

                }
            });
        });

    }

    public void initData(){
        etGameTitle = findViewById(R.id.et_game_title);
        etGameGenre = findViewById(R.id.et_genre);
        etPrice = findViewById(R.id.et_price);
        etDeveloper = findViewById(R.id.et_developer);
        etMode = findViewById(R.id.et_mode);
        etReleaseYear = findViewById(R.id.et_release_year);
        btnTambah = findViewById(R.id.btnTambah);
        apiInterface = APIService.getClient().create(APIInterface.class);
    }

    public void addData(String gameTitle, String gameGenre, String gamePrice, String gameDeveloper, String gameMode, String releaseYear){
        Call<ErrorMessage> dataCall = apiInterface.tambahGame(gameTitle, gameGenre, gamePrice, gameDeveloper, gameMode, releaseYear);
        dataCall.enqueue(new Callback<ErrorMessage>() {
            @Override
            public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {

            }

            @Override
            public void onFailure(Call<ErrorMessage> call, Throwable t) {

            }
        });

    }
}
