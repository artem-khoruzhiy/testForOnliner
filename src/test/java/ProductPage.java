import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Артем on 15.03.2017.
 */
public class ProductPage {
    private WebDriver driver;
    private List<WebElement> filters;
    private String header;
    private WebDriverWait wait;
    private By h1Locator;
    private By locatorOfListProduct = By.xpath("//div[@class='schema-product__title']//span");
    private By filtersLocator = By.xpath("//*[@id='schema-filter']/*[1]/*");

    public ProductPage(WebDriver driver, String header){
        this.header = header;
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 50);
        h1Locator = By.xpath("//h1[text()='" + header + "']");
    }

    public int findCountFilters(){
        filters = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(filtersLocator));
        return filters.size();
    }

    public List<String> findGoodsName(){
        List<WebElement> goods = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorOfListProduct));
        List<String> goodsName = new ArrayList<String>();
        for (WebElement element : goods){
            goodsName.add(element.getText());
        }
        return goodsName;
    }

    public List<String> returnNamesByProducer(String producer) throws Exception{
        By locatorForChooseByProducer = By.xpath("//span[text()='Производитель']/../..//li//span[text()='" + producer + "']");
        driver.findElement(locatorForChooseByProducer).click();
        List<String> names = new ArrayList<String>();
        try {
            names = findGoodsName();
        }catch (StaleElementReferenceException e){
            names =  findGoodsName();
        }
        return names;
    }

    public String geth1OfThisPage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(h1Locator)).getText();
    }

    public String getHeader() {
        return header;
    }
}
