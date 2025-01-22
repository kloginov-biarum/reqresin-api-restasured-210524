import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class AddPetTest {
   @Test
    public void addValidPet(){
        //Call the method of adding a new pet
       //Check that status code is 200
       Pet requestBody =  new Pet();

       Pet.Category category = new Pet.Category();
       category.setId(12);
       category.setName("Cats");

       Pet.Tag tag = new Pet.Tag();
         tag.setId(123);
         tag.setName("cat");


       requestBody.setCategory(category);
       requestBody.setName("Rio");
       requestBody.setStatus("available");
       requestBody.setTags(Arrays.asList(tag));

       Pet pet = given().baseUri("https://petstore.swagger.io")
               .when().log().all()
               .contentType(ContentType.JSON)
               .body(requestBody)
               .post("/v2/pet/")
               .then().log().all()
               .statusCode(200)
               .extract().body().jsonPath().getObject("", Pet.class);

//Check that id is not empty in response
       assertNotNull(pet.getId());
       assertTrue(pet.getId()>0);

       //Check that name from response is the same as in request
       assertEquals(requestBody.getName(), pet.getName());
       //Check that category name and category id are the same as in request

       assertEquals(requestBody.getCategory().getName(), pet.getCategory().getName());
       assertEquals(requestBody.getCategory().getId(), pet.getCategory().getId());

     //Check that pet has any tag (at least one)
       assertTrue(pet.getTags().size()>0);

   }
}
