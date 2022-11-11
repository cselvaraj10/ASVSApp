package asvsapp.igloo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

import asvsapp.amp.ampBestMatchdata;
import asvsapp.amp.getAmpBestMatch;
import asvsapp.csvWrite.csvWriteResults;
import comparator.iglooAmpCompare;

public class parseIglooData {
    public static iglooGoldenData iglooData = new iglooGoldenData();

    public static void praseJsonLine() {

        BufferedReader reader = null;
        String jsonFormattedString = null;

        try {

            Path pathToInputFile = Paths.get("app/src/main/resources/InputData/11072022.json");
            reader = Files.newBufferedReader(pathToInputFile, StandardCharsets.UTF_8);

            String line;

            csvWriteResults.csvWriterOpenAgain();;
            
            while ((line = reader.readLine()) != null) {
                try {
                    StringBuilder jsonStr = new StringBuilder(line);

                    replaceAll(jsonStr, "\\\"", "\"");

                    replaceAll(jsonStr, " \"", " ");
                    replaceAll(jsonStr, "\" ", " ");

                    replaceAll(jsonStr, "\"[", "{");

                    replaceAll(jsonStr, "]\"", "}");

                    jsonFormattedString = jsonStr.toString();

                    iglooGoldenData iglooData = new Gson().fromJson(jsonFormattedString, iglooGoldenData.class);

                    ampBestMatchdata iglooDataSearchTerm = getAmpBestMatch.getJsonBestMatch(iglooData.search_term);

                    // Function call passing 2 objects for compare
                    iglooAmpCompare.compareData(iglooData, iglooDataSearchTerm);


                } catch (Exception err) {
                    System.out.print(jsonFormattedString);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        finally {
            try {
                // Closing the resources
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void replaceAll(StringBuilder builder, String from, String to) {
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
    }

}
