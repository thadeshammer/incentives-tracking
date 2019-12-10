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
     * An incentivized event implementing this MUST implement checkIncentivizedEvent which is to be
     * called each time a user-associated event occurs.
     *  - checkIncentivizedEvent must:     *
     *      perform analysis as to whether the user now qualifies for any tracked rewards,
     *
     *
     * @param userUID
     * @return
     */
    boolean checkIncentivizedEvent(String userUID);

    /**
     * Record the event in terms of whether this furthers or completes progress on this achievement.
     * I'd posit that this will virtually always result in some document/table in a DB getting a new
     * entry or having an existing entry updated.
     *
     * Example 1: Did the user report a birth today
     *
     * @param userUID
     */
    void updateEvent(String userUID);
}
