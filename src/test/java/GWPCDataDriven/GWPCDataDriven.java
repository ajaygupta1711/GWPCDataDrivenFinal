package GWPCDataDriven;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class GWPCDataDriven {

		public static void main(String[] args) throws IOException, InterruptedException {
			
			// Invoke the browser and open the link
			
//			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.get("https://pc-qa2-corecon.emc.dev-1.us-east-1.guidewire.net/PolicyCenter.do");
			
			// Maximize the browser window and wait for populating all web elements
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));
			
			// Create the object of excel driven class
			
			DataLoginGWPC dlogin = new DataLoginGWPC();
			ArrayList <String> data = dlogin.getData("Login");
			
			// Enter the username & password and click on Login button
			
			driver.findElement(By.name("Login-LoginScreen-LoginDV-username")).sendKeys(data.get(1));
			driver.findElement(By.name("Login-LoginScreen-LoginDV-password")).sendKeys(data.get(2));
			driver.findElement(By.id("Login-LoginScreen-LoginDV-submit")).click();
			
// Account Creation		
			
			// Select the New Account option under Account toolbar
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='TabBar-AccountTab'] [class='gw-action--expand-button']"))).click();
			driver.findElement(By.xpath("//div[@id='TabBar-AccountTab-AccountTab_NewAccount']//div[text()='New Account']")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("NewAccount-NewAccountScreen-NewAccountSearchDV-GlobalContactNameInputSet-Name"))).sendKeys(data.get(3));
			
//			driver.findElement(By.cssSelector("div[id='TabBar-AccountTab'] div[class='gw-icon gw-icon--expand']")).click();
//			driver.findElement(By.xpath("//div[@id='TabBar-AccountTab-AccountTab_NewAccount']//div[text()='New Account']")).click();
//			driver.findElement(By.name("NewAccount-NewAccountScreen-NewAccountSearchDV-GlobalContactNameInputSet-Name")).sendKeys(data.get(3));
			
			driver.findElement(By.id("NewAccount-NewAccountScreen-NewAccountSearchDV-SearchAndResetInputSet-SearchLinksInputSet-Search")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='NewAccount-NewAccountScreen-NewAccountButton']//div[text()='te New Account']"))).click();
