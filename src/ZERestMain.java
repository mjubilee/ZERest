import com.fasterxml.jackson.databind.ObjectMapper;
import com.ze.rest.DataETL;
import com.ze.util.Configuration;

import java.io.File;

public class ZERestMain {

    public static void main(String[] args) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            Configuration conf = mapper.readValue(new File("configuration.json"), Configuration.class);

            DataETL etl = new DataETL( conf.createDataSource() );
            etl.processArticle();
        }catch(Exception e) {
            e.printStackTrace();
        }


    }

}
