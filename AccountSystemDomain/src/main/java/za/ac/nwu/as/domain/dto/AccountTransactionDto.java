package za.ac.nwu.as.domain.dto;

import io.swagger.annotations.ApiModel;
import za.ac.nwu.as.domain.persistence.AccountTransaction;

import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "AccountTransaction", description = "A DTO that represents the AccountTransaction")
public class AccountTransactionDto {
    private Long accountTypeId;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;

    public AccountTransactionDto(){

    }

    public AccountTransactionDto(Long accountTypeId, Long memberId, Long amount, LocalDate transactionDate){
        this.accountTypeId = accountTypeId;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public AccountTransactionDto(AccountTransaction accountTransaction) {
        this.setAccountTypeId(accountTransaction.getAccountTypeId());
        this.setAmount(accountTransaction.getAmount());
        this.setMemberId(accountTransaction.getMemberId());
        this.setTransactionDate(accountTransaction.getTransactionDate());
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDto that = (AccountTransactionDto) o;
        return Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTypeId, memberId, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "accountTypeId=" + accountTypeId +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
