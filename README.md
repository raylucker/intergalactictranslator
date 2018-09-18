# Intergalactic Translator
This application is a translator for an intergalactic market transaction. Written in java/spring.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Installing
Just download or clone this repository to your local java/spring environment

## Running The Test
The project can be run from the spring application itself by importing the project into spring workspace and run the application as a java application.
While it run, the application will ask to input an input that will be translated to expected output below. The input format can also be found in the test input section below.

### Library
* glob is I
* prok is V
* pish is X
* tegj is L

### Test Input
* how much is pish tegj glob glob ?
* how many Credits is glob prok Silver ?
* how many Credits is glob prok Gold ?
* how many Credits is glob prok Iron ?
* how much wood could a woodchuck chuck if a woodchuck could chuck wood ?

### Expected Output
* pish tegj glob glob is 42
* glob prok Silver is 68 Credits
* glob prok Gold is 57800 Credits
* glob prok Iron is 782 Credits
* I have no idea what you are talking about

### Error Handling
* Letter case wont be a problem in this application, we handle any form of letter case from the input. The application will give the expected output with the same format as the user input.
* if the input contains any key that's not stated in the library section above, the application will return the "I have no idea what you are talking about" as well as the unknown input format.

## Deployment
### Environment
* JDK 10
* Spring Framework 5

### Built With
* Faceted Project Validation Builder
* Java Builder 
* Maven Project Builder
* Spring Boot Validations Builder
* Spring Project Builder

## Authors
* **Trisno Raynaldy Panjaitan** [Github](https://github.com/raylucker) [Bitbucket](https://bitbucket.org/raypanjaitan/)
