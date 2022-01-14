package com.javakc.conpyriht.banquanyinjin.service;

import com.javakc.conpyriht.banquanyinjin.dao.BanquanyinjinDao;
import com.javakc.conpyriht.banquanyinjin.entity.Banquanyinjin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Progrom: javakc-parent
 * @ClassName: BanquanyinjinService
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/14 22:04
 */
@Service
public class BanquanyinjinService {

    @Autowired
    private BanquanyinjinDao banquanyinjinDao;
    public List<Banquanyinjin> findAll() {
        List<Banquanyinjin> list = banquanyinjinDao.findAll();
        return list;
//        return null;

    }
}
