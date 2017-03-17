import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Артем on 15.03.2017.
 */
public class CatalogOnliner {
    private WebDriver driver;

    @FindBy(linkText = "Мобильные телефоны")
    private WebElement btnMobPhones;

    @FindBy(linkText = "Планшеты")
    private WebElement btnTablets;

    @FindBy(linkText = "Ноутбуки")
    private WebElement btnLaptops;

    @FindBy(linkText = "Телевизоры")
    private WebElement btnTVs;

    public CatalogOnliner(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public ProductPage navigateOnMenu(WebElement element){
        element.click();
        String a;
        try {
            a = element.getText();
        }catch (StaleElementReferenceException e){
            a = element.getText();
        }
        return new ProductPage(driver, a);
    }

    public WebElement getBtnMobPhones() {
        return btnMobPhones;
    }

    public WebElement getBtnTablets() {
        return btnTablets;
    }

    public WebElement getBtnLaptops() {
        return btnLaptops;
    }

    public WebElement getBtnTVs() {
        return btnTVs;
    }
}
