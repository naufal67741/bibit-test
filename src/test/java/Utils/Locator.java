package Utils;

import org.openqa.selenium.By;

public class Locator {
    public final String name;
    public final By by;

    public Locator(String name, By by){
        this.name = name;
        this.by = by;
    }
}
