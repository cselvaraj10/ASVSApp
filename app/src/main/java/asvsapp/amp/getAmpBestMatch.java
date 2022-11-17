package asvsapp.amp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import com.google.gson.Gson;

public class getAmpBestMatch {
    public static Object iglooData;
    public static ampBestMatchdata ampBMData = new ampBestMatchdata();

    public static ampBestMatchdata getJsonBestMatch(String search_term) {

        ampBestMatchdata returnObject = null;
        URI uri;

        try {

            String searchTermEncode = java.net.URLEncoder.encode(search_term, "UTF-8").replace("+", "%20");

            uri = new URI(
                    "https://us.api.iheart.com/api/v3/search/combined?keywords=" + searchTermEncode
                            + "&bestMatch=true&track=true&bundle=true&artist=true&station=true&keyword=true&playlist=true&podcast=true");

            HttpRequest response = HttpRequest.newBuilder(uri)
                    .setHeader("X-Search-Variant-ID", "searchVariantI")
                    .GET()
                    .build();

            String outputString = HttpClient.newHttpClient().send(response,
                    HttpResponse.BodyHandlers.ofString()).body();

            String json = outputString.toString();

            JSONObject mainObject = new JSONObject(json);
            JSONObject bestMatchSection = mainObject.getJSONObject("bestMatch");

            ampBestMatchdata ampBMData = new Gson().fromJson(bestMatchSection.toString(), ampBestMatchdata.class);

            returnObject = ampBMData;

        } catch (Exception e) {
            System.out.println("Could not get bestmatch section: ");
            System.out.println(e);
        }
        return returnObject;

    }

    public static void buildSsearchtermUrl(StringBuilder builder, String from, String to) {
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
    }

}
