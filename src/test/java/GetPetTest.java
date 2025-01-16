import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetPetTest {
    @Test
    public void findPets(){
//        Integer petsId = 2;
       Pet pet =  given().baseUri("https://petstore.swagger.io/")
                .when().log().all()
                .get("/v2/pet/9223372036854765000")
                .then().log().all()
                .statusCode(200)
               .extract().body().jsonPath().getObject("", Pet.class);
       //Check that id is not empty
        assertNotNull(pet.getId());
        //Check that status is available
        assertEquals("available", pet.getStatus());
    }
}
