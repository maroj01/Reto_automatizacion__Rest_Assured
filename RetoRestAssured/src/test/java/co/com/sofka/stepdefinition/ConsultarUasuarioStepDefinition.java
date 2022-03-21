package co.com.sofka.stepdefinition;

import co.com.sofka.stepdefinition.configuracion.servicio.ConfigurarServicio;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class ConsultarUasuarioStepDefinition extends ConfigurarServicio {
    public static final Logger LOGER = Logger.getLogger(ConsultarUasuarioStepDefinition.class);
    private Response response;
    private RequestSpecification resquest;
    private String cuerpoRespuesta;
    private String correo;

    @Dado("que estoy en la seccion de busqueda de usuarios")
    public void irSeccionBusquedaUsuarios() {
        try {
            establecerConfiguracion();
            resquest = given()
                    .log()
                    .all();
        } catch (Exception e) {
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Cuando("busco el usuario con ID {string}")
    public void buscarUsuario(String id) {
        try {

            cuerpoRespuesta =
                    resquest.when()
                            .get(LOGIN_RESOURCE + "/" + id)
                            .getBody()
                            .asString();

        } catch (Exception e) {
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Cuando("obtengo el correo del usuario")
    public void obtenerCorreoUsuario() {
        try {
            JsonPath resJson = new JsonPath(cuerpoRespuesta);
            correo = resJson.getString("data.email");

        } catch (Exception e) {
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Entonces("el formato del correo es el correcto")
    public void verificarCorreoCorrecto() {
        Assertions.assertTrue(comprobarFormatoCorreo());
    }

    public boolean comprobarFormatoCorreo() {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        if (mather.find()) {
            return true;
        } else {
            return false;
        }
    }

}
