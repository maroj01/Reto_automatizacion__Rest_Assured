package co.com.sofka.stepdefinition.configuracion.servicio;

import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.utilidades.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;


public class ConfigurarServicio {
    private static final String BASE_URI = "https://reqres.in";
    private static final String BASE_PATH = "/api";
    protected static final String LOGIN_RESOURCE = "/users";

    protected void establecerConfiguracion() {
        setUpLog4j2();
        setUpBases();
    }

    private void setUpLog4j2() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

    private void setUpBases() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

}
