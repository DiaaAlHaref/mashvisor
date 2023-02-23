import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Tests {
    public WebDriver driver;

    @BeforeMethod
    public void Startup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.mashvisor.com/auth");
    }

    @Test
    public void Test_01_Check_Mortgage_Feature_Not_enabled() throws InterruptedException {
        driver.findElement(By.id("login-email")).sendKeys("shoroq@mashvisor.com");
        driver.findElement(By.id("login-password")).sendKeys("testtest");
        driver.findElement(By.id("loginButton")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']")));

        driver.findElement(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='HeadLine_address__A1cR7']")));

        Thread.sleep(1000);

        WebElement Enable_Mortgage_Button = driver
                .findElement(By.xpath("//label[@data-cy='Mortagage-switch']//span[@class='SwitchCheckBox_slider__OWIoE']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Enable_Mortgage_Button);
        //driver.switchTo().frame("stonly-stat-id").close();
        WebElement MortgageTextLocator = driver.findElement(By.xpath("//div[@class='SwitchCheckBox_container__mY8nA']//div[contains(.,'Mortgage')]"));
        String MortgageTextColor = MortgageTextLocator.getAttribute("style").toString();
        Assert.assertEquals(MortgageTextColor, "color: gray; font-size: 14px;");
    }

    @Test
    public void Test_02_Check_Mortgage_Amount_When_Feature_Not_enabled() throws InterruptedException {
        driver.findElement(By.id("login-email")).sendKeys("shoroq@mashvisor.com");
        driver.findElement(By.id("login-password")).sendKeys("testtest");
        driver.findElement(By.id("loginButton")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']")));

        driver.findElement(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='HeadLine_address__A1cR7']")));

        Thread.sleep(1000);
        //Check Mortgage Value before enabling toggle button
        WebElement MonthlyExpenses = driver.findElement(By.xpath("//td[@class='RentalStrategy_tdHeadline__PAmFH']//span[contains(.,'MONTHLY EXPENSES')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", MonthlyExpenses);
        MonthlyExpenses.click();
        WebElement Mortgage_Amount_Locator = driver.findElement(By.xpath("(//div[@class='RentalStrategy_extraRows__j0wmN']/span)[1]"));
        String Mortgage_Amount = Mortgage_Amount_Locator.getText();
        Assert.assertEquals(Mortgage_Amount, "$0");

    }

    @Test
    public void Test_03_Check_Mortgage_Type_interaction_Before_Feature_Enablement() throws InterruptedException {
        driver.findElement(By.id("login-email")).sendKeys("shoroq@mashvisor.com");
        driver.findElement(By.id("login-password")).sendKeys("testtest");
        driver.findElement(By.id("loginButton")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']")));

        driver.findElement(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='HeadLine_address__A1cR7']")));

        Thread.sleep(1000);


        WebElement Enable_Mortgage_Button = driver
                .findElement(By.xpath("//label[@data-cy='Mortagage-switch']//span[@class='SwitchCheckBox_slider__OWIoE']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Enable_Mortgage_Button);

        //Check Mortgage Type interaction before toggle enablement
        WebElement MortgageDetails = driver.findElement(By.xpath("//div[@class='MortgageCalculator_position__PY1hD']"));
        Assert.assertFalse(MortgageDetails.isSelected());

    }

    @Test
    public void Test_04_Check_Loan_Amount_Responsiveness_with_DownPayment() throws InterruptedException {
        driver.findElement(By.id("login-email")).sendKeys("shoroq@mashvisor.com");
        driver.findElement(By.id("login-password")).sendKeys("testtest");
        driver.findElement(By.id("loginButton")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']")));

        driver.findElement(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='HeadLine_address__A1cR7']")));

        Thread.sleep(1000);

        WebElement Enable_Mortgage_Button = driver
                .findElement(By.xpath("//label[@data-cy='Mortagage-switch']//span[@class='SwitchCheckBox_slider__OWIoE']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Enable_Mortgage_Button);

        Enable_Mortgage_Button.click();

        //GetDownPaymentValue()
        WebElement DownPaymentValueLocator = driver.findElement(By.xpath("//span[@data-cy='Down-Payment-Value']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='Down-Payment-Value']")));
        String DownDownPaymentValue = DownPaymentValueLocator.getText();
        DownDownPaymentValue = DownDownPaymentValue.replace("$", "").replace(",", "");
        double DP_value = Double.parseDouble(DownDownPaymentValue);


        //GetPropertyPriceValue
        WebElement PropertyPriceLocator = driver.findElement(By.xpath("//div/h6[@data-cy='Mortagage-Calculator-Property-Price']"));
        String PropertyPriceValue = PropertyPriceLocator.getText();
        PropertyPriceValue = PropertyPriceValue.replace("$", "").replace(",", "");
        double PP_value = Double.parseDouble(PropertyPriceValue);


        //GetPrincipalAmount
        double PrincipalAmount = PP_value - DP_value;

        Assert.assertEquals(PP_value, DP_value + PrincipalAmount);
    }

    @Test
    public void Test_05_Check_Mortgage_Function_with_30_Years() throws InterruptedException {
        driver.findElement(By.id("login-email")).sendKeys("shoroq@mashvisor.com");
        driver.findElement(By.id("login-password")).sendKeys("testtest");
        driver.findElement(By.id("loginButton")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']")));

        driver.findElement(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='HeadLine_address__A1cR7']")));

        Thread.sleep(1000);


        WebElement Enable_Mortgage_Button = driver
                .findElement(By.xpath("//label[@data-cy='Mortagage-switch']//span[@class='SwitchCheckBox_slider__OWIoE']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Enable_Mortgage_Button);


        Enable_Mortgage_Button.click();

        //GetDownPaymentValue()
        WebElement DownPaymentValueLocator = driver.findElement(By.xpath("//span[@data-cy='Down-Payment-Value']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='Down-Payment-Value']")));
        String DownDownPaymentValue = DownPaymentValueLocator.getText();
        DownDownPaymentValue = DownDownPaymentValue.replace("$", "").replace(",", "");
        double DP_value = Double.parseDouble(DownDownPaymentValue);


        //GetPropertyPriceValue
        WebElement PropertyPriceLocator = driver.findElement(By.xpath("//div/h6[@data-cy='Mortagage-Calculator-Property-Price']"));
        String PropertyPriceValue = PropertyPriceLocator.getText();
        PropertyPriceValue = PropertyPriceValue.replace("$", "").replace(",", "");
        double PP_value = Double.parseDouble(PropertyPriceValue);


        //GetAnnualInterestRateValue
        WebElement AnnualInterestRateLocator = driver.findElement(By.id("interestRate"));
        float AnnualInterestValue = Float.parseFloat(AnnualInterestRateLocator.getAttribute("value"));
        float AnnualInterestFloat = AnnualInterestValue / 100;


        //GetPrincipalAmount
        double PrincipalAmount = PP_value - DP_value;

        //GetTermInYearValue
        Select TermInYearsDropDown = new Select(driver.findElement(By.xpath("//div[@class='LoanSection_width__z2WZ4']//select[@class='CustomSelect_SelectStyle___9Vhm']")));
        String TermInYearsValue = TermInYearsDropDown.getOptions().get(0).getText();
        TermInYearsValue = TermInYearsValue.substring(0, 2);
        int TermInYearsValueInt = Integer.parseInt(TermInYearsValue);


        //GetCalculateMortgage
        System.out.println(MortgagePayment
                .CalculateMortgage(PrincipalAmount, AnnualInterestFloat, TermInYearsValueInt));
        WebElement MonthlyExpenses = driver.findElement(By.xpath("//td[@class='RentalStrategy_tdHeadline__PAmFH']//span[contains(.,'MONTHLY EXPENSES')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", MonthlyExpenses);
        MonthlyExpenses.click();
        WebElement Mortgage_Amount_Locator = driver.findElement(By.xpath("(//div[@class='RentalStrategy_extraRows__j0wmN']/span)[1]"));
        String Mortgage_Amount = Mortgage_Amount_Locator.getText();
        Mortgage_Amount = Mortgage_Amount.replace("$", "").replace(",", "");
        Assert.assertEquals(Mortgage_Amount, "1323");
    }


    @Test
    public void Test_06_Check_Mortgage_Function_with_15_Years() throws InterruptedException {
        driver.findElement(By.id("login-email")).sendKeys("shoroq@mashvisor.com");
        driver.findElement(By.id("login-password")).sendKeys("testtest");
        driver.findElement(By.id("loginButton")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']")));

        driver.findElement(By.xpath("//div[@class='SearchResultItem_propertyInfoWrapper__DBT3N']/a[@aria-label='2873 Gresham Road SE']"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='HeadLine_address__A1cR7']")));

        Thread.sleep(1000);


        WebElement Enable_Mortgage_Button = driver
                .findElement(By.xpath("//label[@data-cy='Mortagage-switch']//span[@class='SwitchCheckBox_slider__OWIoE']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Enable_Mortgage_Button);


        Enable_Mortgage_Button.click();

        //GetDownPaymentValue()
        WebElement DownPaymentValueLocator = driver.findElement(By.xpath("//span[@data-cy='Down-Payment-Value']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='Down-Payment-Value']")));
        String DownDownPaymentValue = DownPaymentValueLocator.getText();
        DownDownPaymentValue = DownDownPaymentValue.replace("$", "").replace(",", "");
        double DP_value = Double.parseDouble(DownDownPaymentValue);


        //GetPropertyPriceValue
        WebElement PropertyPriceLocator = driver.findElement(By.xpath("//div/h6[@data-cy='Mortagage-Calculator-Property-Price']"));
        String PropertyPriceValue = PropertyPriceLocator.getText();
        PropertyPriceValue = PropertyPriceValue.replace("$", "").replace(",", "");
        double PP_value = Double.parseDouble(PropertyPriceValue);


        //GetPrincipalAmount
        double PrincipalAmount = PP_value - DP_value;

        //GetTermInYearValue
        Select TermInYearsDropDown = new Select(driver.findElement(By.xpath("//div[@class='LoanSection_width__z2WZ4']//select[@class='CustomSelect_SelectStyle___9Vhm']")));
        TermInYearsDropDown.selectByIndex(1);
        String TermInYearsValue = TermInYearsDropDown.getOptions().get(1).getText();
        TermInYearsValue = TermInYearsValue.substring(0, 2);
        int TermInYearsValueInt = Integer.parseInt(TermInYearsValue);


        //GetAnnualInterestRateValue
        WebElement AnnualInterestRateLocator = driver.findElement(By.id("interestRate"));
        float AnnualInterestValue = Float.parseFloat(AnnualInterestRateLocator.getAttribute("value"));
        float AnnualInterestFloat = AnnualInterestValue / 100;

        //GetCalculateMortgage
        System.out.println(MortgagePayment
                .CalculateMortgage(PrincipalAmount, AnnualInterestFloat, TermInYearsValueInt));
        WebElement MonthlyExpenses = driver.findElement(By.xpath("//td[@class='RentalStrategy_tdHeadline__PAmFH']//span[contains(.,'MONTHLY EXPENSES')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", MonthlyExpenses);
        MonthlyExpenses.click();
        WebElement Mortgage_Amount_Locator = driver.findElement(By.xpath("(//div[@class='RentalStrategy_extraRows__j0wmN']/span)[1]"));
        String Mortgage_Amount = Mortgage_Amount_Locator.getText();
        Mortgage_Amount = Mortgage_Amount.replace("$", "").replace(",", "");
        Assert.assertEquals(Mortgage_Amount, "1725");
    }

    @AfterMethod
    public void TearDown() {
        driver.close();
    }

}
