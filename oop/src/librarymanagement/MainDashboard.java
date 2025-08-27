package librarymanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainDashboard extends JFrame {
    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID", "Title", "Author", "Year", "ISBN"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable table = new JTable(model);

    public MainDashboard() {
        setTitle("Library Dashboard");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnRefresh = new JButton("Refresh");
        JButton btnAdd = new JButton("Add Book");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnLogout = new JButton("Logout");

        JPanel top = new JPanel();
        top.add(btnRefresh);
        top.add(btnAdd);
        top.add(btnDelete);
        top.add(btnLogout);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnRefresh.addActionListener((ActionEvent e) -> loadBooks());
        btnAdd.addActionListener((ActionEvent e) -> new AddBookDialog(this, this::loadBooks).setVisible(true));
        btnDelete.addActionListener((ActionEvent e) -> deleteSelected());
        btnLogout.addActionListener((ActionEvent e) -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        loadBooks();
    }

    private void loadBooks() {
        model.setRowCount(0);
        java.util.List<LibraryItem> list = DatabaseConnection.fetchBooks();
        for (LibraryItem item : list) {
            if (item instanceof Book) {
                Book b = (Book) item;
                model.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.getYear(), b.getIsbn()});
            } else {
                model.addRow(new Object[]{item.getId(), item.getTitle(), "", "", ""});
            }
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
            return;
        }
        int id = (Integer) model.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Delete book with ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                DatabaseConnection.deleteBookById(id);
                loadBooks();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
