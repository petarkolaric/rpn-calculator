### RPN Calculator

This code is for an RPN (Reverse Polish Notation) calculator.

#### Requirements

To run this application you will need to have Java 8 (or later) installed with correct environment variables set up.

#### To Run

```
./gradlew run
```

#### To Test
```
./gradlew test
```

#### Design Decisions

Due to the command oriented nature of the problem, a command pattern approach was taken. As this was a coding challenge, the use of external frameworks (such as any DI frameworks) was avoided.

#### Assumptions Made

It is assumed that any valid character separated by a space represents a distinct command. This includes individual numbers.
It is assumed that the user should be able to undo all operations.