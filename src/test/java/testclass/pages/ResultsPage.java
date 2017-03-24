package testclass.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.TextBox;
import webdriver.Browser;

import java.util.*;
import java.lang.String;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by Alexandr.Vershok on 3/20/2017.
 */
public class ResultsPage extends BaseForm {

    private Label company = new Label(By.xpath("//*[@class='i-checkbox__real' and @value='samsung'][1]/following-sibling::span"), "samsung");
    private TextBox priceFilter = new TextBox(By.xpath("//*[@class='schema-filter__group']/div[2]/input[@placeholder='до']"), "Price less than field");
    private TextBox producedAfter = new TextBox(By.xpath("//*[@class='schema-filter__group']/div[1]/input[@placeholder='2010']"), "Produced After");
    private Label diagonalGT = new Label(By.xpath("//*[@class='schema-filter__group']/div[1]/select[1]"), "Diagonal from");
    private Label diagonalLow = new Label(By.xpath("//*[@class='schema-filter__group']/div[1]/select[1]/option[22]"), "39 Inches");
    private Label diagonalLT = new Label(By.xpath("//*[@class='schema-filter__group']/div[2]/select[1] "), "Diagonal to");
    private Label diagonalHi = new Label(By.xpath("//*[@class='schema-filter__group']/div[2]/select[1]/option[24] "), "42 Inches");
    private int diagLow = 39;
    private int diagHi = 42;
    private String maker = "Samsung";
    private List<WebElement> listOfResults;
    private String[] arrayOfStrings;
    private String produced;
    private String priceLessThan;


    public ResultsPage() {
        super(By.xpath(".//*[@class='schema-header__title']"), "Page with search results");
    }

    private void listToStrings(List<WebElement> listOfResults) {

        this.listOfResults = listOfResults;

        for (int i = 0; i < listOfResults.size(); i++) {
            arrayOfStrings[i] = listOfResults.get(i).getAttribute("href");
        }
    }


    public void setFilter(String priceLessThan, String produced) {
        this.produced = produced;
        this.priceLessThan = priceLessThan;
        company.click();
        priceFilter.setText(priceLessThan);
        producedAfter.setText(produced);
        diagonalGT.click();
        diagonalLow.click();
        diagonalLT.click();
        diagonalHi.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Wait<WebDriver> wait = new FluentWait(browser.getDriver()).withTimeout(25, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
                                        public WebElement apply(WebDriver driver) {
                                            return browser.getDriver().findElement(By.xpath("//*[@class='footer-style']"));

                                        }
                                    }
        );
    }


    public void checkResults() {

        int count = 1;

        listOfResults = browser.getDriver().findElements(By.xpath("//*[@class='schema-product__group']//div[@class='schema-product__title']/a"));
        arrayOfStrings = new String[listOfResults.size()];


        listToStrings(listOfResults);
        assertEquals(listOfResults.size(), arrayOfStrings.length);

        for (String s : arrayOfStrings) {

            browser.navigate(s);
            Wait<WebDriver> wait = new FluentWait(browser.getDriver()).withTimeout(25, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

            WebElement bar = wait.until(new Function<WebDriver, WebElement>() {
                                            public WebElement apply(WebDriver driver) {
                                                return browser.getDriver().findElement(By.xpath("//*[@class='product-aside__legal']"));

                                            }
                                        }
            );

            //Check Year
            info("Checking year of " + count + " result");
            String year = new Label(By.xpath("//*[@id='specs']//td[contains(text(),'Дата выхода на рынок')]/following-sibling::td/span")).getText();
            assertTrue(Integer.parseInt(produced) >= Integer.parseInt(year.substring(0, 3)));

            //Check Price
            info("Checking price of " + count + " result");
            String price = new Label(By.xpath("//*[@class='offers-description__flex']/div/div[1]/a"), "Check price").getText();
            int priceLessThanInt = Integer.parseInt(priceLessThan);
            if (priceLessThanInt >= Integer.parseInt(price.split(",")[0])) {
                assertTrue(priceLessThanInt >= Integer.parseInt(price.split(",")[0]));

            } else {

                String usedPrice = new Label(By.xpath("//*[@class='offers-description__price offers-description__price_secondary']/a"), "Check Value for Used Price").getText();
                int usedPriceInt = Integer.parseInt(usedPrice.split(",")[0]);
                assertTrue(priceLessThanInt >= usedPriceInt);
            }

            //Check maker
            info("Checking maker of " + count + " result");
            String makerInResults = new Label(By.xpath("//*[@class='breadcrumbs__link']//span[contains(.,'Samsung')]")).getText();
            assertTrue(makerInResults.toLowerCase().contains(maker.toLowerCase()));

            //Check diagonal
            info("Checking diagonal of " + count + " result");
            String diagonal = new Label(By.xpath("//p[@itemprop='description']")).getText();
            int diagonalInt = Integer.parseInt(diagonal.split("\"")[0]);
            assertTrue((diagLow < diagonalInt) && (diagonalInt < diagHi));
            count++;
        }
    }
}


