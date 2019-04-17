package codesquad.service;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import codesquad.exception.account.CannotRegistrationException;
import codesquad.exception.account.UnAuthenticationException;
import codesquad.web.dto.AccountLoginDTO;
import codesquad.web.dto.AccountRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account save(AccountRegistrationDTO dto) {
        if (!dto.passwordConfirm()) {
            throw new CannotRegistrationException("unmatch password");
        }
        return accountRepository.save(new Account(dto).encode(passwordEncoder));
    }

    public Account findAccount(AccountLoginDTO accountLoginDTO) {
        return accountRepository.findByUserId(accountLoginDTO.getUserId())
                .filter(account -> passwordEncoder.matches(accountLoginDTO.getPassword(), account.getPassword()))
                .orElseThrow(UnAuthenticationException::new);
    }
}
