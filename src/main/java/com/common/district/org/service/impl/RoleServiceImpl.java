package com.common.district.org.service.impl;

import com.common.district.common.SysUtil.RespData;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.dao.MenuMapper;
import com.common.district.org.dao.MenuRoleMapper;
import com.common.district.org.dao.RoleMapper;
import com.common.district.org.dao.UserMapper;
import com.common.district.org.model.Menu;
import com.common.district.org.model.MenuRole;
import com.common.district.org.model.Role;
import com.common.district.org.service.RoleService;
import com.common.district.org.vo.BusRoleVo;
import com.common.district.org.vo.RoleVo;
import com.common.district.org.vo.UserKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色设置信息
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Integer ONE = 1;
    private static final Long Zero = 0L;//代表顶级菜单
    private static final Integer Intzero = 0;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    /**
     * 查询角色信息
     */
    @Override
    public List<RoleVo> getAllRole(RoleVo vo) {
        //组装分页参数
        Integer cpVo = vo.getCp();
        vo.setCp((cpVo - ONE) * vo.getPs());
        vo.setPs(cpVo * vo.getPs());
        List<Role> roleList = roleMapper.selectAllRole(vo);
        List<RoleVo> roleVoList = Lists.newArrayList();//角色扩展实体类
        if (CollectionUtils.isEmpty(roleList)) {
            return roleVoList;//查出来是空的
        }
        roleList.forEach(x -> {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(x, roleVo);
            roleVoList.add(roleVo);
        });
        //去重，取所有的用户id（包括创建人id，更新人id，删除人id）
        Set<String> roleVoSet = Sets.newConcurrentHashSet();//去重后的集合
        roleVoList.forEach(x -> {
            if(StringUtils.isNotEmpty(x.getCreateUserId())){
                roleVoSet.add(x.getCreateUserId());//创建人id
            }
            if (StringUtils.isNotEmpty(x.getDelUserId())){
                roleVoSet.add(x.getDelUserId());//删除人id
            }
            if(StringUtils.isNotEmpty(x.getUpdateUserId())){
                roleVoSet.add(x.getUpdateUserId());//更新人id
            }
        });
        List<UserKey> UserKeymap = userMapper.getRoleVoUser(roleVoSet);//查询角色用户
        Map<String, String> map = new HashMap<String, String>();
        UserKeymap.forEach(x -> {//把实体类转化成map
            if (StringUtils.isNotEmpty(x.getUserId()) && StringUtils.isNotEmpty(x.getUserName())) {
                map.put(x.getUserId(), x.getUserName());
            }
        });
        roleVoList.forEach(x -> {
            if (StringUtils.isNotEmpty(x.getCreateUserId())) {
                x.setCreatName(map.get(x.getCreateUserId()));//创建人名
            }
            if (StringUtils.isNotEmpty(x.getUpdateUserId())) {
                x.setUpdateName(map.get(x.getUpdateUserId()));//更新人名
            }
            if (StringUtils.isNotEmpty(x.getDelUserId())) {
                x.setDelName(map.get(x.getDelUserId()));//删除人姓名
            }
            if (StringUtils.isEmpty(x.getUpdateName())) {
                x.setUpdateTime(null);//如果没有更新人，那么更新时间至为空
            }
            if (null != x.getStatus()) {
                x.setStatusName(x.getStatus() == 1 ? "启用" : "禁用");
            }
        });
        return roleVoList;
    }

    /**
     * 分页统计（查询角色列表）
     *
     * @return
     */
    @Override
    public Integer countRole(RoleVo vo) {
        return roleMapper.selectAllRoleCount(vo);
    }

    /**
     * 查询角色设置配置（添加或修改查询）
     * 步骤：
     * 1.判断页面传过来是新增角色设置，还是修改角色设置
     * 2.如果是新增角色设置，则遍历菜单功能表回显页面
     * 3.如果是修改，则回显菜单并展示哪些菜单已勾选
     * vo.getOnOff();true为新增,false为修改
     */
    @Override
    public RespData characterSet(BusRoleVo vo) {
        List<BusRoleVo> busRoleVos = null;
        if (vo.getOnOff()) {
            busRoleVos = getTree(vo);
        } else {
            //修改查询
            if (StringUtils.isNotEmpty(vo.getRoleId())) {//角色id不为空
                busRoleVos = getTree(vo);
                Role role = roleMapper.selectByPrimaryKey(vo.getRoleId());
                String dataRange = menuRoleMapper.selectByDataRange(vo.getRoleId());//数据范围
                //组装角色姓名和角色状态
                busRoleVos.get(Intzero).setRoleName(role.getRoleName());//角色姓名
                busRoleVos.get(Intzero).setStatus(role.getStatus());//角色状态
                busRoleVos.get(Intzero).setAccessRange("集团".equals(dataRange) ? 0 : "地区".equals(dataRange) ? 1 : 2);
            }
        }
        return RespData.success(busRoleVos);
    }

    //查询树
    private List<BusRoleVo> getTree(BusRoleVo vo) {
        List<Long> list = Lists.newArrayList();
        List<Menu> menuList = menuMapper.getAllMenu(vo.getOrganId());//查询是排序的
        List<Menu> functionList = menuMapper.getMenuFunction(vo.getOrganId(), Intzero, 4);//查询功能（合同审批，合同查看，等）'菜单等级(1、2、3、4)'
        List<BusRoleVo> busRoleVoList = Lists.newArrayList();
        List<Menu> functionByRoleList = Lists.newArrayList();
        if (!vo.getOnOff()) {//修改(开关)
            if (StringUtils.isNotEmpty(vo.getRoleId())) {
                //根据角色id查询角色菜单中间表
                list = menuRoleMapper.selectByRoleId(vo.getRoleId(), vo.getOrganId());//集合里面放的是菜单功能表的主键(element-ui组件，只返回三级)
                functionByRoleList = menuMapper.getMenuFunctionRole(vo.getRoleId(), vo.getOrganId(),4);//根据角色查询出关联的功能
            }
        }
        List<Long> finalList = list;//必须这样（不可变，不然编译不过 ）
        List<Menu> finalFunctionByRoleList = functionByRoleList;
        menuList.forEach(x -> {
            BusRoleVo busRoleVo = new BusRoleVo();
            BeanUtils.copyProperties(x, busRoleVo);
            busRoleVo.setExpandedNode(finalList);
            busRoleVo.setDevices(functionList);
            busRoleVo.setCheckedDevices(finalFunctionByRoleList);
            busRoleVoList.add(busRoleVo);
        });
        List<BusRoleVo> busRoleVos = makeTree(busRoleVoList, Zero);//获取菜单功能树
        return busRoleVos;
    }

    /**
     * 添加或者修改角色菜单功能
     * 步骤：
     * 1.先判断是修改还是添加
     * 2.如果是添加，需给角色表，菜单功能表，角色菜单功能中间表添加信息。
     * 3.如果是修改，修改角色，菜单功能表，角色菜单功能中间表信息
     *
     * @param vo
     * @return
     */
    @Override
    public RespData appendCharacterSet(BusRoleVo vo) {
        //vo.getOnOff();true为新增,false为修改
        if (vo.getOnOff()) {
            if (StringUtils.isEmpty(vo.getRoleName())) {
                return RespData.fail("修改角色姓名为空");
            }
            if (null == vo.getAccessRange()) {
                return RespData.fail("修改角色数据范围为空");
            }
            if (CollectionUtils.isEmpty(vo.getCheckedNode())) {
                return RespData.fail("修改角色菜单为空");
            }
            //检查参数和组装参数
            Role role = new Role();
            role.setRoleId(UUID.randomUUID().toString());//主键
            vo.setRoleId(role.getRoleId());//设置roleid
            role.setRoleName(vo.getRoleName());//角色名
            role.setCreateTime(new Date());//创建日期
            role.setCreateUserId(vo.getCreateUserId());//创建人
            role.setOriginId(vo.getOrganId());//租户id
            role.setRoleDesc("角色描述");
            role.setStatus(ONE);//启用
            role.setDel(Intzero);//是否删除 0否 1是
            role.setRoleType(vo.getAccessRange());//角色类型，分为集团0，地区1，个人2
            roleMapper.insertSelective(role);
            addRoleAndMenu(vo);
            return RespData.success();
        } else {
            //修改的是先修改角色表，再物理删除角色用户中间表，在添加。
            if (StringUtils.isEmpty(vo.getRoleId())) {
                return RespData.fail("修改角色id为空");
            }
            if (StringUtils.isEmpty(vo.getRoleName())) {
                return RespData.fail("修改角色姓名为空");
            }
            if (null == vo.getAccessRange()) {
                return RespData.fail("修改角色数据范围为空");
            }
            if (null == vo.getStatus()) {
                return RespData.fail("修改角色状态为空");
            }
            if (CollectionUtils.isEmpty(vo.getCheckedNode())) {
                return RespData.fail("修改角色菜单为空");
            }
            Role role = new Role();
            role.setRoleId(vo.getRoleId());//角色id
            role.setRoleName(vo.getRoleName());//角色姓名
            role.setOriginId(vo.getOrganId());//租户id
            role.setStatus(vo.getStatus());//角色状态
            role.setUpdateUserId(vo.getCreateUserId());//修改人
            role.setUpdateTime(new Date());//修改时间
            role.setRoleType(vo.getAccessRange());//角色类型（数据范围也就是角色类型）
            roleMapper.updateByPrimaryKeySelective(role);//修改角色
            menuRoleMapper.deleteByRoleId(vo.getRoleId());//角色菜单功能表，物理删除（根据角色id）
            addRoleAndMenu(vo);//新增角色菜单中间表信息
            menuRoleMapper.updateStatus(vo.getRoleId(), vo.getStatus());//根据角色id修改角色菜单的状态的有效和无效
            return RespData.success();
        }
    }

    /*  新增角色菜单功能中间表信息
     *  判断menuRoleList集合里面的菜单等级是否缺少。
     * （element组件el-tree复选框的时候，如果你某一个二级菜单下的三级没有全选，那么你这个二级也就没有选择）
     *  这个时候你角色和菜单就少了。
     *  如果vo.getCheckedNode()里面有二级菜单，则需要把这个二级菜单下的子级全部关联。（因为element组件，当你只勾选二级的时候，那么数组里只有二级的MenuId）
     */
    private void addRoleAndMenu(BusRoleVo vo) {
        List<Menu> menuList = menuMapper.getAllMenu(vo.getOrganId());//根据租户id查询所有菜单
        List<Menu> AllMenu = menuMapper.getAll(vo.getOrganId());
        Map<Long, String> map = Maps.newConcurrentMap();//组装菜单id和菜单code
        Map<Long, Long> longLongMap = Maps.newConcurrentMap();//组装菜单id和父级菜单id
        Map<Long, Long> parentMap = Maps.newConcurrentMap();//组装key父级菜单id和value菜单id
        Map<Long, Integer> longIntegerMap = Maps.newConcurrentMap();//key:主键，value：等级
        Map<String,String> stringStringMap =Maps.newConcurrentMap();//key:菜单姓名，value：菜单code
        Set<Long> longs = Sets.newConcurrentHashSet();//去重
        AllMenu.forEach(x->{
            stringStringMap.put(x.getMenuName(),x.getMenuCode());
        });
        menuList.forEach(x -> {
            map.put(x.getMenuId(), x.getMenuCode());
            longLongMap.put(x.getMenuId(), x.getParentId());
            longIntegerMap.put(x.getMenuId(), x.getMenuLevel());
            parentMap.put(x.getParentId(), x.getMenuId());
        });
        if (!vo.getCheckedNode().isEmpty()) {
            /*
             *  判断menuRoleList集合里面的菜单等级是否缺少。
             * （element组件el-tree复选框的时候，如果你某一个二级菜单下的三级没有全选，那么你这个二级也就没有选择）
             *  这个时候你角色和菜单就少了。
             *  如果vo.getCheckedNode()里面有二级菜单，则需要把这个二级菜单下的子级全部关联。（因为element组件，当你只勾选二级的时候，那么数组里只有二级的MenuId）
             */
            longs.addAll(vo.getCheckedNode());//去重
            longs.forEach(x -> {
                if (2 == longIntegerMap.get(x)) {//页面勾选的里面有二级的，则证明子级都有
                    menuList.forEach(y -> {
                        if (y.getParentId() == x) {
                            longs.add(y.getMenuId());
                        }
                    });
                }
            });
            longs.forEach(x -> {
                longs.add(longLongMap.get(x));//根据子节点找到父节点，并去重
            });
            longs.add(parentMap.get(Zero));//添加顶级节点
            longs.forEach(x->{
                if (StringUtils.isEmpty(map.get(x))) {//获取code为空，则需要在set中删除
                    longs.remove(x);
                }
            });
            String dataRange = vo.getAccessRange() == Intzero ? "集团" : (vo.getAccessRange() == ONE ? "地区" : "个人");
            List<MenuRole> menuRoleList = Lists.newArrayList();
            longs.forEach(x -> {
                MenuRole menuRole = new MenuRole();
                menuRole.setCreateTime(new Date());//创建日期
                menuRole.setCreateUserId(vo.getCreateUserId());//创建人id
                menuRole.setMenuCode(map.get(x));//菜单代码
                menuRole.setRoleId(vo.getRoleId());//角色id
                menuRole.setStatus(ONE);//状态 0无效1有效
                menuRole.setOrganId(vo.getOrganId());//租户id
                menuRole.setDataRange(dataRange);//数据范围
                menuRoleList.add(menuRole);
            });
            if (!CollectionUtils.isEmpty(vo.getCheckedDevicesString())){ //功能插入
                vo.getCheckedDevicesString().forEach(x->{
                   MenuRole menuRole = new MenuRole();
                   menuRole.setCreateTime(new Date());//创建日期
                   menuRole.setCreateUserId(vo.getCreateUserId());//创建人id
                   menuRole.setMenuCode(stringStringMap.get(x));//菜单代码
                   menuRole.setRoleId(vo.getRoleId());//角色id
                   menuRole.setStatus(ONE);//状态 0无效1有效
                   menuRole.setOrganId(vo.getOrganId());//租户id
                   menuRole.setDataRange(dataRange);//数据范围
                   menuRoleList.add(menuRole);
               });
            }
            menuRoleMapper.insertList(menuRoleList);
        }
    }


    /**
     * 获取菜单功能树
     *
     * @param menuList
     * @return
     */
    private List<BusRoleVo> makeTree(List<BusRoleVo> menuList, Long pId) {

        //子类
        List<BusRoleVo> children = menuList.stream().filter(x -> pId == x.getParentId()).collect(Collectors.toList());

        //后辈中的非子类
        List<BusRoleVo> successor = menuList.stream().filter(x -> pId != x.getParentId()).collect(Collectors.toList());

        children.forEach(x ->
                {
                    makeTree(successor, x.getMenuId()).forEach(
                            y -> x.getChildren().add(y)
                    );
                }
        );
        return children;
    }


}