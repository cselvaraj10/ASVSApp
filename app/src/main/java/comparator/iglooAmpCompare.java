package comparator;

import asvsapp.amp.ampBestMatchdata;
import asvsapp.csvWrite.csvWriteResults;
import asvsapp.igloo.iglooGoldenData;

public class iglooAmpCompare {

    static String SSR;
    static String QSR;

    public static void compareData(iglooGoldenData igloo, ampBestMatchdata amp) {

        processedDataResult processedDataResultObject = new processedDataResult();

        // SSR PASS / FAIL check "content_name & name / title / description",
        // "content_id & id / contentId", "content_Type & typename"
        if (igloo != null && igloo.ssr != null && igloo.qsr != null && amp != null) {

            processedDataResult.Igloo_Rank = igloo.rank;
            processedDataResult.Igloo_TotalOccurances = igloo.total_occurrences;
            processedDataResult.Igloo_SearchTerm = igloo.search_term;

            processedDataResult.Igloo_SSRContentName = igloo.ssr.content_name;
            processedDataResult.Igloo_SSRContentId = igloo.ssr.content_id;
            processedDataResult.Igloo_SSRContentType = igloo.ssr.content_type;

            processedDataResult.Igloo_QSRContentName = igloo.qsr.content_name;
            processedDataResult.Igloo_QSRContentId = igloo.qsr.content_id;
            processedDataResult.Igloo_QSRContentType = igloo.qsr.content_type;

            if (amp.name != null || amp.title != null || amp.description != null) {
                if (igloo.ssr.content_name.equals(amp.name)) {
                    processedDataResult.Amp_ContentName = amp.name;
                } else if (igloo.ssr.content_name.equals(amp.title)) {
                    processedDataResult.Amp_ContentName = amp.title;
                } else if (igloo.ssr.content_name.equals(amp.description)) {
                    processedDataResult.Amp_ContentName = amp.description;
                } else {
                    processedDataResult.Amp_ContentName = "Not Valid!";
                }
            }

            if (amp.contentId != null || amp.id != null) {
                if (igloo.ssr.content_id.equals(amp.contentId)) {
                    processedDataResult.Amp_ContentId = amp.contentId;
                } else if (igloo.ssr.content_id == amp.id) {
                    processedDataResult.Amp_ContentId = amp.id;
                } else {
                    processedDataResult.Amp_ContentId = null;
                }
            }

            if (amp.contentType != null || amp.typeName != null) {
                if (igloo.ssr.content_type.equals(amp.contentType)) {
                    processedDataResult.Amp_ContentType = amp.contentType;
                } else if (igloo.ssr.content_type.equalsIgnoreCase(amp.typeName)) {
                    processedDataResult.Amp_ContentType = amp.typeName;
                } else {
                    processedDataResult.Amp_ContentType = "Not Valid!";
                }
            }

            if (((igloo.ssr.content_name.equals(amp.name) || igloo.ssr.content_name.equals(amp.title)
                    || igloo.ssr.content_name.equals(amp.description))
                    && (igloo.ssr.content_id.equals(amp.contentId) || igloo.ssr.content_id == amp.id))
                    && (igloo.ssr.content_type.equals(amp.contentType)
                            || igloo.ssr.content_type.equalsIgnoreCase(amp.typeName))) {

                processedDataResult.SSR_Result = "PASS";
            } else {
                processedDataResult.SSR_Result = "FAIL";
            }

            /*
             * QSR PASS / FAIL check "content_name & name / title / description",
             * "content_id & id / contentId", "content_Type & typename"
             */

            if (((igloo.qsr.content_name.equals(amp.name) || igloo.qsr.content_name.equals(amp.title)
                    || igloo.qsr.content_name.equals(amp.description))
                    && (igloo.qsr.content_id.equals(amp.contentId) || igloo.qsr.content_id == amp.id))
                    && (igloo.qsr.content_type.equals(amp.contentType)
                            || igloo.qsr.content_type.equalsIgnoreCase(amp.typeName))) {

                processedDataResult.QSR_Result = "PASS";
            } else {
                processedDataResult.QSR_Result = "FAIL";
            }
        }

        csvWriteResults.writeResultDataIglooAmpSSRQSR(processedDataResultObject);

    }

}
