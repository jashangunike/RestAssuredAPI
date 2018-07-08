package basic;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import files.resource;              // Import the resource file here
import files.payload;                  //Import the resource fro payload here
import java.io.FileInputStream;


import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class advanceBasic {

    Properties prop=new Properties();

    @BeforeTest
    public void getData() throws IOException
    {

        FileInputStream fis=new FileInputStream("C:\\Users\\jashan\\IdeaProjects\\RestAssuredAPI\\src\\files\\env.properties");
        prop.load(fis);

       // prop.get("HOST");
    }


    @Test
    public void ADDandDel(){



        //1.Grabb the response
        RestAssured.baseURI = prop.getProperty("HOST");
        Response res = given().
                queryParam("key",prop.getProperty("KEY")).  // value comes from env.properties files
                body(payload.getPstData()).                         // value comes from payload.java resource
                when().
                post(resource.placeStoreData()).            // Values coming from resource file

                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status",equalTo("OK")).
                extract().response();


                String  ResponseStrng = res.asString();
                System.out.println(ResponseStrng); // result comes as RAW
                //2. Grab the place ID
                JsonPath js = new JsonPath(ResponseStrng); //Convert RAW into JSON
                String PlaceID = js.get("place_id");
                System.out.println(PlaceID);

                //3.Delete the PlaceID
                given().
                        queryParam("key","AIzaSyBupjF1W_8Mn4w19nw9YUL9dud7bq0VSf8").
                        body("{\n" +
                                "  \"place_id\": \""+PlaceID+"\"" +
                                "}").

                        when().
                        post("/maps/api/place/delete/json").

                    then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                        body("status",equalTo("OK"));










    }

}
