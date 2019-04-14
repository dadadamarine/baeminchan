package codesquad.service;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import codesquad.exception.CannotJoinException;
import codesquad.exception.UnAuthenticationException;
import codesquad.web.dto.AccountLoginDTO;
import codesquad.web.dto.AccountRegistrationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    private static Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account save(AccountRegistrationDTO dto) {
        if(!dto.passwordConfirm()){
            throw new CannotJoinException("unmatch password");
        }
        return accountRepository.save(new Account(dto).encode(passwordEncoder));
    }

    public Account findAccount(AccountLoginDTO accountLoginDTO) throws UnAuthenticationException {
//        String encodedPassword = passwordEncoder.encode(accountLoginDTO.getPassword());
        return accountRepository.findByUserId(accountLoginDTO.getUserId())
                .filter(account -> passwordEncoder.matches(accountLoginDTO.getPassword(),account.getPassword()))
                .orElseThrow(UnAuthenticationException::new);
    }
}
