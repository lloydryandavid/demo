# demo

This is repository demonstrates the possibility of starting up a web service locally, and its dependencies via test
containers. (e.g. postgres intance)  

There are a couple of dependencies to run the tests, and generate the test report:
1. Java SDK 1.8
2. Maven
3. Allure (via node)  

To run the test, please follow the instructions below:

1. Do a maven clean install on the parent project directory.  
``demo$ mvn clean install -DskipTests``  

2. Change directory to cucumber-tests.  
`` demo$ cd cucumber-tests``  

3. Compile, and process the needed resources for the test.  
``cucumber-tests$ mvn clean compile test-compile process-test-resources``  

4. Execute the test and state the profile of the test.  
``cucumber-tests$ mvn test -Dtest=TestRunner -Dspring.profiles.active=local``  

Obviously, you can create another profile for the purpose of test execution on another environment. 
(e.g. run the tests against in a deployed environment) To do this, one needs to add another test runner and setup its 
context configuration. I will demonstrate this once I get the time to do so.
