package com.pang.book.dao.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;

/**
 * @author pang
 * @version V1.0
 * @ClassName: CommonDao
 * @Package com.pang.book.dao.config
 * @Description: ${添加描述}
 * @date 2019/1/2 19:12
 */
public class CommonDao extends SqlSessionDaoSupport {
    @Override
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
