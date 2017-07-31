package com.jubi.service;

import com.jubi.dao.CoinDao;
import com.jubi.entity.CoinEntity;
import com.jubi.service.vo.CoinVo;
import com.jubi.util.BeanMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
@Service
public class CoinService {

    @Autowired
    private CoinDao coinDao;

    public List<CoinVo> getAllCoins() {
        List<CoinEntity> ds = coinDao.queryAll();
        return BeanMapperUtil.mapList(ds, CoinVo.class);
    }

}
