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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.WindowsTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Windows.startApplicationWithTitle('C:\\Windows\\System32\\calc.exe', 'Calculator')

//verify not already in Standard mode by checking if
// - element "Trigonometry" present (Scientific)
// - element "Bitwise" present (Programmer)
// - element "Difference between dates" (Date calc)
// - element "Inequalities" (Graphing)
// not present
//set FailureHandling.OPTIONAL so the run continues when false
boolean inStandard = Windows.verifyElementNotPresent(findWindowsObject('Object Repository/Scientific/Text Trigonometry'), 3, FailureHandling.OPTIONAL) &&
	Windows.verifyElementNotPresent(findWindowsObject('Object Repository/Programmer/Text Bitwise'), 3, FailureHandling.OPTIONAL) &&
	Windows.verifyElementNotPresent(findWindowsObject('Object Repository/Date calc/Text Difference between'), 3, FailureHandling.OPTIONAL) &&
	Windows.verifyElementNotPresent(findWindowsObject('Object Repository/Graphing/Text Inequalities'), 3, FailureHandling.OPTIONAL)

	
if (!inStandard) {
	//if false, then we open burger menu
	Windows.click(findWindowsObject('Object Repository/Burger Menu'))
	//click the Standard menu
	Windows.click(findWindowsObject('Object Repository/Menu Standard'))
	//verify again
	Windows.verifyElementNotPresent(findWindowsObject('Object Repository/Scientific/Text Trigonometry'), 3)
	Windows.verifyElementNotPresent(findWindowsObject('Object Repository/Programmer/Text Bitwise'), 3)
	Windows.verifyElementNotPresent(findWindowsObject('Object Repository/Date calc/Text Difference between'), 3)
	Windows.verifyElementNotPresent(findWindowsObject('Object Repository/Graphing/Text Inequalities'), 3)
}