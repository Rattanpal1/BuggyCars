BuggyCars
BuggyCar is a suite developed as a demonstrate an approach to automate testcases for buggy car webapp as a part of the interview process.

Installation/Prerequisites:

1. Java version "13.0.2"
2. Apache Maven 3.6.3
3. git version 2.25.1.windows.1
4. Eclipse IDE for Java Developers Version: 2019-12 (4.14.0) and Build id: 20191212-1212
5. Chrome browser. 
Note: Need to set environment variables for Java and Maven HOME.

#Steps to run the suite

Suite can be run at command line terminal using below three Steps:
1. git clone https://github.com/Rattanpal1/BuggyCars.git
2. cd BuggyCars
3) Execute this command: mvn clean test -DsuiteXmlFile=testng.xml
OR
3) Execute these commands: mvn clean, mvn test

Alternatively, the repository can be downloaded and run using any IDE. Below instructions can be followed to execute using eclipse IDE:
1. Clone the project in IDE.
2. Right click on pom.xml file in the root directory.
3. Select "Maven clean" option from "Run As" dropdown.
4. Select "Maven test" option from "Run As" dropdown.

Reporting:
1. HTML Extent reports gets generate at "BuggyCars\test-output\ExtentReportNG.html" path. 
2. Screenshots related to failed testcases gets accumulated at "BuggyCars\Screenshots" folder.

#CI CD Dev Ops pipeline can be accessed using below credentials:

URL: https://dev.azure.com/ Username: Rstest360@gmail.com password: testaccount!

Note: Devops pipelines are still under development because of "Azure DevOps Parallelism Request" (Limited access in trial version of the account.)

#API Test

N/A

#License Open source and trial versions of the softwares are used. The software versions can be updated in pom.xml file available at root location.
