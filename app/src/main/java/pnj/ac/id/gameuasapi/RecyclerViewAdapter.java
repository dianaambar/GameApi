package pnj.ac.id.gameuasapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pnj.ac.id.gameuasapi.Model.Game;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Game> dataList = new ArrayList<>();
    private onViewClick listener;

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_game,parent,false));
    }

    @Override
    public void onBindViewHolder( RecyclerViewAdapter.ViewHolder holder, int position) {
        Game data = dataList.get(position);
        holder.txtGameTitle.setText(data.getGame_title());
        holder.txtGameGenre.setText(data.getGame_genre());
        holder.txtPrice.setText(data.getPrice());
        holder.txtDeveloper.setText(data.getDeveloper());
        holder.txtMode.setText(data.getMode());
        holder.txtReleaseYear.setText(data.getRelease_year());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtGameTitle, txtGameGenre, txtPrice, txtDeveloper, txtMode, txtReleaseYear;

        public ViewHolder( View itemView){
            super(itemView);
            txtGameTitle = itemView.findViewById(R.id.game_title);
            txtGameGenre= itemView.findViewById(R.id.game_genre);
            txtPrice = itemView.findViewById(R.id.game_price);
            txtDeveloper = itemView.findViewById(R.id.game_developer);
            txtMode = itemView.findViewById(R.id.game_mode);
            txtReleaseYear = itemView.findViewById(R.id.game_release_year);

            itemView.setOnClickListener(view -> listener.onViewClick(getAdapterPosition()));
        }
    }

    public void setDataList(List<Game> list ){
        this.dataList = list;
        notifyDataSetChanged();
    }

    public void clearDataList(List<Game> list ){
        this.dataList = list;
        dataList.clear();
    }

    public interface onViewClick{
        void onViewClick(int position);
    }

    public void setOnClickListener(onViewClick listener){
        this.listener = listener;
    }
}
