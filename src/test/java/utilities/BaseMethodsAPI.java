package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseMethodsAPI {

    /**
     *
     * @param apiEndPoint
     * @param body  usename and password as a Json type
     * @param tokenPath  tokenID
     * @return token as a string
     */
    public static String getTokenViaApiEndpoint (String apiEndPoint, String body, String tokenPath){

        Response response = given().contentType("application/json")
                .when().body(body).post(apiEndPoint);
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString(tokenPath);
    }

    public static Response getAllDataFrom(String apiEndPoint){

        return given().headers("Authorization",
                        "Bearer "+ ConfigurationReader.getProperty("token"),
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON)
                .when()
                .get(ConfigurationReader.getProperty(apiEndPoint))
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
    }

    public static Response getAllDataFrom2(String apiEndPoint){

        return given().accept(ContentType.JSON)
                .auth()
                .oauth2(ConfigurationReader.getProperty("token"))
                .when()
                .get(ConfigurationReader.getProperty(apiEndPoint));
    }

    public static Response createData(String apiEndPoint, String body){

        return given().headers("Authorization",
                        "Bearer "+ConfigurationReader.getProperty("token"),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept", ContentType.JSON)
                .when()
                .body(body)
                .post(ConfigurationReader.getProperty(apiEndPoint))
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
    }

    public static Response updateData(String apiEndPoint, String body){

        return given().headers("Authorization",
                        "Bearer "+ConfigurationReader.getProperty("token"),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept", ContentType.JSON)
                .when()
                .body(body)
                .put(ConfigurationReader.getProperty(apiEndPoint))
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
    }

    public static Response deleteData (String apiEndPoint, String itemId){
        return given().contentType(ContentType.JSON)
                .auth().oauth2(ConfigurationReader.getProperty("token"))
                .when().delete(apiEndPoint + itemId);
    }
}
