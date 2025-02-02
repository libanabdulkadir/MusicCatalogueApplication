
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MusicCatalogueAppTest {

    @Test
    public void testFrameInitialization() {
        MusicCatalogueApp app = new MusicCatalogueApp();
        JFrame frame = getPrivateField(app, "frame");

        assertNotNull(frame, "Frame should not be null");
        assertEquals("Music Catalogue", frame.getTitle(), "Frame title should be 'Music Catalogue'");
        assertEquals(new Dimension(600, 400), frame.getSize(), "Frame size should be 600x400");
        assertTrue(frame.isVisible(), "Frame should be visible");
    }

    @Test
    public void testComponentInitialization() {
        MusicCatalogueApp app = new MusicCatalogueApp();

        JLabel titleLabel = getPrivateField(app, "titleLabel");
        JPanel southPanel = getPrivateField(app, "southPanel");
        JPanel centerPanel = getPrivateField(app, "centerPanel");
        JPanel westPanel = getPrivateField(app, "westPanel");
        JButton submitButton = getPrivateField(app, "submitButton");
        JButton exitButton = getPrivateField(app, "exitButton");
        JTextField artistNameField = getPrivateField(app, "artistNameField");
        JTextField recordingStudioField = getPrivateField(app, "recordingStudioField");
        JComboBox<String> categoryComboBox = getPrivateField(app, "categoryComboBox");
        JCheckBox availableCheckBox = getPrivateField(app, "availableCheckBox");
        JList<String> categoryList = getPrivateField(app, "categoryList");

        assertNotNull(titleLabel, "Title label should not be null");
        assertNotNull(southPanel, "South panel should not be null");
        assertNotNull(centerPanel, "Center panel should not be null");
        assertNotNull(westPanel, "West panel should not be null");
        assertNotNull(submitButton, "Submit button should not be null");
        assertNotNull(exitButton, "Exit button should not be null");
        assertNotNull(artistNameField, "Artist name field should not be null");
        assertNotNull(recordingStudioField, "Recording studio field should not be null");
        assertNotNull(categoryComboBox, "Category combo box should not be null");
        assertNotNull(availableCheckBox, "Available checkbox should not be null");
        assertNotNull(categoryList, "Category list should not be null");
    }

    @Test
    public void testTitleLabel() {
        MusicCatalogueApp app = new MusicCatalogueApp();
        JLabel titleLabel = getPrivateField(app, "titleLabel");

        assertEquals("My Music Catalogue", titleLabel.getText(), "Title label text should be 'My Music Catalogue'");
        assertEquals(new Font("Serif", Font.BOLD, 24), titleLabel.getFont(), "Title label font should be Serif, BOLD, 24");
    }

    @Test
    public void testCategoryList() {
        MusicCatalogueApp app = new MusicCatalogueApp();
        JList<String> categoryList = getPrivateField(app, "categoryList");

        String[] expectedCategories = {"Rap", "Reggae", "Ballads", "Rock", "Jazz"};
        ListModel<String> model = categoryList.getModel();

        assertEquals(expectedCategories.length, model.getSize(), "Category list should have 5 items");
        for (int i = 0; i < expectedCategories.length; i++) {
            assertEquals(expectedCategories[i], model.getElementAt(i), "Category at index " + i + " should match");
        }

        assertEquals(ListSelectionModel.SINGLE_SELECTION, categoryList.getSelectionMode(), "Category list should have single selection mode");
    }

    @Test
    public void testCategoryComboBox() {
        MusicCatalogueApp app = new MusicCatalogueApp();
        JComboBox<String> categoryComboBox = getPrivateField(app, "categoryComboBox");

        String[] expectedCategories = {"Rap", "Reggae", "Ballads", "Rock", "Jazz"};
        ComboBoxModel<String> model = categoryComboBox.getModel();

        assertEquals(expectedCategories.length, model.getSize(), "Category combo box should have 5 items");
        for (int i = 0; i < expectedCategories.length; i++) {
            assertEquals(expectedCategories[i], model.getElementAt(i), "Category at index " + i + " should match");
        }
    }

    @Test
    public void testPrintDetails() throws Exception {
        MusicCatalogueApp app = new MusicCatalogueApp();

        JTextField artistNameField = getPrivateField(app, "artistNameField");
        JTextField recordingStudioField = getPrivateField(app, "recordingStudioField");
        JComboBox<String> categoryComboBox = getPrivateField(app, "categoryComboBox");
        JCheckBox availableCheckBox = getPrivateField(app, "availableCheckBox");

        artistNameField.setText("Test Artist");
        recordingStudioField.setText("Test Studio");
        categoryComboBox.setSelectedItem("Jazz");
        availableCheckBox.setSelected(true);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Method printDetailsMethod = app.getClass().getDeclaredMethod("printDetails");
        printDetailsMethod.setAccessible(true);
        printDetailsMethod.invoke(app);

        String expectedOutput = "Artist Name: Test Artist" + System.lineSeparator() +
                "Recording Studio: Test Studio" + System.lineSeparator() +
                "Category: Jazz" + System.lineSeparator() +
                "Available: true" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "Printed details should match expected output");

        System.setOut(System.out);
    }

    private <T> T getPrivateField(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get private field " + fieldName, e);
        }
    }
}
