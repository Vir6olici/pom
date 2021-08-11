package user.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hi")
    public String hello(){
        return "Hi from users";
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/insert")
    public ResponseEntity save(@RequestParam(name = "firstname") String firstname,
                               @RequestParam(name = "lastname") String lastname) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(new User(firstname,lastname)));
    }
}
