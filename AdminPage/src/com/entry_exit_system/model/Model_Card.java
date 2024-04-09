package com.entry_exit_system.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Model_Card {

    private Icon icon;
    private String title;
    private String values;

    public Model_Card(Icon icon, String title, String values) {
        this.icon = resizeIcon(icon, 55, 55);
        this.title = title;
        this.values = values;
    }

    public Model_Card() {
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    // Method to resize the icon to the specified width and height
    private ImageIcon resizeIcon(Icon icon, int width, int height) {
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();
            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        } else {
            return null;
        }
    }
}
