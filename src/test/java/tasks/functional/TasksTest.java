package tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
		
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap= DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.135:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.0.135:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
		
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {
			
			// Clicar em Todo
			driver.findElement(By.id("addTodo")).click();

			// Preencher a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Preencher a data
			driver.findElement(By.id("dueDate")).sendKeys("30/12/2030");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar a mensagem
			Assert.assertEquals("Success!", driver.findElement(By.id("message")).getText());

		} finally {
			// fechar o browser
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {
			
			// Clicar em Todo
			driver.findElement(By.id("addTodo")).click();

			// Preencher a data
			driver.findElement(By.id("dueDate")).sendKeys("30/12/2030");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar a mensagem
			Assert.assertEquals("Fill the task description", driver.findElement(By.id("message")).getText());

		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {
			
			// Clicar em Todo
			driver.findElement(By.id("addTodo")).click();

			// Preencher a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar a mensagem
			Assert.assertEquals("Fill the due date", driver.findElement(By.id("message")).getText());

		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {

			// Clicar em Todo
			driver.findElement(By.id("addTodo")).click();

			// Preencher a descricao
			driver.findElement(By.id("task")).sendKeys("Teste data passada");

			// Preencher a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();


			// validar a mensagem
			Assert.assertEquals("Due date must not be in past", driver.findElement(By.id("message")).getText());

		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void deveRemoverTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {
			
			// Inserir
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("30/12/2030");
			driver.findElement(By.id("saveButton")).click();
			Assert.assertEquals("Success!", driver.findElement(By.id("message")).getText());
			
			// Remover
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']"));
			Assert.assertEquals("Success!", driver.findElement(By.id("message")).getText());
			
			
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	

}
