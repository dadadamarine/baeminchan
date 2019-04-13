package codesquad.service;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    private static Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    public Account save(Account account) {
        accountRepository.save(account);
        return null;
    }

    //TODO : 암호화 적용

    //TODO : ServiceTest 먼저 짜기
}
