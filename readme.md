# NGAC Authorisation System

This package has an authorisation system that supports the HTTP, HTTPS, CoAP and CoAP/DTLS protocols. A sample service consumer and service provider are also providerd all these protocols for easy integration to any system. 

Requirements
-------------

The following softwares are needed to execute the system

- Java 1.8
- My SQL server 5.7

Additionally, ***OpenSSL*** is needed to generate certificates. Please refer to cert generation.md in this folder to understand how the certificates are generated for this project. 

Project details
----------------

This NGAC system provides the following functionalities.

- ***VerifyAccess***:  To simply verify whether a service consumer (SC1) is allowed to consume service (S1) from service provider (SP1). If a consume request is allowed, it responds with ***HTTP 200 OK*** (or) ***CoAP 2.05***  or else it respond with ***HTTP 403 FORBIDDEN*** (or) ***CoAP  4.03***. If the request is incorrect it responds with ***HTTP 400 BAD_REQUEST*** (or) ***CoAP  4.00***

- ***RequestToken***: To simply verify whether a service consumer (SC1) is allowed to consume service (S1) from service provider (SP1). If a consume request is allowed, it responds with ***HTTP 200 OK*** (or) ***CoAP 2.05***  along with a **token** or else it respond with ***HTTP 403 FORBIDDEN*** (or) ***CoAP  4.03***. If the request is incorrect it responds with ***HTTP 400 BAD_REQUEST*** (or) ***CoAP  4.00***  

- ***GenerateRules***: This module is to generate rules in the below format to make this system compatible with other access control models.

  ​	ConsumerSystem                     ProviderSystem               AllowedService

  ​	             SC1                                           SP1                                      S1

  ​	             SC1                                           SP2                                      S2

  ​	             SC2                                           SP1                                      S1

  ​	             SC2                                           SP5                                      S5

  ​	             SC3                                           SP3                                      S3

  ​	             SC3                                           SP4                                      S4

  ​	             SC4                                           SP6                                      S6

  ​	            SC5                                            SP2                                      S2 

## NGAC database

A sample ngac database is created for demo purpose and placed in NGAC_Authorisation_System\resources\ngac_db.json This should be updated for any application accordingly. 

It is a json file in the below format

	  {
	 	"ArrowheadCloud":{
	 		"Operator": "operatorA",	
	   	"CloudName": "LC2",		
	 		"AuthenticationInfo": "A",			
	 		"PolicyClass": [
	 						{"PolicyClassName" : "PC1",
	 						"ArrowheadSystem" : [
	 												{"NodeType": "User", "ID": "1", "Name": "SystemName" , "Desc": "system description", "IP": "AAA.BBB.CCC.DDD", "Port": "XXXX", "AuthenticationInfo": "Password"},
	 												{"NodeType": "Obj", "ID": "2", "Name": "SystemName" , "Desc": "system description", "IP": "AAA.BBB.CCC.DDD", "Port": "XXXX", "AuthenticationInfo": "Password"}
	 											],
	 						"SystemAttributes":	[
	 												{"NodeType": "U_attr", "ID": "3", "Name": "AttrName" , "Desc": "Attribute description"},
	 												{"NodeType": "O_attr", "ID": "4", "Name": "AttrName" , "Desc": "Attribute description"}
	 											],
	  						"ArrowheadService":	[
	 												{"NodeType": "Oper", "ID": "5", "Name": "ServiceName" , "Desc": "Service description", "Metadata": "metadata of service"}
	 											],
	 						"Assignment":	[
	 											{"Node1": "1", "Node2": "3"},
	 											{"Node1": "2", "Node2": "4"}
	 										],
	 						"Association":	[
	 											{"Node1": "3", "Node2": "4", "ServiceNode": "5"},			
	 										],
	 						"Prohibition":[
	 										{"ProhibitionType":"U_attr", "Node1":"3","ServiceNode":"5","Node2":"4"}
	 									  ]
	 						},
	 					]
	 	"PolicyClass": [
	 						{"PolicyClassName" : "PC2",
	 						"ArrowheadSystem" : [...],
	 						"SystemAttributes":	[...],
	  						"ArrowheadService":	[...],
	 						"Assignment":	[...],
	 						"Association":	[...],
	 						"Prohibition":[...]
	 						},
	 					]
	 	
	 				}
	 	}
The Operator, CloudName and Authentication info represent the cloud details

Policy class is an array of JSON elements, each element provides the systems, their attributes, services, assignments, associations and prohibitions in that policy class. When access between systems depend on multiple factors, multiple ploicy classes are used to represent it. To understand how access rules are derived in case of multiple policy classes, refer to [NIST Special Publication 800-178](https://csrc.nist.gov/publications/detail/sp/800-178/final).

Executing the project
----------------------

For the current project, MySQL uses username:root and password:root, any changes should be reflected in the hibernate.cfg and hibernate_accounting.cfg.xml in the NGAC_Authorisation_System\src\main\resources folder.

All the keystore and truststore has password: password , this is used to view the keystore and truststore of any application.

To run the project, First execute the SQL script policydb.sql in NGAC_Authorisation_System folder and this loads anempty database policydb_ngac into the MySQL. 

To easily execute the project, bat files are created. In windows, double click those bat files to execute each system individually.  To execute the project from terminal or command prompt, go to folder and execute the command

```java -jar target\XXX-jar-with-dependencies.jar```

change XXX-jar-with-dependencies.jar to corresponding jar file.

First, the NGAC server should be started. It starts NGAC application in four different protocols (HTTP, HTTPS; CoAP, CoAP/DTLS) and then execute the provider and consumer applications of required protocols.

To try different consumer and producer systems, update the auth_request.json file in resources folder of service consumer project. change these fields to required consumer and producer and play with these settings. (No need to turn of the Authorisation server, simply close the consumer application and change these settings and execute, it should work!!!)

To change any other settings of server and provider, head to config.properties in their respective resources folder.

Note: Whenever the Authorisation system is closed, make sure to run the policydb.sql. This drops the existing tables and creates empty tables. At the moment, the Authorisation system populates the database and if it is not cleaned before execution, you will endup in hibernate errors.