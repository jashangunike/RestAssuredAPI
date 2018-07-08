package basic;

import files.payload;
import files.resource;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.ReusableMethod;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.equalTo;
import files.payload;
import files.resource;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Basic3 {


    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\jashan\\IdeaProjects\\RestAssuredAPI\\src\\files\\env.properties");
        prop.load(fis);

        // prop.get("HOST");
    }

    @Test
    public void ADDandDelXML() throws IOException {

        //1.Grabb the response
       String postData =  GenerateStringFromResource("C:\\Users\\jashan\\Desktop\\postData.xml");

        RestAssured.baseURI ="https://maps.googleapis.com";
        Response res =
            given().
                queryParam("key","AIzaSyBupjF1W_8Mn4w19nw9YUL9dud7bq0VSf8").log().all().  // value comes from env.properties files
                body(postData).                         // value comes from XML file
                when().
                post("/maps/api/place/add/xml").

                then().assertThat().statusCode(200).and().contentType(ContentType.XML).
                    extract().response();
                //String respo= res.asString(); // use asString not Tostring
                //System.out.println(respo);
                 //XmlPath x = new XmlPath(respo);
                XmlPath x = ReusableMethod.rawToXML(res); // comes from reusablemethod filee
               String placeid = x.get("PlaceAddResponse.place_id"); // Parent child transverse here placeaddresponse is in XML
                 System.out.println(placeid);




              //  body("status", equalTo("OK"));
          //      extract().response();


      //  String ResponseStrng = res.asString();
        //System.out.println(ResponseStrng); // result comes as RAW

    }

    public static String GenerateStringFromResource(String path) throws IOException{
        return new String(Files.readAllBytes(Paths.get(path))); // read the bytes and convert to string

    }
}