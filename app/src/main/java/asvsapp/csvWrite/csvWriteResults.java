package asvsapp.csvWrite;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class csvWriteResults {
    public static void writeResultData() throws IOException {

CSVWriter csvWrite = new CSVWriter(new FileWriter("app/src/main/resources/results/results.csv"));
 String[] entries = {"eid","name","vid","emp_id","balance_amt","handover_to","entry_date"};
//  csvWrite.writeNext(entries);
csvWrite.writeNext(entries);
    
}
}
