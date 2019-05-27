**# ANZ Automation Task**

**### Technologies - Used**

- Java
- TestNG
- Maven
- Selenium-Java
- Lombok
- AWS-Grid-lastic

**###**  **Plugins**  **For Eclipse**

- TestNG
- Lombok
  - https://projectlombok.org/setup/eclipse

**### Commands To Run from terminal:**

- mvn test -Dtest=BorrowingCalculatorTest -DrunLocal=false -DbrowserName=chrome

- [browserName] Values : chrome/firefox
- [runLocal] : true/false
  - if [true] : Download the .exes from the following locations and assign the variables with location.
    - [chromeDriverInterfacePath] , [firefoxDriverInterfacePath] should be set based on the browser selection

- [http://chromedriver.chromium.org/downloads](http://chromedriver.chromium.org/downloads)
- [https://github.com/mozilla/geckodriver/releases](https://github.com/mozilla/geckodriver/releases)

-
  - if [false] : Add the Grid Hub details as below. For Interview pupose I added Gridlastic[AWS-Based-Grid] in automation.properties.
    -  set [gridHubHost] , [gridHubPort]
      - Eg: -DgridHubHost=[IPAddress/HostName of Hub] -DgridHubPort=[Port of Hub]

- test=BorrowingCalculatorTest

            - This is to specify the TestClass which we want to run. If User want to tun specific method add [TestClass#[TestMethodName]]



### **From Eclipse IDE:**

- Install TestNG plugin
  - Edit the automation.properties as per our requirement to run chrome/firefox -- LocalExecution / Grid Execution
- Run As TestNG Class / TestNG Suite

### **Results/Reports:**
- After the execution , Custome HTML report will be generated in the main directory of Codebase with name "automation-report.html"