package github;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class BasicSteps {

    static String s = RandomStringUtils.randomAlphabetic(8);
    private static final String BASE_URL = "https://github.com";

    @Step("Открываем сайт Github")
    public void openMainPage() {
        open(BASE_URL);
    }

    @Step("Заходим в учетную запись Github")
    public void SignInGithub() {
        $(byText("Sign in")).click();
        $("#login_field").setValue("Tester-al");
        $("#password").setValue("sdpchound322");
        $(byName("commit")).click();
    }

    @Step("Создаем новое Issue")
    public void CreateIssue() {
        $(".d-inline-flex").click();
        $("a[href='/Tester-al/asdqweqe/issues']").click();
        $(".ml-3").click();
        $("#issue_title").click();
        $("#issue_title").setValue(s);
        $(byText("Submit new issue")).click();
    }

    @Step("Проверяем Issue по его названию, которое генерируется автоматически")
    public void CheckIssueByName() {
        $("a[href='/Tester-al/asdqweqe/issues']").click();
        $("body").shouldHave(text(s));
    }

}
