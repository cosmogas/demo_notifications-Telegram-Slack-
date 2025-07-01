package qa_mentorUI_demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

@Epic("QA Mentor Website")
public class UITests {

    @BeforeAll
    static void setUp() {
        Configuration.headless = true;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;
    }

    @Test
    @Feature("Home Page")
    @Story("Open home and check title")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("UI: Open home page and check main header")
    @Description("Open https://qamentor.com and check for a visible h1 or company name")
    void testHomePageHeaderVisible() {
        open("https://qamentor.com");
        $("body").shouldHave(Condition.text("QA Mentor"));
    }

    @Test
    @Feature("Page: About Us")
    @Story("Open and verify About Us content")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("UI: Open About Us page and verify content")
    @Description("Navigate to About Us and ensure it contains expected text")
    void testAboutUsPage() {
        open("https://qamentor.com/about-us/");
        $("body").shouldHave(Condition.text("QA Mentor is a global leader"));
    }

    @Test
    @Feature("Page: Contact")
    @Story("Open Contact Us and check address or phone")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("UI: Open Contact Us and check info block")
    @Description("Navigate to Contact Us and verify address or support info is visible")
    void testContactInfoPresent() {
        open("https://qamentor.com/contact-us/");
        $("body").shouldHave(Condition.text("support@qamentor.com"));
    }

    @Test
    @Feature("Sitemap")
    @Story("Check that sitemap contains links")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("UI: Sitemap contains core service links")
    @Description("Open sitemap page and verify expected service links are there")
    void testSitemapLinks() {
        open("https://qamentor.com/sitemap/");
        $$("a").findBy(Condition.text("AI Enabled Testing Services")).shouldBe(Condition.visible);
        $$("a").findBy(Condition.text("Application Testing Services")).shouldBe(Condition.visible);
    }
}



