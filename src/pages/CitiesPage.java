package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CitiesPage {
//	dugme New Item
//	input za pretragu
//	metodu koja ceka da se dijalog za editovanje i kreiranje pojavi
//	metodu koja ceka da se dijalog za brisanje pojavi
//	save dugme iz dijalog za editovanje i kreiranje
//	delete dugme iz dijaloga za brisanje
//	metodu koja ceka da se u tabeli pojavi odredjeni broj redova. Metoda kao parametar prima broj redova. Ako se metoda pozove sa parametrom 5, to znaci da metoda ceka da se u tabeli pojavi 5 redova.
//	metodu koja vraca celiju iz odredjenog reda. Metoda kao parametre prima red i kolonu a vraca td iz tabele. Npr: ukoliko se metoda pozove row=2, cell=3 tada metoda vraca trecu celiju iz 2 reda.
//	metodu koja vraca edit dugme trazenog reda. Metoda kao parametar prima red a vraca dugme. Npr ako se metoda pozove row=3, metoda vraca edit dugme iz treceg reda tabele
//	metodu koja vraca delete dugme trazenog reda. Metoda kao parametar prima red a vraca dugme. Npr ako se metoda pozove row=3, metoda vraca delete dugme iz treceg reda tabele

	private WebDriver driver;
	private WebDriverWait wait;

	public CitiesPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;

	}

	public WebElement getNewItemButton() {
		return this.driver.findElement(By.xpath("//button[contains(@class, 'btnNewItem')]"));
	}

	public WebElement getSearchInput() {
		return this.driver.findElement(By.id("search"));
	}

	public void waitForNewEditDialogToBeVisible() {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'v-dialog dlgNewEditItem')]")));
	}

	public void waitDeleteDialogToBeVisible() {
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'delete this item')]")));
	}

	public WebElement getEditDialogSaveBtn() {
		return this.driver.findElement(By.xpath("//button[contains(@class, 'btnSave')]"));
	}

	public WebElement getDialogDeleteBtn() {
		return this.driver
				.findElement(By.xpath("//div[@class='v-dialog v-dialog--active']//button[contains(@class, 'red')]"));
	}

	public void waitForTableRowsToBe(int row) {
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//table/tbody//tr"), row));
	}

	public WebElement getCellFromRow(int row, int column) {
		return this.driver.findElement(By.xpath("//table/tbody/tr[" + row + "]/td[" + column + "]"));
	}

	public WebElement getEditBtnByRow(int row) {
		return this.driver.findElement(By.xpath("//table/tbody//tr[" + (row) + "]//button[@id='edit']"));
	}

	public WebElement getDeleteBtnByRow(int row) {
		return this.driver.findElement(By.xpath("//table/tbody//tr[" + row + "]//button[@id='delete']"));
	}

}
