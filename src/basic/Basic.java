package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Basic {
    @Test
    public void GoogleApi() {
        //BAse URL or BAse
        RestAssured.baseURI = "https://maps.googleapis.com";

        given().
                params("location","28.4770788,77.0600442").
                params("radius","1500").
                params("type","restaurant").
                params("keyword","cruise").
                params("key","AIzaSyBupjF1W_8Mn4w19nw9YUL9dud7bq0VSf8").log().all(). // with all info
              /*  header("xyz","abcd").               // not required in get request but in post required
                cookie("dsadasdas","asdasdad").
                body(). */      // not required in get request but in post required

                when().
                get("/maps/api/place/nearbysearch/json").

                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().  // status code 200 and contenttype JSON
                body("results[0].name",equalTo("The Hook Gurgaon")).and().
                body("results[0].id",equalTo("cd6e60827da91e835cbd6121fac47282ccb24392")).and().
                header("Server","scaffolding on HTTPServer2"); // pass

    }
}
