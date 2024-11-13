import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//numbers to be added, hardcoded for now
Integer[] numArr = [1, 3, 5, 6, 8, 2, 5, 3]

Windows.callTestCase(findTestCase('switch to Standard'), [:], FailureHandling.STOP_ON_FAILURE)

def count = 0 //to keep track of the looping
for (num in numArr) {
	Windows.click(findWindowsObject("Object Repository/Standard/Button ${num}"))
	if (count == numArr.length - 1) {
		//last element, don't click button '+', just '=' (calc will add last number with itself if '=' is pressed after '+')
		Windows.click(findWindowsObject('Object Repository/Standard/Button Equals'))
	} else {
		//click add button
		Windows.click(findWindowsObject('Object Repository/Standard/Button Plus'))
		count++
	}
}

//verify result on calc is correct
Windows.verifyElementAttributeValue(findWindowsObject('Object Repository/Standard/Result'), "Name", "Display is ${numArr.sum()}", 3)
