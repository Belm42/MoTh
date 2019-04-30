package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.CalendarService;

/**
 * Manage calendar service.
 * @author Mohammed & Thomas
 *
 */
@RestController
public class EventController {

    /** Logger Log4j declaration. */
    private static final Logger LOG = LogManager.getLogger();

    /** Injection d'une dependence de la classe CalendarService dans le EventController. */
    @Autowired
    private CalendarService gcService;

    /** Display next event in server client.
     * @param userKey connected.
     * @param nb event to display
     * @return textual representation of next event(s).
     * @throws IOException if the credentials.json file can not be found.
     * @throws GeneralSecurityException can not connect to google sever.
     */
    @RequestMapping("/event/nextevent")
    public String getService(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam("userKey") final String userKey) throws GeneralSecurityException, IOException {

        LOG.info("Searching for next the \" + nb + \" nexts event for user + \" userKey");

        return gcService.nextEvents(nb, userKey);

    }
}
