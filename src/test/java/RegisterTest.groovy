import io.restassured.http.ContentType
import org.junit.jupiter.api.Test

import static io.restassured.RestAssured.given

class RegisterTest {

    @Test
    public void successRegister(){
        RegisterRequest requestBody = new RegisterRequest("eve.holt@reqres.in", "pistol");

       SuccessRegisterResponse response = given().baseUri("https://reqres.in")
                .when().log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
               .post("/api/register" )
                .then().log().all()
                .statusCode(200)
        .extract().body().jsonPath().getObject("",SuccessRegisterResponse.class);
        System.out.println(response.getId());
        //Check that token is not empty
        assertFalse(response.getToken().isEmpty());
    }

    @Test
    public void registerWithoutPassword(){
        RegisterRequest requestBody = new RegisterRequest("eve.holt@reqres.in", "");
        ErrorRegisterResponse response = given().baseUri("https://reqres.in")
                .when().log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/register" )
                .then().log().all()
                .statusCode(400)
        .extract().body().jsonPath().getObject("",ErrorRegisterResponse.class);
        System.out.println(response.getError());
        //Check that error message is not empty
        assertFalse(response.getError().isEmpty());
//Check that error message is "Missing password"
        assertEquals("Missing password",response.getError());
    }
    @Test
    public void registerWithoutEmail(){
        RegisterRequest requestBody = new RegisterRequest("", "aMd");
        ErrorRegisterResponse response = given().baseUri("https://reqres.in")
                .when().log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/register" )
                .then().log().all()
                .statusCode(400)
                .extract().body().jsonPath().getObject("",ErrorRegisterResponse.class);
        System.out.println(response.getError());

//Check that error message is not empty
        assertFalse(response.getError().isEmpty());
// //Check that error message is "Missing password"
        assertEquals("Missing email or username",response.getError());
    }
    @Test
    public void registerEmptyCredentianals(){
        RegisterRequest requestBody = new RegisterRequest("", "");
        ErrorRegisterResponse response = given().baseUri("https://reqres.in")
                .when().log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/register" )
                .then().log().all()
                .statusCode(400)
                .extract().body().jsonPath().getObject("",ErrorRegisterResponse.class);
        System.out.println(response.getError());

//Check that error message is not empty
        assertFalse(response.getError().isEmpty());
// //Check that error message is "Missing password"
        assertEquals("Missing email or username",response.getError());
    }

}
