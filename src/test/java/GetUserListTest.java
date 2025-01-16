import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetUserListTest {

    @Test
    public void getUserListTest(){
       List<UserDataResponse> users = given().baseUri("https://reqres.in")
                .when().log().all()
                .get("api/users?page=2")
                .then().log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("data", UserDataResponse.class);

    //Check that users list contains 6 elements
        assertEquals(6, users.size());
        for (UserDataResponse user: users){
            assertTrue(user.getId()>0);
            user.getEmail().endsWith("@reqres.in");
        }
    }

}