//			driver.findElement(By.xpath("//div[@id='NewAccount-NewAccountScreen-NewAccountButton']//div[text()='te New Account']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='NewAccount-NewAccountScreen-NewAccountButton-NewAccount_Company']//div[text()='Company']"))).click();
//			driver.findElement(By.xpath("//div[@id='NewAccount-NewAccountScreen-NewAccountButton-NewAccount_Company']//div[text()='Company']")).click();
			
			
			// Enter/Select the value on mandatory fields on New Account screen
			
			driver.findElement(By.name("CreateAccount-CreateAccountScreen-CreateAccountDV-ApplicationReceivedDate_Ext")).sendKeys(data.get(4));
			driver.findElement(By.name("CreateAccount-CreateAccountScreen-CreateAccountDV-ClearanceDate_Ext")).sendKeys(data.get(5));
			driver.findElement(By.name("CreateAccount-CreateAccountScreen-CreateAccountDV-Nickname")).sendKeys(data.get(6));
			
			driver.findElement(By.name("CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-AddressLine1")).sendKeys(data.get(7));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-City']//input[@name='CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-City']")).sendKeys(data.get(8));
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("[class='gw-vw--value'] [name='CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-State']")).click();
			
			driver.findElement(By.xpath("//div[@id='CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-City']//input[@name='CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-City']")).sendKeys(data.get(9));
			driver.findElement(By.cssSelector("[class='gw-vw--value'] [name='CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-State']")).click();
			Thread.sleep(2000);
			WebElement selectStateDropdown = driver.findElement(By.xpath("//select[@name='CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-State']"));
			Select dropdown = new Select(selectStateDropdown);
			Thread.sleep(1000);
			dropdown.selectByVisibleText(data.get(10)); 
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[name='CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-PostalCode']")).sendKeys(data.get(11));
			driver.findElement(By.xpath("//div[contains(text(), 'Verify Address')]")).click();
			Thread.sleep(4000);
		
			WebElement selectOrganizationType = driver.findElement(By.name("CreateAccount-CreateAccountScreen-CreateAccountDV-OrgType"));
			Select dropdown10 = new Select(selectOrganizationType);
			Thread.sleep(2000);
			dropdown10.selectByVisibleText(data.get(12));
			
			Thread.sleep(2000);
			driver.findElement(By.name("CreateAccount-CreateAccountScreen-CreateAccountDV-OfficialIDInputSet-OfficialIDDV_Input")).sendKeys(data.get(13));
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@id=\"CreateAccount-CreateAccountScreen-CreateAccountDV-IndustryCode-SelectIndustryCode\"]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"CreateAccount-CreateAccountScreen-CreateAccountDV-IndustryCode-SelectIndustryCode\"]/span")).click();

			driver.findElement(By.name("IndustryCodeSearchPopup-IndustryCodeSearchScreen-IndustryCodeSearchDV-Code")).sendKeys(data.get(14));
			driver.findElement(By.id("IndustryCodeSearchPopup-IndustryCodeSearchScreen-IndustryCodeSearchDV-SearchAndResetInputSet-SearchLinksInputSet-Search")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[contains(text(), 'Select')]")).click();
						
			WebElement selectNAICSCodeDropdown = driver.findElement(By.name("CreateAccount-CreateAccountScreen-CreateAccountDV-NAICSCode_Ext"));
			Select dropdown1 = new Select(selectNAICSCodeDropdown);
			dropdown1.selectByVisibleText(data.get(15));
			
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[name='CreateAccount-CreateAccountScreen-CreateAccountDV-ProducerSelectionInputSet-Producer']")).sendKeys(data.get(16)); // C0003
			driver.findElement(By.cssSelector("*[id='CreateAccount-CreateAccountScreen-CreateAccountDV-ProducerSelectionInputSet-Producer-SelectOrganization'] span")).click();
			driver.findElement(By.xpath("//*[@id='CreateAccount-CreateAccountScreen-CreateAccountDV-ProducerSelectionInputSet-IndividualProducer_Ext-SelectIndividualProducer_Ext']/span")).click();
			driver.findElement(By.name("UserSearchPopup-UserSearchPopupScreen-UserSearchDV-Username")).sendKeys(data.get(17));  // /0033458
			driver.findElement(By.id("UserSearchPopup-UserSearchPopupScreen-UserSearchDV-SearchAndResetInputSet-SearchLinksInputSet-Search")).click();
			
			driver.findElement(By.xpath("//*[@id='UserSearchPopup-UserSearchPopupScreen-UserSearchResultsLV-0-_Select']")).click();
			
			driver.findElement(By.xpath("//div[@class='gw-label']//div[contains(text(), 'U')]")).click();
			driver.findElement(By.cssSelector("div[id='DuplicateContactsPopup-__crumb__']")).click();
			driver.findElement(By.xpath("//div[@class='gw-label']//div[contains(text(), 'U')]")).click();

			System.out.println("Account Number " + driver.findElement(By.cssSelector("div[id='AccountFile_Summary-AccountSummaryDashboard-AccountDetailsDetailViewTile-AccountDetailsDetailViewTile_DV-1'] [id='AccountFile_Summary-AccountSummaryDashboard-AccountDetailsDetailViewTile-AccountDetailsDetailViewTile_DV-AccountNumber']")).getText());
			System.out.println("Account Holder " + driver.findElement(By.cssSelector("div[id='AccountFile_Summary-AccountSummaryDashboard-AccountDetailsDetailViewTile-AccountDetailsDetailViewTile_DV-AccountHolder_Input'] [id='AccountFile_Summary-AccountSummaryDashboard-AccountDetailsDetailViewTile-AccountDetailsDetailViewTile_DV-AccountHolder']")).getText());
								
// Submission Creation		
		
				// Select action and click on new submission option
				
				driver.findElement(By.cssSelector("div[id='AccountFile-AccountFileMenuActions'] div[role='button']")).click();
				driver.findElement(By.xpath("//div[@id='AccountFile-AccountFileMenuActions-AccountFileMenuActions_Create-AccountFileMenuActions_NewSubmission']//div[contains(text(), 'New Su')]")).click();				driver.findElement(By.cssSelector("div[id='NewSubmission-NewSubmissionScreen-ProductOffersDV-ProductSelectionLV-0-addSubmission']")).click();
				Thread.sleep(5000);
				
				// Enter/Select the value in mandatory fields on Policy Info Screen 
				
				WebElement selectPackModDropdown = driver.findElement(By.name("SubmissionWizard-LOBWizardStepGroup-SubmissionWizard_PolicyInfoScreen-SubmissionWizard_PolicyInfoDV-PolicyInfoInputSet-PackageModification_Ext"));
				Select dropdown2 = new Select(selectPackModDropdown);
				dropdown2.selectByVisibleText(data.get(18));
				WebElement selectRiskDropdown = driver.findElement(By.name("SubmissionWizard-LOBWizardStepGroup-SubmissionWizard_PolicyInfoScreen-SubmissionWizard_PolicyInfoDV-PolicyInfoInputSet-TypeOfRisk_Ext"));
				Select dropdown3 = new Select(selectRiskDropdown);
				dropdown3.selectByVisibleText(data.get(19));
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'Next')]")).click();
				Thread.sleep(5000);
				
				// Enter/Select the value in mandatory fields on Subline Selection Screen and navigate to the next page
				
				driver.findElement(By.cssSelector("input[name='SubmissionWizard-LOBWizardStepGroup-LineWizardStepSet-GL7SublineSelectionScreen-GL7SublineSelectionDV-0-subline']")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("SubmissionWizard-LOBWizardStepGroup-LineWizardStepSet-GL7SublineSelectionScreen-GL7SublineSelectionDV-0-Jurisdictions_button")).click();	
				driver.findElement(By.cssSelector("input[name='GL7SublineJurisdictionsPopup-21-_Checkbox']")).click(); // 13 for Iowa & 21 for Minneapolis
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'OK')]")).click();
				Thread.sleep(2000);
				
				// From Subline Selection Screen, navigate to the next page
				
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'Next')]")).click();
				Thread.sleep(4000);
				
				// From General Liability Screen, Select Extentions and navigate to the next page
				
				WebElement extentions = driver.findElement(By.name("SubmissionWizard-LOBWizardStepGroup-LineWizardStepSet-GL7GeneralLiabilityCoveragesScreen-GL7GeneralLiabilitySublines_ExtCV-0-LobEntityDV-39-LobInputSet-LobInternalInputSet-Input"));
				Select dropdownextentions = new Select(extentions);
				dropdownextentions.selectByVisibleText(data.get(20));
				Thread.sleep(7000);
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'Next')]")).click();
				Thread.sleep(3000);
				
				// From State Specific Information Screen, navigate to the next page
				
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'Next')]")).click();
				Thread.sleep(2000);
				
				// Enter/Select the value in mandatory fields on Location screen and navigate to the next page
				
				driver.findElement(By.id("SubmissionWizard-LOBWizardStepGroup-LineWizardStepSet-GL7LocationsScreen-GL7LocationsPanelSet-LocationsEdit_DP-LocationsEdit_LV-0-Loc_button")).click();
				Thread.sleep(2000);
				WebElement selectPremopscodeDropdown = driver.findElement(By.name("GL7LocationPopup-LocationScreen-GL7LocationDetailCV-SublinePropertiesPanel-0-LobEntityDV-16-LobInputSet-LobInternalInputSet-Input"));
				Select dropdown5 = new Select(selectPremopscodeDropdown);
				dropdown5.selectByVisibleText(data.get(21));
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"GL7LocationPopup-LocationScreen-Update\"]/div/div[2]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'Next')]")).click();
				Thread.sleep(4000);
				
				// Enter/Select the value in mandatory fields on Exposure screen and navigate to the next page
				
				driver.findElement(By.xpath("//div[@id='SubmissionWizard-LOBWizardStepGroup-LineWizardStepSet-GL7ExposuresScreen-GL7ExposuresPanelSet-ExposureLDV_tb-AddExposureBtn']/div[@role='button']")).click();
				driver.findElement(By.xpath("//div[contains(text(), 'Premises/Operations and Products/Completed Operations')]")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//div[@id='GL7ExposurePopup-GL7ExposureCV-LobEntityDV-1-LobInputSet-LobInternalInputSet-ClassCode-SelectClassCode']/span")).click();
				driver.findElement(By.xpath("//div[@id='GL7ClassCodeSearchPopup-GL7ClassCodeSearchScreen-ClassCodeSearchResultsLV-0-_Select']/div/div[contains(text(), 'Select')]")).click();
				driver.findElement(By.xpath("//div[@id='GL7ExposurePopup-Update']/div/div[2]")).click();
				Thread.sleep(4000);
				
				driver.findElement(By.xpath("//div[@id='WebMessageWorksheet-WebMessageWorksheetScreen-WebMessageWorksheet_ClearButton']/div/div[2]")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("GL7ExposurePopup-GL7ExposureCV-LobEntityDV-54-LobInputSet-LobInternalInputSet-Input")).clear();
				Thread.sleep(2000);
				driver.findElement(By.name("GL7ExposurePopup-GL7ExposureCV-LobEntityDV-54-LobInputSet-LobInternalInputSet-Input")).sendKeys(data.get(22));
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[@id='GL7ExposurePopup-Update']/div/div[2]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'Next')]")).click();
				Thread.sleep(3000);
				
				// From State Supplemental Questions Screen, navigate to the next page
				
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'Next')]")).click();
				Thread.sleep(4000);
						
				// From State Modifiers Screen, navigate to the next page
						
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'Next')]")).click();
				Thread.sleep(4000);
				
				// Click on Quote button on Risk Analysis screen
				
				driver.findElement(By.xpath("//div[@class='gw-label']/div[contains(text(), 'Q')]")).click();
				Thread.sleep(5000);
				
				// Print the submission number and total premium amount on console
				
				System.out.println("Submission Number " + driver.findElement(By.cssSelector("div[id='SubmissionWizard-SubmissionWizard_QuoteScreen-Quote_SummaryDV-JobNumber_Input'] div[id='SubmissionWizard-SubmissionWizard_QuoteScreen-Quote_SummaryDV-JobNumber']")).getText());
				System.out.println("Total Premium " + driver.findElement(By.cssSelector("div[id='SubmissionWizard-SubmissionWizard_QuoteScreen-Quote_SummaryDV-TotalPremium'] div[class='gw-value-readonly-wrapper']")).getText());
				
				// Click on Risk Analysis screen from side bar from Quote screen
				
				driver.findElement(By.xpath("//div[@id='SubmissionWizard-RiskAnalysis']/div/div[contains(text(), 'Risk Analysis')]")).click();
				Thread.sleep(2000);
				
				// Approve the underwrite messages from Risk Analysis screen
				
				driver.findElement(By.id("SubmissionWizard-Job_RiskAnalysisScreen-RiskAnalysisCV-RiskEvaluationPanelSet-1-UWIssueRowSet-SpecialApprove")).click();
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@role='button']/div[contains(text(), 'OK')]")).click();
				Thread.sleep(5000);
				
				// Navigate to the Payments screen and Select the Billing Method and Payment Plan
				
				driver.findElement(By.xpath("//div[@id='SubmissionWizard-BillingInfo']//div[contains(text(), 'Payment')]")).click();
								
				WebElement billingmethod = driver.findElement(By.name("SubmissionWizard-SubmissionWizard_PaymentScreen-BillingAdjustmentsPanelSet-BillingPanel-BillingMethod"));
				Select dropdown12 = new Select(billingmethod);
				dropdown12.selectByVisibleText(data.get(23));
				
