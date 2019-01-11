package com.pang.book.controller.rest;

import com.pang.book.entity.Common;
import com.pang.book.entity.RestJson;
import com.pang.book.services.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: RestCommon
 * @Package com.pang.book.controller.rest
 * @description: Common的restful api
 * @date 2019/1/8 12:59
 */
@RestController
@Api(tags = "Common", description = "回复")
public class RestCommon {
    @Autowired
    private CommonService commonDao;

    /**
     * 根据留言查询回复
     *
     * @param id 留言id
     * @return com.pang.book.entity.RestJson<java.util.List       <       com.pang.book.entity.Common>>
     * @author pang
     * @date 2019/1/8
     */
    @ApiOperation(value = "根据留言查回复", notes = "根据留言id查询回复")
    @ApiImplicitParam(name = "id", value = "留言id", paramType = "path", dataType = "int", required = true)
    @RequestMapping(value = "common/{id}", method = RequestMethod.GET, produces = "application/json")
    public RestJson<List<Common>> getCommonByMessageId(@PathVariable int id) {
        RestJson<List<Common>> restJson = new RestJson<>();
        List<Common> commons = commonDao.findByMessageId(id);
        if (commons.size() > 0) {
            restJson.setData(commons).setSuccess(true).setStatus(200).setMsg("select commons by message");
        } else {
            restJson.setMsg("fount no common")
                    .setStatus(404)
                    .setSuccess(false);
        }
        return restJson;
    }

    /**
     * 查找所有回复
     *
     * @param
     * @return com.pang.book.entity.RestJson<java.util.List       <       com.pang.book.entity.Common>>
     * @author pang
     * @date 2019/1/8
     */
    @ApiOperation("查找所有回复")
    @RequestMapping(value = "common", method = RequestMethod.GET, produces = "application/json")
    public RestJson<List<Common>> getCommonByMessageId() {
        RestJson<List<Common>> restJson = new RestJson<>();
        List<Common> commons = commonDao.findAllCommon();
        if (commons.size() > 0) {
            restJson.setData(commons).setSuccess(true).setStatus(200).setMsg("select commons by message");
        } else {
            restJson.setMsg("fount no common")
                    .setStatus(404)
                    .setSuccess(false);
        }
        return restJson;
    }

    /**
     * 添加回复
     *
     * @param common
     * @return com.pang.book.entity.RestJson<com.pang.book.entity.Common>
     * @author pang
     * @date 2019/1/8
     */
    @ApiOperation("添加回复")
    @RequestMapping(value = "common", method = RequestMethod.POST, produces = "application/json")
    public RestJson<Common> insertCommon(Common common) {
        RestJson<Common> restJson = new RestJson<>();
        common.setCommonTime(new Date());
        /*common.setCommonFrom(((User)session.getAttribute("user")).getUserId());*/
        /* 开发环境，添加默认的sender */
        if (common.getCommonTo() != null && common.getCommonContent() != null) {
            commonDao.insert(common);
            restJson.setSuccess(true)
                    .setStatus(200)
                    .setMsg("insert one common")
                    .setData(common);
        } else {
            restJson.setMsg("insert fail")
                    .setStatus(404)
                    .setSuccess(false);
        }
        return restJson;
    }

    /**
     * 更新回复
     *
     * @param common 回复请求
     * @param id      要更改的id
     * @return com.pang.book.entity.RestJson<com.pang.book.entity.Common>
     * @author pang
     * @date 2019/1/8
     */
    @ApiOperation("更新回复")
    @ApiImplicitParam(name = "id",value = "回复id",required = true,paramType = "path",dataType = "int")
    @RequestMapping(value = "common/{id}", method = RequestMethod.PUT, produces = "application/json")
    public RestJson<Common> editCommon(@PathVariable int id, Common common) {
        Common commonTemp = commonDao.findById(id);
        RestJson<Common> restJson = new RestJson<>();
        if (commonTemp != null) {
            commonTemp.setCommonContent(common.getCommonContent());
            commonDao.update(commonTemp);
            restJson.setSuccess(true)
                    .setStatus(200)
                    .setMsg("update success")
                    .setData(commonTemp);
        } else {
            restJson.setMsg("update fail")
                    .setStatus(404)
                    .setSuccess(false);
        }
        return restJson;
    }

    /**
     * 删除回复
     *
     * @param id 要删除的回复id
     * @return com.pang.book.entity.RestJson<com.pang.book.entity.Common>
     * @author pang
     * @date 2019/1/8
     */
    @ApiOperation("删除回复")
    @ApiImplicitParam(name = "id",value = "回复id",required = true,dataType = "int",paramType = "path")
    @RequestMapping(value = "common/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public RestJson<Common> deleteCommon(@PathVariable int id) {
        Common commonTemp = commonDao.findById(id);
        RestJson<Common> restJson = new RestJson<>();
        if (commonTemp != null) {
            commonDao.deleteById(id);
            restJson.setSuccess(true)
                    .setStatus(200)
                    .setMsg("delete successly")
                    .setData(commonTemp);
        } else {
            restJson.setMsg("delete fail")
                    .setStatus(404)
                    .setSuccess(false);
        }
        return restJson;
    }
}
