import com.ze.rest.DataCollection;
import com.ze.rest.DataSource;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ZERestMain {

    public static void main(String[] args) {


        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add( new BasicNameValuePair("source", "cnn") );
        parameters.add( new BasicNameValuePair("sortBy", "top") );
        parameters.add( new BasicNameValuePair("apiKey", "2a4639109b424bd3970e2fdf00fa54de") );



        DataSource ds = new DataSource();
        ds.setScheme("https");
        ds.setHost("newsapi.org");
        ds.setPath("/v1/articles");
        ds.setParameters(parameters);

        DataCollection dc = new DataCollection(ds);
        dc.retrieveArticles();


    }

}
