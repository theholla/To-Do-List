import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Task list!");
  }

  @Test
  public void taskIsCreatedTest(){
    goTo("http://localhost:4567");
    fill("#description").with("Water the vegetables");
    submit(".btn");
    assertThat(pageSource()).contains("Your task has been saved.");
  }

  @Test
  public void taskIsDisplayedTest() {
    goTo("http://localhost:4567");
    fill("#description").with("Water the vegetables");
    submit(".btn");
    //multiple links per page: pass the second argument (filter) withText
    //to tell the web driver to only click on a n anchor tag with the specific text
    click("a", withText("Go back"));
    assertThat(pageSource()).contains("Water the vegetables");
  }

  @Test
  public void multipleTasksAreDisplayedTest() {
    goTo("http://localhost:4567");
    fill("#description").with("Water the vegetables");
    submit(".btn");
    click("a", withText("Go back"));
    fill("#description").with("Buy groceries");
    submit(".btn");
    click("a", withText("Go back"));
    //want to make sure that both tasks are being displayed
    assertThat(pageSource()).contains("Water the vegetables");
    assertThat(pageSource()).contains("Buy groceries");

  }
}
