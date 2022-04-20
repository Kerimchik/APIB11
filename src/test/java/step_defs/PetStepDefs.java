package step_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.PetPojo;
import post.Slack;

public class PetStepDefs {

    Response response;

    @Given("I have valid url to create a pet")
    public void i_have_valid_url_to_create_a_pet() {

        RestAssured.baseURI ="https://petstore.swagger.io";
        RestAssured.basePath ="v2/pet";

    }
    @When("I send POST request to create a pet")
    public void i_send_post_request_to_create_a_pet() {

        //I define variable on a class leve;
        PetPojo pet = new PetPojo();
        pet.setStatus("taken");
        pet.setName("Balu");
        pet.setId(45573);


        response =  RestAssured.given()
               .accept(Slack.APPLICATION_JSON)
               .contentType(Slack.APPLICATION_JSON)
               .body(pet)
               .when().post();




    }



    @Then("status code should be {int}")
    public void status_code_should_be(int expectedStatusCode) {

       int actualStatusCode =  response.statusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
    }


    @Then("response should be in json format")
    public void response_should_be_in_json_format() {

        // java.PendingException(); --common java exception in cucumber

        String actualContentType = response.getContentType();
        Assert.assertEquals(Slack.APPLICATION_JSON, actualContentType);




    }

}
