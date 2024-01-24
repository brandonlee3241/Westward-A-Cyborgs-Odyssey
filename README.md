# CMPT276F23_group21

## Building the Game

1. Ensure you have Java JDK installed on your machine. You can check this by running `java -version` in your terminal. If you don't have Java installed, you can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. Navigate to the project directory in your terminal.
3. Run `mvn clean install` to build the project and install any necessary dependencies.

## Running the Game

1. After building the project, you can run the game with the command `java -jar Phase2-1.0-SNAPSHOT.jar`.

## Testing the Game

We use JUnit for our unit tests. Here's how to run them:

1. Ensure you have JUnit 5 installed. If not, you can add it as a dependency in your `pom.xml` file.
2. Navigate to the project directory in your terminal.
3. Run `mvn test` to execute the tests.

### Specific Tests

#### UnitTest

1. Set up JUnit Test as:
    - Name: UnitTest
    - Test Class: UnitTest
    - JUnit Version: JUnit 5
2. Run UnitTest: Run JUnit test of name "UnitTest"

#### Running Game Test

1. Set Up JUnit Test as:
    - Name: RunningTest
    - Test Class: RunningFailTest
    - JUnit Version: JUnit 5
2. Run RunningTest: 
    - Run JUnit test of name "RunningTest"
    - Run three times:
        1. Pick up a reward then run into a trap
        2. Pick up a reward then run into a monster
        3. Pick up a reward then run into exit
