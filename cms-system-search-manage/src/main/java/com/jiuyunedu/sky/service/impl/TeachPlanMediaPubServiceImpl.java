package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.bean.TeachplanMediaPub;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.service.TeachPlanMediaPubService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeachPlanMediaPubServiceImpl implements TeachPlanMediaPubService {

    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;
    @Value("${elasticsearch.media.index}")
    private String searchIndex;

    @Override
    public QueryResponseResult<TeachplanMediaPub> getMedia(Collection<String> teachplanIds) {
        // 根据id查询
        SearchHits<TeachplanMediaPub> teachplanMediaPubSearchHits = elasticsearchTemplate.search(
                new NativeSearchQueryBuilder().withQuery(QueryBuilders.termsQuery("teachplan_id", teachplanIds)).build(),
                TeachplanMediaPub.class,
                IndexCoordinates.of(searchIndex));
        List<TeachplanMediaPub> teachplanMediaPubs = teachplanMediaPubSearchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        return new QueryResponseResult<>(CommonCode.SUCCESS, new QueryResult<>(teachplanMediaPubs, teachplanMediaPubs.size()));
    }
}
