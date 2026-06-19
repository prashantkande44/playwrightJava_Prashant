package pages;

import com.microsoft.playwright.Page;

import base.BaseTest;

public class LoginPage {
	
	private Page page;
	private final String usernameTextbox="//input[@placeholder='Username']";
	private final String passwordTextbox= "//input[@placeholder='Password']";
	private final String loginButton ="//button[normalize-space()='Login']";
	
	
	public LoginPage(Page page) {
		this.page = page;
		}
	public void addUsername(String username) {
		page.fill(usernameTextbox, username);
	}
	
	public void addPassword(String password) {
		page.fill(passwordTextbox, password);
	}
	
	public void clickLoginButton() {
		page.click(loginButton);
	}
}
