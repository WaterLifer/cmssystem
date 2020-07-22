package com.jiuyunedu.sky.dao;

import com.jiuyunedu.sky.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author WaterLifer
 * @Date 2020/7/2 22:23
 * @Description 可以根据Repository的继承结构来决定使用哪一个Repository，由于需要分页，因此我们使用MongoRepository
 * @Version 1.0
 */
@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {

    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);
}
