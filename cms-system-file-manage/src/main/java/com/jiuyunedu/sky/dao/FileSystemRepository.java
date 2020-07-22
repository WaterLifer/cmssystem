package com.jiuyunedu.sky.dao;

import com.jiuyunedu.sky.filesystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileSystemRepository extends MongoRepository<FileSystem, String> {
}
