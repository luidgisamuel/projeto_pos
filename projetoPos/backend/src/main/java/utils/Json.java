package utils;
import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
public class Json {
  public static String getJsonFromRequestBody(BufferedReader requestReader) {

    StringBuilder json = new StringBuilder();
    BufferedReader reader = requestReader;
    String linha;
    try {
        while( (linha = reader.readLine()) != null ){
            json.append(linha);
        }
        return json.toString();
    }
    catch (IOException e) {
        return null;
    }

}

public static <T> T parseJsonToObject(String json, Class<T> classOfObject) {
    try {
        return new Gson().fromJson(json, classOfObject);
    }
    catch (JsonSyntaxException e) {
        return null;
    }
}

}
