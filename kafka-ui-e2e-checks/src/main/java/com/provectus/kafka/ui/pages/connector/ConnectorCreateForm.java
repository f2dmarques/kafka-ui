package com.provectus.kafka.ui.pages.connector;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.provectus.kafka.ui.utilities.WebUtils.clickByJavaScript;
import static com.provectus.kafka.ui.utilities.screenshots.Screenshooter.log;

public class ConnectorCreateForm {

    protected SelenideElement nameField = $(By.xpath("//input[@name='name']"));
    protected SelenideElement contentTextArea = $(".ace_text-input");
    protected SelenideElement submitButton = $(By.xpath("//button[@type='submit']"));

    private static final String path = "/ui/clusters/secondLocal/connectors/create_new";

    @Step("Set connector config JSON")
    public ConnectorDetails setConnectorConfig(String connectName, String configJson) {
        nameField.setValue(connectName);
        $("#config").click();
        contentTextArea.setValue("");
        contentTextArea.setValue(String.valueOf(configJson.toCharArray()));
        nameField.click();
        clickByJavaScript(submitButton);
        sleep(4000);
        log.info("Connector config is submitted");
        return new ConnectorDetails();
    }

    @Step
    public ConnectorCreateForm waitUntilScreenReady() {
        nameField.shouldBe(Condition.visible);
        return this;
    }
}