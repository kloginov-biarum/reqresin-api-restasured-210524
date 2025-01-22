import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetPetTest {
    @Test
    public void findPets() {
//        Integer petsId = 2;
        Pet pet = given().baseUri("https://petstore.swagger.io/")
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

    @Test
    public void getPetByStatusSold() {
        //Call API method to get pets by status = sold
        //Check that status of each pet is sold

        List<Pet> pets = given().baseUri("https://petstore.swagger.io/")
                .when().log().all()
                .get("/v2/pet/findByStatus?status=sold")
                .then().log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("", Pet.class);

        for (Pet pet : pets) {
            assertEquals("sold", pet.getStatus());
            //Check that id is not empty
            assertNotNull(pet.getId());
        }

        //Check that first item has name of category  (is not empty)
        assertFalse(pets.get(0).getCategory().getName().isEmpty());
        //Check that first item has any tag (size of the list > 0)
        assertTrue(pets.get(0).getTags().size()>0);
        pets.get(1).getTags().get(0).getName();
    }


    @Test
    public void getPetByStatusAvailable() {
        //Call API method to get pets by status = sold
        //Check that status of each pet is sold

        List<Pet> pets = given().baseUri("https://petstore.swagger.io/")
                .when().log().all()
                .get("/v2/pet/findByStatus?status=available")
                .then().log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("", Pet.class);

        for (Pet pet : pets) {
            assertEquals("available", pet.getStatus());
        }

    }
    @Test
    public void getPetByStatusPending() {
        List<Pet> pets = given().baseUri("https://petstore.swagger.io/")
                .when().log().all()
                .get("/v2/pet/findByStatus?status=pending")
                .then().log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("", Pet.class);

        for (Pet pet : pets) {
            assertEquals("pending", pet.getStatus());
        }
    }
}

