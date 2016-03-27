# Spray Blocking Requests

## Update

*NOTE*: The whole premise of this test was flawed. I was testing by firing up 4 tabs in Chrome.
For whatever reason Chrome was limited to 1 request per server (maybe that happens when it's a non-standard port,
but doesn't matter the reason at this point). When I tried the same test in Safari and Chrome, I got the
parallelism I was looking for.

The tests exhibit the same "problem", but I'm guessing without a specific config that's actually a matter of
spray-testkit purposefully limiting parallelism for ease of debugging.

## Overview

An example of blocking, sequential requests in Spray Routing (undesired behavior).

Our requests take 5 seconds to complete (scheduled with the `Waiter` Actor).

If you make multiple requests they'll "stack". So 4 requests will take 20 seconds
to complete. This was unexpected behavior for me (at least in run mode outside of testing).

To reproduce the issue you can run a server:

    sbt
    > re-start

And then make multiple requests through your web-browser to http://localhost:9000/

Or you can run the tests:

    sbt
    > test
