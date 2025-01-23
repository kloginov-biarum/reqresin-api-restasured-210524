import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateUser extends BaseTest {

    @Test
    public void checkUpdateUser(){
        //Create new user
        //Store user id
        //John, Junior QA
        CreateUserRequest requestBodyCreate = new CreateUserRequest("John", "Junior QA");
        Response response = postRequest("/api/users", 201, requestBodyCreate);
        SuccessfulCreateUserResponse responseBodyCreate = response.as(SuccessfulCreateUserResponse.class);
        //Call putRequest() method to created user
        //Middle QA

        CreateUserRequest requestBodyUpdate = new CreateUserRequest("John", "Middle QA");
        Response responseUpdate = putRequest("/api/users/" + responseBodyCreate.getId(),
                200, requestBodyUpdate);
        UpdateUserResponse responseBodyUpdate =  responseUpdate.as(UpdateUserResponse.class);
        //Check that name from response equals to the name from request
        assertEquals(requestBodyUpdate.getName(), responseBodyUpdate.getName());
        //Check that job value has changed (old job value is not equal to new job value)
        assertNotEquals(responseBodyCreate.getJob(), responseBodyUpdate.getJob());
        assertFalse(responseBodyCreate.getJob().equals(responseBodyUpdate.getJob()));

        //Delete created user
        deleteRequest("/api/users/" + responseBodyCreate.getId(), 204);
        }



    }


