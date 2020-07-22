package com.jiuyunedu.sky.dao;

import com.jiuyunedu.sky.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDictionaryRepository extends MongoRepository<SysDictionary, String> {

    SysDictionary findByType(String typeCode);

}
