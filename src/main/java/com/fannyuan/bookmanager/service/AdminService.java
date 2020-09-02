package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.dao.AdminDao;
import com.fannyuan.bookmanager.model.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;

    public Admin selectAdminByUid(int uid) {
        return adminDao.selectByUid(uid);
    }
}
