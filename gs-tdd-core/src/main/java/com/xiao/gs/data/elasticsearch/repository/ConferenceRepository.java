package com.xiao.gs.data.elasticsearch.repository;

import com.xiao.gs.data.elasticsearch.document.Conference;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ConferenceRepository extends ElasticsearchRepository<Conference, String> {
}
