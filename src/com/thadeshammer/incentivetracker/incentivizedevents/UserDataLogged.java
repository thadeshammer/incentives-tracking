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
    public synchronized boolean checkIncentivizedEvent(String userUID) {
        return validate(userUID);
    }

    private boolean validate(String userUID) {

        /*
            TODO We'll need to be tracking this in the DB in its own document or table, so that
            we can keep track of how many times the user's logged data, how many days this has
            happened, and whether it also happened yesterday.

            If the user's NOT yet tracked for this achievement, OR they are BUT they failed to
            log data yesterday, set them up to be tracked anew: their
         */

        return true;
    }
}
