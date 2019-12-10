package com.thadeshammer.incentivetracker.incentivizedevents;

/**
 * thade created on 12/10/2019
 */
public class UserDataLogged implements IncentivizedEvent {

    // singleton pattern
    private static UserDataLogged INSTANCE;
    public static UserDataLogged getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDataLogged();
        }

        return INSTANCE;
    }
    private UserDataLogged() {}

    @Override
    public synchronized boolean checkEvent(String userUID) {
        /*
            This ought to just be a query vs USER_ACHIEVEMENT_TABLE to see whether this event's row
            value "consecutiveUpdates" is 5 or greater. (NOTE. Hard-coded values like this make my
            skin crawl, so I'd want to surface that in configuration however you all do that. Honestly,
            I kind of want to push a LOT more of this to configuration to the point of almost being
            a scripting language so we don't need code changes for new event/achievement tracking,
            but I'd need to see more examples of events to pick apart commonalities to do that,
            make maybe a few base classes more generic than my examples here.)

            NOTE this should wait on updateEvent call(s), but if this is operating client-side that's
            on the DB or datastore to handle that for us. If not, well, this is where we handle it.
         */

        return true;
    }

    @Override
    public void updateEvent(String userUID) {
        /*
            (See BirthReported::class for the assumptions/definitions used here.)

            This is a touch more complicated than BirthReported in that we want to track user behavior
            over time. Thus, check USER_ACHIEVEMENT_TABLE to see whether the user has this achievement
            being tracked yet OR whether it's tracked but their most recent trigger on this event is not
            yesterday: in either of these cases, the row will have some field which is "lastUpdatedDate"
            which is set to today, and "consecutiveUpdates" will be set to 1.

            If the most recent trigger on this event was in fact yesterday, increment the value stored
            in "consecutiveUpdates".

            If the most recent trigger on this event was today, just ignore this.
         */
    }
}
