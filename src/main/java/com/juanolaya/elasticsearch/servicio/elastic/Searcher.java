package com.juanolaya.elasticsearch.servicio.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Searcher {

    private final String ELASTIC_SERVER = System.getenv("ELASTIC_SERVER");
    private final String ELASTIC_SERVER_PORT = System.getenv("ELASTIC_SERVER_PORT");

    public List<Search> search(String query)
    {
        String documentUser = "NO_USER";
        String documentDate = "NO_DATE";
        String contentFragment = "NO_CONTENT";
        List<Search> allSearches = new ArrayList<>();

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                    new HttpHost(ELASTIC_SERVER, Integer.parseInt(ELASTIC_SERVER_PORT), "http")));
        try{
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("content", query);

            HighlightBuilder highlightBuilder = new HighlightBuilder();
            HighlightBuilder.Field highlightContent = new HighlightBuilder.Field("content");
            highlightContent.highlighterType("unified");
            highlightBuilder.field(highlightContent);
            highlightBuilder.preTags("<b>");
            highlightBuilder.postTags("</b>");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.highlighter(highlightBuilder);
            searchSourceBuilder.query(matchQueryBuilder);

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            for (SearchHit hit : searchResponse.getHits()) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();

                documentUser = (String) sourceAsMap.get("title");
                documentDate = (String) sourceAsMap.get("postDate");

                HighlightField highlight = highlightFields.get("content");
                Text[] fragments = highlight.fragments();
                contentFragment = fragments[0].string();

                allSearches.add(new Search(documentUser, documentDate, contentFragment));
            }
            client.close();
        } catch (IOException e) {
            System.out.println("Error Type: " + e.getClass() + "\n Message: " + e.getMessage());
        }

        return allSearches;
    }
}
