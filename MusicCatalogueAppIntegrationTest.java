ackage org.example;

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
        JTextField recordingStudioField = getPrivateField(app, "recordingStudio