package gr.ece.is;

public class Main {

    public static void main(String[] args) {
        String jsonInQuestion = JUtils.returnJson("Jsons\\dianomi.geojson.json");
        if (JUtils.isJson(jsonInQuestion)) {
            System.out.println(JUtils.isCorrectJson(jsonInQuestion));
        }
    }
}
