package gr.ece.is;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;


public final class JUtils {

    private static final Gson gson = new Gson();
    private static final String jType = "\"FeatureCollection\"";
    private static final String jName = "\"dianomi_0\"";
    private static final String jCrs = "{\"type\":\"name\",\"properties\":{\"name\":\"urn:ogc:def:crs:OGC:1.3:CRS84\"}}";
    private static final String jLine = "\"LineString\"";

    private JUtils() {}

    public static boolean isJson(String jsonString) {
        try {
            gson.fromJson(jsonString, Object.class);
            return true;
        }
        catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    public static boolean isCorrectJson(String jsonString) {
        HashSet<String> providersHashSet = new HashSet<String>();
        providersHashSet.add("\"wind\"");
        providersHashSet.add("\"cosmote\"");
        providersHashSet.add("\"vodafone\"");
        try {
            JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
            JsonElement jsonElement = jsonObject.get("type");;
            if (!jsonElement.toString().equals(jType)) {
                throw new JsonSyntaxException("ex");
            }
            jsonElement = jsonObject.get("name");;
            if (!jsonElement.toString().equals(jName)) {
                throw  new JsonSyntaxException("ex");
            }
            jsonElement = jsonObject.get("crs");
            if (!jsonElement.toString().equals(jCrs)) {
                throw  new JsonSyntaxException("ex");
            }
            JsonArray jsonArray = jsonObject.getAsJsonArray("features");
            for (JsonElement element: jsonArray) {
                if (!element.getAsJsonObject().get("type").toString().equals("\"Feature\"")) {
                    throw new JsonSyntaxException("ex");
                }
                if (!providersHashSet.contains(element.getAsJsonObject().get("properties").getAsJsonObject().get("provider").toString())) {
                    throw new JsonSyntaxException("ex");
                }
                if (!element.getAsJsonObject().get("geometry").getAsJsonObject().get("type").toString().equals(jLine)) {
                    throw new JsonSyntaxException("ex");
                }
                //if you want to parse them to your db Niko
                //JsonArray geometries = element.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray();
                //for (JsonElement point: geometries) {
                //	TODO
                //}
            }
            return true;
        } catch (JsonSyntaxException ex) {
            return false;
        } catch (IllegalStateException ex) {
            return false;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public static String returnJson(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                //stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
        catch (IOException ex) {
            return "";
        }
    }
}
