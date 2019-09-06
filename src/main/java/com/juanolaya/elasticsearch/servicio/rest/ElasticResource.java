package com.juanolaya.elasticsearch.servicio.rest;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.juanolaya.elasticsearch.servicio.elastic.Indexer;
import com.juanolaya.elasticsearch.servicio.elastic.Search;
import com.juanolaya.elasticsearch.servicio.elastic.Searcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticResource {
    @RequestMapping("/")
    public String root() {
        return "<h1>Elasticsearch CICD is running!</h1>";
    }

    @RequestMapping("/health")
    public String health() {
        return "<h1>Elasticsearch CICD is running!</h1>";
    }

    @RequestMapping("/index")
    public String index(@RequestParam(value="docsPath") String docsPath) {
        getIndexer().index(docsPath);
        return "Documents have been successfully Indexed!";
    }

    @RequestMapping("/search")
    public List<Search> search(@RequestParam(value="query") String query) {
        return getSearcher().search(query);
    }

    private Searcher getSearcher()
    {
        return new Searcher();
    }

    private Indexer getIndexer() {
        return new Indexer();
    }
}
