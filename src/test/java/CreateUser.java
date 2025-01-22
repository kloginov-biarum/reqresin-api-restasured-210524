import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;


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
       SuccessfulCreateUserResponse responseBody =  response.as(SuccessfulCreateUserResponse.class);
        //Check that name is the same as in request
        assertEquals(name, responseBody.getName());

        //Check that job is the same as in request
        assertEquals("QA", responseBody.getJob());
        //Check that id is not empty and ud value >0 (positive):
        assertFalse(responseBody.getId().isEmpty());
        assertTrue(parseInt(responseBody.getId())>0);

        //Check that createdAt is not empty and contains 2025 year
        assertFalse(responseBody.getCreatedAt().isEmpty());
        assertTrue(responseBody.getCreatedAt().contains("2025"));

    }
}
