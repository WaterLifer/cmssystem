package com.jiuyunedu.sky.dao;

import com.jiuyunedu.sky.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsConfigRepository extends MongoRepository<CmsConfig, String> {
}
