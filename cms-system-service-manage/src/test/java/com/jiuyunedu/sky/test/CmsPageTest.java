package com.jiuyunedu.sky.test;

import com.jiuyunedu.sky.service.ICmsPageService;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class CmsPageTest {

    @Autowired
    private GridFsOperations gridFsOperations;

    @Test
    public void testInsert() throws IOException {

        Resource file = new FileSystemResource("E:\\workspace\\intellijIDE\\cmssystem\\cms-system-template\\src\\main\\resources\\templates\\course_main_template.html");

        gridFsOperations.store(file.getInputStream(), "course_main_template.html");
    }


    @Test
    public void testRead() throws IOException {

        GridFSFile gridFSFile = gridFsOperations.findOne(Query.query(GridFsCriteria.where("_id").is("5f0870ffdb65d5455969cdd5")));

        assert gridFSFile != null;
        GridFsResource resource = gridFsOperations.getResource(gridFSFile);

        String htmlCode = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);

        System.out.println(htmlCode);
    }
}
