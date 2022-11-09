package comparator;

import asvsapp.amp.ampBestMatchdata;
import asvsapp.igloo.iglooGoldenData;

public class iglooAmpCompare {

    public static void compareData(iglooGoldenData igloo, ampBestMatchdata amp) {

        // SSR PASS / FAIL check "content_name & name / title / description",
        // "content_id & id / contentId", "content_Type & typename"

        if (igloo !=null && igloo.ssr !=null && amp != null) {

            if(igloo.ssr.content_name.equals(amp.name)||igloo.ssr.content_name.equals(amp.title))
            {
                System.out.println("SSR: Match");
            }
            else
            System.out.println("SSR: No Match");

            if(igloo.ssr.content_id == amp.contentId||igloo.ssr.content_id == amp.contentId)
            {
                System.out.println("SSR: Match");
            }
            else
            System.out.println("SSR: No Match");

            if(igloo.ssr.content_type.equals(amp.contentType)||igloo.ssr.content_type.equals(amp.typeName))
            {
                System.out.println("SSR: Match");
            }
            else
            System.out.println("SSR: No Match");
        } 
        

        // QSR PASS / FAIL check "content_name & name / title / description",
        // "content_id & id / contentId", "content_Type & typename"

        if (igloo !=null && igloo.qsr !=null && amp != null) {

            if(igloo.qsr.content_name.equals(amp.name)||igloo.qsr.content_name.equals(amp.title))
            {
                System.out.println("QSR: Match");
            }
            else
            System.out.println("QSR: No Match");

            if(igloo.qsr.content_id == amp.contentId||igloo.qsr.content_id == amp.contentId)
            {
                System.out.println("QSR: Match");
            }
            else
            System.out.println("QSR: No Match");

            if(igloo.qsr.content_type.equals(amp.contentType)||igloo.qsr.content_type.equals(amp.typeName))
            {
                System.out.println("QSR: Match");
            }
            else
            System.out.println("QSR: No Match");
        } 

        

    }

}
