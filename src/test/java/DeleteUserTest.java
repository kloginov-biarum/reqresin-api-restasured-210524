import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class DeleteUserTest extends BaseTest{
    @Test
    public void deleteUser(){
        //Create a new user
        //Store users id
        CreateUserRequest requestBody = new CreateUserRequest("Jack", "admin");
        Response response = postRequest("/api/users", 201, requestBody);
        SuccessfulCreateUserResponse responseBody = response.as(SuccessfulCreateUserResponse.class);
        //Call delete user method for the user from the previous step
        //Check that status code is 204

//Call delete user method for the user from the previous step
//Check that status code is 204

        deleteRequest("/api/users/" + responseBody.getId(), 204);
    }

}
