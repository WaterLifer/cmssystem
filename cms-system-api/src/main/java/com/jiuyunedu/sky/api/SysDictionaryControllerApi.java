package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.system.response.SysDictionaryResponse;

public interface SysDictionaryControllerApi {

    SysDictionaryResponse findByType(String typeCode);

}
