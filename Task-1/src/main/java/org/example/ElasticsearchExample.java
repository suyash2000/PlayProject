package org.example;



import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;

public class ElasticsearchExample {

    private static RestHighLevelClient client;

    public static void main(String[] args) throws IOException {
        // Create a client to connect to the Elasticsearch cluster
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // Index a document
        IndexRequest request = new IndexRequest("myindex", "_doc", "2")
                .source("id", "2", "name", "yash");
        System.out.println("Data Inserted");
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        // Check if the document was indexed successfully
        if (response.status() == RestStatus.CREATED) {
            System.out.println("Document indexed successfully");
        }

        // Get the document
        GetRequest getRequest = new GetRequest("myindex", "_doc", "1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        // Print the document
        System.out.println(getResponse.getSourceAsString());

        // Close the client
        client.close();
    }
}