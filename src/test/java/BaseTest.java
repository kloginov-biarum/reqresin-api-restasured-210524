import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class BaseTest {

    static String BASE_URI = "https://reqres.in";

    static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .build();

    public static Response postRequest (String endPoint, Integer expectedStatusCode, Object body){
       Response response = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .log().all()
                .post(endPoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
          return response;
    }

    //getRequest method
    public static Response getRequest(String endPoint, Integer expectedStatusCode){
        Response response = given()
                .spec(requestSpecification)
                .when()
                .log().all()
                .get(endPoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }


    //putRequest method
    public static Response putRequest(String endPoint, Integer expectedStatusCode, Object body){
        Response response = given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .log().all()
                .put(endPoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }

    //deleteRequest method
    public static Response deleteRequest(String endPoint, Integer expectedStatusCode){
        Response response = given()
                .spec(requestSpecification)
                .when()
                .log().all()
                .delete(endPoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }

}
