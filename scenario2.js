const puppeteer = require('puppeteer')
const assert = require('assert')

let userData = {
    "noOfDependents": 0,
    "primaryIncome": 82600,
    "otherIncome": 10000,
    "livingExpenses": 500,
    "currentHomeLoanRepayments": 0,
    "otherLoanPayments": 100,
    "otherCommitments": 0,
    "totalCreditCardLimit": 10000,
    "estimatedLimit": "$479,000"
}

try {
    (async () => {

        const browser = await puppeteer.launch()
        const page = await browser.newPage()
        await page.setViewport({ width: 1280, height: 800 })
        await page.goto('https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/')
        await page.screenshot({ path: 'scenario2-Screenshot-1.png' })
        await page.waitForSelector('#application_type_single')
        await page.click('label[for=\'application_type_single\']')

        await page.waitForSelector('select[title=\'Number of dependants\']')
        await page.select('select[title=\'Number of dependants\']', userData.noOfDependents.toString())
        
        await page.click('label[for=\'borrow_type_home\']')
        await page.screenshot({ path: 'scenario2-Screenshot-2.png' })
        await page.type('input[aria-labelledby=\'q2q1\']', userData.primaryIncome.toString())
        await page.type('input[aria-labelledby=\'q2q2\']', userData.otherIncome.toString())
        await page.type('#expenses', userData.livingExpenses.toString())
        await page.type('#homeloans', userData.currentHomeLoanRepayments.toString())
        await page.screenshot({ path: 'scenario2-Screenshot-3.png' })
        await page.type('#otherloans', userData.otherLoanPayments.toString())
        await page.type('input[aria-labelledby=\'q3q4\']', userData.otherCommitments.toString())
        await page.type('#credit',userData.totalCreditCardLimit.toString())
        await page.screenshot({ path: 'scenario2-Screenshot-4.png' })
        await page.click('button[class*=\'btn--borrow__calculate\']')
        await page.waitForSelector('span[aria-live=\'assertive\'] span')
    
        const element = await page.$("span[aria-live=\'assertive\'] span")
        const actualEligibleLoanAmount = await (await element.getProperty('textContent')).jsonValue()
        console.log("Eligible Amount ===> "+actualEligibleLoanAmount);
        await page.screenshot({ path: 'scenario2-Screenshot-5.png' })

        await page.click('button[class=\'start-over\']')

        const primaryIncomeElement = await page.$("input[aria-labelledby=\'q2q1\']")
        const primaryIncomeElementText = await (await primaryIncomeElement.getProperty('textContent')).jsonValue()

        await page.screenshot({ path: 'scenario2-Screenshot-6.png' })
        assert.equal(primaryIncomeElementText,'')
        await page.screenshot({ path: 'scenario2-Screenshot-7.png' })

        await browser.close()

    })()
} catch (err) {
    console.error(err)
}