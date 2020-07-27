package com.jiuyunedu.sky.test;

import com.jiuyunedu.sky.dao.TeachPlanMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeachPlanTest {

    @Autowired
    private TeachPlanMapper teachPlanMapper;

    @Test
    public void testTeachPlan() {
        teachPlanMapper.getTeachPlanNodeByCourseId("4028e58161bd3b380161bd3bcd2f0000");
    }
}
