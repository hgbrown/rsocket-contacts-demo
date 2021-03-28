# RSocket Server Demo

- [Watch YouTube video](https://youtu.be/GcBl9byna68)

- [Read Blog article](https://hbrown.dev/kotlin/2021/03/28/rsocket-server-demo-aws-ec2-deployment.html)

Demonstrates how to easy it is to create a RSocket server with Spring Boot.

This demo includes only a sample of a request-stream interaction using a search of a contact database as the example.

This demo depends on:

- Gradle (v 6.8.3)
- Kotlin (v 1.4.31)
- JUnit (v 5.7.1)
- [Strikt](https://strikt.io) (v 0.29.0)

## Building the code

To build the code, run:

```shell
./gradlew build
```

## Running the tests

To run the tests:

```shell
./gradlew test
```

## Running the server

To run the tests:

```shell
./gradlew bootRun
```

## Testing RSocket endpoints using [rsc](https://github.com/making/rsc)

If you want to see the full debug information, use the `--debug` flag:

```shell
rsc --debug --stream --load src/test/resources/data/name-search.json --route v1.contact.search tcp://localhost:5000
```

This example will search contacts by name.

The following example, searches by multiple properties and pipes the output through [jq](https://stedolan.github.io/jq/) to make it more readable.

```shell
rsc --stream --load src/test/resources/data/multiple-search.json --route v1.contact.search tcp://localhost:5000 | jq
```

which given the test data should look something like this:

```json
{
  "id": 1,
  "firstName": "Amy",
  "lastName": "Aniston",
  "mobileNumber": "27830000000",
  "email": "amanda@one.com"
}
```
```json
{
  "id": 3,
  "firstName": "Cindy",
  "lastName": "Crawford",
  "mobileNumber": "27813333333",
  "email": "cc@three.com"
}
```
```json
{
  "id": 4,
  "firstName": "Donald",
  "lastName": "Drew",
  "mobileNumber": "27804444444",
  "email": "drew@four.co.za"
}
```
