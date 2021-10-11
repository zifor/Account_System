package za.ac.nwu.as.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.logic.flow.CreateAccountTypeFlow;

import javax.transaction.Transactional;

@Transactional
@Component("createAccountTypeFlow")
public class CreateAccountTypeFlowImpl implements CreateAccountTypeFlow {
    @Override
    public AccountTypeDto create(AccountTypeDto accountType) {
        return null;
    }
//    @Override
//    public AccountTypeDto create(AccountTypeDto accountType) {
//        return null;
//    }
//
//    private final AccountTypeTranslator accountTypeTranslator;
//
//    public CreateAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator){
//        this.accountTypeTranslator = accountTypeTranslator;
//    }
//
//    @Override
//    public AccountTypeDto create(AccountTypeDto accountType){
//        if(null == accountType.getCreationDate()){
//            accountType.setCreationDate(LocalDate.now());
//        }
//    }
}
