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
             TODO query DB to verify user is recognized and that a birth was recently reported
                Depending on how this is used we can possibly offload this validation (trust)
                the caller here.
         */
        return validateThatThingsAreCopasetic(userUID);
    }

    private boolean validateThatThingsAreCopasetic(String userUID) {
        return true;
    }

    @Override
    public void updateEvent(String userUID) {
        /*
            TODO hit the DB. As mentioned above, births aren't one-and-done, they're just rare (no more than one event
            year in most cases) but we need to decide whether reporting the birth of twins, for instance, counts
            as a single reported birth OR as two reported births.

            Keep in mind, that this code is hit AFTER the DB's been updated with this, so what's being
            updated here is whatever tracking this Achievement needs, not the event itself.

            So, we check to see whether there's already an entry for this in the relevant achievement tracking db
            table; if so, there's nothing to update, otherwise, update it.

            In either case, check to see whether the
         */
    }
}
