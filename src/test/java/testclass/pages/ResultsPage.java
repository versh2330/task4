package testclass.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.google.common.base.Function;
import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.TextBox;
import webdriver.Browser;
import java.util.Iterator;
import java.util.List;
import java.lang.String;

import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by Alexandr.Vershok on 3/20/2017.
 */
public class ResultsPage extends BaseForm {
//TODO private String[] linksArray; copy links from List listOfResults
    private Label company = new Label(By.xpath(".//*[@class=\"i-checkbox__real\" and @value=\"samsung\"][1]/following-sibling::span"), "samsung");
    private TextBox priceFilter = new TextBox(By.xpath(".//*[@class=\"schema-filter__group\"]/div[2]/input[@placeholder=\"до\"]"),"Price less than field");
    private TextBox producedAfter = new TextBox(By.xpath(".//*[@class=\"schema-filter__group\"]/div[1]/input[@placeholder=\"2010\"]"),"Produced After");
    private Label diagonalGT = new Label(By.xpath(".//*[@class=\"schema-filter__group\"]/div[1]/select[1]"), "Diagonal from");
    private Label inches39 = new Label(By.xpath(".//*[@class=\"schema-filter__group\"]/div[1]/select[1]/option[22]"), "39 inches");
    private Label diagonalLT = new Label(By.xpath(".//*[@class=\"schema-filter__group\"]/div[2]/select[1]"), "Diagonal to");
    private Label inches42 = new Label(By.xpath(".//*[@class=\"schema-filter__group\"]/div[2]/select[1]/option[24]"), "42 inches");
    private List<WebElement> listOfResults;


    public ResultsPage() { super(By.xpath(".//*[@class=\"schema-header__title\"]"), "TVs");}

    public void setFilter(){
        company.click();
        priceFilter.setText("1000");
        producedAfter.setText("2013");
        diagonalGT.click();
        inches39.click();
        diagonalLT.click();
        inches42.click();


    }


//@Parameters({"fromYear", "Make", "fromSize", "toSize", "toPrice")
    public void checkResults(){
        String url = Browser.getLocation();
        System.out.println("Starting step4");
        listOfResults = Browser.getDriver().findElements(By.xpath(".//*[@class=\"schema-product__group\"]/div/div/div[2]/a/img"));
        //linkArray = new String[listOfResults.size()];?
        for(WebElement result : listOfResults){ //stale element reference; replace by linkArray

            result.click();
            System.out.println("Searching for year");
            String year = new Label(By.xpath(".//*[@class=\"product-specs__table\"]/tbody[1]/tr[2]/td[2]/span[contains(.,\"2013\") or contains(.,\"2014\") or contains(.,\"2015\")  or contains(.,\"2016\")  or contains(.,\"2017\")]"),"Check Year").getText();
           //split(" ") the year. get the year[0], 
            // check @param fromYear is <= Integer.parseInt(year[0])
            String price = new Label(By.xpath(".//*[@class=\"offers-description__flex\"]/div/div[1]/a"),"Check price").getText();
            String lowestPrice = price.split(",")[0];
            int lPrice = Integer.parseInt(lowestPrice);
            assertTrue(lPrice < 1000);
            System.out.println(year);
            System.out.println(lPrice);
            Browser.navigate(url);


        }
    }




}
