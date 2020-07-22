package com.jiuyunedu.sky.dao;

import com.jiuyunedu.sky.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate, String> {

}
