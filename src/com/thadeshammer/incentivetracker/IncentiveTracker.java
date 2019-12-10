package com.thadeshammer.incentivetracker;

import com.thadeshammer.incentivetracker.incentivizedevents.BirthReported;
import com.thadeshammer.incentivetracker.incentivizedevents.UserDataLogged;

/**
 * thade created on 12/10/2019
 */
public class IncentiveTracker {

    /**
     * These would likely be defined elsewhere in the code but for our purposes we'll
     * temporarily define them here.
     */
    public enum IncentivizedEventTypes {
        REPORT_BIRTH,
        LOG_DATA
    }

    /*
        Elsewhere (external to this lib) something akin to reportEvent(...) is called which
        may result in an update to the datastore.

        Different events will require different types of validation: some (like reported
        birth) will trigger a reward event immediately upon happening (no real "tracking"
        to be done as it's binary) whereas other events may be periodic in some way, like
        data being logged with some amount of regularity.

        The initial ask here is for a reward to proc after the fifth consecutive day that
        data is logged (something like an achievement woudl be logged perhaps, then this
        one is "achieved" for the given user and not "achievable" again) but perhaps we'd
        want something more complicated at some point: for instance, an on-going incentive
        that pops up some sort of congratulations message if the user repeatedly reports
        daily beyond the fifth day, or five out of seven days a week, or five out of every
        seven days...more complicated logic may be desirable. So we'll set this up as
        generically as possible to that end.
     */

    boolean checkEvent(String userUID, IncentivizedEventTypes eventType) {

        /*
            Rather than construct these simple helper objects on the stack we do so lazily via
            a singleton pattern; this assumes either we need no scaling OR that we'll scale with
            multiple services, and maybe both of those assumptions are crazy.
         */

        boolean eventResultsInAchievement = false;

        switch (eventType) {
            case LOG_DATA:
                eventResultsInAchievement = UserDataLogged.getInstance().checkEvent(userUID);
                break;
            case REPORT_BIRTH:
                eventResultsInAchievement = BirthReported.getInstance().checkEvent(userUID);
                break;
            default:
                // nothing to do here but that might be something we want to report too.
        }

        return eventResultsInAchievement;
    }
}
