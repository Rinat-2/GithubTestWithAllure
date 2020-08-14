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
import static io.qameta.allure.Allure.step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


@Owner("Rinat")
@Feature("Создание Issue")
public class IssueTests {

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
    public void signOut() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Проверка создания issue")
    public void createIssue (){
        step("Открываем сайт Github", () -> {
            open(BASE_URL);
        });
        step("Заходим в учетную запись Github", () -> {
            $(byText("Sign in")).click();
            $("#login_field").setValue("Tester-al");
            $("#password").setValue("sdpchound322");
            $(byName("commit")).click();
        });
        step("Создаем новое Issue", () -> {
            $(".d-inline-flex").click();
            $("a[href='/Tester-al/asdqweqe/issues']").click();
            $(".ml-3").click(); //Кликаем на New issue
            $("#issue_title").click();
            $("#issue_title").setValue(issue_name);
            $(byText("Submit new issue")).click();
        });
        step("Проверяем Issue по его названию, которое генерируется автоматически", () -> {
            $("a[href='/Tester-al/asdqweqe/issues']").click();
            $("body").shouldHave(text(issue_name));
        });
    }
}
