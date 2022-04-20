package post;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayLoadUtil;

public class Pet {




    @Test
    public void createPetTest(){
        /*
        * 1.post
        * 2.validate the status code and response body (name id status)
        * Get
        * Validate the status code and res
        * */
        String petName ="Venom";
        int petId = 896544;
        String status ="currently available";

       Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(PayLoadUtil.getPetPayload(petId,petName,status))
                .when().post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().response();

         PetPojo parsedResponse =  response.as(PetPojo.class);

        Assert.assertEquals(petId, parsedResponse.getId());
        Assert.assertEquals(petName, parsedResponse.getName());
        Assert.assertEquals(status, parsedResponse.getStatus());

        /*
        * Add Get https https://petstore.swagger.io/v2/pet/896544
        *Validate name, id, status are still same
        *
        * */

        RestAssured.given().header("Accept", "application/json")
                .when().get("https://petstore.swagger.io/v2/pet/"+petId)
                .then().statusCode(200)
                .and()
                .body("id", Matchers.is(petId))
                .and()
                .body("name", Matchers.equalTo(petName))
                .and()
                .body("status", Matchers.equalTo(status));





    }








}
