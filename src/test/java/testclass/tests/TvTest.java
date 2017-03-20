package testclass.tests;
import testclass.pages.CatalogPage;
import testclass.pages.MainPage;
import testclass.pages.ResultsPage;
import webdriver.BaseTest;

/**
 * Created by Alexandr.Vershok on 3/20/2017.
 */
public class TvTest extends BaseTest{

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
       resultsPage.setFilter();

       logStep();
       resultsPage.checkResults();

   }


}
