package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.fixtures.AccountFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountDatabaseAdapterTest {

    @InjectMocks
    AccountDatabaseAdapter accountDatabaseAdapter;

    @Mock
    AccountJpaRepository accountJpaRepository;

    @Test
    void save_shouldSaveByUsingJpaRepository() {
        // Given
        Account account = AccountFixture.aNewAccount();
        ArgumentCaptor<AccountJPA> accountJpaArgumentCaptor = ArgumentCaptor.forClass(AccountJPA.class);

        // When
        accountDatabaseAdapter.save(account);

        // Then
        verify(accountJpaRepository).save(accountJpaArgumentCaptor.capture());
        assertThat(accountJpaArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(account);
    }
}
