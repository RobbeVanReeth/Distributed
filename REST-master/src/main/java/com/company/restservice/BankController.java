package com.company.restservice;

import org.springframework.web.bind.annotation.*;

@RestController
public class BankController {

    // https://codereview.stackexchange.com/questions/231594/simple-banking-application-in-spring-boot-jpa-rest-where-balance-can-be-transf
    @GetMapping(value = "/balance/{id}")
    float getBalance(@PathVariable Integer id)
    {
        return Bank.getInstance().getAccount(id).getBalance();
    }

    @PutMapping(value = "/addMoney/{accID}/{amount}")
    float addMoney(@PathVariable Integer accID, @PathVariable(value = "amount") int amount)
    {
        Bank.getInstance().getAccount(accID).addMoney(amount);
        return Bank.getInstance().getAccount(accID).getBalance();
    }

    @PutMapping(value = "/withdrawMoney/{accID}/{amount}")
    String withdrawMoney(@PathVariable Integer accID, @PathVariable(value = "amount") int amount)
    {
        if(Bank.getInstance().getAccount(accID).getMoney(amount))
            return "Money withdrawn!";
        else
            return "Not enough money, or in use by your peer";
    }

}
