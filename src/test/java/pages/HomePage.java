package pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;
	private final String timeLink ="//a[@class='oxd-main-menu-item active']";
	
	public HomePage(Page page) {
		this.page =page;
		
	}
	
	public void ClickTimeLink() {
		page.click(timeLink);
	}
	
}
