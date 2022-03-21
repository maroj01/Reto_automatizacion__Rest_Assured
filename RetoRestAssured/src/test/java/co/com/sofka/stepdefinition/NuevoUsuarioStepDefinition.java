package co.com.sofka.stepdefinition;

import co.com.sofka.stepdefinition.configuracion.servicio.ConfigurarServicio;
import co.com.sofka.utilidades.LecturaJson;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

import static co.com.sofka.utilidades.EnumJson.NOMBRE;
import static co.com.sofka.utilidades.EnumJson.TRABAJO;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class NuevoUsuarioStepDefinition extends ConfigurarServicio {
    private String RUTA_ARCHIVO_JASON = "C:\\Users\\dell i5 10gn\\Documents\\ADRIANA\\SOFKA\\Proyectos sofka\\Reto_RestAssured\\RetoRestAssured\\src\\test\\resources\\archivos\\crearusuario\\creacion.json";
    public static final Logger LOGER = Logger.getLogger(NuevoUsuarioStepDefinition.class);
    private Response response;
    private RequestSpecification resquest;

    @Dado("que me encuentro en la seccion de creacion de usuarios")
    public void encuentrarSeccionCreacionUsuarios() {
        try{
            establecerConfiguracion();
            resquest = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON);
        } catch (Exception e){
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Cuando("ingreso los datos del usuario: nombre {string} trabajo {string}")
    public void ingresarDatosUsuario(String nombre, String trabajo) {
        try{
            LecturaJson lecturaJason = new LecturaJson();
            Map<String, String> datos = new HashMap<String, String>();

            datos.put(NOMBRE.getValue(),nombre);
            datos.put(TRABAJO.getValue(),trabajo);
            response = resquest.when().body(lecturaJason.leer(datos, RUTA_ARCHIVO_JASON))
                    .post(LOGIN_RESOURCE);

        } catch (Exception e){
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Entonces("se creara el nuevo usuario con su respectivo ID.")
    public void crearNuevoUsuario() {
        try{
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .body("createdAt", notNullValue());
        } catch (Exception e){
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }

    }


}
