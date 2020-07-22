package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.system.response.SysDictionaryResponse;

public interface SysDictionaryService {

    SysDictionaryResponse findByType(String typeCode);

}
