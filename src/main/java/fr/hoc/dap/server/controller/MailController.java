package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.GmailService;

/**
 * @author Mohammed & Thomas
 *
 */

@RestController
public class MailController {
    /** injection of Dependency with Autowired annotation . */
    @Autowired
    private GmailService gmService = new GmailService();

    /** Display the email unread.
     * @return nb email unread.
     * @throws GeneralSecurityException can not connect to google sever.
     * @throws IOException if the credentials.json file can not be found.
     * @param userKey connected.
     */
    @RequestMapping("/mail/nbunread")
    public Integer nombre(@RequestParam("userKey") final String userKey) throws IOException, GeneralSecurityException {

        return gmService.displayNumbeOfEmailUnread(userKey);
    }

}
