import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;

public class MovieTicketBookingApp extends JFrame {
    private JTextField nameField;
    private JComboBox<String> movieBox, timeBox, seatBox, dateBox;
    private JTextArea ticketArea;
    private JButton bookBtn, resetBtn;

    public MovieTicketBookingApp() {
        setTitle("Movie Ticket Booking System");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 30);
        nameField = new JTextField();
        nameField.setBounds(150, 30, 250, 30);

        JLabel movieLabel = new JLabel("Select Movie:");
        movieLabel.setBounds(30, 80, 100, 30);
        movieBox = new JComboBox<>(new String[]{"Jawan", "Pathaan", "KGF 2", "RRR"});
        movieBox.setBounds(150, 80, 250, 30);

        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setBounds(30, 130, 100, 30);
        dateBox = new JComboBox<>(new String[]{"25-June-2025", "26-June-2025", "27-June-2025"});
        dateBox.setBounds(150, 130, 250, 30);

        JLabel timeLabel = new JLabel("Select Time:");
        timeLabel.setBounds(30, 180, 100, 30);
        timeBox = new JComboBox<>(new String[]{"10:00 AM", "2:00 PM", "6:00 PM", "9:00 PM"});
        timeBox.setBounds(150, 180, 250, 30);

        JLabel seatLabel = new JLabel("Select Seat:");
        seatLabel.setBounds(30, 230, 100, 30);
        seatBox = new JComboBox<>(new String[]{"A1", "A2", "A3", "B1", "B2", "C1"});
        seatBox.setBounds(150, 230, 250, 30);

        bookBtn = new JButton("Book Ticket");
        bookBtn.setBounds(100, 280, 130, 30);

        resetBtn = new JButton("Reset");
        resetBtn.setBounds(250, 280, 130, 30);

        ticketArea = new JTextArea();
        ticketArea.setBounds(30, 330, 420, 180);
        ticketArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ticketArea);
        scrollPane.setBounds(30, 330, 420, 180);

        add(nameLabel); add(nameField);
        add(movieLabel); add(movieBox);
        add(dateLabel); add(dateBox);
        add(timeLabel); add(timeBox);
        add(seatLabel); add(seatBox);
        add(bookBtn); add(resetBtn);
        add(scrollPane);

        bookBtn.addActionListener(e -> bookTicket());
        resetBtn.addActionListener(e -> resetForm());

        setVisible(true);
    }

    private void bookTicket() {
        String name = nameField.getText();
        String movie = (String) movieBox.getSelectedItem();
        String date = (String) dateBox.getSelectedItem();
        String time = (String) timeBox.getSelectedItem();
        String seat = (String) seatBox.getSelectedItem();

        StringBuilder ticket = new StringBuilder();
        ticket.append("Booking Confirmation:\n\n");
        ticket.append("Name: ").append(name).append("\n");
        ticket.append("Movie: ").append(movie).append("\n");
        ticket.append("Date: ").append(date).append("\n");
        ticket.append("Time: ").append(time).append("\n");
        ticket.append("Seat: ").append(seat).append("\n\n");
        ticket.append("Enjoy your show!");

        ticketArea.setText(ticket.toString());

        // Save to file
        try (PrintWriter writer = new PrintWriter("ticket.txt")) {
            writer.println(ticket);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving ticket.");
        }
    }

    private void resetForm() {
        nameField.setText("");
        movieBox.setSelectedIndex(0);
        dateBox.setSelectedIndex(0);
        timeBox.setSelectedIndex(0);
        seatBox.setSelectedIndex(0);
        ticketArea.setText("");
    }

    public static void main(String[] args) {
        new MovieTicketBookingApp();
    }
}