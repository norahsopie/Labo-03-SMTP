# HEIGVD-RES-2016-Labo-SMTP 
 
**Author**: Dongmo Annie Sandra / Sopie Noulala Norah Jeannine   
**Date**: 2016-02-23  
**Instructors** Olivier Liechti /  
Cliquer [ici](git@github.com:norahsopie/Labo-03-SMTP.git) pour aller sur le repos

## Objectives

In this lab, we will develop a client application (TCP) in Java. This client application will use the Socket API to communicate with a SMTP server. The code that we are going to write will include a **partial implementation of the SMTP protocol**. These are the objectives of the lab:

* Make practical experiments to become familiar with the **SMTP protocol**. 

* Understand the notions of **test double** and **mock server**, which are useful when developing and testing a client-server application. 

* Understand what it means to **implement the SMTP protocol** and be able to send e-mail messages, by working directly on top of the Socket API (i.e. we are not allowed to use a SMTP library).

* **See how easy it is to send forged e-mails**, which appear to be sent by certain people but in reality are issued by malicious users.

* **Design a simple object-oriented model** to implement the functional requirements described in the tasks specification.

## Description of the project

The aim of this project is to develop a client application (TCP) in Java than enable the user to send pranks to a certains group of people. This client application will use the Socket API to communicate with a SMTP MockMock server. The forged mails should appear to be sent by certain people but in reality are issued by malicious users. 

## Class Diagrams
This is the Diagram of classes of our project  

![Diagrame de classe](figures\UML_Diagram.jpg)
## Description of the implementation
The webcast proposed by our teacher was of great help. We implement our project like this. 
  [Youtube Webcast part 1](https://youtu.be/ot-bDyqgTtk)  
  [Youtube Webcast part 2](https://youtu.be/Nj34XicS6JM)  
  [Youtube Webcast part 3](https://youtu.be/LoFKsT9Rj10)  
  [Youtube Webcast part 4](https://youtu.be/OrSdRCt_6YQ) 

## Installation configuration and usage of the project
To correctly use this application, first of all read wel the objectives and the description of this project to understand more what it is all about. Then you can download files in this repository and run it on your favorite IDE especially on Netbeans (where i implement it), then enjoy it.

## Installation and setup of the MockMock server

The easiest way to install and run MockMock is by downloading the jar file [here](https://github.com/tweakers-dev/MockMock/blob/master/release/MockMock.jar?raw=true) (right click -> "save target as"). Extract it to any place you like and start the server by running:
`java -jar MockMock.jar`

This will run MockMock on the default ports 25 (for SMTP) and 8282 (the web interface). Please note you might need root permissions on some systems to let it listen on port 25.
After it started you can use your internet browser to navigate to [http://localhost:8282] (replace host name and web port if necessary). This will now show you the emails it received (which should be none at the moment). You can now send emails using the built in SMTP server running on port 25 by default. The emails should be visible via the web interface. To run MockMock on another port, you can start it with the following parameters:
`java -jar MockMock.jar -p 25000 -h 8080`

This will run MockMock on SMTP port 25000 and http port 8080.

