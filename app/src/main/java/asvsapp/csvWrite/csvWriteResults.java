package asvsapp.csvWrite;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.opencsv.CSVWriter;

import comparator.processedDataResult;

public class csvWriteResults {

    protected static final String resultsFolder = "app/src/main/resources/results";
    static CSVWriter csvWrite;

    static String date = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

    static String resultsFileName = resultsFolder + "/" + date + "_" + "ASVSTestResults.csv";

    public static void writeResultDataHeader() {

        try {
            csvWrite = new CSVWriter(new FileWriter(resultsFileName));
            String[] entries = { "Igloo-Rank", "Igloo-TotalOccurences", "Igloo-Search_Term", "Igloo-SSR-content_name",
                    "Igloo-SSR-content_id", "Igloo-SSR-content_type",
                    "Igloo-QSR-content_name", "Igloo-QSR-content_id", "Igloo-QSR-content_type",
                    "Amp-content_name", "Amp-content_id", "Amp-content_type", "SSR PASS/FAIL",
                    "QSR PASS/FAIL" };

            csvWrite.writeNext(entries);
        } catch (IOException e) {
            System.out.println("CSV Write Failed!! ");
            e.printStackTrace();
        }

    }

    public static void writeResultDataIglooAmpSSRQSR(processedDataResult result) {

        String[] results = { Integer.toString(result.Igloo_Rank), Integer.toString(result.Igloo_TotalOccurances),
                result.Igloo_SearchTerm,
                result.Igloo_SSRContentName, Integer.toString(result.Igloo_SSRContentId), result.Igloo_SSRContentType,
                result.Igloo_QSRContentName, Integer.toString(result.Igloo_QSRContentId), result.Igloo_QSRContentType,
                result.Amp_ContentName, Integer.toString(result.Amp_ContentId), result.Amp_ContentType,
                result.SSR_Result,
                result.QSR_Result };

        csvWrite.writeNext(results);

    }

    public static void csvWriterOpenAgain() {

        String csv = resultsFileName;
        try {
            csvWrite = new CSVWriter(new FileWriter(csv, true));
        } catch (IOException e) {
            System.out.println("CSV Write Append Failed!! ");
            e.printStackTrace();
        }

    }

    public static void csvWriterClose() {
        try {
            csvWrite.close();
        } catch (IOException e) {
            System.out.println("CSV Close Failed!! ");
            e.printStackTrace();
        }
    }

}
