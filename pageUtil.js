
function fillLoanApplication(scenarioDetails,page,userData){

        console.log("Starting the filling details....")
        page.waitForSelector('#application_type_single')
        page.click('label[for=\'application_type_single\']')

        page.waitForSelector('select[title=\'Number of dependants\']')
        page.select('select[title=\'Number of dependants\']', userData.noOfDependents.toString())
            
        page.click('label[for=\'borrow_type_home\']')
        page.screenshot({ path: scenarioDetails+'-Screenshot-2.png' })
        page.type('input[aria-labelledby=\'q2q1\']', userData.primaryIncome.toString())
        page.type('input[aria-labelledby=\'q2q2\']', userData.otherIncome.toString())
        page.type('#expenses', userData.livingExpenses.toString())
        page.type('#homeloans', userData.currentHomeLoanRepayments.toString())
        page.screenshot({ path: scenarioDetails+'-Screenshot-3.png' })
        page.type('#otherloans', userData.otherLoanPayments.toString())
        page.type('input[aria-labelledby=\'q3q4\']', userData.otherCommitments.toString())
        page.type('#credit',userData.totalCreditCardLimit.toString())
        page.screenshot({ path: scenarioDetails+'-Screenshot-4.png' })
            console.log("Completed the filling details....")
        return page;
}

exports.fillLoanApplication = fillLoanApplication