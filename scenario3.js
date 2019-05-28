const puppeteer = require('puppeteer')
const assert = require('assert')

let userData = {
    "livingExpenses": 1,
    "errorMessage": "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 100 641."
}

try {
    (async () => {

        const browser = await puppeteer.launch()
        const page = await browser.newPage()
        await page.setViewport({ width: 1280, height: 800 })
        await page.goto('https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/')
        await page.waitForSelector('#application_type_single')
        await page.screenshot({ path: 'scenario3-Screenshot-1.png' })


        await page.type('#expenses', userData.livingExpenses.toString())
        
        await page.screenshot({ path: 'scenario2-Screenshot-2.png' })
        await page.click('button[class*=\'btn--borrow__calculate\']')
        await page.waitForSelector('span[aria-live=\'assertive\']')

        const errorMessageElement = await page.$("span[aria-live=\'assertive\']")
        const errorMessageElementElementText = await (await errorMessageElement.getProperty('textContent')).jsonValue()

        await page.screenshot({ path: 'scenario2-Screenshot-3.png' })
        assert.equal(errorMessageElementElementText.toString().trim(),userData.errorMessage.toString())
        await page.screenshot({ path: 'scenario2-Screenshot-4.png' })

        await browser.close()

    })()
} catch (err) {
    console.error(err)
}