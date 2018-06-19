package com.system.facade;

/**
 *
 */
public interface SysHospitalizationVisitStatusService {
    /**
     * 更新住院表探视状态，并增加/修改log
     * @param id
     * @param status
     */
    void updateHospitalizationVisitStatus(Long id, Integer status);
}
