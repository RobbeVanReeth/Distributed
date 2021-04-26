package com.company.restservice;

import java.util.HashMap;

public class Bank {

    private HashMap<Integer, Account> accDatabase;

    public Bank(){
        this.accDatabase = new HashMap<>();
        //this is for exercise 1
        this.accDatabase.put(1,new Account(1));

        //this is for exercise 2 (expanding to joint accounts)
        Account ac1 = new Account(1);
        //if we do it like this both accounts are actually the same
        //but the delay in the methods has been avoided so 2 accounts
        //can get money at the same time
        this.accDatabase.put(2,ac1);
        this.accDatabase.put(3, new jointAccount(3,ac1));
    }

    public Account getAccount(int accID) {return this.accDatabase.get(accID);}

    private static Bank instance;
    public static Bank getInstance(){ //Singleton, there is only 1 bank
        if(instance == null)
            instance = new Bank();
        return instance;
    }
}
