package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CovidStatistics {


    @Test
    public void testCorona(){
        //basically this method perform an action of clicking the send button
        Response response =  RestAssured.given().header("Accept","application/json")
                .when()
                .get("https://corona.lmao.ninja/v2/all")
                .then().statusCode(200).extract().response();

        //Map <Key is always String, And value is Object because it can be any data type>

        Map<String,Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {});
        //Typeref is abstract class make deserialization which takes all data and matching
        //new TypeRef<Map<String, Object>>() {}  --> creating anonymous class from abstract class. It is not usual object cause we cannot create new object from abstract class


        //typesafe
        int affectedcountries= (int)deserializedResponse.get("affectedCountries");
        Assert.assertEquals(227, affectedcountries);

        //It works without casting because this method is overloaded, so it is getting an int as an Object already
        // Assert.assertEquals(227,deserializedResponse.get("category"));
    }










}
