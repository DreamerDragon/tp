package seedu.address.model;

import java.time.LocalDate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a visit in the visit list.
 * Includes personId, locationId and date of visit.
 */

public class Visit {

    private String personId;
    private String locationId;
    private LocalDate date;

    public Visit(String personId, String locationId, LocalDate date) {
        requireAllNonNull(personId, locationId, date);
        this.personId = personId;
        this.locationId = locationId;
        this.date = date;
    }




}
