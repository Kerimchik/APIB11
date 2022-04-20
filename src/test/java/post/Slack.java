package post;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import pojo.SlackMessagePojo;
import utils.PayLoadUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Slack {


    @Before//hook
    public void setup() {

        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";
    }

    public static final String APPLICATION_JSON = "application/json";


    @Test
    public void slackMessageTest() {

        RestAssured.given()
                .accept("application/json")//alternativer for .header.get("accept""application/json")
                .contentType("application/json")
                .header("Authorization", "TOKEN") //"Bearer xoxb-2694972852931            //-3301004561938-5HbvEoX49duFra8t1Gmd8iyj
                .body(PayLoadUtil.getSlackMessagePayload("Hi, Java"))
                .when().post()
                .then().statusCode(200)
                .body("ok", Matchers.is(true));

    }


    @Test
    public void sendMessageTest1() {
        Map<String, String> slackMessageMap = new HashMap<>();
        slackMessageMap.put("channel", "C0397J4JY3T");
        slackMessageMap.put("text", "Kerima: Lovely weather!");

        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .auth().oauth2("TOKEN")   //xoxb-2694972852931-3301004561938-  //5HbvEoX49duFra8t1Gmd8iyj
                .body(slackMessageMap)
                .when().post()
                .then().statusCode(200)
                .and()
                .body("ok", Matchers.equalTo(true));

    }


    @Test
    public void sendMessageTest2() {
//        Map<String,String>  slackMessageMap = new HashMap<>();
//        slackMessageMap.put("channel", "C0397J4JY3T");
//        slackMessageMap.put("text","Kerima: Lovely weather!");

        //creating the instance of a file class

        File slackMessageFile = new File("src/test/resources/SlackMessage.json");


        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .auth().oauth2("TOKEN")   //xoxb-2694972852931-3301004561938-  //5HbvEoX49duFra8t1Gmd8iyj
                .body(slackMessageFile)
                .then().statusCode(200)
                .and().body("ok", Matchers.equalTo(true));
    }



    @Test
    public void sendMessageWithPojoTest(){

        //serialization of java object to json object
        SlackMessagePojo slackMessagePojo = new SlackMessagePojo();
        slackMessagePojo.setChannel("C0397J4JY3T");
        slackMessagePojo.setText("Kerima: March 29,2022");

        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .auth().oauth2("TOKEN")   //xoxb-2694972852931-3301004561938-  //5HbvEoX49duFra8t1Gmd8iyj
                .body(slackMessagePojo)
                .when().post()
                .then().statusCode(200)
                .body("ok", Matchers.equalTo(true));



    }














}