package com.system.service;

import com.system.entity.SysSurgery;
import com.system.pojo.SysSurgeryDTO;
import com.system.pojo.SysSurgeryQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:45
 * @Description:
 */
public interface SysSurgeryService {
    SysSurgeryDTO getDTO(long id);

    SysSurgery get(long id);

    List<SysSurgeryDTO> getList();

    List<SysSurgeryDTO> getList(SysSurgeryQuery sysSurgeryQuery);

    boolean insert(SysSurgeryDTO createSysSurgeryInfo);

    boolean update(SysSurgeryDTO sysUserDTO);

    boolean update(SysSurgery sysUser);

    boolean delete(List<Long> idList);

}
