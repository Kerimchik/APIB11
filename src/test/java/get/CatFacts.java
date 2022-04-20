package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CatFacts {



  @Test
  public void factsAboutCats(){

    Response response = RestAssured.given().header("Accept","applications/json")
            .when().get("https://cat-fact.herokuapp.com/facts")
            .then().statusCode(200).extract().response();


    List<Map<String,Object>> listOfFactsAboutCats = response.as(new TypeRef<List<Map<String, Object>>>() {
    });


    System.out.println(listOfFactsAboutCats);



    for(int i =0; i<listOfFactsAboutCats.size(); i++){

        Map<String,Object> catFactMap = listOfFactsAboutCats.get(i);
       // catFactMap.get("text");
      System.out.println(catFactMap.get("text"));
      if(i==0) {
         Assert.assertEquals("Cats make about 100 different sounds. Dogs make only about 10.",catFactMap.get("text") );
      }

    }

  //  System.out.println(listOfFactsAboutCats.get(listOfFactsAboutCats.size()-1));
   // Assert.assertEquals("5887e1d85c873e0011036889",listOfFactsAboutCats );

    Assert.assertEquals("Cats are the most popular pet in the United States: There are 88 million pet cats and 74 million dogs.",listOfFactsAboutCats.get(listOfFactsAboutCats.size()-1).get("text"));






  }






}
