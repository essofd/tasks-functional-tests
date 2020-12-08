package tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void naoDeveSalvarTarefaComDataPassada() {
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

}
