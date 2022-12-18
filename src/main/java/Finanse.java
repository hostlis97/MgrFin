import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Finanse {
    protected List<JsonRequest> bascet = new ArrayList<>();

    public void add(BufferedReader in) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String read = in.readLine();
        JsonRequest request = gson.fromJson(read, JsonRequest.class);
        bascet.add(request);
    }

    public JSONObject sum() throws IOException {
        File file = new File("categories.tsv");
        List<String[]> categories = new ArrayList<>();
        Map<String, String> MapCategories = new HashMap<>();
        Map<String, Integer> post = new HashMap<>();
        categories = Files.lines(file.toPath())
                .map(line-> line.split("\t"))
                .collect(Collectors.toList());
        for (String[] str : categories) {
            MapCategories.put(str[0],str[1]);
        }
        for (JsonRequest key :bascet) {
            if (!MapCategories.containsKey(key.title)) {
                if (!post.containsKey("другое")) {
                    post.put("другое", key.sum);
                } else {
                    int sum = post.get("другое");
                    sum += key.sum;
                    post.put("другое", sum);
                }
            }
            if (MapCategories.containsKey(key.title)) {
                if (post.isEmpty()) {
                    post.put(MapCategories.get(key.title), key.sum);
                } else {
                    int sum = post.containsKey(MapCategories.get(key.title)) ? post.get(MapCategories.get(key.title)) : 0;
                    sum += key.sum;
                    post.put(MapCategories.get(key.title), sum);
                }
            }
        }

        String maxFinCategory = Collections.max(post.entrySet(),
                Comparator.comparingInt(Map.Entry::getValue)).getKey();
        int maxFinSum = post.get(maxFinCategory);
        JSONObject jsonMaxSum = new JSONObject();
        jsonMaxSum.put("category", maxFinCategory);
        jsonMaxSum.put("sum", maxFinSum);


        return jsonMaxSum;
    }
}
