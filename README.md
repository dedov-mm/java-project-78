[![Actions Status](https://github.com/dedov-mm/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/dedov-mm/java-project-78/actions)
[![Actions Status](https://github.com/dedov-mm/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/dedov-mm/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/ad32bb43dd701bd085d2/maintainability)](https://codeclimate.com/github/dedov-mm/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/ad32bb43dd701bd085d2/test_coverage)](https://codeclimate.com/github/dedov-mm/java-project-78/test_coverage)

# Data validator 
Data validator is a project that can be used to check the correctness of any data. Define a scheme and check data.

## Getting Started

### Setup

```bash
make build
```

### Run

```bash
cd ~/projects/java-project-78/app/src/main/java
javac hexlet/code/Validator.java hexlet/code/schemas/StringSchema.java
jshell --class-path .
```
### Exit
```bash
/exit
```

The validator works as follows: first, a validator object is created, then we create and configure a data validation schema. After that, we perform data validation using the created scheme. 

The validation scheme can be configured using various methods that the scheme provides. You can chain methods to build a schema. Different types of data are validated using their own schemes.


