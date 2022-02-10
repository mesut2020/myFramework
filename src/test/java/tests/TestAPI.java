package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class TestAPI {

    Response response;
    int new_user_id;

    @BeforeTest
    public void setBaseURI(){
        baseURI = "https://reqres.in/api";
    }

    @Test
    public void getListOfUsers(){

        response = when().get("/users?page=2") //when().request("GET","/users?page=2")
                .then()
                .statusCode(200)
                .body("data[0].first_name", Matchers.containsString("Michael"))
                .extract().response();
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertTrue(jsonPath.getInt("page")==2);
        Assert.assertTrue(jsonPath.getString("data[0]").contains("Michael"));
    }

    @Test
    public void getSingleUser(){
        int user_id = 3;
        response = when().get("/users/"+user_id)
                .then()
                .statusCode(200)
                .body("data.first_name", Matchers.containsString("Emma"))
                .body("data.id", Matchers.equalTo(3))
                .extract().response();
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.getString("data.first_name"),"Emma");
        Assert.assertEquals(jsonPath.getInt("data.id"), 3);
    }

    @Test
    public void createUser(){
        String body = "{  \n" +
                "    \"email\": \"mesutdogan@reqres.in\",\n" +
                "    \"first_name\": \"Mesut\",\n" +
                "    \"last_name\": \"Dogan\"  \n" +
                "}";
        response = given().headers("Content-Type", ContentType.JSON)
                .when()
                .body(body)
                .post("/users")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(201)
                .body("last_name", Matchers.containsString("Dogan"))
                .extract().response();

        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        new_user_id = jsonPath.getInt("id");

        Assert.assertEquals(jsonPath.getString("last_name"),"Dogan");
    }

    @Test (dependsOnMethods = {"createUser"})
    public void updateUser() {

        String body = "{\n" +
                "    \"email\": \"ozlem@reqres.in\",\n" +
                "    \"first_name\": \"Ozlem\",\n" +
                "    \"last_name\": \"Dogan\"\n" +
                "}";
        response = given().headers("Content-Type", ContentType.JSON)
                .when()
                .body(body)
                .put("/users/" + new_user_id)
                .then().statusCode(200)
                .body("first_name", Matchers.containsString("Ozlem"))
                .extract().response();
    }

    @Test (dependsOnMethods = {"createUser"})
    public void updateUserLastName() {

        String body = "{\n" +
                "    \"last_name\": \"Kaya\"\n" +
                "}";
        response = given().headers("Content-Type", ContentType.JSON)
                .when()
                .body(body)
                .patch("/users/" + new_user_id)
                .then().statusCode(200)
                .body("last_name", Matchers.containsString("Kaya"))
                .extract().response();
    }

    @Test (dependsOnMethods = {"updateUserLastName"})
    public void deleteUser() {

        response = when().delete("/users/" + new_user_id)
                .then().statusCode(204)
                .extract().response();

        Assert.assertEquals(response.prettyPrint(), "");
    }

    @Test (enabled = false)
    public void GetWeatherDetailsCondensed()
    {
        // Specify the base URL to the RESTful web service
        baseURI = "https://demoqa.com/utilities/weather/city";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Make a GET request call directly by using RequestSpecification.get() method.
        // Make sure you specify the resource name.
        Response response = httpRequest.get("/Hyderabad");

        // Response.asString method will directly return the content of the body
        // as String.
        System.out.println("Response Body is =>  " + response.asString());
        response.prettyPrint();
    }


}
