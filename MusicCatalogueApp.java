import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicCatalogueApp {

    public static void main(String[] args) {
        new MusicCatalogueApp();
    }



    private JLabel titleLabel;
    private JPanel southPanel, centerPanel, westPanel;
    private JButton submitButton, exitButton;
    private JTextField artistNameField, recordingStudioField;
    private JComboBox<String> categoryComboBox;
    private JCheckBox availableCheckBox;
    private JList<String> categoryList;
    private JFrame frame;

    public MusicCatalogueApp() {
        // Set up the main window
        frame = new JFrame("Music Catalogue");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // North: Title label
        titleLabel = new JLabel("My Music Catalogue", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // West: Category list
        String[] categories = {"Rap", "Reggae", "Ballads", "Rock", "Jazz"};
        categoryList = new JList<>(categories);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        westPanel = new JPanel();
        westPanel.setBorder(BorderFactory.createTitledBorder("Categories"));
        westPanel.add(new JScrollPane(categoryList));
        frame.add(westPanel, BorderLayout.WEST);

        // Center: Input form
        centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        centerPanel.add(new JLabel("Artist Name:"));
        artistNameField = new JTextField();
        centerPanel.add(artistNameField);

        centerPanel.add(new JLabel("Recording Studio:"));
        recordingStudioField = new JTextField();
        centerPanel.add(recordingStudioField);

        centerPanel.add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(categories);
        centerPanel.add(categoryComboBox);

        centerPanel.add(new JLabel("Available:"));
        availableCheckBox = new JCheckBox();
        centerPanel.add(availableCheckBox);

        frame.add(centerPanel, BorderLayout.CENTER);

        // South: Buttons
        southPanel = new JPanel();
        submitButton = new JButton("Submit");
        exitButton = new JButton("Exit");
        southPanel.add(submitButton);
        southPanel.add(exitButton);
        frame.add(southPanel, BorderLayout.SOUTH);

        // Event listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printDetails();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private void printDetails() {
        String artistName = artistNameField.getText();
        String recordingStudio = recordingStudioField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        boolean available = availableCheckBox.isSelected();

        System.out.println("Artist Name: " + artistName);
        System.out.println("Recording Studio: " + recordingStudio);
        System.out.println("Category: " + category);
        System.out.println("Available: " + available);

    }


}
