package PageObjectMailShoot;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AIPDFChat extends BaseClass
{
WebDriver driver;
public AIPDFChat(WebDriver driver)
{
super(driver);
this.driver=driver;
}
public void VoiceChatGPT()
{
 String expectedTitle = "AiPdf";
 String actualTitle = driver.getTitle();
 Assert.assertEquals(actualTitle,expectedTitle);
 if(expectedTitle.equals(actualTitle))
{
System.out.println("The page title is: " + actualTitle);
}
}
}
