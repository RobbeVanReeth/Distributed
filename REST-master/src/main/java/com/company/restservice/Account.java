package com.company.restservice;

public class Account {
    private float balance;
    private int accountNmbr;
    private boolean busy=false;

    public Account(int accountNmbr){
        this.balance = 0;
        this.accountNmbr = accountNmbr;
    }

    public float getBalance(){
        return balance;
    }

    public boolean getMoney(float money){
        if(busy)
            return false;
            //somebody is already getting money from this account at this moment
        else{
        busy = true;
        try{ Thread.sleep(3000);
        }catch (Exception e){}

        if(balance >= money) {
            balance -= money;
            busy = false;
            return true;
        } else {return false;}
    }}

    public void addMoney(float money){

        balance += money;
    }
}
