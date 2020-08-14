package github;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class BasicSteps {

    private static final String BASE_URL = "https://github.com";

    @Step("Открываем сайт Github")
    public void openMainPage() {
        open(BASE_URL);
    }

    @Step("Заходим в учетную запись Github")
    public void signInGithub() {
        $(byText("Sign in")).click();
        $("#login_field").setValue("Tester-al");
        $("#password").setValue("sdpchound322");
        $(byName("commit")).click();
    }

    @Step("Создаем новое Issue")
    public void createIssue(String issueName) {
        $(".d-inline-flex").click();
        $("a[href='/Tester-al/asdqweqe/issues']").click();
        $(".ml-3").click();
        $("#issue_title").click();
        $("#issue_title").setValue(issueName);
        $(byText("Submit new issue")).click();
    }

    @Step("Проверяем Issue по его названию, которое генерируется автоматически")
    public void checkIssueByName(String issueName) {
        $("a[href='/Tester-al/asdqweqe/issues']").click();
        $("body").shouldHave(text(issueName));
    }

}
