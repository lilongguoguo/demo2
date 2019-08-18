package com.common.district.res.service;

import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.org.model.LoginUser;
import com.common.district.res.model.Position;
import com.common.district.res.model.Project;
import com.common.district.res.vo.PositionVO;
import com.common.district.res.vo.ProjectVo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ProjectService {
    /**
     * 根据租户id和公司id集合获取项目信息集合
     * @param companyList 可空
     * @param organId 租户id
     * @auther fc
     * @return
     */
    List<Project> getProjectListByOrganIdAndConLst(@Param("comLst") List<String> companyList, @Param("organId")String organId);
    /**
     * @author tanwei
     * @description 项目列表查询
     *
     * @date 2019/8/12 16:10
     * @param
     * @return
     */
    PageInfo<ProjectVo> selectPageList(ProjectVo projectVo);
    /**
     * @author tanwei
     * @description 导出项目
     * @date 2019/8/12 16:10
     * @param
     * @return
     */
    void projectExport(HttpServletResponse response, ProjectVo projectVo) throws Exception;
    /**
     * @author tanwei
     * @description 删除项目信息
     * @date 2019/8/12 16:09
     * @param
     * @return
     */
    RespData deleteProject(LoginUser loginUser, String projectId) throws Exception;
    /**
     * @author tanwei
     * @description 新增项目时判重
     * @date 2019/8/13 11:04
     * @param
     * @return
     */
    //boolean checkProject(String organId, String projectName) throws Exception;
    /**
     * @author tanwei
     * @description 修改 新增 项目
     * @date 2019/8/13 14:18
     * @param
     * @return
     */
    RespData saveProject(Project project) throws Exception;

    RespData getProjectDetail(String projectId) throws Exception;
}
