package com.tientvpd10102.lab6;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class LoginTest {
    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Login Results");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Email");
        headerRow.createCell(1).setCellValue("Password");
        headerRow.createCell(2).setCellValue("Kết quả");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        String[][] testData = {
                { "sai_email@gmail.com", "sai_password" },
                { "dung_email@gmail.com", "dung_password" }
        };
        for (int i = 0; i < testData.length; i++) {
            String email = testData[i][0];
            String password = testData[i][1];
            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("email")).sendKeys(email);
            driver.findElement(By.id("pass")).clear();
            driver.findElement(By.id("pass")).sendKeys(password);
            driver.findElement(By.name("login")).click();
            boolean loginFailed;
            try {
                WebElement errorMessage = driver.findElement(By.className("_9ay7"));
                loginFailed = errorMessage.isDisplayed();
            } catch (Exception e) {
                loginFailed = false;
            }
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(email);
            row.createCell(1).setCellValue(password);
            row.createCell(2).setCellValue(loginFailed ? "Thất bại" : "Thành công");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
        try (FileOutputStream fileOut = new FileOutputStream(new File("TestResults.xlsx"))) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Kết quả kiểm thử đã được lưu vào TestResults.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
