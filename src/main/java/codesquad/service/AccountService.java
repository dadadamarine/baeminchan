package codesquad.service;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import codesquad.exception.account.CannotRegistrationException;
import codesquad.exception.account.UnAuthenticationException;
import codesquad.web.dto.AccountLogin;
import codesquad.web.dto.AccountRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account save(AccountRegistration dto) {
        if (!dto.passwordConfirm()) {
            throw new CannotRegistrationException("unmatch password");
        }
        return accountRepository.save(new Account(dto).encode(passwordEncoder));
    }

    public Account findAccount(AccountLogin accountLogin) {
        return accountRepository.findByUserId(accountLogin.getUserId())
                .filter(account -> passwordEncoder.matches(accountLogin.getPassword(), account.getPassword()))
                .orElseThrow(UnAuthenticationException::new);
    }
}
