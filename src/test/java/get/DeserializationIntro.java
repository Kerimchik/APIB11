package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class DeserializationIntro {

//    @Test
//    public void testPet(){
//    //basically this method perform an action of clicking the send button
//      RestAssured.given().header("Accept","application/json")
//                .when()
//                .get("https://petstore.swagger.io/v2/pet/775")
//                .then().statusCode(200);
//    }



    //Deserialization is taking response body and converting json to java and do our java testing




    @Test
    public void testPet(){
        //basically this method perform an action of clicking the send button
        Response response =  RestAssured.given().header("Accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/775")
                .then().statusCode(200).extract().response();

        //Map <Key is always String, And value is Object because it can be any data type>

        Map<String,Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {});
        //Typeref is abstract class make deserialization which takes all data and matching
        //new TypeRef<Map<String, Object>>() {}  --> creating anonymous class from abstract class. It is not usual object cause we cannot create new object from abstract class


        System.out.println(deserializedResponse);

        Assert.assertEquals(775, deserializedResponse.get("id"));

        //Assert.assertEquals(0,deserializedResponse.get("category"));
    }





}
