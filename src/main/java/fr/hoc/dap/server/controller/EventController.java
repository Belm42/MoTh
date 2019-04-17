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

    /** injection of Dependency with Autowired annotation .*/
    @Autowired
    private CalendarService gcService;

    /** Display next event in server client.
     * @param userKey connected.
     * @param nb of events. //TODO moth by Djer |JavaDoc| "nb event to display" serait mieux
     * @return list of the next events. //TODO moth by Djer |JavaDoc| Ca n'est pas une liste, mais une "represnetation textuelle des prochains évènnements"
     * @throws IOException if the credentials.json file can not be found.
     * @throws GeneralSecurityException can not connect to google sever.
     */
    @RequestMapping("/event/nextevent")
    public String getService(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam("userKey") final String userKey) throws GeneralSecurityException, IOException {

      //TODO moth by Djer |Log4J| Contextualise tes messages : "Searching for next the " + nb + " nexts event for user + " userKey
        LOG.info("Next event obtained");

        return gcService.nextEvents(nb, userKey);

    }
}
