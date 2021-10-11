package za.ac.nwu.as.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.domain.persistence.AccountType;


@Repository
public interface AccountTransactionRepository extends JpaRepository <AccountTransaction, Long> {
    @Query(value = "SELECT " +
            "       ACCOUNT_TX_ID" +
            "       MEMBER_ID" +
            "       AMOUNT" +
            "       TX_DATE" +
            "   FROM" +
            "       HR.TRANSACTION" +
            "   WHERE ACCOUNT_TYPE_ID = :accountTypeId ", nativeQuery = true)
    AccountType getAccountTransactionByAccountTypeIdNativeQuery(Long accountTypeId);

    @Query(value = "SELECT t FROM AccountTranaction t WHERE t.accountTransactionId = :accountTransactionId")
    AccountType getAccountTtansactionByAccountTypeId(Long accountTransactionId);

    @Query(value = "SELECT new za.ac.nwu.as.domain.dto.AccountTransactionDto(t.memberId, t.amount,t.transactionDate) FROM AccountType at WHERE t.accountTransactionId = :accountTransactionId")
    AccountTypeDto getAccountTransactionDtoByAccountTypeId(Long accountTransactionId);

    @Query(value = "UPDATE AMOUNT IN HR.TRANSACTION WHERE ACCOUNT_TRANSACTION_ID = :accountTransactionId")
    AccountType getUpdateAmount(Long accountTransactionId);
}
