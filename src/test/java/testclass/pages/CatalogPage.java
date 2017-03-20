package testclass.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;
import java.awt.*;

/**
 * Created by Alexandr.Vershok on 3/20/2017.
 */
public class CatalogPage extends BaseForm {

    private Label electro = new Label(By.xpath(".//*[@class=\"catalog-navigation\"]//span[contains(.,\"Электроника\")]"), "Electronics");
    private Label teleAndAudio = new Label(By.xpath(".//*[@class=\"catalog-navigation-list__wrapper\"]/div/div/div/div[2]/div[1]"), "Tele and Audio");
    private Label tvCategory = new Label(By.xpath(".//*[@class=\"catalog-navigation-list__wrapper\"]//a[@href=\"https://catalog.onliner.by/tv\"][1]"),"TV category");

    public CatalogPage() { super(By.className("catalog-navigation__title"), "Catalog page"); }

    public void openCategory(){
        electro.click();
        teleAndAudio.click();
        tvCategory.click();
    }

}
