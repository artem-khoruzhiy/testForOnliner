import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private WebDriverWait wait;
    private List<WebElement> filters;
    private String header;


    private By locatorOfListProduct = By.xpath("//div[@class='schema-product__title']//span");
    private By filtersLocator = By.xpath("//*[@id='schema-filter']/*[1]/*");
    private String h1 = "//h1[text()='%s']";
    private String locatorForChooseByProducer = "//span[text()='Производитель']/../..//li//span[text()='%s']";

    public ProductPage(WebDriver driver, String head){
        this.header = head;
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
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
        driver.findElement(By.xpath(String.format(locatorForChooseByProducer, producer))).click();
        List<String> names;
        try {
            names = findGoodsName();
        }catch (StaleElementReferenceException e){
            names =  findGoodsName();
        }
        return names;
    }

    public String geth1OfThisPage() {
        return driver.findElement(By.xpath(String.format(h1, header))).getText();
    }

    public String getHeader() {
        return header;
    }
}
