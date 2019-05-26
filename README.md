**# ANZ Automation Task**

**### Technologies - Used**

- Java
- TestNG
- Selenium-Java
- Lombok
- AWS-Grid-lastic

**###**  **Plugins**  **For Eclipse**

- TestNG
- Lombok
  - https://projectlombok.org/setup/eclipse

**### Commands To Run from terminal:**

- **mvn**  **test -**** Dtest ****=BorrowingCalculatorTest-DrunLocal=true -DbrowserName=**** chrome**

- [browserName] Values : chrome/firefox
- [runLocal] : true/false
  - if [true] :
    - [chromeDriverInterfacePath] , [firefoxDriverInterfacePath] should be set based on the browser selection

- [http://chromedriver.chromium.org/downloads](http://chromedriver.chromium.org/downloads)
- [https://github.com/mozilla/geckodriver/releases](https://github.com/mozilla/geckodriver/releases)

-
  - if [false] :
    -  set [gridHubHost] , [gridHubPort]
      - Eg: -DgridHubHost=[IPAddress/HostName of Hub] -DgridHubPort=[Port of Hub]

- test=BorrowingCalculatorTest

            - This is to specify the TestClass which we want to run. If User want to tun specific method add [TestClass#[TestMethodName]]



### **From Eclipse IDE:**

- Install TestNG plugin
  - Edit the automation.properties as per our requirement to run chrome/firefox -- LocalExecution / Grid Execution
- Run As TestNG Class / TestNG Suite