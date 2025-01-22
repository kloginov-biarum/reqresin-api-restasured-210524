import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class UpdateUser extends BaseTest {

    @Test
    public void checkUpdateUser(){
        //Call putRequest() method to update user
        CreateUserRequest requestBody = new CreateUserRequest("Mike", "leader");
        Response response = putRequest("/api/users/2", 200, requestBody);
       UpdateUserResponse responseBody =  response.as(UpdateUserResponse.class);


        }
        //Check that name from response equals to the name from request
    }


