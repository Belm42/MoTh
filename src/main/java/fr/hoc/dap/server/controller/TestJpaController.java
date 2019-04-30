/**
 * 
 */
package fr.hoc.dap.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.data.DapUser;
import fr.hoc.dap.server.data.DapUserRepository;

/**
 * Permet de faire des tests des entitées JPA.
 * @author Mohammed
 *
 */
@RestController
public class TestJpaController {

    @Autowired
    private DapUserRepository dapUserRepository;

    @RequestMapping("/test/createDapUser")
    public DapUser creatDapUser(@RequestParam String loginName, String userKey) {
        DapUser monUser = new DapUser();
        monUser.setLoginName(loginName);
        monUser.setUserKey(userKey);
        DapUser savedUser = dapUserRepository.save(monUser);
        return savedUser;

    }

    @RequestMapping("/test/loadDapUser")
    public DapUser loadDapUser(@RequestParam String userKey) {
        DapUser monUser1 = new DapUser();
        monUser1.setUserKey(userKey);
        DapUser loadUser = dapUserRepository.findByUserKey(userKey);
        return loadUser;

    }
}