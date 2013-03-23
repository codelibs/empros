Empros
======

## Overview

Empros is Event Management and Processing System.

## Usage

Empros provides a server and client(agent) component.

### Run Server

Empros server component is provided as WAR component to run on Servlet 3.0 container.
You can deploy the war file on your application server. 
If you want to try it easily, just run the following command:

    $ mvn tomcat7:run

### Send Events

#### Send Single Event

    $ curl http://localhost:8080/empros/events/ -H "Content-type: application/json" \
      -X POST -d "{\"aaa\":1,\"bbb\":2}"

or

    $ curl http://localhost:8080/empros/events/ -H "Content-type: application/json" \
      -X POST -d "[{\"aaa\":1,\"bbb\":2}]"

#### Send Multiple Events

    $ curl http://localhost:8080/empros/events/ -H "Content-type: application/json" \
      -X POST -d "[{\"aaa\":1,\"bbb\":2},{\"ccc\":\"CCC\",\"ddd\":\"DDD\"}]"

## Development

### Environment

- Java 7
- Eclipse (m2e, m2e-wtp, Spring Tool Suite)
- Maven 3

