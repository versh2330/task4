package testclass.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * Created by Alexandr.Vershok on 3/20/2017.
 */
public class MainPage extends BaseForm{

    private Label catalog = new Label(By.xpath(".//*[@class=\"b-main-navigation\"]/li/a[contains(.,\"Каталог\")]"), "Catalog link");

    public MainPage() { super(By.xpath(".//*[@class=\"col-sub\"]/div"),"News block");}

    public void openCatalog(){
        catalog.click();
    }

}
