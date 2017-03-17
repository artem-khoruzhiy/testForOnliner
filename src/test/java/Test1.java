import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * Created by Артем on 15.03.2017.
 */
public class Test1 {
    private WebDriver driver;
    private CatalogOnliner catalogOnliner;
    private ProductPage productPage;

    @DataProvider(name = "menu")
    public Object[][] getElementsMenu() {
        return new Object[][] {
                {catalogOnliner.getBtnMobPhones()},
                {catalogOnliner.getBtnTablets()},
                {catalogOnliner.getBtnLaptops()},
                {catalogOnliner.getBtnTVs()},
        };
    }

    @BeforeClass
    public void setup(){
        driver = new FirefoxDriver();
        driver.get("https://catalog.onliner.by");
        catalogOnliner = new CatalogOnliner(driver);
    }

    @Test(dataProvider = "menu")
    public void testForExistenceElements(WebElement element){
        assertNotNull(element);
    }

    @Test(dataProvider = "menu")
    public void testExistenceFilters(WebElement element){
        assertTrue(catalogOnliner.navigateOnMenu(element).findCountFilters() > 0);
    }

    @Test(dataProvider = "menu")
    public void testHeading(WebElement element) throws Exception{
        productPage = catalogOnliner.navigateOnMenu(element);
        assertEquals(productPage.geth1OfThisPage(), productPage.getHeader());
    }

    @Test(dataProvider = "menu")
    public void testListOfGoods(WebElement element){
        productPage = catalogOnliner.navigateOnMenu(element);
        assertTrue( productPage.findGoodsName().size()> 0);
    }

    @AfterMethod
    public void toStart(){
        driver.get("https://catalog.onliner.by");
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }


}
