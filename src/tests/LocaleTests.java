package tests;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocaleTests extends BasicTest {
	
//	Test #1: Set locale to ES
//	Koraci: 
//	Postaviti jezik na ES
//	Verifikovati da se na stranici u hederu javlja tekst Página de aterrizaje

	@Test(priority = 10)
	public void setLocaleToES() {
		navPage.getLanguageBtn().click();
		new Actions(driver).moveToElement(navPage.getSpanishBtn()).click().perform();

		Assert.assertTrue(navPage.getHomeLink().getText().contains("Página de aterrizaje"),
				"Header" + "should be 'Página de aterrizaje'");

	}

//	Test #2: Set locale to EN
//	Koraci: 
//	Postaviti jezik na EN
//	Verifikovati da se na stranici u hederu javlja tekst Landing

	@Test(priority = 20)
	public void setLocaleToEN() {
		navPage.getLanguageBtn().click();
		new Actions(driver).moveToElement(navPage.getEnglishBtn()).click().perform();

		Assert.assertTrue(navPage.getHomeLink().getText().contains("Landing"), "Header" + "should be 'Landing'");

	}

//	Test #3: Set locale to CN
//	Koraci: 
//	Postaviti jezik na CN
//	Verifikovati da se na stranici u hederu javlja tekst 首页

	@Test(priority = 30)
	public void setLocaleToCN() {
		navPage.getLanguageBtn().click();
		new Actions(driver).moveToElement(navPage.getChineseBtn()).click().perform();

		Assert.assertTrue(navPage.getHomeLink().getText().contains("首页"), "Header" + "should be '首页'");

	}

//	Test #4: Set locale to FR
//	Koraci: 
//	Postaviti jezik na FR
//	Verifikovati da se na stranici u hederu javlja tekst Page d'atterrissage
//

	@Test(priority = 40)
	public void setLocaleToFR() {
		navPage.getLanguageBtn().click();
		new Actions(driver).moveToElement(navPage.getFranceBtn()).click().perform();

		Assert.assertTrue(navPage.getHomeLink().getText().contains("Page d'atterrissage"),
				"Header" + "should be 'Page d'atterrissage'");

	}

}
