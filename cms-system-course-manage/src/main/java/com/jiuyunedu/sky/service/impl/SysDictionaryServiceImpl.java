package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.course.response.CourseCode;
import com.jiuyunedu.sky.dao.SysDictionaryRepository;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.service.SysDictionaryService;
import com.jiuyunedu.sky.system.SysDictionary;
import com.jiuyunedu.sky.system.response.SysDictionaryResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    private final SysDictionaryRepository sysDictionaryRepository;

    @Autowired
    public SysDictionaryServiceImpl(SysDictionaryRepository sysDictionaryRepository) {
        this.sysDictionaryRepository = sysDictionaryRepository;
    }

    @Override
    public SysDictionaryResponse findByType(String typeCode) {
        if (StringUtils.isEmpty(typeCode)) {
            ExceptionCast.throwException(CourseCode.COURSE_DICTIONARY_CODE_ISNULL);
        }
        SysDictionary sysDictionary = sysDictionaryRepository.findByType(typeCode);
        if (sysDictionary == null) {
            ExceptionCast.throwException(CourseCode.COURSE_DICTIONARY_ISNULL);
        }
        return new SysDictionaryResponse(CommonCode.SUCCESS, sysDictionary);
    }
}
