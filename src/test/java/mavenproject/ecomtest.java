package mavenproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

public class ecomtest {
	 private WebDriver driver;
	 private WebDriverWait wait;

	    @BeforeTest
	    public void setUp() {
	        // Set up ChromeDriver - make sure to update the path to your chromedriver executable
	        System.setProperty("webdriver.chrome.driver", "D:\\downloads work\\viddeo\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	        driver = new ChromeDriver();
	        wait = new WebDriverWait(driver, 10);
	        driver.manage().window().maximize();
	    }

	    @Test(priority=1)
	    public void testAmazonProductSearch() throws InterruptedException {
	        searchAndNavigateProduct("https://www.amazon.in", "Titan watch");
	    }


	    private void searchAndNavigateProduct(String url, String productName) throws InterruptedException {
	        driver.get(url);

	        // Search for the product
	        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(" form > div > div > input")));
	        searchBox.sendKeys(productName);
	        searchBox.submit();

	        // Wait for search results and select the first product
	        List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#search > div.s-desktop-width-max.s-desktop-content.s-opposite-dir.s-wide-grid-style.sg-row > div.sg-col-20-of-24.s-matching-dir.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span.rush-component.s-latency-cf-section > div.s-main-slot.s-result-list.s-search-results.sg-row > div:nth-child(11) > div > div > span > div > div")));
	        if (!searchResults.isEmpty()) {
	            WebElement firstProduct = searchResults.get(0);

	            // Capture product details
	            String name = firstProduct.findElement(By.cssSelector("h2 span")).getText();
	            String price = firstProduct.findElement(By.cssSelector(".a-price-whole")).getText();
	            String link = firstProduct.findElement(By.cssSelector("h2 a")).getAttribute("href");

	            System.out.println("Product Name: " + name);
	            System.out.println("Product Price: " + price);
	            System.out.println("Product Link: " + link);

	            // Click on the first product
	            firstProduct.click();
	            Thread.sleep(12000);
	          //   = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
	            WebElement addToCartButton =driver.findElement(By.cssSelector("#submit\\.add-to-cart"));
	         // Scroll the button into view in case it's not visible
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);

	         // Try to click the button using Selenium's click
	         try {
	             addToCartButton.click();
	         } catch (Exception e) {
	             // If the regular click fails, fallback to using JavaScript click
	             System.out.println("Regular click failed, using JavaScript click.");
	             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
	         }
	            // Handle sliding pop-up (like warranty offer) if it appears
	            try {
	                WebElement warrantyPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'warranty-offer-popup')]")));
	                WebElement declineOfferButton = warrantyPopup.findElement(By.xpath("//button[contains(text(), 'No Thanks')]"));
	                declineOfferButton.click();
	            } catch (Exception e) {
	                System.out.println("No warranty popup appeared.");
	            }

	            // Proceed to checkout
	            WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("proceed-to-checkout-button")));
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", proceedToCheckoutButton);
	            proceedToCheckoutButton.click();

	            // Navigate to Payment Gateway screen
	            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("proceedToRetailCheckout")));
	            continueButton.click();

	            // Verify we're on the payment screen (this may vary depending on the website and user's account status)
	            wait.until(ExpectedConditions.urlContains("payment"));
	            System.out.println("Successfully navigated to the payment gateway screen");
	        } else {
	            System.out.println("No search results found for " + productName);
	        }
	    }

	//    @AfterTest
	//    public void tearDown() {
	//        if (driver != null) {
	//            driver.quit();
	//        }
	    }

