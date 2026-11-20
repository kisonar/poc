package kisonar.poc.learning.java.myinterview.berlinclock.app;

import kisonar.poc.learning.java.myinterview.berlinclock.logic.converter.Converter;
import kisonar.poc.learning.java.myinterview.berlinclock.logic.display.DisplayData;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class BerlinClockApplication {

    private final Converter converter = new Converter();
    private static final Color LAMP_OFF = Color.DARK_GRAY;
    private static final Color LAMP_RED = Color.RED;
    private static final Color LAMP_YELLOW = Color.ORANGE;
    private final JPanel secondsLamp = createLamp();
    private final JPanel[] hoursTop = createLamps(4);
    private final JPanel[] hoursBottom = createLamps(4);
    private final JPanel[] minutesTop = createLamps(11);
    private final JPanel[] minutesBottom = createLamps(4);
    private final JLabel timeLabel = new JLabel("", SwingConstants.CENTER);

    static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BerlinClockApplication().show());
    }

    private void show() {
        JFrame frame = new JFrame("Berlin Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(createLayout());
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        refresh();
        new Timer(1000, _ -> refresh()).start();
    }

    private JPanel createLayout() {
        JPanel secondsRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        secondsRow.setOpaque(false);
        secondsLamp.setPreferredSize(new Dimension(60, 60));
        secondsRow.add(secondsLamp);

        JPanel rows = new JPanel(new GridLayout(5, 1, 0, 8));
        rows.setOpaque(false);
        rows.add(secondsRow);
        rows.add(createRow(hoursTop));
        rows.add(createRow(hoursBottom));
        rows.add(createRow(minutesTop));
        rows.add(createRow(minutesBottom));

        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(timeLabel.getFont().deriveFont(Font.BOLD, 18f));

        JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(rows, BorderLayout.CENTER);
        mainPanel.add(timeLabel, BorderLayout.SOUTH);
        return mainPanel;
    }

    private void refresh() {
        LocalTime now = LocalTime.now().withNano(0);
        DisplayData data = converter.transformLocalTimeToDisplayData(now);
        secondsLamp.setBackground(now.getSecond() % 2 == 0 ? LAMP_YELLOW : LAMP_OFF);
        light(hoursTop, data.displayDataHours().activatedLampsHoursAtTop(), false);
        light(hoursBottom, data.displayDataHours().activatedLampsHoursAtBottom(), false);
        light(minutesTop, data.displayDataMinutes().activatedLampsMinutesAtTop(), true);
        light(minutesBottom, data.displayDataMinutes().activatedLampsMinutesAtBottom(), true);
        timeLabel.setText(now.toString());
    }

    private static void light(JPanel[] lamps, int activated, boolean minutes) {
        for (int i = 0; i < lamps.length; i++) {
            Color onColor = LAMP_RED;
            if (minutes) {
                // In the minutes row every 3rd lamp (quarters) is red, the rest yellow
                onColor = lamps.length == 11 && (i + 1) % 3 == 0 ? LAMP_RED : LAMP_YELLOW;
            }
            lamps[i].setBackground(i < activated ? onColor : LAMP_OFF);
        }
    }

    private static JPanel createRow(JPanel[] lamps) {
        JPanel row = new JPanel(new GridLayout(1, lamps.length, 5, 0));
        row.setOpaque(false);
        for (JPanel lamp : lamps) {
            row.add(lamp);
        }
        return row;
    }

    private static JPanel[] createLamps(int count) {
        JPanel[] lamps = new JPanel[count];
        for (int i = 0; i < count; i++) {
            lamps[i] = createLamp();
        }
        return lamps;
    }

    private static JPanel createLamp() {
        JPanel lamp = new JPanel();
        lamp.setBackground(LAMP_OFF);
        lamp.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        return lamp;
    }
}
