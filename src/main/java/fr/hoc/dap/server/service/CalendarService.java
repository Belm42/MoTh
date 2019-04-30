package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import fr.hoc.dap.server.Config;

/**
 * Service for Google Calendar access.
 * @author Mohammed et Thomas
 *
 */
@Service
public class CalendarService extends GoogleService {

    /** Logger Log4j declaration. */
    private static final Logger LOG = LogManager.getLogger();

    /**
     *  injection of Dependency with Autowired annotation .
     */
    @Autowired
    private Config maConf;

    /** Calendar service.
     *
     * @param userKey connected.
     * @return calendar service instance.
     * @throws GeneralSecurityException can not connect to google sever.
     * @throws IOException where the credentials.json file cannot be found.
     */

    public Calendar buildService(final String userKey) throws GeneralSecurityException, IOException {
        // Build a new authorized API client service.
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httptransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(maConf.getApplicationName()).build();

        LOG.debug("Calendar Service obtained for user");
        return service;
    }

    /** Display the next events.
     * @return Textual representation of next event(s).
     * @throws GeneralSecurityException can not connect the sever.
     * @throws IOException if the credentials.json file can not be found.
     * @param userKey DaP user (login)
     * @param nb of events to display.
     */
    public String nextEvents(final Integer nb, final String userKey) throws IOException, GeneralSecurityException {
        Calendar service = buildService(userKey);
        String results = "";

        DateTime now = new DateTime(System.currentTimeMillis());

        Events events = service.events().list("primary").setMaxResults(nb).setTimeMin(now).setOrderBy("startTime")
                .setSingleEvents(true).execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            LOG.info("No event found");
            results = "No upcoming events";
        } else {
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                results += event.getSummary() + " " + start;
            }
        }

        return results;
    }
}
