package delete;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import post.Slack;
import utils.PayLoadUtil;

public class Pet {

    String name = "Venom";
    String status ="Gone";
    int id = 437267;


    @Test
    public void deletePetTest(){


        //DELETE: https://petstore.swagger.io/v2/pet/{petID}

        RestAssured.given().accept(Slack.APPLICATION_JSON)
                .when().delete(String.valueOf(id))
                .then().statusCode(200);




    }






    @Before
    public void setiup(){
        RestAssured.baseURI ="https://petstore.swagger.io";
        RestAssured.basePath ="v2/pet";


        RestAssured.given()
                .contentType(Slack.APPLICATION_JSON)
                .accept(Slack.APPLICATION_JSON)
                .body(PayLoadUtil.getPetPayload(id, name, status))
                .when().post()
                .then().statusCode(200);



    }







    //DELETE: https://petstore.swagger.io/v2/pet/896544

//Post: https://petstore.swagger.io/v2/pet
















}
