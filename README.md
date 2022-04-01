# Numberwang

_Hello and welcome to Numberwang! The maths quiz that everyone is talking about!_


## Running Numberwang

`make run`

Can you guess the number? To try, run `curl localhost:8080/number=<guess>` where `<guess>`
is a number between 0 and 9. Take as many guesses as you need!

Each time you restart the numberwang server, the number changes.


## Testing Numberwang

First, make sure Numberwang isn't running (as the tests start it, and use numberwang's port 8080).

Then run `make test`


_That's numberwang_


