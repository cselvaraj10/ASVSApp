/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package asvsapp;

import asvsapp.csvWrite.csvWriteResults;

public class app {

    public static void main(String[] args) {

        csvWriteResults.writeResultDataHeader();
        csvWriteResults.csvWriterClose();

        asvsapp.igloo.parseIglooData.praseJsonLine();

        csvWriteResults.csvWriterClose();

    }
}
