package get;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PokemonCharacterPojo;
import pojo.PokemonPojo;
import pojo.StarWarsCharactersPojo;
import pojo.StarWarsPojo;

import java.util.List;
import java.util.Map;

public class Pokemon {



   @Before
   public void setup(){
       RestAssured.baseURI="https://pokeapi.co";
       RestAssured.basePath="api/v2/pokemon";
   }


    @Test
    public void pokemonTest(){
    Response response =  RestAssured.given()
            .header("Accept","application/json")
            .when().get()
            .then().statusCode(200).extract().response();

        PokemonPojo parsedResponse = response.as(PokemonPojo.class);

        Assert.assertEquals(1126, parsedResponse.getCount());

        List<PokemonCharacterPojo> results = parsedResponse.getResults();

        for(PokemonCharacterPojo characters: results){
            System.out.println(characters.getName());
            System.out.println(characters.getUrl());
        }



   }






 //Third type of Deserialization



  @Test
  public void pokemonTest2(){

      Response response =  RestAssured.given()
            .header("Accept","application/json")
            .when().get()
            .then().statusCode(200).extract().response();


      JsonPath jsonPath = response.jsonPath();
      String nextUrl = jsonPath.getString("next");
      System.out.println(nextUrl);
      String firstPokemonName = jsonPath.getString("results[0].name");
      String allPokemonNamesInOneString = jsonPath.getString("results.names");
      System.out.println(firstPokemonName);
      System.out.println(allPokemonNamesInOneString);
      List<Map<String,String>> resultsListWithJsonPath = jsonPath.getList("results");

      for(Map<String,String> pokemon: resultsListWithJsonPath){
          System.out.println(pokemon.get("name"));
      }

  }





 @Test
 public void pokemonTest3()   {

       Response response= RestAssured.given().header("Accept","application/json").log().all()
               .when().get()
               .then().statusCode(200).body("count", Matchers.equalTo(1126))
               .body("results[0].name",Matchers.equalTo("bulbasaur"))
               .log().body().extract().response(); //you can print all, everything






 }





















}
