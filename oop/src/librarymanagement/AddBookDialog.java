package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddBookDialog extends JDialog {
    private final JTextField txtTitle = new JTextField();
    private final JTextField txtAuthor = new JTextField();
    private final JSpinner spYear = new JSpinner(new SpinnerNumberModel(2020, 1500, 2100, 1));
    private final JTextField txtIsbn = new JTextField();

    public interface OnSaved { void saved(); }

    public AddBookDialog(Frame owner, OnSaved callback) {
        super(owner, "Add Book", true);
        setSize(420, 260);
        setLocationRelativeTo(owner);

        JPanel form = new JPanel(new GridLayout(5,2,8,8));
        form.add(new JLabel("Title:"));
        form.add(txtTitle);
        form.add(new JLabel("Author:"));
        form.add(txtAuthor);
        form.add(new JLabel("Year:"));
        form.add(spYear);
        form.add(new JLabel("ISBN:"));
        form.add(txtIsbn);

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        JPanel actions = new JPanel();
        actions.add(btnSave);
        actions.add(btnCancel);

        add(form, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);

        btnSave.addActionListener((ActionEvent e) -> {
            String title = txtTitle.getText().trim();
            String author = txtAuthor.getText().trim();
            int year = (Integer) spYear.getValue();
            String isbn = txtIsbn.getText().trim();

            if (title.isEmpty() || author.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title and Author are required.");
                return;
            }

            try {
                DatabaseConnection.addBook(title, author, year, isbn);
                JOptionPane.showMessageDialog(this, "Book saved.");
                if (callback != null) callback.saved();
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving book: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dispose());
    }
}
