package com.jubi.dao;

import com.jubi.dao.vo.CoinOrderNumVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */
public class CoinOrderDaoTest extends BaseDaoTest {

    @Autowired
    private CoinOrderExtDao coinOrderExtDao;

    @Test
    public void testQueryStatistic() {
        String coin = "xas";
        int time = 1502211941;
        boolean plus = true;

        CoinOrderNumVo vo = coinOrderExtDao.queryStatistic(coin, time, plus);
        assertNotNull(vo);
    }

}
