# Date Difference Challenge

Create an application that can read in pairs of dates in the following format -
## DD MM YYYY, DD MM YYYY
Validate the input data, and compute the difference between the two dates in days.
Output of the application should be of the form -
## DD MM YYYY, DD MM YYYY, difference


## Getting Started

### Download or clone the application using url
https://github.com/VivekKhandre/DateDiffApplication.git or git@github.com:VivekKhandre/DateDiffApplication.git
### Build

To build the project, just run `mvn clean package`.
The built JAR along with sample inputs can be found in the `./target/` directory.

### Test

Building with `mvn clean package` already runs the unit tests.

`mvn clean verify` would also run tests.

## Example usage

Invoke the Date Difference Application as follows:

```sh
## Command Line 
java -jar DateDifference-<version>-SNAPSHOT.jar

## From file 
java -jar DateDifference-<version>-SNAPSHOT.jar [dates.txt]

For example,
java -jar target/DateDifference-0.0.1-SNAPSHOT.jar 

```
