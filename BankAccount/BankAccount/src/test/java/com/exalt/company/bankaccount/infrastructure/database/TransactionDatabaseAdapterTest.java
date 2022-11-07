package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.fixtures.AccountFixture;
import com.exalt.company.bankaccount.fixtures.TransactionFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionDatabaseAdapterTest {

    @InjectMocks
    TransactionDatabaseAdapter transactionDatabaseAdapter;

    @Mock
    TransactionJpaRepository transactionJpaRepository;

    @Mock
    AccountJpaRepository accountJpaRepository;

    @Test
    void save_shouldSaveByUsingJpaRepository() {
        // Given
        AccountJpa accountJpa = new AccountJpa(AccountFixture.aAccount());
        Transaction transaction = TransactionFixture.aTransaction(accountJpa.getId());
        TransactionJpa transactionJpa = new TransactionJpa(transaction);
        transactionJpa.setAccount(accountJpa);
        Optional<AccountJpa> optionalAccountJpa =  Optional.of(accountJpa);
        ArgumentCaptor<TransactionJpa> accountJpaArgumentCaptor = ArgumentCaptor.forClass(TransactionJpa.class);

        doReturn(optionalAccountJpa).when(accountJpaRepository).findById(transactionJpa.getAccount().getId());
        doReturn(transactionJpa).when(transactionJpaRepository).save(transactionJpa);

        // When
        transactionDatabaseAdapter.save(transaction);

        // Then
        verify(transactionJpaRepository).save(accountJpaArgumentCaptor.capture());
        assertThat(accountJpaArgumentCaptor.getValue().getId()).isEqualTo(transaction.getId());
    }
}
