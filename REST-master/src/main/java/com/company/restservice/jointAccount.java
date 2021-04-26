package com.company.restservice;

public class jointAccount extends Account {

    Account acc;

    public jointAccount(int accID, Account acc){
        super(accID);
        this.acc = acc;
    }

    @Override
    public float getBalance(){
        return acc.getBalance();
    }

    @Override
    public void addMoney(float money) {
        acc.addMoney(money);
    }

    @Override
    public boolean getMoney(float money){
        return acc.getMoney(money);
    }
}
