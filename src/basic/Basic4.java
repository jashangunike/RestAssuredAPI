package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import files.ReusableMethod;



public class Basic4 {
    @Test
    public void ExtractNamingApi() {
        //BAse URL or BAse
        RestAssured.baseURI = "https://maps.googleapis.com";

                io.restassured.response.Response res =  given().
                params("location","28.4770788,77.0600442").
                params("radius","1500").
                params("type","restaurant").
                params("keyword","cruise").
                params("key","AIzaSyBupjF1W_8Mn4w19nw9YUL9dud7bq0VSf8").

                when().
                get("/maps/api/place/nearbysearch/json").

                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().  // status code 200 and contenttype JSON
                body("results[0].name",equalTo("The Hook Gurgaon")).and().
                body("results[0].id",equalTo("cd6e60827da91e835cbd6121fac47282ccb24392")).and().
                header("Server","scaffolding on HTTPServer2"). // pass
                extract().response();
                 JsonPath JS = ReusableMethod.rawTojson(res);
                int count = JS.get("results.size()");
                for(int i=0;i<=count;i++)
                {
                  String RestroName =  JS.get("results["+i+"].name");
                    System.out.println(RestroName);
                }
        System.out.println(count);



    }
}
