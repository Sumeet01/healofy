package data;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataProvider {
    private Reference<Context> contextReference;
    private final Map<String, List<String>> artistMap = new LinkedHashMap<>();
    private final Map<String, List<String>> albumMap = new LinkedHashMap<>();

   public DataProvider(Context context) {
        contextReference = new WeakReference<Context>(context);
    }

    public void initData() {
        try {
            InputStreamReader is = new InputStreamReader(contextReference.get().getAssets()
                    .open("sample_music_data.csv"));
            BufferedReader reader = new BufferedReader(is);
            reader.readLine();
            String line;
            String[] nextLine;
            while ((line = reader.readLine()) != null) {
                nextLine = line.split(",");
                if(!TextUtils.isEmpty(nextLine[1]) && !TextUtils.isEmpty(nextLine[0])) {
                    if (artistMap.containsKey(nextLine[1]))
                        artistMap.put(nextLine[1], artistMap.get(nextLine[1])).add(nextLine[0]);
                    else {
                        List<String> value = new ArrayList<>();
                        value.add(nextLine[0]);
                        artistMap.put(nextLine[1], value);
                    }
                }
                if(!TextUtils.isEmpty(nextLine[2]) && !TextUtils.isEmpty(nextLine[0])) {
                    if (albumMap.containsKey(nextLine[2]))
                        albumMap.put(nextLine[2], albumMap.get(nextLine[2])).add(nextLine[0]);
                    else {
                        List<String> value1 = new ArrayList<>();
                        value1.add(nextLine[0]);
                        albumMap.put(nextLine[2], value1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> getArtistMap() {
        return artistMap;
    }

    public Map<String, List<String>> getAlbumMap() {
        return albumMap;
    }
}

