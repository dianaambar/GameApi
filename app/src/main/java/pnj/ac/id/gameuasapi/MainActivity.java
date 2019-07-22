package pnj.ac.id.gameuasapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pnj.ac.id.gameuasapi.Model.DataResponse;
import pnj.ac.id.gameuasapi.Model.Game;
import pnj.ac.id.gameuasapi.Network.APIInterface;
import pnj.ac.id.gameuasapi.Network.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private APIInterface apiInterface;
    private List<Game> dataList = new ArrayList<>();
    private RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter();
    FloatingActionButton btnFloat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            recyclerView = findViewById(R.id.rcy_list_game);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(recyclerViewAdapter);
            apiInterface = APIService.getClient().create(APIInterface.class);
            btnFloat = findViewById(R.id.btnFloat);

            btnFloat.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddData.class)));

            recyclerViewAdapter.setOnClickListener(position -> {
                Game data = dataList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateData.class);
                intent.putExtra("game_title", data.getGame_title());
                intent.putExtra("game_genre",String.valueOf(data.getGame_genre()));
                intent.putExtra("price",String.valueOf(data.getPrice()));
                intent.putExtra("mode", String.valueOf(data.getMode()));
                intent.putExtra("release_year", String.valueOf(data.getRelease_year()));
                intent.putExtra("developer", String.valueOf(data.getDeveloper()));
                intent.putExtra("id", data.getId());
                Log.d(TAG, "onCreate: " + data.getId());
                startActivity(intent);
            });

            getItem();
    }

    public void getItem(){
        Call<DataResponse> dataCall = apiInterface.getData();
        dataCall.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                List<Game> items = response.body().getData();
                recyclerViewAdapter.setDataList(items);
                dataList.addAll(items);
                Log.d(TAG, "onResponse: gg " + dataList);
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewAdapter.clearDataList(dataList);
        getItem();
    }
}
