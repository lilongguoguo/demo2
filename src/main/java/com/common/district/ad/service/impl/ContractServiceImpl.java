package com.common.district.ad.service.impl;

import com.common.district.ad.dao.*;
import com.common.district.ad.model.*;
import com.common.district.ad.service.ContractService;
import com.common.district.ad.vo.*;
import com.common.district.common.Constant;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.constants.CodeTypeEnum;
import com.common.district.common.constants.ContractStatusEnum;
import com.common.district.common.constants.ContractViolateEnum;
import com.common.district.common.utils.CodeGeneratorUtils;
import com.common.district.common.utils.DateUtil;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.dao.DepartmentMapper;
import com.common.district.org.dao.UserMapper;
import com.common.district.org.model.Department;
import com.common.district.org.model.LoginUser;
import com.common.district.org.model.User;
import com.common.district.res.dao.DictsMapper;
import com.common.district.res.dao.PositionMapper;
import com.common.district.res.dao.ResourceTypeMapper;
import com.common.district.res.model.Dicts;
import com.common.district.res.model.Position;
import com.common.district.res.model.ResourceType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ContractServiceImpl implements ContractService {

    private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private ContractDetailMapper contractDetailMapper;
    @Autowired
    private ContractDetailPointMapper contractDetailPointMapper;
    @Autowired
    private ContractAttachMapper contractAttachMapper;
    @Autowired
    private ContractEnergyPlanMapper contractEnergyPlanMapper;
    @Autowired
    private ContractDepositPlanMapper contractDepositPlanMapper;
    @Autowired
    private ContractIncomeSetMapper contractIncomeSetMapper;
    @Autowired
    private DictsMapper dictsMapper;
    @Autowired
    private ResourceTypeMapper resourceTypeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContractCostDictsMapper contractCostDictsMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public RespData<ContractVO> getContract(Long contId, LoginUser loginUser) {
        ContractVO contractVo = new ContractVO();
        // 获取合同主体信息
        Contract contract = contractMapper.selectByPrimaryKey(contId);
        BeanUtils.copyProperties(contract, contractVo);
        // 码表转化
        setContractVOName(contractVo, loginUser.getOriginId());
        //如果原合同id不为空，则查询原合同名称
        if (contract.getOgContId() > 0) {
            Contract ogContract = contractMapper.selectByPrimaryKey(contract.getOgContId());
            contractVo.setOgContName(ogContract.getContName());
            contractVo.setOgContStartTime(ogContract.getContStartTime());
            contractVo.setOgContEndTime(ogContract.getContEndTime());
        }
        //2. 查询投放明细旧合同明细
        List<ContractDetail> contractDetails = contractDetailMapper.getContractDetailByContId(contId, loginUser.getOriginId());
        //3. 查询合同附件
        List<ContractAttach> contractAttaches = contractAttachMapper.getAttachListByContId(contId);
        contractVo.setContractDetailVoList(copyPropertiesForListNoTransform(contractDetails, ContractDetailVO.class));
        contractVo.setContractAttachVoList(contractAttaches);
        List<ContractDetailPoint> contractDetailPoints = contractDetailPointMapper.contractDetailPointByContId(contId);
        if (!CollectionUtils.isEmpty(contractDetailPoints)) {
            setPositionForDetail(contractVo, contractDetailPoints);
        }
        // 是否是提报人createContMan
        contractVo.setCreateContMan(contractVo.getCreateUserId().equals(loginUser.getUserId()));
        Integer violateType = contractVo.getViolateType();
        if (null != violateType) {
            String name = ContractViolateEnum.getNameByCode(violateType);
            contractVo.setViolateTypeValue(name);
        }
        return RespData.success(contractVo);
    }

    @Override
    public RespData<List<Contract>> listCanBeBoundContracts(String organId) {
        List<Contract> contracts = contractMapper.listCanBeBoundContracts(ContractStatusEnum.ARCHIVE.getCode(),organId);
        return RespData.success(contracts);
    }

    @Override
    public RespData saveContract(ContractVO contractVo, LoginUser loginUser) {
        RespData resp = checkContractDetail(contractVo.getContractDetailVoList());
        if (!resp.getResult()) {
            return resp;
        }
        // 只取增量的投放明细和附件数据
        // 全量的删除后在全量的插入方式，且把记录中的主键ID改为空，变为新纪录插入表中
        List<ContractDetailVO> contractDetailVoList = Lists.newArrayList();
        for (ContractDetailVO contractDetailVo : contractVo.getContractDetailVoList()) {
            contractDetailVo.setContDetailId(null);
            contractDetailVoList.add(contractDetailVo);
        }
        contractVo.setContractDetailVoList(contractDetailVoList);
        List<ContractAttach> contractAttachVos = Lists.newArrayList();
        for (ContractAttach contractAttachVo : contractVo.getContractAttachVoList()) {
            if (null == contractAttachVo.getContAttachId() || 0 == contractAttachVo.getContAttachId()) {
                contractAttachVos.add(contractAttachVo);
            }
        }
        contractVo.setContractAttachVoList(contractAttachVos);
        if (null == contractVo.getFrameContId() || contractVo.getFrameContId().equals(0L)) {
            contractVo.setFrameContName("");
        }
        contractVo.setContStartTime(DateUtil.dateToStartDay(contractVo.getContStartTime()));
        contractVo.setContEndTime(DateUtil.dateToEndDate(contractVo.getContEndTime()));
        // 将机构编码传递给合同编号，生成合同编号时作为前缀使用
        String company = StringUtils.getPYIndexStr(contractVo.getSelfCompanyName(), true);
        String HTDL = StringUtils.getPYIndexStr(contractVo.getResTypeName(), true);
        if (null == contractVo.getContId() || contractVo.getContId().equals(0L)) {
            contractVo.setContCode(company.concat(HTDL).concat(CodeTypeEnum.WY_CONTRACT.getCode()));
        } else if (StringUtils.isNotEmpty(contractVo.getContCode())) {
            // 如果是已经保存了的合同，为了处理我放单位发生变化，所以同时修改合同编号，跟随我方单位编码变
            contractVo.setContCode(company.concat(HTDL).concat(CodeTypeEnum.WY_CONTRACT.getCode()) +
                    contractVo.getContCode().split(CodeTypeEnum.WY_CONTRACT.getCode())[1]);
        }
        //1. 保存合同
        logger.info("保存合同：{}({})开始------", contractVo.getContName(), contractVo.getContId());
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractVo, contract);
        if (contractVo.getContId() == null) {
            contract.setContCode(CodeGeneratorUtils.getContractCode(contractVo.getContCode()));
            contract.setContStatus(ContractStatusEnum.DRAFT.getCode());
            int insertResult = contractMapper.insertSelective(contract);
            logger.info("合同保存结果：{}", insertResult);
        } else {
            contractMapper.updateByPrimaryKeySelective(contract);
            logger.info("合同'{}'更新完成！", contract.getContId());
        }
        //2. 为投放明细设置合同id， 并保存
        Long contractId = contract.getContId();
        saveContractDetail(contractVo.getContractDetailVoList(), contractId, contractVo.getDeleteContractDetailIds());
        logger.info("保存投放明细，点位结束");
        // 3. 保存合同附件
        saveAttach(contractVo.getContractAttachVoList(), contractId, contractVo.getDeleteContractAttacheIds());
        logger.info("保存合同附件结束");
        // 4.保存保证金
        saveDepositPlan(contractVo.getContractDepositPlanVos(),loginUser, contractId);
        logger.info("保存保证金结束");
        // 5.保存能源信息
        saveEnergyBillingType(contractVo.getAdcEnergyBillingTypeVos(), loginUser, contractId, contractVo.getDeleteEnergyIds());
        logger.info("保存能源信息结束");
        // 6.收入类计划
        saveReceipt(contractVo.getContractReceiptVos(), loginUser, contractId);
        logger.info("保存收入类计划结束");
        return RespData.success();
    }

    @Override
    public List<Department> getCompanyList(String ogranId) {
        return departmentMapper.getcompanyListByogranId(ogranId);
    }

    @Override
    public RespData<List<User>> getAllDepUserByUserId(String companyId, String userId) {
        List<User> userList = userMapper.getAllDepUserByUserId(companyId);
        return RespData.success(userList);
    }

    @Override
    public List<ContractDepositPlanVO> getContractDepositPlanList(ContractVO contractVo) {
        List<ContractDepositPlan> list = contractDepositPlanMapper.getContractDepositPlanList(contractVo);
        return copyPropertiesForListNoTransform(list, ContractDepositPlanVO.class);
    }

    @Override
    public List<ContractEnergyPlanVO> getEnergyPlanList(ContractVO contractVo) {
        List<ContractEnergyPlan> list = contractEnergyPlanMapper.getEnergyPlanList(contractVo);
        return copyPropertiesForListNoTransform(list, ContractEnergyPlanVO.class);
    }

    @Override
    public List<ContractIncomeSetVO> getContractIncomeSetList(ContractVO contractVo) {
        List<ContractIncomeSet> list = contractIncomeSetMapper.getContractIncomeSetList(contractVo);
        return copyPropertiesForListNoTransform(list, ContractIncomeSetVO.class);
    }

    @Override
    public RespData<ContractCostDictsListVO> getCostItemList(String organId) {
        List<ContractCostDicts> lists = contractCostDictsMapper.getCostItemList(organId);
        List<ContractCostDicts> depositCostItem = Lists.newArrayList();
        List<ContractCostDicts> energyCostItem = Lists.newArrayList();
        List<ContractCostDicts> incomeSetCostItem = Lists.newArrayList();
        ContractCostDictsListVO costVO = new ContractCostDictsListVO();
        for (ContractCostDicts cost : lists) {
            if (cost.getCostTypeId().equals(1)) {
                depositCostItem.add(cost);
            }
            if (cost.getCostTypeId().equals(2)) {
                energyCostItem.add(cost);
            }
            if (cost.getCostTypeId().equals(3)) {
                incomeSetCostItem.add(cost);
            }
        }
        costVO.setDepositCostItem(depositCostItem);
        costVO.setEnergyCostItem(energyCostItem);
        costVO.setIncomeSetCostItem(incomeSetCostItem);
        return RespData.success(costVO);
    }

    /**
     * 检查投放明细选择点位是否合理
     * @param contractDetail
     * @return
     */
    public RespData checkContractDetail(List<ContractDetailVO> contractDetail) {
        if (null == contractDetail) {
            return RespData.success();
        }
        // 首先判断每个投放明细中的点位是否有重复的，如果有直接返回
        for (ContractDetailVO vo : contractDetail) {
            List<Long> positionIds = vo.getPositionIds();
            Set<Long> setPositionIds = Sets.newHashSet();
            if (CollectionUtils.isNotEmpty(positionIds)) {
                for (Long id : positionIds) {
                    setPositionIds.add(id);
                }
                if (setPositionIds.size() != setPositionIds.size()) {
                    return RespData.fail("同一个投放明细中不能有相同点位,请重新选择");
                }
            }
        }
        // 再验证不同投放明细中点位选择是否合理
        for (int i = 0; i < contractDetail.size() - 1; i++) {
            // 第一个投放明细
            ContractDetailVO contractDetailVo = contractDetail.get(i);
            List<Long> positionIds = contractDetailVo.getPositionIds();
            long detailStartTime = contractDetailVo.getDetailStartTime().getTime();
            long detailEndTime = contractDetailVo.getDetailEndTime().getTime();
            // 第二个投放明细
            for (int j = i + 1; j < contractDetail.size(); j++) {
                ContractDetailVO contractDetailVo2 = contractDetail.get(j);
                List<Long> positionIds2 = contractDetailVo2.getPositionIds();
                long detailStartTime2 = contractDetailVo2.getDetailStartTime().getTime();
                long detailEndTime2 = contractDetailVo2.getDetailEndTime().getTime();
                // 时间有交集
                if (((detailStartTime2 <= detailEndTime) && (detailStartTime2 >= detailStartTime))
                        || ((detailEndTime2 <= detailEndTime) && (detailEndTime2 >= detailStartTime))) {
                    for (Long pid : positionIds) {
                        for (Long pid2 : positionIds2) {
                            if (pid.equals(pid2)) {
                                i = i + 1;
                                j = j + 1;
                                Position position = positionMapper.selectByPrimaryKey(pid);
                                String pointName = position.getPositionName();
                                return RespData.fail("序号" + i + "和序号" + j + "的投放明细中点位名称为:[" + pointName + "]冲突,请重新选择");
                            }
                        }
                    }
                }
            }

        }
        return RespData.success();
    }

    /**
     * 保存投放明细
     * @param contractDetails
     * @param contractId
     */
    protected void saveContractDetail(List<ContractDetailVO> contractDetails, Long contractId, List<Long> deleteContractDetailIds) {
        if (CollectionUtils.isNotEmpty(contractDetails)) {
            //删除原来的投放记录
            contractDetailMapper.delContractDetailByConId(contractId);
            for (ContractDetailVO contractDetailPointVO : contractDetails) {
                contractDetailPointVO.setContId(contractId);
                //判断点位
                if(CollectionUtils.isNotEmpty(contractDetailPointVO.getPositionIds())){
                    contractDetailPointVO.setPointCount(contractDetailPointVO.getPositionIds().size());
                    ContractDetail model = new ContractDetail();
                    BeanUtils.copyProperties(contractDetailPointVO, model);
                    model.setDetailStartTime(DateUtil.dateToStartDay(model.getDetailStartTime()));
                    model.setDetailEndTime(DateUtil.dateToEndDate(model.getDetailEndTime()));
                    contractDetailMapper.insertSelective(model);
                    //3. 为点位关联表设置投放明细id，并保存
                    Long contDetailId = model.getContDetailId();
                    List<ContractDetailPoint> lists = Lists.newArrayList();
                    for (Long positionId : contractDetailPointVO.getPositionIds()) {
                        ContractDetailPoint contractDetailPoint = new ContractDetailPoint();
                        contractDetailPoint.setPositionId(positionId);
                        contractDetailPoint.setContId(contractId);
                        contractDetailPoint.setContDetailId(contDetailId);
                        lists.add(contractDetailPoint);
                    }
                    if (CollectionUtils.isNotEmpty(lists)) {
                        contractDetailPointMapper.insertDetailPoint(lists);
                    }
                }
            }
        }else{//如果前端没有传入投放明细，则用合同ID删除相关的投放明细
            contractDetailMapper.delContractDetailByConId(contractId);
        }
        // 删除投放明细
        if (CollectionUtils.isNotEmpty(deleteContractDetailIds)) {
            int deleteDetailCount = contractDetailMapper.deleteContractDetailByContDetailIds(deleteContractDetailIds);
            logger.info("删除投放明细结束，删除{}条！", deleteDetailCount);
        }
    }

    /**
     * 保证金保存
     * @param contractDepositPlanVos
     * @param loginUser
     * @param contId
     */
    private void saveDepositPlan(List<ContractDepositPlanVO> contractDepositPlanVos, LoginUser loginUser,Long contId) {
        List<Long> ids = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(contractDepositPlanVos)) {
            List<ContractDepositPlan> lists = Lists.newArrayList();
            for (ContractDepositPlanVO contractDepositPlanVo : contractDepositPlanVos) {
                ContractDepositPlan depositPlan = new ContractDepositPlan();
                BeanUtils.copyProperties(contractDepositPlanVo, depositPlan);
                if (null == contractDepositPlanVo.getId() || contractDepositPlanVo.getId().equals(0L)) {
                    depositPlan.setContId(contId);
                    depositPlan.setCreateUserId(loginUser.getUserId());
                    depositPlan.setCreateUserName(loginUser.getUserName());
                    lists.add(depositPlan);
                } else {
                    ids.add(contractDepositPlanVo.getId());
                    depositPlan.setUpdateUserId(loginUser.getUserId());
                    depositPlan.setUpdateUserName(loginUser.getUserId());
                    depositPlan.setUpdateTime(new Date());
                    contractDepositPlanMapper.updateByPrimaryKeySelective(depositPlan);
                }
            }
            contractDepositPlanMapper.deleteDepositPlan(contId, ids);
            if (lists.size() > 0) {
                contractDepositPlanMapper.insertDepositPlans(lists);
            }
        } else {//如果前端没有传入保证金，则用合同ID删除相关的保证金
            contractDepositPlanMapper.deleteDepositPlanByContDetailIds(contId);
        }
    }

    /**
     * 保存、修改、删除能源信息
     * @param energyPlanVos
     * @param loginUser
     * @param contId
     * @param deleteEnergyBillTypeIds
     * @throws Exception
     */
    private void saveEnergyBillingType(List<ContractEnergyPlanVO> energyPlanVos, LoginUser loginUser, Long contId, List<Long> deleteEnergyBillTypeIds){
        Date curDate = new Date();
        if (CollectionUtils.isNotEmpty(energyPlanVos)) {
            List<ContractEnergyPlan> lists = Lists.newArrayList();
            for (ContractEnergyPlanVO energyPlanVO : energyPlanVos) {
                ContractEnergyPlan energyPlan = new ContractEnergyPlan();
                BeanUtils.copyProperties(energyPlanVO, energyPlan);
                energyPlan.setContId(contId);
                if (energyPlan.getEnergyPlanId() == null) {
                    energyPlan.setCreateUserId(loginUser.getUserId());
                    energyPlan.setCreateUserName(loginUser.getUserName());
                    lists.add(energyPlan);
                } else {
                    energyPlan.setUpdateUserId(loginUser.getUserId());
                    energyPlan.setUpdateUserName(loginUser.getUserName());
                    energyPlan.setUpdateTime(curDate);
                    contractEnergyPlanMapper.updateByPrimaryKey(energyPlan);
                }
            }
            if (lists.size() > 0) {
                contractEnergyPlanMapper.insertEnergyPlans(lists);
            }
        }
        if (CollectionUtils.isNotEmpty(deleteEnergyBillTypeIds)) {
            contractEnergyPlanMapper.deleteEnergyPlanId(deleteEnergyBillTypeIds);
        }
    }

    /**
     * 收入类计划保存
     * @param contractReceiptVos
     * @param loginUser
     * @param contId
     */
    private void saveReceipt(List<ContractIncomeSetVO> contractReceiptVos, LoginUser loginUser, Long contId) {
        List<Long> ids = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(contractReceiptVos)) {
            List<ContractIncomeSet> lists = Lists.newArrayList();
            for (ContractIncomeSetVO contractReceiptVo : contractReceiptVos) {
                ContractIncomeSet income = new ContractIncomeSet();
                BeanUtils.copyProperties(contractReceiptVo, income);
                if (null == contractReceiptVo.getIncomePlanId() || contractReceiptVo.getIncomePlanId().equals(0L)) {
                    income.setContId(contId);
                    income.setCreateUserId(loginUser.getUserId());
                    income.setCreateUserName(loginUser.getUserName());
                    lists.add(income);
                } else {
                    ids.add(income.getIncomePlanId());
                    income.setUpdateUserId(loginUser.getUserId());
                    income.setUpdateUserName(loginUser.getUserName());
                    income.setUpdateTime(new Date());
                    contractIncomeSetMapper.updateByPrimaryKeySelective(income);
                }
            }
            // 作废前端页面删除的数据
            contractIncomeSetMapper.deleteIncomeSet(contId, ids);
            if (lists.size() > 0) {
                contractIncomeSetMapper.insertIncomeSets(lists);
            }
        } else {
            //如果前端没有传入收款计划，则用合同ID删除相关的收款计划
            contractIncomeSetMapper.deleteIncomeSetByContDetailIds(contId);
        }
    }

    /**
     * 合同附件保存
     * @param contractAttachs
     * @param contId
     * @param deleteContractAttachsIds
     */
    private void saveAttach(List<ContractAttach> contractAttachs,Long contId, List<Long> deleteContractAttachsIds) {
        //保存附件
        if (CollectionUtils.isNotEmpty(contractAttachs)) {
            List<ContractAttach> lists = Lists.newArrayList();
            for (ContractAttach contractAttach : contractAttachs) {
                if (contractAttach.getContAttachId() == null || contractAttach.getContAttachId().equals(0L)) {
                    contractAttach.setContId(contId);
                    lists.add(contractAttach);
                }
            }
            contractAttachMapper.insertContractAttach(lists);
        }
        // 删除附件
        if (CollectionUtils.isNotEmpty(deleteContractAttachsIds)) {
            int deleteAttachCount = contractAttachMapper.deleteContractAttachByBatch(deleteContractAttachsIds);
            logger.info("删除附件结束，删除{}条！", deleteAttachCount);
        }
    }

    public void setContractVOName(ContractVO contractVO,String organId) {
        // 合同类型
        if (contractVO.getContTypeId() == null) {
            List<Dicts> dicts = dictsMapper.getDictsByType(organId, Constant.CONTRACT_TYPE);
            for (Dicts dict : dicts) {
                if (dict.getId().intValue() == contractVO.getContTypeId()) {
                    contractVO.setContTypeName(dict.getDictsName());
                }
            }
        }
        // 合同状态
        if (contractVO.getContStatus() == null) {
            List<Dicts> dicts = dictsMapper.getDictsByType(organId, Constant.CONTRACT_STATUS);
            for (Dicts dict : dicts) {
                if (dict.getId().intValue() == contractVO.getContStatus()) {
                    contractVO.setContStatusName(dict.getDictsName());
                }
            }
        }
        ResourceType  resourceType = resourceTypeMapper.selectByPrimaryKey(contractVO.getResTypeId());
        if (null != resourceType) {
            // 合同大类
            if (resourceType.getId().equals(contractVO.getResTypeId())) {
                contractVO.setResTypeName(resourceType.getResourceName());
                }
            }
        }

    /**
     * List深复制
     *
     * @param sourceList
     * @param dest
     * @return: void
     */
    public static <T> List<T> copyPropertiesForListNoTransform(List<?> sourceList, final Class<T> dest) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayListWithExpectedSize(0);
        }
        List<T> transform = Lists.newArrayList();
        for (Object input : sourceList) {
            T destObj = null;
            try {
                destObj = dest.newInstance();
                BeanUtils.copyProperties(input, destObj);
                transform.add(destObj);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return transform;
    }

    private void setPositionForDetail(ContractVO contractVO, List<ContractDetailPoint> contractDetailPoints){
        Map<Long, List<Long>> listMap = Maps.newHashMap();
        for (ContractDetailPoint contractDetailPoint : contractDetailPoints) {
            List<Long> points = Lists.newArrayList();
            if (listMap.containsKey(contractDetailPoint.getContDetailId())) {
                points = listMap.get(contractDetailPoint.getContDetailId());
            }
            points.add(contractDetailPoint.getPositionId());
            listMap.put(contractDetailPoint.getContDetailId(), points);
        }
        if (CollectionUtils.isNotEmpty(contractVO.getContractDetailVoList())) {
            for (ContractDetailVO contractDetailVO : contractVO.getContractDetailVoList()) {
                if (listMap.containsKey(contractDetailVO.getContDetailId())) {
                    contractDetailVO.setPositionIds(listMap.get(contractDetailVO.getContDetailId()));
                }
            }
        }
    }
}
