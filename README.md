# 📱💻 API / Web / Android Automation Framework

This project is a unified test automation framework for:

- 🌐 Web UI testing using Selenium WebDriver  
- 🤖 Android mobile testing using Appium  
- 🔌 REST API testing using RestAssured  

All test layers are implemented using **Java**, **Cucumber (BDD)**, and **Maven**.

---

## 📂 Project Structure

src
├── main
│ └── java
│ └── com.bibit.automation # Core shared logic
├── test
│ ├── java
│ │ ├── Runner # Test runners (API, Android, Web)
│ │ ├── StepDefinitions # StepDefs for each platform
│ │ ├── Payloads # API request payloads
│ │ ├── Page # Page objects (Web/Android)
│ │ └── Utils # DriverTools and shared helpers
│ └── resources
│ ├── features
│ │ ├── api.feature
│ │ ├── android.feature
│ │ └── web.feature
│ ├── schemas
│ │ ├── post.json # For create post
│ │ ├── get.json # For retrieve all posts
│ │ ├── put.json # For update post
│ │ └── delete.json # For delete post
│ └── testdata # App/web test data and config


---

## ✅ Prerequisites

- Java 17+
- Maven 3.8+
- Node.js
- Android Studio + emulator/device
- Appium (v2.x)
- ChromeDriver (for Web testing)

### 📦 Install Appium

```bash
npm install -g appium
appium driver install uiautomator2

### **How to run the test**
API: mvn clean test -Dtest=Runner.APITestRunner
Android: mvn clean test -Dtest=Runner.AndroidTestRunner
Web: mvn clean test -Dtest=Runner.WebTestRunner
