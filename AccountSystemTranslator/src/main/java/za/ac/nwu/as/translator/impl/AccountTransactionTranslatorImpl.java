package za.ac.nwu.as.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.repo.persistence.AccountTransactionRepository;
import za.ac.nwu.as.translator.AccountTypeTransactionTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTransactionTranslatorImpl implements AccountTypeTransactionTranslator {
    private final AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public AccountTransactionTranslatorImpl(AccountTransactionRepository accountTransactionRepository){
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    public List<AccountTransactionDto> getAllAccountTransactions() {
        List<AccountTransactionDto> accountTransactionDto = new ArrayList<>();

        try{
            for(AccountTransaction accountTransaction : accountTransactionRepository.findAll()){
                accountTransactionDto.add(new AccountTransactionDto(accountTransaction));
            }
        }catch (Exception e){
            throw new RuntimeException("Unable to read from the DB",e);
        }
        return accountTransactionDto;
    }
}
