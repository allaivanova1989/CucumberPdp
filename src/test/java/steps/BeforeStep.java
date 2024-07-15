package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;

public class BeforeStep {

    @Given("I am on site {string}")
    public void openSite(String url){
        System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
        Configuration.timeout = 60000;
        Selenide.open(url);
    }

}
