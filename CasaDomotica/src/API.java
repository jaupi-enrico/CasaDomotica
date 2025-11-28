import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

public class API {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static String toJSON(Object obj) {
        return gson.toJson(obj);
    }

    public static Lampadina fromJSONLampadina(String json) {
        return gson.fromJson(json, Lampadina.class);
    }

    public static ArrayList<Lampadina> fromJSONList(String json) {
        return gson.fromJson(json,
                new com.google.gson.reflect.TypeToken<ArrayList<Lampadina>>() {}.getType());
    }
}
