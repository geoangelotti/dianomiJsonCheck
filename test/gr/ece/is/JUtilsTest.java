package gr.ece.is;

import org.junit.Test;

import static org.junit.Assert.*;


public class JUtilsTest {

    @Test
    public void dianomiJson() {
        assertTrue(JUtils.isCorrectJson(JUtils.returnJson("Jsons\\dianomi.geojson.json")));
    }

    @Test
    public void emptyJson() {
        assertFalse(JUtils.isCorrectJson(""));
    }

    @Test
    public void wrongJson1() {
        assertFalse(JUtils.isCorrectJson(JUtils.returnJson("Jsons\\cyta.geojson.json")));
    }

    @Test
    public void wrongJson2() {
        assertFalse(JUtils.isCorrectJson(JUtils.returnJson("Jsons\\wrong.geojson.json")));
    }

    @Test
    public void noInput() {
        assertFalse(JUtils.isCorrectJson(JUtils.returnJson("")));
    }

    @Test
    public void notJson() {
        assertFalse(JUtils.isCorrectJson(JUtils.returnJson("Tzamaloukas.iml")));
    }
}