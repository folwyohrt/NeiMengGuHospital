package com.system.service;

import com.system.entity.SysArea;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
public interface SysAreaService {
    SysArea get(int id);
    List<SysArea> getList();

}
