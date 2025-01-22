import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateUser extends BaseTest{

    //Check that 201 status code is returned when a new user is created

    @Test
    public void successCreatedUser() {
        String name = "Rio";
        CreateUserRequest requestBody = new CreateUserRequest(name, "QA");

 /*       SuccessfulCreateUserResponse response = given().baseUri("https://reqres.in")
                .when().log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/users")
                .then().log().all()
                .statusCode(201)
                .extract().body().jsonPath().getObject("", SuccessfulCreateUserResponse.class);
        System.out.println(response.getName());*/

       Response response =  postRequest("/api/users", 201, requestBody);

    }
}
