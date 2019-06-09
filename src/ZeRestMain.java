import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ze.rest.DataEtl;
import com.ze.util.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * This is the main class to trigger the application. This class will read the application
 * configuration file in JSON format and then will deserialized the JSON to Configuration object.
 * The Configuration object will be passed to DataEtl object in order DataEtl object to start
 * consuming REST API from the data source and transform it into CSV file
 *
 * @author  Mahalunggu Jubilee
 * @version 1.0
 * @since   2018-06-07
 */

public class ZeRestMain {

    public static void main(String[] args) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Configuration configuration = objectMapper.readValue(new File("configuration.json"), Configuration.class);

            DataEtl dataEtl = new DataEtl( configuration );
            dataEtl.processArticle();

        } catch(JsonMappingException e) {
            e.printStackTrace();
        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }


    }

}
