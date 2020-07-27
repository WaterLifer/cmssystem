package com.jiuyunedu.sky;

import com.jiuyunedu.sky.bean.CoursePub;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.search.CourseSearchParam;
import com.jiuyunedu.sky.service.CoursePubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchTest {

    @Autowired
    private CoursePubService coursePubService;

    @Test
    public void testSearch() {
        CourseSearchParam courseSearchParam = new CourseSearchParam();
        courseSearchParam.setKeyword("Redis项目");
         //courseSearchParam.setMt("1-1");

        QueryResponseResult<CoursePub> responseResult = coursePubService.search("0", "2", courseSearchParam);
        if (responseResult.isSuccess()) {
            System.out.println(responseResult.getQueryResult().getList().size());
            responseResult.getQueryResult().getList().forEach(System.out::println);
        }
    }
}
