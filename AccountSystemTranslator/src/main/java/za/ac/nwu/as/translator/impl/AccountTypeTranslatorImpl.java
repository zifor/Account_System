package za.ac.nwu.as.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.domain.persistence.AccountType;
import za.ac.nwu.as.repo.persistence.AccountTypeRepository;
import za.ac.nwu.as.translator.AccountTypeTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTypeTranslatorImpl implements AccountTypeTranslator {

    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeTranslatorImpl(AccountTypeRepository accountTypeRepository){
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypes() {
        List<AccountTypeDto> accountTypeDto = new ArrayList<>();

        try{
            for(AccountType accountType : accountTypeRepository.findAll()){
                accountTypeDto.add(new AccountTypeDto(accountType));
            }
        }catch (Exception e){
            throw new RuntimeException("Unable to read from the DB",e);
        }
        return accountTypeDto;
    }

    @Override
    public AccountTypeDto getAccountTypeByNameNativeQuery() {
        return null;
    }

    @Override
    public AccountTypeDto getAccountTypeByNameNativeQuery(String mnemonic) {
        try{
            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonicNativeQuery(mnemonic);
            return new AccountTypeDto(accountType);
        }catch (Exception e){
            throw new RuntimeException("Unable to read from the DB",e);
        }
    }

    @Override
    public AccountTypeDto getAccountTypeByMneminic(String mnemonic){
        try{
            AccountType accountType = accountTypeRepository.getAccountTypeByMneminic(mnemonic);
            return new AccountTypeDto(accountType);
        }catch (Exception e){
            throw new RuntimeException("Unable to read from the DB",e);
        }
    }

    @Override
    public AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic){
        try {
            return accountTypeRepository.getAccountTypeDtoByMnemonic(mnemonic);
        }catch (Exception e){
            throw new RuntimeException("Unable to read from the DB",e);
        }
    }
}
