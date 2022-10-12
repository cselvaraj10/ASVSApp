# AlgoliaSearchValidationSystem
Automated Results Validation

How might we see how search is performing for top terms against a maintainable golden dataset of expected results?
Daily prod testing proposal:

    Export top 1,000 search queries from Igloo for past 24 hours with query term and top chosen result

    Compare to current API output for bestMatch

    Mark each as success or failure or missing definition for expected result

    Generate report displaying results and record daily accuracy score

    Graph trended daily accuracy score for past 90 days

Pre-prod testing proposal:

    Same as daily prod but configurable by nonprod/prod algolia index, count, country, boostMarketId, source (golden dataset, custom CSV)

    Ability to manually run and see results

Automated Data Delivery Validation

How might we build an automated test to ensure catalog data is getting to Algolia and back to us accurately?

    Engineering will have monitoring/alerts on the processes themselves. 

    Do we need a podcast/station/etc that changes daily to confirm results have been updated?

Resources

Igloo Search Data Export (updated by Data Engineering daily)

    Stored in S3: s3://ihr-de-stg-prod/search_golden_dataset

    Access granted to TE Group and Digital-Support-Prod roles in AWS

    Existing selection_by_search_term and streamed_top_content don’t provide the data needed for these automation tests

    Proposed new logic:

        top_search_terms (top search terms from Igloo search table, limit:1000, ordered by rank)

            rank (1)

            search_term (country)

            SSR - result clicked from search

                content_type (link)

                content_id (70)

                content_name (Country Directory)

                occurrences (1000)

            QSR - at least 30 seconds of listening

                content_type (live)

                content_id (4418)

                content_name (iHeartCountry Radio)

                occurrences (100)

            Example:

{“rank”:1,”search_term”:”country”,”ssr”:[“content_type”:”link”,”content_id”:”70”,”content_name”:”Country Directory”,”occurrences”:1000],”qsr”:[“content_type”:”live”,”content_id”:”4418”,”content_name”:”iHeartCountry Radio”,”occurrences”:100]}
{“rank”:2,”search_term”:”80s”,”ssr”:[“content_type”:”link”,”content_id”:”121212”,”content_name”:”Country Directory”,”occurrences”:1000],”qsr”:[“content_type”:”live”,”content_id”:”4418”,”content_name”:”iHeartCountry Radio”,”occurrences”:90]}
{“rank”:3,”search_term”:”90”,”ssr”:[“content_type”:”link”,”content_id”:”121212”,”content_name”:”Country Directory”,”occurrences”:1000],”qsr”:[“content_type”:”live”,”content_id”:”4418”,”content_name”:”iHeartCountry Radio”,”occurrences”:89]}
