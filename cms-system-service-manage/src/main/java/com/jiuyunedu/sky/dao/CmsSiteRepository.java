package com.jiuyunedu.sky.dao;

import com.jiuyunedu.sky.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsSiteRepository extends MongoRepository<CmsSite, String> {
}
