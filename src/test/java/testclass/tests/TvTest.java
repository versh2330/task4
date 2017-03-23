package testclass.tests;


import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import testclass.pages.CatalogPage;
import testclass.pages.MainPage;
import testclass.pages.ResultsPage;
import webdriver.BaseTest;

/**
 * Created by Alexandr.Vershok on 3/20/2017.
 */
public class TvTest extends BaseTest {
    private String priceLessThan;
    private String producedBefore;


    @BeforeSuite
    @Parameters({"priceLessThan", "producedBefore"})
    public void setUp(String priceLessThan, String producedBefore) {
        System.setProperty("webdriver.chrome.driver", "./../../../resources/chromedriver.exe"); //Will it work?
        this.priceLessThan = priceLessThan;
        this.producedBefore = producedBefore;
    }


    @Override
    public void runTest() {

        logStep();
        MainPage mainPage = new MainPage();
        mainPage.openCatalog();

        logStep();
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.openCategory();


        logStep();
        ResultsPage resultsPage = new ResultsPage();
        resultsPage.setFilter(priceLessThan, producedBefore);

        logStep();
        resultsPage.checkResults();

    }


}
