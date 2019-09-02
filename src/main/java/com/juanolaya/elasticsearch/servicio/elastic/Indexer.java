package com.juanolaya.elasticsearch.servicio.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class Indexer {

    public void index(String docsPath) {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        try{
            File docDir = new File(docsPath);

            if (docDir.canRead()) {
                if (docDir.isDirectory()) {
                    File[] docFiles = docDir.listFiles();
                    if (docFiles != null) {
                        long startTime = System.nanoTime();
                        for (File currentFile : docFiles) {

                            if (currentFile.getName().endsWith(".txt")) {
                                BufferedReader reader = new BufferedReader(new FileReader(currentFile));
                                StringBuilder text = new StringBuilder();
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    text.append(line);
                                }
                                reader.close();
                                XContentBuilder builder = XContentFactory.jsonBuilder();
                                builder.startObject();
                                {
                                    builder.field("title", currentFile.getName().split(".txt")[0]);
                                    builder.timeField("postDate", new Date());
                                    builder.field("content", text.toString());
                                }
                                builder.endObject();
                                IndexRequest indexRequest = new IndexRequest("elastic").source(builder);

                                client.index(indexRequest, RequestOptions.DEFAULT);
                            }
                        }
                    }
                }

                client.close();
            }
        } catch (IOException e) {
            System.out.println("Error Type: " + e.getClass() + "\n Message: " + e.getMessage());
        }
    }
}
