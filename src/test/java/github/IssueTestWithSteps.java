package github;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

@Owner("Rinat")
@Feature("Создание Issue with Steps")


public class IssueTestWithSteps {

    static String issue_name = RandomStringUtils.randomAlphabetic(8);
    private static final String BASE_URL = "https://github.com";

    private final BasicSteps steps = new BasicSteps();

    @BeforeEach
    public void initLogger() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }

    @AfterEach
    public void SignOut() {
        $$(".avatar-user").find(visible).click();
        $$(".dropdown-signout").find(visible).click();
    }

    @Test
    @DisplayName("Создание Issue и его проверка через Steps")
    public void CreateIssueWithSteps (){
        steps.openMainPage();
        steps.SignInGithub();
        steps.CreateIssue();
        steps.CheckIssueByName();
    }

}