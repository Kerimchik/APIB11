package put;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PetPojo;
import post.Slack;

import java.util.Map;

public class Pet {



    @Before
    public void setiup(){
        RestAssured.baseURI ="https://petstore.swagger.io";
        RestAssured.basePath ="v2/pet";

    }


    @Test
    public void updatePetTest(){

        PetPojo pet =new PetPojo();
        pet.setName("Sugar baby");
        pet.setId(896544);
        pet.setStatus("fluffy");


        Response response = RestAssured.given()
                .accept(Slack.APPLICATION_JSON)
                .contentType(Slack.APPLICATION_JSON)
                .body(pet)
                .when().put()
                .then().statusCode(200).extract().response();


         Map<String,Object>  deserializedResp=  response.as(new TypeRef<Map<String,Object>>() {
        });

        String name = (String)  deserializedResp.get("name");  //casting

        String name1 = String.valueOf(deserializedResp.get("name"));  //valueof
        //when I make final i cannot extend

        Assert.assertEquals("Sugar baby",name);


        int id =(int) deserializedResp.get("id");

        Assert.assertEquals(896544, id);



    }










}