//				driver.switchTo().alert().accept();
				Thread.sleep(1000);
				
				List<WebElement> Options = driver.findElements(By.cssSelector("[class='gw-radio-button']"));

				for (WebElement option :Options) 
				{
					if(option.getText().equalsIgnoreCase(data.get(24)))
					{
						option.click();
						break;
					}
				}
				
/*				int Size = RadioButton.size();
				for(int i=0; i<Size; i++)
		          { 
		        String val = RadioButton.get(i).getAttribute("value");
		        if (val.equalsIgnoreCase(data.get(24)))
		        {  
		        	RadioButton.get(i).click();
		        	break;
		        }
		           }  */   
				
				
//				WebElement paymentplan = driver.findElement(By.cssSelector("[class='gw-radio-button']"));
//				driver.findElement(with(By.tagName("input")).toLeftOf(paymentplan)).click();
				
//				driver.findElement(By.id("SubmissionWizard-SubmissionWizard_PaymentScreen-BillingAdjustmentsPanelSet-PaymentSchedulePanel-PlanInputSet-PaymentsPlanDV-PaymentsPlanInputSet-InstallmentPlan-BillingAdjustmentsInstallmentsLV-1-InstallmentPlan_radio")).click();										  
				Thread.sleep(1000);
				
		// Policy Issuance
				
				driver.findElement(By.xpath("//div[@id='SubmissionWizard-BillingInfo']//div[contains(text(), 'Payment')]")).click();
				driver.findElement(By.cssSelector("[id='SubmissionWizard-SubmissionWizard_PaymentScreen-JobWizardBillingToolbarButtonSet-BindOptions'] [role='button']")).click();
				driver.findElement(By.xpath("//div[contains(text(), 'Issue Policy')]")).click();
				Thread.sleep(2000);
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				
				// Save the policy issued screenshot on targeted location
					
//				File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//				FileUtils.copyFile(src, new File("D:\\GWBC - Daily Task Tracker\\Sprint #28\\Selenium Backup_19Feb2022\\Policy.jpg"));
				
				// Click on Policy issued link
				
				driver.findElement(By.cssSelector("div[id='JobComplete-JobCompleteScreen-JobCompleteDV-ViewPolicy_Input'] div[role='link']")).click();
				Thread.sleep(3000);
				
				// Print the policy number on console
				
				System.out.println("Policy Number " +driver.findElement(By.cssSelector("div[id=\"PolicyFile_Summary-PolicyOverviewDashboard-PolicyDetailsDetailViewTile-PolicyDetailsDetailViewTile_DV-PolicyNumber\"] div[class='gw-value-readonly-wrapper']")).getText()); 
		
		}
}