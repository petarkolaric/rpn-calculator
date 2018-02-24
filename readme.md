### RPN Calculator

This code is for an RPN (Reverse Polish Notation) calculator.

#### Requirements

TODO - fill me in

#### To Run

```
./gradlew run
```

#### To Test
```
./gradlew test
```

#### Design Decisions

#### Assumptions Made

It is assumed that if there are not enough items in the stack for a command, we will leave the stack as-is and print a message to the user.
It is assumed that any valid character seperated by a space represents a distinct command. This includes individual numbers.
It is assumed that the user should be able to undo all operations.

NOTES
- line can contain an operator, or not contain one