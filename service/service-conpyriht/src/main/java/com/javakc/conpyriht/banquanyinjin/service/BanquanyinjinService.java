package com.javakc.conpyriht.banquanyinjin.service;

import com.javakc.conpyriht.banquanyinjin.dao.BanquanyinjinDao;
import com.javakc.conpyriht.banquanyinjin.entity.Banquanyinjin;
import com.javakc.conpyriht.banquanyinjin.vo.BanquanyinjinQuery;
import com.javakc.oss.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import jpa.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Progrom: javakc-parent
 * @ClassName: BanquanyinjinService
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/14 22:04
 */
@Service
public class BanquanyinjinService extends BaseService<BanquanyinjinDao,Banquanyinjin> {

    @Autowired
    private BanquanyinjinDao banquanyinjinDao;

    /**
     * 查询所以数据
     * @return
     */
    public List<Banquanyinjin> findAll() {
        List<Banquanyinjin> list = banquanyinjinDao.findAll();
        return list;
    }


    public Page<Banquanyinjin> pageBanquanyinjin(BanquanyinjinQuery banquanyinjinQuery,Integer pageNo,Integer pageSize){
/**
 * 可用操作符
 * = 等值 != 不等值 (字符串  数字)
 * >=  <=  <  (数字)
 * : 表示like  %v%
 * l:  表示 v%
 * :l  表示 %v
 * null  表示 is null
 * !null  表示 is not null
 */
        SimpleSpecificationBuilder<Banquanyinjin> simpleSpecificationBuilder = new SimpleSpecificationBuilder<>();
        if (!StringUtils.isEmpty(banquanyinjinQuery.getId())) {
            simpleSpecificationBuilder.and("id", ":", banquanyinjinQuery.getId());
        }
        if (!StringUtils.isEmpty(banquanyinjinQuery.getBanquanName())) {
            simpleSpecificationBuilder.and("banquanName", ":", banquanyinjinQuery.getBanquanName());
        }
        if (StringUtils.isEmpty(banquanyinjinQuery.getGongsiName())){
            simpleSpecificationBuilder.and("gongsiName",":",banquanyinjinQuery.getGongsiName());
        }
        if (StringUtils.isEmpty(banquanyinjinQuery.getBeizhu())){
            simpleSpecificationBuilder.and("beizhu",":",banquanyinjinQuery.getBeizhu());
        }

      Page page = banquanyinjinDao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo - 1, pageSize));
        return page;

    }
}




