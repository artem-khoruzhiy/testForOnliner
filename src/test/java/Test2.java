import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Артем on 15.03.2017.
 */
public class Test2 {
    private WebDriver driver;
    private CatalogOnliner catalogOnliner;
    private ProductPage productPage;

    @DataProvider(name = "producers")
    public Object[][] getProducers() {
        return new Object[][] {
                {"Samsung"},
                {"Xiaomi"},
                {"Huawei"},
                {"Apple"},
        };
    }

    @BeforeClass
    public void globalSetup(){
        driver = new FirefoxDriver();
    }

    @BeforeMethod
    public void localSettings(){
        driver.get("https://catalog.onliner.by");
        catalogOnliner = new CatalogOnliner(driver);
        productPage = catalogOnliner.navigateOnMenu(catalogOnliner.getButtonMobPhones());
    }

    @Test(dataProvider = "producers")
    public void testContainsGoodsOfThisProducer(String producer) throws Exception{
        boolean condition = false;
        List<String> goods = productPage.returnNamesByProducer(producer);
        for (String good : goods) {
            if (good.contains(producer))
                condition = true;
            else
                condition = false;
        }
        assertTrue(condition);
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }
}
