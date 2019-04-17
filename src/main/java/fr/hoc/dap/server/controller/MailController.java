package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.GmailService;

/**
 * Controller for the gestion of mails.
 * @author Mohammed & Thomas
 *
 */

@RestController
public class MailController {

    /** Logger Log4j declaration. */
    private static final Logger LOG = LogManager.getLogger();

    //TODO moth by Djer |JavaDoc| Tu ne dois pas docummenter l'annotation mais l'attribut en dessous
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
      //TODO moth by Djer |Log4J| Contextualise tes messages : "Searching for number of uread email for user : " + userKey
        LOG.info("Number of unread mails obtained");

        return gmService.displayNumbeOfEmailUnread(userKey);
    }

}
