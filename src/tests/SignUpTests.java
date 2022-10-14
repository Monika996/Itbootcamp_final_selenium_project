package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTests extends BasicTest {
	
//	Test #1: Visits the signup page
//	Koraci: 
//	Klik na sign up dugme iz navigacije
//	Verifikovati da se u url-u stranice javlja /signup ruta 

	@Test(priority = 10)
	public void visitsTheSignupPage() {
		navPage.getSignUpBtn().click();
		Assert.assertTrue(driver.getCurrentUrl().contains("/signup"), "URL should countain '/signup'");
	}

//	Test #2: Checks input types
//	Koraci: 
//	Klik na sign up dugme iz navigacije
//	Verifikovati da polje za unos emaila za atribut type ima vrednost email
//	Verifikovati da polje za unos lozinke za atribut type ima vrednost password	Verifikovati da polje za unos lozinke za potvrdu za atribut type ima vrednost password
//	Verifikovati da polje za unos lozinke za potvrdu za atribut type ima vrednost password

	@Test(priority = 20)
	public void checksInputTypes() {
		navPage.getSignUpBtn().click();

		Assert.assertEquals(signUpPage.getNameInput().getAttribute("type"), ("text"),
				"Name input should be type 'text'");
		Assert.assertEquals(signUpPage.getEmailInput().getAttribute("type"), ("email"),
				"Email input should be type 'email'");
		Assert.assertEquals(signUpPage.getPasswordInput().getAttribute("type"), ("password"),
				"Password input should be type 'password'");
		Assert.assertEquals(signUpPage.getConfirmPasswordInput().getAttribute("type"), ("password"),
				"Confirm password input should be type " + "'password'");
	}

//	Test #3: Displays errors when user already exists
//	Podaci: 
//	name: Another User
//	email: admin@admin.com
//	password: 12345
//	confirm password: 12345
//	Koraci: 
//	Klik na sign up dugme iz navigacije
//	Verifikovati da se u url-u stranice javlja /signup ruta 
//	Popuniti formu za registraciju podacima
//	Klik na sign up dugme
//	Sacekati da popu za prikaz poruke bude vidljiv
//	Verifikovati da greska sadrzi poruku E-mail already exists
//	Verifikovati da se u url-u stranice javlja /signup ruta 

	@Test(priority = 30)
	public void displaysErrorsWhenUserAlreadyExists() {
		String name = "Another User";
		String email = "admin@admin.com";
		String password = "12345";
		String confirmPassword = "12345";

		navPage.getSignUpBtn().click();
		Assert.assertTrue(driver.getCurrentUrl().contains("/signup"), "URL should countain '/signup'");

		signUpPage.getNameInput().sendKeys(name);
		signUpPage.getEmailInput().sendKeys(email);
		signUpPage.getPasswordInput().sendKeys(password);
		signUpPage.getConfirmPasswordInput().sendKeys(confirmPassword);
		signUpPage.getSignMeUpButton().click();

		messagePopUpPage.waitUserPopUpToBeVisiable();
		;
		Assert.assertTrue(messagePopUpPage.getElementWithTextMessage().getText().contains("E-mail already exists"),
				"Status message should be " + "'E-mail already exists'");
		Assert.assertTrue(driver.getCurrentUrl().contains("/signup"), "URL should countain '/signup'");
	}

//	Test #4: Signup
//	Podaci: 
//	name: Ime i prezime polaznika
//	email template: ime.prezime@itbootcamp.rs
//	password: 12345
//	confirm password: 12345
//	Koraci: 
//	Klik na sign up dugme iz navigacije
//	Popuniti formu podacima za registraciju
//	Klik na sign up dugme
//	Ucitati stranicu /home
//	Verifikovati da dijalog za obavestenje sadrzi tekst IMPORTANT: Verify your account
//	Kliknuti na Close dugme iz dijaloga
//	Kliknuti na logout

	@Test(priority = 40)
	public void signUp() throws InterruptedException {
		String name = "Monika Nikolic";
		String email = "monika.nikolic@itbootcamp.rs";
		String password = "12345";
		String confirmPassword = "12345";

		navPage.getLoginBtn().click();
		signUpPage.getNameInput().sendKeys(name);
		signUpPage.getEmailInput().sendKeys(email);
		signUpPage.getPasswordInput().sendKeys(password);
		signUpPage.getConfirmPasswordInput().sendKeys(confirmPassword);
		signUpPage.getSignMeUpButton().click();
		driver.get(baseUrl + "/home");

		Assert.assertTrue(
				messagePopUpPage.getElementWithVerifyAccountMessage().getText()
						.contains("IMPORTANT: Verify your account"),
				"Status message " + "should be 'IMPORTANT: Verify your account'");

		messagePopUpPage.getPopUpCloseBtn().click();
		navPage.getLogoutBtn().click();
	}

}
