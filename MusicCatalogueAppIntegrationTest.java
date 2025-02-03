package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MusicCatalogueAppIntegrationTest {

    @Test
    public void testFullApplicationWorkflow() throws Exception {

        MusicCatalogueApp app = new MusicCatalogueApp();
        JFrame frame = getPrivateField(app, "frame");
        assertNotNull(frame, "Frame should be initialized");
        assertTrue(frame.isVisible(), "Frame should be visible");
        JTextField artistNameField = getPrivateField(app, "artistNameField");
        JTextField recordingStudioField = getPrivateField(app, "recordingStudioField");
        JComboBox<String> categoryComboBox = getPrivateField(app, "categoryComboBox");
        JCheckBox availableCheckBox = getPrivateField(app, "availableCheckBox");
        JButton submitButton = getPrivateField(app, "submitButton");

        artistNameField.setText("Integration Artist");
        recordingStudioField.setText("Integration Studio");
        categoryComboBox.setSelectedItem("Jazz");
        availableCheckBox.setSelected(true);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        submitButton.doClick();

        String expectedOutput = "Artist Name: Integration Artist" + System.lineSeparator() +
                "Recording Studio: Integration Studio" + System.lineSeparator() +
                "Category: Jazz" + System.lineSeparator() +
                "Available: true" + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString(), "Application should correctly handle input and print output");

        System.setOut(System.out);
    }

    private <T> T getPrivateField(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get private field " + fieldName, e);
        }
    }
}