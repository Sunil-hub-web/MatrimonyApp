package com.example.matrimonyapp.modelclass;

public class IncomeModel {

    String income_id,income_name;

    public IncomeModel(String income_id, String income_name) {
        this.income_id = income_id;
        this.income_name = income_name;
    }

    public String getIncome_id() {
        return income_id;
    }

    public void setIncome_id(String income_id) {
        this.income_id = income_id;
    }

    public String getIncome_name() {
        return income_name;
    }

    public void setIncome_name(String income_name) {
        this.income_name = income_name;
    }
}
