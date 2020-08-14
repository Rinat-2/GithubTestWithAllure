package github;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;


@Feature("Создание Issue with Listener")
public class ListenerTests {

    static String issueName = RandomStringUtils.randomAlphabetic(8);
    private static final String BASE_URL = "https://github.com";

    @BeforeEach
    public void initLogger() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }

    @AfterEach
    public void signOut() {
        $$(".avatar-user").find(visible).click();
        $$(".dropdown-signout").find(visible).click();
    }

    @Test
    @DisplayName("Проверка создания issue")
    public void createIssue (){

            open(BASE_URL);

            $(byText("Sign in")).click();
            $("#login_field").setValue("Tester-al");
            $("#password").setValue("sdpchound322");
            $(byName("commit")).click();

            $(".d-inline-flex").click();
            $("a[href='/Tester-al/asdqweqe/issues']").click();
            $(".ml-3").click(); //Кликаем на New issue
            $("#issue_title").click();
            $("#issue_title").setValue(issueName);
            $(byText("Submit new issue")).click();

            $("a[href='/Tester-al/asdqweqe/issues']").click();
            $("body").shouldHave(text(issueName));
    }
}




