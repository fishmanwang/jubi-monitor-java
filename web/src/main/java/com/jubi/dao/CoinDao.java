package com.jubi.dao;

import com.jubi.dao.entity.CoinEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
public interface CoinDao {

    List<CoinEntity> queryAll();

}
