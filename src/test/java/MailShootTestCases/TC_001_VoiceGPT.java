package MailShootTestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import MailShootTestBase.BasePage;
import PageObjectMailShoot.VoiceGPT;

public class TC_001_VoiceGPT extends BasePage
{
@Test(groups= {"master","regression"})
public void setup_VoiceGPT()
{
VoiceGPT vg = new VoiceGPT(driver);
vg.VoiceChatGPT();
}
}
