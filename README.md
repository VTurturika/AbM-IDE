# Abstract Machines IDE
Interpreter and fully IDE for development programs for abstract algorithms machines. Software may be used for learn algorithms theory.

### Main features:

* Emulate work of [Unlimited Register Machine](https://en.wikipedia.org/wiki/Register_machine), [Turing Machine](https://en.wikipedia.org/wiki/Turing_machine), [Markov algorithms](https://en.wikipedia.org/wiki/Markov_algorithm).
* Run code written for, [Unlimited Register Machine](https://en.wikipedia.org/wiki/Register_machine), [Turing Machine](https://en.wikipedia.org/wiki/Turing_machine), [Markov algorithms](https://en.wikipedia.org/wiki/Markov_algorithm).
* Support regular expression in Markov programs.
* Runtime highlight and analyze code.
* Step-by-step debug (work in process).

## How to use ?

Project has built under [Maven](https://maven.apache.org/), required Java 8.

#### Typical actions

Build executable jar:
```
mvn jfx:jar
```

Run tests:
```
mvn test
```

Start application:
```
java -jar target/jfx/app/Abstract-Machines-IDE.jar
```
