package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;

import base.BaseTest;

public class LoginTest extends BaseTest{
	
	  @Test
	  public void test() {
	    page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	    page.getByText("Username : Admin").click();
	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("Admin");
	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("admin123");
	    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
	    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Time")).click();
	    page.locator("form").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("View")).click();
	    page.locator("div").filter(new Locator.FilterOptions().setHasText("Select EmployeeEmployee")).nth(3).click();
	    assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Time"))).isVisible();
	    assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type for hints..."))).isEmpty();
	  }
	}
