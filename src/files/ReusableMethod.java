package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;


public class ReusableMethod {
    public static XmlPath rawToXML(io.restassured.response.Response r )
    {


            String respon = r.asString();
            XmlPath x = new XmlPath(respon);
            return x;

    }


        public static JsonPath rawTojson(io.restassured.response.Response r )
        {

            String respon = r.asString();
            JsonPath x = new JsonPath(respon);
            return x;

        }
}
