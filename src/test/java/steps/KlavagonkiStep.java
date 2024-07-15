package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KlavagonkiStep {

    private final SelenideElement closeWindowButton = $x("//input[@value='Закрыть']");
    private final SelenideElement startGameButton = $x("//a[@id='host_start']");
    private  SelenideElement highlightWord = $x("//span[@id='typefocus']");
    private final SelenideElement inputField = $x("//input[@id='inputtext']");
    private  SelenideElement afterFocus = $x("//span[@id='afterfocus']");
    private  SelenideElement resultText = $x("//td[text()='Это вы']//ancestor-or-self::div//div[@class='stats']//div[2]/span/span");

    private String getCurrentWord(){
        return highlightWord.getText().replaceAll("c", "с").replaceAll("o", "о");
    }

     @When("We start game")
    public void startGame() {
        closeWindowButton.click();
        if(startGameButton.isDisplayed()){
            startGameButton.click();
        }
    }

    @And("We wait start of the game")
    public void waitForStartGame() {
         highlightWord.click();
            }

    @And("Input highlighted words")
    public void playGame() {
         while (true){

             String currentWord = getCurrentWord();
             System.out.println(currentWord);
             String afterFocusedSymbol = afterFocus.getText();
             System.out.println(afterFocusedSymbol);
             inputField.sendKeys(currentWord);
             if(afterFocusedSymbol.equals(".")){
                 inputField.sendKeys(".");
                 break;
             }
             inputField.sendKeys(Keys.SPACE);
         }
    }

    @Then("We record that the game is over and there are more than {int} symbols")
    public void endGame(int minValue) {
        String result =resultText.getText();
        int resultNumber = Integer.parseInt(result);
        System.out.println("Количество знаков в минуту: " + resultNumber );
        assertTrue(resultNumber > minValue,"Актуальный результат: " + resultNumber);
    }
}
