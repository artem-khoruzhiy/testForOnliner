import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Артем on 15.03.2017.
 */
public class CatalogOnliner {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Мобильные телефоны")
    private WebElement buttonMobPhones;

    @FindBy(linkText = "Планшеты")
    private WebElement buttonTablets;

    @FindBy(linkText = "Ноутбуки")
    private WebElement buttonLaptops;

    @FindBy(linkText = "Телевизоры")
    private WebElement buttonTVs;

    public CatalogOnliner(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    public ProductPage navigateOnMenu(WebElement element){
        element.click();
        return new ProductPage(driver, element.getText());
    }

    public WebElement getButtonMobPhones() {
        return buttonMobPhones;
    }

    public WebElement getButtonTablets() {
        return buttonTablets;
    }

    public WebElement getButtonLaptops() {
        return buttonLaptops;
    }

    public WebElement getButtonTVs() {
        return buttonTVs;
    }
}
