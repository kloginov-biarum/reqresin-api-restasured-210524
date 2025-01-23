import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class GetSingleUserTest extends BaseTest {

    @Test
    public void getSingleUserTest() {
/*
      Integer userId = 5;
      UserDataResponse response = given().baseUri("https://reqres.in")
                .when().log().all()
                .get("/api/users/" + userId)
                .then().log().all()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data", UserDataResponse.class);
        //Check that id is 2
        assertEquals(userId, response.getId());
        //Check that email is not empty
        assertFalse(response.getEmail().isEmpty());
        //Check that email ends with "@reqres.in"
        response.getEmail().contains("@reqres.in");
        response.getEmail().endsWith("@reqres.in");

        //Check that avatar ends with "id-image.jpg"
        assertTrue(response.getAvatar().endsWith(userId + "-image.jpg"));*/




        //Crete user
        CreateUserRequest requestBodyCreate = new CreateUserRequest("John", "Junior QA");
        Response response = postRequest("/api/users", 201, requestBodyCreate);
        SuccessfulCreateUserResponse responseBodyCreate = response.as(SuccessfulCreateUserResponse.class);


        //Get user by id
        Response responseGet = getRequest("/api/users/" + responseBodyCreate.getId(), 200);
        UserDataResponse responseBodyData
                = response.body().jsonPath().getObject("data", UserDataResponse.class);

        //Check that userId in response is equal to id from request
        assertEquals(5, responseBodyData.getId());
        //Check that email ends with "@reqres.in"
        assertTrue(responseBodyData.getEmail().endsWith("@reqres.in"));

        //Check that avatar ends with "id-image.jpg"
        assertTrue(responseBodyData.getAvatar().endsWith(responseBodyData.getId() + "-image.jpg"));


        //Delete user
        deleteRequest("/api/users/" + responseBodyCreate.getId(), 204);
    }

}
