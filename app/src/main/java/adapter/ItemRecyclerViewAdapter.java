package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.sumeetsinha.healofy.R;
import java.util.ArrayList;
import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<String> songList = new ArrayList<>();
    ItemRecyclerViewAdapter (List<String> songList){
       this.songList = songList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemViewHolder)viewHolder).title.setText(songList.get(i));
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private Button title;
        private ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.songName);
        }
    }
}
