package com.jiuyunedu.sky.dao;

import com.jiuyunedu.sky.media.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaFileRepository extends MongoRepository<MediaFile, String> {
}
