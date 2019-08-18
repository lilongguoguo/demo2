package com.common.district.ad.vo;

import com.common.district.ad.model.ContractCostDicts;

import java.util.List;

public class ContractCostDictsListVO {

    private List<ContractCostDicts> depositCostItem;
    private List<ContractCostDicts> energyCostItem;
    private List<ContractCostDicts> incomeSetCostItem;

    public List<ContractCostDicts> getDepositCostItem() {
        return depositCostItem;
    }

    public void setDepositCostItem(List<ContractCostDicts> depositCostItem) {
        this.depositCostItem = depositCostItem;
    }

    public List<ContractCostDicts> getEnergyCostItem() {
        return energyCostItem;
    }

    public void setEnergyCostItem(List<ContractCostDicts> energyCostItem) {
        this.energyCostItem = energyCostItem;
    }

    public List<ContractCostDicts> getIncomeSetCostItem() {
        return incomeSetCostItem;
    }

    public void setIncomeSetCostItem(List<ContractCostDicts> incomeSetCostItem) {
        this.incomeSetCostItem = incomeSetCostItem;
    }
}
