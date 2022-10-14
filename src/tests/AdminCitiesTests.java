package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminCitiesTests extends BasicTest{
	
//	Test #1: Visits the admin cities page and list cities
//	Podaci: 
//	admin email: admin@admin.com
//	admin password: 12345
//	Koraci: 
//	Klik na sign up dugme iz navigacije
//	Prijaviti se na sistem admin kredencijalima
//	Klik na admin dugme iz navigacije
//	Klik na Cities dugme iz padajuceg Admin menija
//	Verifikovati da se u url-u stranice javlja /admin/cities ruta 

	@Test(priority = 10)
	public void visitsTheAdminCitiesPageAndListCities() throws InterruptedException {
		String emailAdmin = "admin@admin.com";
		String passwordAdmin = "12345";

		navPage.getSignUpBtn().click();
		loginPage.getEmailInput().sendKeys(emailAdmin);
		loginPage.getPasswordInput().sendKeys(passwordAdmin);
		loginPage.getLoginButton().click();
		navPage.getAdminButton().click();
		new Actions(driver).moveToElement(navPage.getCitiesLink()).click().perform();

		Assert.assertTrue(driver.getCurrentUrl().contains("/admin/cities"), "URL should countain '/admin/cities'");
	}

//	Test #2: Checks input types for create/edit new city
//	Koraci: 
//	Klik na admin dugme iz navigacije
//	Klik na Cities dugme iz padajuceg Admin menija
//	Kliknuti na New Item dugme
//	Sacekati da se dijalog za kreiranje i editovanje grada pojavi
//	Verifikovati da polje za unos grada za atribut type ima tekst text

	@Test(priority = 20)
	public void checksInputTypesForCrediteEditNewCity() {
		navPage.getAdminButton().click();
		new Actions(driver).moveToElement(navPage.getCitiesLink()).click().perform();
		citiesPage.getNewItemButton().click();
		citiesPage.waitForNewEditDialogToBeVisible();

		Assert.assertEquals(citiesPage.getNewItemButton().getAttribute("type"), ("text"),
				"Dialog input should be type text");
	}

//	Test #3: Create new city
//	Podaci: 
//	city: [Ime i prezime polaznika]’s city
//	Koraci: 
//	Klik na admin dugme iz navigacije
//	Klik na Cities dugme iz padajuceg Admin menija
//	Kliknuti na New Item dugme
//	Sacekati da se dijalog za kreiranje i editovanje grada pojavi
//	Uneti ime grada u polje
//	Kliknuti na Save dugme
//	Sacekati da popu za prikaz poruke bude vidljiv
//	Verifikovati da poruka sadrzi tekst Saved successfully

	@Test(priority = 30)
	public void createNewCity() throws InterruptedException {
		String city = "Monika Nikolic's city";
		navPage.getAdminButton().click();
		new Actions(driver).moveToElement(navPage.getCitiesLink()).click().perform();
		citiesPage.getNewItemButton().click();
		citiesPage.waitForNewEditDialogToBeVisible();
		citiesPage.getNewItemButton().sendKeys(city);
		citiesPage.getEditDialogSaveBtn().click();

		messagePopUpPage.waitForPopUpToBeDisplayed();
		Assert.assertTrue(messagePopUpPage.getElementWithNewEditPopUpMessage().getText().contains("Saved successfully"),
				"Pop up message should be " + "'Saved successfully'");
	}

//	Test #4: Edit city
//	Podaci: 
//	old city name: [Ime i prezime polaznika]’s city
//	new city name: [Ime i prezime polaznika]’s city Edited
//	Koraci: 
//	Klik na admin dugme iz navigacije
//	Klik na Cities dugme iz padajuceg Admin menija
//	U polje za pretragu uneti staro ime grada
//	Sacekati da broj redova u tabeli bude 1
//	Kliknuti na dugme Edit iz prvog reda
//	Uneti novo ime za grad
//	Kliknuti na dugme Save
//	Sacekati da popu za prikaz poruke bude vidljiv
//	Verifikovati da poruka sadrzi tekst Saved successfully

	@Test(priority = 40)
	public void editCity() throws InterruptedException {
		String oldCityName = "Monika Nikolic's city";
		String newCityName = "Monika Nikolic's city Edited";

		navPage.getAdminButton().click();
		new Actions(driver).moveToElement(navPage.getCitiesLink()).click().perform();
		citiesPage.getSearchInput().sendKeys(oldCityName);
		citiesPage.waitForTableRowsToBe(1);
		citiesPage.getEditBtnByRow(1).click();

		citiesPage.getNewItemButton().sendKeys(Keys.CONTROL + "a");
		citiesPage.getNewItemButton().sendKeys(Keys.DELETE);

		citiesPage.getNewItemButton().sendKeys(newCityName);
		citiesPage.getEditDialogSaveBtn().click();

		messagePopUpPage.waitForPopUpToBeDisplayed();
		Assert.assertTrue(messagePopUpPage.getElementWithNewEditPopUpMessage().getText().contains("Saved successfully"),
				"Pop up message should be " + "'Saved successfully'");
	}

//	Test #5: Search city
//	Podaci: 
//	city name: [Ime i prezime polaznika]’s city Edited
//	Koraci: 
//	Klik na admin dugme iz navigacije
//	Klik na Cities dugme iz padajuceg Admin menija
//	U polje za pretragu uneti staro ime grada
//	Sacekati da broj redova u tabeli bude 1
//	Verifikovati da se u Name koloni prvog reda nalazi tekst iz pretrage

	@Test(priority = 50)
	public void searchCity() {
		String cityName = "Monika Nikolic's city Edited";
		navPage.getAdminButton().click();
		new Actions(driver).moveToElement(navPage.getCitiesLink()).click().perform();
		citiesPage.getSearchInput().sendKeys(cityName);
		citiesPage.waitForTableRowsToBe(1);

		Assert.assertEquals(citiesPage.getCellFromRow(1, 2).getText(), cityName);
	}

	// Test #5: Delete city
	// Podaci:
	// city name: [Ime i prezime polaznika]’s city Edited
	// Koraci:
	// Klik na admin dugme iz navigacije
	// Klik na Cities dugme iz padajuceg Admin menija
	// U polje za pretragu uneti staro ime grada
	// Sacekati da broj redova u tabeli bude 1
	// Verifikovati da se u Name koloni prvog reda nalazi tekst iz pretrage
	// Kliknuti na dugme Delete iz prvog reda
	// Sacekati da se dijalog za brisanje pojavi
	// Kliknuti na dugme Delete iz dijaloga
	// Sacekati da popu za prikaz poruke bude vidljiv
	// Verifikovati da poruka sadrzi tekst Deleted successfully

	@Test(priority = 60)
	public void deleteCity() {
		String cityName = "Monika Nikolic's city Edited";
		navPage.getAdminButton().click();
		new Actions(driver).moveToElement(navPage.getCitiesLink()).click().perform();
		citiesPage.getSearchInput().sendKeys(cityName);
		citiesPage.waitForTableRowsToBe(1);

		Assert.assertEquals(citiesPage.getCellFromRow(1, 2).getText(), cityName);

		citiesPage.getDeleteBtnByRow(1).click();
		messagePopUpPage.waitForWarningPopUp();

		citiesPage.getDialogDeleteBtn().click();
		Assert.assertTrue(messagePopUpPage.getSuccessfullyDeletedPopUp().getText().contains("Deleted successfully"),
				"Pop up message shoud " + "countain 'Deleted successfully'");

	}

}
