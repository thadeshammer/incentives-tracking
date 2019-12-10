# incentives-tracking
Toy skeletal example for tracking incentives or achievements for health tracking apps; hyper simplistic, sketched out in roughly 2.5 hours.

Intended Usage:
	Call the updateEvent() endpoint/api hook when an event has taken place so that, if it's an incentivized event, it will be correctly tracked. This needs to BLOCK checkEvent().
  
  The UI or backend may check a given event (by event type UID) whether a given incentivized event's requirements have been satisfied by a given user (by user UID) by calling checkEvent() hook/endpoint. Probably we'll want to notify a user IMMEDIATELY when an achievement is scored, as it were (by a pop-up with some fanfare perhaps) so the UI will need to call this immediately after calling updateEvent(); note that checkEvent really needs to be BLOCKED PER USER by updateEvent so we correctly tell the user when they're getting a pillow.
  
  NOTE we *could* consider modeling this as a callback, perhaps only for the client-side app (if there's already other stuff operating that way) but in terms of informing the service provider that it's time to mail a pillow somewhere, that should probably be another process that checks once per day or so.
  
Assumptions:
	Users are uniquely identified across the system by UID.
  
  Some "datastore" (DB) already exists that tracks all data collected about each user of the software/service and these events.
  
  Each Incentivized Event is uniquely identifiable from other events, either by UID, its name, endpoint, something. This application assumes there exists eventTypeUID trackable as an incentivized event, modeled as an enum.
  
  This code will be a statically linked library that leverages it; though it could be set up as a standalone REST service, it	would still need access to the data store(s) that the calling system has. Essentially whenever an event would be recorded under normal operation, checkEvent should be called here. We *could* more deeply integrate this, but my bias is to decouple as much as possible.

Short-comings:
  There are a lot, but one that really sticks out is that there's no built-in way to track whether an achievement is coupled with a physical reward (only in custom handling, i.e. somebody needs to be notified we need to ship an item).
  
  It would be REALLY nice to further abstractify the handler classes and allow much more of it to be configuration-driven so we don't necessarily need code changes for new events to be tracked, but without more examples I'd be pulling even more out of the air to showcase that, so I didn't.
  
  The shared interface does enforce a contract but could be used better: the traditional "iterate over this list of given user-associated events and determine whether each one is or isn't a completed achievement" pattern was my initial intention for it.
