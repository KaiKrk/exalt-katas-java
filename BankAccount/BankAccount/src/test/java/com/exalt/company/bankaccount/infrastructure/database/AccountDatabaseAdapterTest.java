package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.fixtures.AccountFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
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
        AccountJpa accountJpa = new AccountJpa(account);
        ArgumentCaptor<AccountJpa> accountJpaArgumentCaptor = ArgumentCaptor.forClass(AccountJpa.class);
        doReturn(accountJpa).when(accountJpaRepository).save(accountJpa);
        // When
        accountDatabaseAdapter.updateAccount(account);

        // Then
        verify(accountJpaRepository).save(accountJpaArgumentCaptor.capture());
        assertThat(accountJpaArgumentCaptor.getValue().getFirstname()).isEqualTo(account.getFirstname());
    }
}
