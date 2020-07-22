package com.jiuyunedu.sky.dao;

import com.jiuyunedu.sky.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {
}
