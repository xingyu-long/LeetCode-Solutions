"""
For the purposes of this interview, imagine that we own a store. This

store doesn't always have customers shopping: there might be some long

stretches of time where no customers enter the store. We've asked our

employees to write simple notes to keep track of when customers are

shopping and when they aren't by simply writing a single letter every

hour: 'Y' if there were customers during that hour, 'N' if the store

was empty during that hour.

 

For example, our employee might have written "Y Y N Y", which means

the store was open for four hours that day, and it had customers

shopping during every hour but its third one.

 

  hour: | 1 | 2 | 3 | 4 |

  log:  | Y | Y | N | Y |

                  ^

                  |

            No customers during hour 3

 

We suspect that we're keeping the store open too long, so we'd like to

understand when we *should have* closed the store. For simplicity's

sake, we'll talk about when to close the store by talking about how

many hours it was open: if our closing time is `2`, that means the

store would have been open for two hours and then closed.

 

  hour:     | 1 | 2 | 3 | 4 |

  log:      | Y | Y | N | Y |

  closing_time: 0   1   2   3   4

                ^           ^

                |           |

         before hour #1 after hour #4

 

(A closing time of 0 means we simply wouldn't have opened the store at

all that day.)

 

First, let's define a "penalty": what we want to know is "how bad

would it be if we had closed the store at a given hour?" For a given

log and a given closing time, we compute our penalty like this:

 

  +1 penalty for every hour that we're *open* with no customers

  +1 penalty for every hour that we're *closed* when customers would have shopped

 

For example:

 

  hour: | 1 | 2 | 3 | 4 |   penalty = 3:

  log: | Y | Y | N | Y | (three hours with customers after closing)

  penalty: | * | * |   | * |

           ^

           |

         closing_time = 0

 

  hour: | 1 | 2 | 3 | 4 |   penalty = 2:

  log: | N | Y | N | Y |  (one hour without customers while open +

  penalty: | * |   |   | * |   one hour with customers after closing)

                   ^

                   |

                 closing_time = 2

 

  hour: | 1 | 2 | 3 | 4 |   penalty = 1

  log: | Y | Y | N | Y | (one hour without customers while open)

  penalty: |   |   | * |   |

                           ^

                           |

                         closing_time = 4

 

Note that if we have a log from `n` open hours, the `closing_time`

variable can range from 0, meaning "never even opened", to n, meaning

"open the entire time".

 

1)

Write a function `compute_penalty` that computes the total penalty, given

  a store log (as a space separated string) AND

  a closing time (as an integer)

 

In addition to writing this function, you should use tests to

demonstrate that it's correct. Do some simple testing, and then quickly

describe a few other tests you would write given more time.

 

## Examples

 

compute_penalty("Y Y N Y", 0) should return 3

compute_penalty("N Y N Y", 2) should return 2

compute_penalty("Y Y N Y", 4) should return 1


2)

Write another function named `find_best_closing_time` that returns

the best closing time in terms of `compute_penalty` given just a

store log. You should use your answer from part 1 to solve this problem.

 

Again, you should use tests to demonstrate that it's correct. Do

some simple testing, and then quickly describe a few other tests

you would write given more time.  

 

## Example

find_best_closing_time("Y Y N N") should return 2
"""

from math import inf


def compute_penalty(log: str, closing_time: int) -> int:
    states = log.split()
    if not states:
        return 0
    n = len(states)
    if closing_time > n:
        return -1
    res = 0
    for i in range(n):
        if i < closing_time:
            if states[i] == "N":
                res += 1
        else:
            #  the store is closed from now
            if states[i] == "Y":
                res += 1
    return res


assert compute_penalty("Y Y N Y", 0) == 3
assert compute_penalty("N Y N Y", 2) == 2
assert compute_penalty("Y Y N Y", 4) == 1
assert compute_penalty("", 0) == 0
assert compute_penalty("Y Y N Y", 5) == -1


def find_best_closing_time(log: str) -> int:
    n = len(log.split())
    penalty = inf
    res = -1
    for i in range(n + 1):
        cost = compute_penalty(log, i)
        if cost < penalty:
            penalty = cost
            res = i
    return res


assert find_best_closing_time("Y Y N Y") == 2
assert find_best_closing_time("Y Y N N") == 2
assert find_best_closing_time("Y Y Y Y") == 4
assert find_best_closing_time("N N N N") == 0
