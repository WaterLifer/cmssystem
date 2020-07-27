package com.jiuyunedu.sky.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.jiuyunedu.sky.bean.CoursePub;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.search.CourseSearchParam;
import com.jiuyunedu.sky.service.CoursePubService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursePubServiceImpl implements CoursePubService {

    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;
    @Value("${elasticsearch.index}")
    private String searchIndex;

    @Override
    public QueryResponseResult<CoursePub> search(String page, String size, CourseSearchParam courseSearchParam) {
        if (!NumberUtil.isNumber(page) || StringUtils.isEmpty(page)) {
            page = "0";
        }
        if (!NumberUtil.isNumber(size) || StringUtils.isEmpty(size)) {
            size = "10";
        }

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        // 一个个进行判断
        // 关键字搜索
        String keyword = courseSearchParam.getKeyword();
        if (StringUtils.isNotEmpty(keyword)) {
            // 匹配关键字
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
                    "name", "description", "teachplan");
            // 提升另一个字段的boost值
            multiMatchQueryBuilder.field("name", 10);
            booleanQueryBuilder.must(multiMatchQueryBuilder);
        }
        // 分类搜索：过滤
        String mt = courseSearchParam.getMt();
        if (StringUtils.isNotEmpty(mt)) {
            booleanQueryBuilder.filter(QueryBuilders.termQuery("mt", mt));
        }
        String st = courseSearchParam.getSt();
        if (StringUtils.isNotEmpty(st)) {
            booleanQueryBuilder.filter(QueryBuilders.termQuery("st", st));
        }
        String grade = courseSearchParam.getGrade();
        if (StringUtils.isNotEmpty(grade)) {
            booleanQueryBuilder.filter(QueryBuilders.termQuery("grade", grade));
        }
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(booleanQueryBuilder);
        queryBuilder.withPageable(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)));

        // 设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("name")
                .preTags("<em>")
                .postTags("</em>")
                .requireFieldMatch(true)
                .fragmentSize(Integer.MAX_VALUE)
                .numOfFragments(0);
        queryBuilder.withHighlightBuilder(highlightBuilder);

        SearchHits<CoursePub> coursePubSearchHits = elasticsearchTemplate.search(queryBuilder.build(), CoursePub.class, IndexCoordinates.of(searchIndex));
        List<CoursePub> coursePubs = coursePubSearchHits.get().map(SearchHit::getContent).collect(Collectors.toList());

        return new QueryResponseResult<>(CommonCode.SUCCESS, new QueryResult<>(coursePubs, coursePubSearchHits.getTotalHits()));
    }
}
