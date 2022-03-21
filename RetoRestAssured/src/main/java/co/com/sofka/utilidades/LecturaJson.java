package co.com.sofka.utilidades;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class LecturaJson {

    Object objetoJason;

    public String leer(Map<String, String> datos, String rutaArchivoJason) throws IOException, ParseException {
        objetoJason = new JSONParser().parse(new FileReader(rutaArchivoJason));
        JSONObject js = (JSONObject) objetoJason;

        for (Map.Entry<String, String> entry : datos.entrySet()) {
            js.put(entry.getKey(), entry.getValue());
        }
        return js.toJSONString();
    }


}
