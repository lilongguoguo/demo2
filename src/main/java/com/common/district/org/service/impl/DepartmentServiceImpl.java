package com.common.district.org.service.impl;

import com.common.district.common.SysUtil.RespData;
import com.common.district.org.dao.DepartmentMapper;
import com.common.district.org.model.Department;
import com.common.district.org.model.LoginUser;
import com.common.district.org.service.DepartmentService;
import com.common.district.org.vo.DepartmentVo;
import com.common.district.res.dao.DictsMapper;
import com.common.district.res.model.Dicts;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 组织框架
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final String Zero = "0";//代表顶级部门
    private static final Long DepartmentOfType = 4L;//部门类型
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DictsMapper dictsMapper;

    /**
     * 查询组织框架（查询所有部门，到顶级部门）
     *
     * @return
     */
    @Override
    public RespData getAllDepartment() {
        List<Department> listDepartment = departmentMapper.getAllDepartment();
        List<DepartmentVo> listDepartmentVo = Lists.newArrayList();//部门扩展实体类
        Map<String,String> concurrentMap = Maps.newConcurrentMap();//组装父类的名字和父类的id
        listDepartment.forEach(x -> {
            DepartmentVo departmentVo = new DepartmentVo();
            BeanUtils.copyProperties(x, departmentVo);
            listDepartmentVo.add(departmentVo);
            concurrentMap.put(x.getDeptId(),x.getDeptName());//key部门id ，value部门名字
        });
        //给每一个parentName赋值
        listDepartmentVo.forEach(x->{
            if(Zero.equals(x.getParentId())) {//顶级部门没有上级机构
                x.setParentName("");//把部门信息赋值给父的姓名
            }else{
                x.setParentName(concurrentMap.get(x.getParentId()));
            }
        });
        //获取组织框架树
        List<DepartmentVo> tree = makeTree(listDepartmentVo, Zero);
        //组装前端需要的返回的类型数据[id:1,label: '华润物业科技服务有限公司',children:[] ]，注意必须是以1开始

        return RespData.success(tree);
    }

    /**
     * 添加组织框架（添加部门）
     *
     * @return
     */
    @Override
    public RespData saveDepartment(Department department) {
        departmentMapper.insert(department);
        return RespData.success();
    }

    /**
     * 删除组织框架（逻辑删除部门并且停用）
     *
     * @return
     */
    @Override
    public RespData deleteDepartment(Department department) {
        //删除前，先查询部门下是否还有子类。（如果有，则不让删除）
        int count = departmentMapper.selectParentId(department.getDeptId());
        if (count>0){//则不删除
            return RespData.fail("此部门下还有子节点！");
        }
        departmentMapper.logicDeleteByPrimaryKey(department.getDeptId(), department.getDelUserId());
        return RespData.success();
    }

    /**
     * 查询全部部门类型
     *
     * @return
     */
    @Override
    public RespData getDepartmentOfType(String organId) {
        //根据部门类型和租户id来获取部门类型
        List<Dicts> dictsList = dictsMapper.selectByDepartmentOfType(DepartmentOfType, organId);
        return RespData.success(dictsList);
    }

    /**
     * 修改部门信息
     * @param department
     * @return
     */
    @Override
    public RespData revisionDepartment(Department department) {
        departmentMapper.updateByDepartment(department);
        return RespData.success();
    }

    /**
     * 获取组织框架树
     *
     * @param listDepartmentVo
     * @param pId
     * @return
     */
    private static List<DepartmentVo> makeTree(List<DepartmentVo> listDepartmentVo, String pId) {

        //子类
        List<DepartmentVo> children = listDepartmentVo.stream().filter(x -> pId.equals(x.getParentId())).collect(Collectors.toList());

        //后辈中的非子类
        List<DepartmentVo> successor = listDepartmentVo.stream().filter(x -> !pId.equals(x.getParentId())).collect(Collectors.toList());

        children.forEach(x ->
                {
                    makeTree(successor, x.getDeptId()).forEach(
                            y -> x.getChildren().add(y)
                    );
                }
        );
        return children;
    }


}
