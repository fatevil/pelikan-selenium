package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.todo.TodoPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TodoMvcTests extends DriverBase {
    @Test
    public void createTodo() {
        TodoPage todoPage = new TodoPage(driver);
        todoPage.goTo();
        todoPage.waitFor();
        todoPage.createTodo("My first todo");

        assertThat(driver.findElement(By.cssSelector("ul.todo-list li:first-child")).getText()).isEqualToIgnoringCase("My first todo");
    }

    @Test(dependsOnMethods= "createTodo")
    public void editTodo() {
        TodoPage todoPage = new TodoPage(driver);
        todoPage.editTodo(0," (edited)");

        assertThat(driver.findElement(By.cssSelector("ul.todo-list li:first-child")).getText()).isEqualToIgnoringCase("My first todo (edited)");
    }

    @Test(dependsOnMethods= "editTodo")
    public void deleteTodo() {
        TodoPage todoPage = new TodoPage(driver);
        todoPage.deleteTodo(0);

        assertThat(driver.findElements(By.cssSelector("ul.todo-list li:first-child"))).isEmpty();
    }

}
