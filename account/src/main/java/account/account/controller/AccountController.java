package account.account.controller;

import account.account.Account;
import account.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/hi")
    public String hello() {
        return "Hi from accounts";
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
    }

    @GetMapping("/accounts")
    public ResponseEntity save(@RequestParam(name = "firstname") String firstname,
                               @RequestParam(name = "lastname") String lastname) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.save(new Account(firstname,lastname)));
    }
}
