package test.com.ze.rest;

import com.ze.rest.DataEtl;
import com.ze.util.Configuration;
import com.ze.util.Parameter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestDataEtl  {
     private static final String FILE_EXT = ".csv";
     private static final String DATE_FORMAT = "MMM_dd_yyyy";

     private static String fileName;
     private static Configuration configuration;

    @BeforeClass
    public static void prepareConfiguration() {
        configuration = new Configuration();
        configuration.setScheme("https");
        configuration.setHost("newsapi.org");
        configuration.setPath("/v1/articles");
        configuration.setDelimiter("~");
        configuration.setOutputFileName("test_top_headline_");
        configuration.setTestMode("false");
        configuration.setTestFile("newsTest.json");

        List<Parameter> parameters = new ArrayList<>();
        parameters.add( new Parameter("source","cnn")  );
        parameters.add( new Parameter("sortBy","top")  );
        parameters.add( new Parameter("apiKey","2a4639109b424bd3970e2fdf00fa54de")  );

        configuration.setParameters(parameters);

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        fileName = configuration.getOutputFileName() + dateFormat.format(new Date()) + FILE_EXT;

    }

    @Test
    public void generateResultFileTest()  {

        DataEtl testDataEtl = new DataEtl(configuration); // MyClass is tested
        testDataEtl.processArticle();

        File file = new File(fileName);

        assertEquals(true, file.isFile() );

    }

    @AfterClass
    public static void deleteResult() {
        File file = new File(fileName);
        file.delete();
    }


}
