package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.CalendarService;

/** Manage calendar service.
 * @author Mohammed & Thomas
 *
 */
@RestController
public class EventController {
    /** injection of Dependency with Autowired annotation .*/
    @Autowired
    private CalendarService gcService = new CalendarService();

    /** Display next event in server client.
     * @param userKey connected.
     * @param nb of events.
     * @return list of the next events.
     * @throws IOException if the credentials.json file can not be found.
     * @throws GeneralSecurityException can not connect to google sever.
     */
    @RequestMapping("/event/nextevent")
    public String getService(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam("userKey") final String userKey) throws GeneralSecurityException, IOException {

        return gcService.nextEvents(nb, userKey);

    }
}
