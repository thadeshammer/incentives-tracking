package com.thadeshammer.incentivetracker.incentivizedevents;

/**
 * Simple base for incentivized events. This represents my initial idea, which is to encapsulate each
 * incentivized event with its own object, as each one may require custom logic for tracking them.
 *
 * Definitions:
 *
 * ACHIEVEMENT: an incentivized event's requirements for reward have been satisfied.
 * REQUIREMENTS: markers that must be met in order to qualify for a reward.
 *
 * thade created on 12/10/2019
 */
public interface IncentivizedEvent {
    /**
     * This represents my initial idea: to encapsulate each incentivized event within their own object,
     * as each one may require its own non-trivial logic behind validating that its been "achieved" by
     * a given user and thus that a reward has been earned.
     *
     * An incentivized event implementing this MUST implement checkEvent which is to be
     * called each time a user-associated event occurs.
     *  - checkEvent must:     *
     *      perform analysis as to whether the user now qualifies for any tracked rewards,
     *
     *
     * @param userUID
     * @return
     */
    boolean checkEvent(String userUID);

    /**
     * Record the event in terms of whether this furthers or completes progress on this achievement.
     * I'd posit that this will virtually always result in some document/table in a DB getting a new
     * entry or having an existing entry updated.
     *
     * Example 1: Did the user report a birth today
     *
     * NOTE. This is obviously going to need to BLOCK checkEvent for a given user. This informs how
     * we track this in the DB (which is why you may notice the implication in how I talk about it
     * elsewhere that we're tracking for this Per User, e.g. a Mongo document for a user tracking
     * their progress on all of their incentivized events.
     *
     * @param userUID
     */
    void updateEvent(String userUID);
}
