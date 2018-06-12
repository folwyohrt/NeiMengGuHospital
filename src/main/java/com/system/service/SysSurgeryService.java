package com.system.service;

import com.system.pojo.SysSurgeryDTO;
import com.system.pojo.SysSurgeryQuery;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:45
 * @Description:
 */
public interface SysSurgeryService {
    SysSurgeryDTO get(long id);

    List<SysSurgeryDTO> getList();

    List<SysSurgeryDTO> getList(SysSurgeryQuery sysSurgeryQuery) throws ParseException;

    boolean insert(SysSurgeryDTO createSysSurgeryInfo) throws ParseException;

    boolean update(SysSurgeryDTO sysUserDTO) throws ParseException;

    boolean delete(List<Long> idList);

}
