package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.fixtures.AccountFixture;
import com.exalt.company.bankaccount.fixtures.TransactionFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionDatabaseAdapterTest {

    @InjectMocks
    TransactionDatabaseAdapter transactionDatabaseAdapter;

    @Mock
    TransactionJpaRepository transactionJpaRepository;

    @Test
    void save_shouldSaveByUsingJpaRepository() {
        // Given
        Transaction transaction = TransactionFixture.aTransaction();
        ArgumentCaptor<AccountJPA> accountJpaArgumentCaptor = ArgumentCaptor.forClass(AccountJPA.class);

        // When
        transactionDatabaseAdapter.save(transaction);

        // Then
        verify(transactionJpaRepository).save(accountJpaArgumentCaptor.capture());
        assertThat(accountJpaArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(transaction);
    }
}
