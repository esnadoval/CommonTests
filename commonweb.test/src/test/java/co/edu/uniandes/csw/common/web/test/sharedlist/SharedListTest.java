package co.edu.uniandes.csw.common.web.test.sharedlist;

import co.edu.uniandes.csw.common.web.test.rules.CommonTestRule;
import co.edu.uniandes.csw.common.web.test.utils.InitializeData;
import co.edu.uniandes.csw.testa.logic.dto.TestADTO;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SharedListTest {

    private static WebDriver driver;
    private static String baseUrl;
    @Rule
    public CommonTestRule rule = new CommonTestRule("dataSample");
    public Integer dataSample;

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /**
         * Cambia el tamanio de la ventana del explorador para que los controles
         * abckbone se muestren correctamente
         */
        driver.manage().window().setSize(new Dimension(1400, 700));
        InitializeData.flushDataShared();
    }

    @Before
    public void setUpUrl() {
        driver.get(baseUrl + "/testc.master.service.subsystem/");
        
    }

  

    @Test
    public void createWSharedList() throws Exception {
       Thread.sleep(5000);
        InitializeData.initDataShared(dataSample);
        driver.findElement(By.xpath("//button[contains(@id,'createButton')]")).click();

        Thread.sleep(2000);

        driver.findElement(By.id("name")).clear();

        driver.findElement(By.id("name")).sendKeys("nombre");

        driver.findElement(By.id("atr3")).clear();
        driver.findElement(By.id("atr3")).sendKeys("atributo");

        driver.findElement(By.xpath("//a[contains(@href,'testB')]")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(@id,'addButton')]")).click();
        List<WebElement> lst = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (int i = 0; i < lst.size(); i++) {

            if (!lst.get(i).isSelected()) {
                lst.get(i).click();

            }
        }
        driver.findElement(By.id("addButton")).click();

        List<WebElement> tables = driver.findElement(By.xpath("//div[contains(@id,'testB')]")).findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
        if (tables.size() != lst.size()) {
            fail();
        }
        driver.findElement(By.xpath("//button[contains(@id,'saveButton')]")).click();
        Thread.sleep(3000);

        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
        boolean fail = false;

        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));

            if (elems.get(0).getText().equals("nombre") && elems.get(1).getText().equals("atributo")) {
                fail = true;
            }

        }
        InitializeData.flushDataShared();
        assertTrue(fail);

    }
    /*
     @Test
     public void editWSharedList() throws Exception {

     driver.findElements(By.linkText("Edit")).get(driver.findElements(By.linkText("Edit")).size() - 1).click();

     Thread.sleep(2000);

     driver.findElement(By.id("name")).clear();

     driver.findElement(By.id("name")).sendKeys("nombremod");

     driver.findElement(By.id("atr3")).clear();
     driver.findElement(By.id("atr3")).sendKeys("atributomod");

     driver.findElement(By.xpath("//a[contains(@href,'testB')]")).click();

     Thread.sleep(2000);

     driver.findElement(By.xpath("//button[contains(@id,'addButton')]")).click();
     List<WebElement> lst = driver.findElements(By.xpath("//input[@type='checkbox']"));
     for (int i = 0; i < lst.size(); i++) {

     if (!lst.get(i).isSelected()) {
     lst.get(i).click();

     }
     }
     driver.findElement(By.id("addButton")).click();

     driver.findElement(By.xpath("//button[contains(@id,'saveButton')]")).click();
     Thread.sleep(3000);

     List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
     boolean fail = false;

     for (WebElement webElement : table) {
     List<WebElement> elems = webElement.findElements(By.xpath("td"));

     if (elems.get(0).getText().equals("nombre") && elems.get(1).getText().equals("atributo")) {
     fail = true;
     }

     }
     assertTrue(fail);

     }
     */

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();

    }

}
