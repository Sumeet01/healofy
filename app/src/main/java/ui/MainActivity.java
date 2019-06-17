package ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.sumeetsinha.healofy.R;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import adapter.MainRecyclerViewAdapter;
import data.DataProvider;

public class MainActivity extends AppCompatActivity {

   private MainRecyclerViewAdapter recyclerViewAdapter;
   private DataProvider dataProvider;
    private  Map<String, List<String>> artistMap = new LinkedHashMap<>();
    private  Map<String, List<String>> albumMap = new LinkedHashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        if(dataProvider == null)
            dataProvider = new DataProvider(this);
        dataProvider.initData();
        artistMap = dataProvider.getArtistMap();
        albumMap = dataProvider.getAlbumMap();
    }

    private void initView() {
        Toolbar t = findViewById(R.id.tToolbar);
        setSupportActionBar(t);
        getSupportActionBar().setTitle(null);
        final Spinner mSpinnerItem1 = findViewById(R.id.menu_spinner1);
        final Spinner mSpinnerItem2 = findViewById(R.id.menu_spinner2);

        final ArrayAdapter<CharSequence> entityAdapter = ArrayAdapter.createFromResource(this,
                R.array.entity, android.R.layout.simple_spinner_item);
        entityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerItem1.setAdapter(entityAdapter);
        mSpinnerItem1.setSelection(0);

        final ArrayAdapter<CharSequence> rowCountAdapter = ArrayAdapter.createFromResource(this,
                R.array.row_count, android.R.layout.simple_spinner_item);
        rowCountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerItem2.setAdapter(rowCountAdapter);
        mSpinnerItem2.setSelection(2);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new MainRecyclerViewAdapter();
        recyclerViewAdapter.setParams(artistMap,3);
        recyclerView.setAdapter(recyclerViewAdapter);

        mSpinnerItem1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String entity = (String) entityAdapter.getItem(i);
                String rowCount = (String) mSpinnerItem2.getSelectedItem();
                recyclerViewAdapter.setParams(entity.equalsIgnoreCase("Artist") ? artistMap : albumMap,Integer.parseInt(rowCount));
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerItem2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String rowCount = (String) rowCountAdapter.getItem(i);
                String entity = (String) mSpinnerItem1.getSelectedItem();
                recyclerViewAdapter.setParams(entity.equalsIgnoreCase("Artist") ? artistMap : albumMap,Integer.parseInt(rowCount));
                recyclerViewAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
