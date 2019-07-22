package pnj.ac.id.gameuasapi;

import android.content.Intent;
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

public class UpdateData extends AppCompatActivity {

    private static final String TAG = "Tambah Data";
    private APIInterface apiInterface;

    EditText etGameTitle, etGameGenre, etPrice, etDeveloper, etMode, etReleaseYear;
    Button btnTambah;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game);

        Intent intent = getIntent();

        etGameTitle = findViewById(R.id.et_game_title);
        etGameGenre = findViewById(R.id.et_genre);
        etDeveloper = findViewById(R.id.et_developer);
        etMode = findViewById(R.id.et_mode);
        etPrice = findViewById(R.id.et_price);
        etReleaseYear = findViewById(R.id.et_release_year);
        btnTambah = findViewById(R.id.btnTambah);
        btnDelete = findViewById(R.id.btnDelete);

        String gameTitle = intent.getStringExtra("game_title");
        String gameGenre = intent.getStringExtra("game_genre");
        String price = intent.getStringExtra("price");
        String developer = intent.getStringExtra("developer");
        String gameMode = intent.getStringExtra("mode");
        String releaseYear = intent.getStringExtra("release_year");
        int id = intent.getIntExtra("id", 0);

        Log.d(TAG, "onCreate: gg " + intent.getStringExtra("game_title"));
        Log.d(TAG, "onCreate: " + id);

        etGameTitle.setText(gameTitle);
        etGameGenre.setText(gameGenre);
        etPrice.setText(price);
        etReleaseYear.setText(releaseYear);
        etMode.setText(gameMode);
        etDeveloper.setText(developer);
        apiInterface = APIService.getClient().create(APIInterface.class);
        btnTambah.setOnClickListener(view -> updateData(id, etGameTitle.getText().toString(), (etGameGenre.getText().toString()), (etPrice.getText().toString()), (etReleaseYear.getText().toString()), (etMode.getText().toString()), (etDeveloper.getText().toString())));

        btnDelete.setOnClickListener(view -> deleteData(id) );

    }

    private void updateData(Integer id, String gameTitle, String gameGenre, String price, String releaseYear, String gameMode, String developer){
        Call<ErrorMessage> dataCall = apiInterface.ubahGame(id, gameTitle, gameGenre, price, releaseYear, gameMode, developer);
        dataCall.enqueue(new Callback<ErrorMessage>() {
            @Override
            public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                Toast.makeText(UpdateData.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: " + response.raw());
                onBackPressed();
            }
            @Override
            public void onFailure(Call<ErrorMessage> call, Throwable t) {
                Toast.makeText(UpdateData.this, "Data gagal diupdate", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }


        });



    }
    private void deleteData(int id){
        Call<ErrorMessage> data = apiInterface.hapusGame(id);
        data.enqueue(new Callback<ErrorMessage>() {
            @Override
            public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                Toast.makeText(UpdateData.this, "Data Berhasil dihapus", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: jj " + response.raw());
            }

            @Override
            public void onFailure(Call<ErrorMessage> call, Throwable t) {
                Toast.makeText(UpdateData.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: jj ", t);
            }
        });
    }


}
