# Intergalactic Translator
This application is a translator for an intergalactic market transaction. Written in java/spring.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Installing
Just download or clone this repository to your local java/spring environment

## Running The Test
The project can be run from the spring application itself by importing the project into spring workspace and run the application as a java application.
While it run, the application will ask to input an entry that will be translated to expected output below. The input format can also be found in the test input section below.

### Input Example
The application can process single or multiple inputs in one batch. The inputs can be seperated only by dot, question mark and exclamation mark.
For example:
glob is I. prok is V. pish is X. tegj is L. glob glob Silver is 34 Credits. glob prok Gold is 57800 Credits. pish pish Iron is 3910 Credits. how much is pish tegj glob glob ?. how many Credits is glob prok Silver ?. how many Credits is glob prok Gold ?. how many Credits is glob prok Iron ?. how much wood could a woodchuck chuck if a woodchuck could chuck wood !

### Expected Output
To make it easier to read, the output will be serve in several lines, depends on the quantity of the input. Below is the output of the input example.
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 782 Credits
I have no idea what you are talking about

### Error Handling & Rules
errorMessage = "I have no idea what you are talking about".
* The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. (They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX). "D", "L", and "V" can never be repeated.
* "I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted.
* Letter case wont be a problem in this application, we handle any form of letter case from the input. The application will give the expected output with the same format as the user input.
* If the statement contains any key that's not stated earlier, the application will return the the errorMessage as well as the unknown input format.
* A statement that assign a key to a value that's not a roman numeral symbols will end up to an error with errorMessage.
* If the statement contains a key of roman numeral that has not been set, the application will return the errorMessage.
* Input the word "exit" to terminate the program.

## Deployment
### Environment
* JDK 10
* Spring Framework 3

### Built With
* Faceted Project Validation Builder
* Java Builder 
* Maven Project Builder
* Spring Boot Validations Builder
* Spring Project Builder

## Authors
* **Trisno Raynaldy Panjaitan** - [Github](https://github.com/raylucker) | [Bitbucket](https://bitbucket.org/raypanjaitan/)
