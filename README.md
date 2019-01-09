# Java Temperature Control System

This project will be restructure in the coming months, and documentation has yet to be written.

### Project structure

* **client-common**: a common library module for the other maven modules. Contains all the data transfer objects and common dependencies.
* **consumer**: client skeleton which requests a specific `ArrowheadService` from the Orchestrator
* **provider**: client skeleton which registers a specific `ArrowheadService` into the Service Registry and runs a web server where the service is available

