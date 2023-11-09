package com.example.common.config.elasticsearch;

import co.elastic.clients.elasticsearch.core.IndexResponse;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;

@Slf4j
public class ElasticsearchAsync implements ActionListener<IndexResponse> {
    @Override
    public void onResponse(IndexResponse indexResponse) {
        log.info("Save log elastic success!");
    }

    @Override
    public void onFailure(Exception e) {
        log.error("Log to elastic error", e);
    }
}
