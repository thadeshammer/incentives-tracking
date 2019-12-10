package com.thadeshammer.incentivetracker.incentivizedevents;

/**
 * DEV NOTE. I bet this can be further generalized; I'd guess there are likely to be more incentivized
 * events fitting the scheme of "One and Done" (i.e. the user does a thing one time and gets a reward,
 * e.g. signing up for the service, filling out their profile completely, adding a profile photo, whatever)
 * but since the underlying interface only has one method, I didn't see value in further abstracting
 * things at this time.
 *
 * Naturally more than one birth may be recorded per user at a time (in the event of twins or more) or
 * over a very long use case (maybe this user has another baby and reports that too) so not a "One and Done"
 * for this in particular.
 *
 * thade created on 12/10/2019
 */
public class BirthReported implements IncentivizedEvent {

    /*
        I used singletons here because it was a quick fix to my initial implementation pass
        stack allocating each of these helper objects when the switch was called, which I
        didn't like.
     */
    // singleton pattern
    private static BirthReported INSTANCE;
    public static BirthReported getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BirthReported();
        }

        return INSTANCE;
    }
    private BirthReported() {}

    @Override
    public boolean checkEvent(String userUID) {

        /*
            Assumption: there exists a db document/table that tracks birth reporting for a given
            user, independent of this library's needs, referred to here as USER_TABLE.

            There also exist a db doc/table explicitly for this library's needs, referred to here
            as USER_ACHIEVEMENT_TABLE.

             TODO cross ref USER_TABLE and USER_ACHIEVEMENT_TABLE to determine whether a birth
              was reported that hasn't yet been awarded for:

                - if no recent birth, just return false.

                - if recent birth which hasn't yet been celebrated, update the DB entry as celebrated,
                then return true. NOTE we really need to tell the UI we haven't celebrated yet,
                so if the caller here is the client-side app, return True so they can throw a fanfare!

                - if recent birth which has already been celebrated, we also need to check whether
                the order to ship the associated physical reward has been acted upon. So in this case,
                if the caller is client-side, we're going to return false OTHERWISE we return true. Now
                that I'm typing this, I'm realizing this is woefully inadequate: we may want to have
                separate hooks entirely for the backend check vs the mobile check (since each one
                refers to its own entry in the db table/doc anyway.

         */
        return validateThatThingsAreCopasetic(userUID);
    }

    private boolean validateThatThingsAreCopasetic(String userUID) {
        // Send them ALL PILLOWS EVERY TIME cost isn't an issue, right? Infinite pretty pillows.
        return true;
    }

    @Override
    public void updateEvent(String userUID) {
        /*
            TODO hit the DB. As mentioned above, births aren't one-and-done, they're just rare (no more than one event
            year in most cases) but we need to decide whether reporting the birth of twins, for instance, counts
            as a single reported birth OR as two reported births.

            Keep in mind, that this code is hit AFTER the DB's been updated with this, so what's being
            updated here is whatever tracking this Achievement's db document, not the event itself.

            So, if no birth has been recorded in USER_ACHIEVEMENT_TABLE for the associated date (which
            we'll get from the USER_TABLE) then record it. There will be two flags in this row/document
            named something like "hasBeenMailedPhysicalReward" and "hasExperiencedUIFanfare", both initially
            set to FALSE.
         */
    }
}
