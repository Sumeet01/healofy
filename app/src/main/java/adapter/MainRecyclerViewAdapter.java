package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sumeetsinha.healofy.R;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter {

    private Map<String, List<String>> entity = new LinkedHashMap<>();
    private int rowCount;
    private List<String> entityKeyList;
    private Context context;

    public void setParams(Map<String, List<String>> entity, int rowCount) {
        this.entity = entity;
        this.rowCount = rowCount;
        entityKeyList = new ArrayList<>(entity.keySet());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        context = viewGroup.getContext();
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_recycler_view, viewGroup, false);
        return new HeaderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((HeaderViewHolder)viewHolder).title.setText(entityKeyList.get(i));
        ItemRecyclerViewAdapter itemRecyclerViewAdapter= new ItemRecyclerViewAdapter(entity.get(entityKeyList.get(i)));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, entity.get(entityKeyList.get(i)).size() >= rowCount ? rowCount : entity.get(entityKeyList.get(i)).size(), GridLayoutManager.HORIZONTAL, false);
        ((HeaderViewHolder)viewHolder).itemRecyclerView.setLayoutManager(gridLayoutManager);
        ((HeaderViewHolder)viewHolder).itemRecyclerView.setAdapter(itemRecyclerViewAdapter);
    }

    @Override
    public int getItemCount() {
        return entityKeyList.size();
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView itemRecyclerView;

        private HeaderViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.header);
            itemRecyclerView = itemView.findViewById(R.id.recycler_view_item);
        }
    }
}
