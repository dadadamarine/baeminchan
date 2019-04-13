package codesquad.service;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import codesquad.web.dto.AccountRegistrationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    private static Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    public Account save(AccountRegistrationDTO account) {
        if(account.passwordConfirm()){

        }
        Account newAcount = new Account(account);
        accountRepository.save(newAcount);
        return null;
    }

    //TODO : 암호화 적용

    //TODO : ServiceTest 먼저 짜기
}
