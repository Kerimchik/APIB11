package get;

import com.sun.tools.internal.xjc.model.CTypeRef;
import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.StarWarsCharactersPojo;
import pojo.StarWarsPojo;

import java.util.List;
import java.util.Map;

public class StarWars {



    @Test
    public void starWars(){

        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();


        Map<String, Object> starWarsMap = response.as(new TypeRef<Map<String, Object>>() {
        });


       List<Map<String,Object>> listOfCharacters = (List<Map<String, Object>>) starWarsMap.get("results");

        int count =0;

        for (int i =0; i<listOfCharacters.size();i++){
            Map<String,Object> charMap = listOfCharacters.get(i);

            if (charMap.get("gender").equals("female")){
                ++count;
            }

        }

        Assert.assertTrue(count>0);

    }


    @Test
    public void deserializeWithPojo(){

        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();
        //take response and matches variables and if you miss variables it fails by giving UnrecognizedPropertyException

        StarWarsPojo deserializedResp = response.as(StarWarsPojo.class);

        Assert.assertEquals(82,deserializedResp.getCount());
        Assert.assertEquals("https://swapi.dev/api/people/?page=2", deserializedResp.getNext());
        Assert.assertEquals(null, deserializedResp.getPrevious());


//        List<Map<String,Object>> results = deserializedResp.getResults();
//        for(Map<String,Object> map: results){
//            System.out.println(map.get("name"));
//        }

        List<StarWarsCharactersPojo> results = deserializedResp.getResults();

        System.out.println(results.get(0).getName());
        System.out.println(results.get(results.size()-1).getName());


        for(StarWarsCharactersPojo character: results){
            System.out.println(character.getName());
            System.out.println(character.getBirth_year());
         }


    }










}
