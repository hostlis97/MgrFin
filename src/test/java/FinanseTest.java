import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FinanseTest {
    final String jsonRequest = "{\"title\": \"Машина\", \"date\": \"2022.02.08\", \"sum\": 100000}";
    Finanse finanse = new Finanse();
    Gson gsonTest = new Gson();
    private Server server;

    @BeforeEach
    void setUp() {
        server = Mockito.mock(Server.class);
    }

    @Test
    void categoryTest() throws IOException {
        JsonRequest jsonRequestTest = gsonTest.fromJson(jsonRequest, JsonRequest.class);
        finanse.bascet.add(jsonRequestTest);
        Map actualResult = (finanse.sum());
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("category", "другое");
        expectedResult.put("sum", 100000);
        Assertions.assertEquals(expectedResult.get("category"), actualResult.get("category"));
    }

    @Test
    void sum_test() throws IOException {
        JsonRequest jsonRequestTest = gsonTest.fromJson(jsonRequest, JsonRequest.class);
        finanse.bascet.add(jsonRequestTest);
        Map actualResult = (finanse.sum());
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("category", "другое");
        expectedResult.put("sum", 100000);
        Assertions.assertEquals(expectedResult.get("sum"), actualResult.get("sum"));
    }
}