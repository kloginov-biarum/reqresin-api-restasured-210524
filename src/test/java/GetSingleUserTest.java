import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class GetSingleUserTest {

    @Test
    public void getSingleUserTest() {

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
        assertTrue(response.getAvatar().endsWith(userId + "-image.jpg"));
    }

}
