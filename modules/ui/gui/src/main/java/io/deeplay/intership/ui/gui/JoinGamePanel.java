package io.deeplay.intership.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import io.deeplay.intership.model.Color;

public class JoinGamePanel implements Panel {
    public final JDialog jDialog;
    public final GridLayout layout;
    public final DrawGui drawGui;
    public final JLabel gameIdLabel;
    public final JLabel colorLabel;
    public final JTextField gameId;
    public final JRadioButton buttonBlack;
    public final JRadioButton buttonWhite;
    public final JButton buttonSubmit;
    public final JPanel jPanel;
    public boolean isVisible;

    public JoinGamePanel(DrawGui drawGui) {
        this.drawGui = drawGui;
        jPanel = new JPanel();
        jDialog = new JDialog(drawGui.frame, Settings.ENTRANCE);
        layout = new GridLayout(3, 1, 50, 10);
        gameIdLabel = new JLabel("Enter game id: ");
        colorLabel = new JLabel("Choose color: ");
        gameId = new JTextField( Settings.INPUT_LENGTH / 2);
        buttonBlack = new JRadioButton(Settings.BLACK);
        buttonBlack.addActionListener(this);
        buttonWhite = new JRadioButton(Settings.WHITE);
        buttonWhite.addActionListener(this);
        buttonSubmit = new JButton(Settings.SUBMIT);
        buttonSubmit.addActionListener(this);
        isVisible = false;
        setPanel();
    }

    @Override
    public void showPanel() {
        jDialog.setVisible(true);
    }

    @Override
    public void hidePanel() {
        gameId.setText("");
        buttonBlack.setSelected(false);
        buttonWhite.setSelected(false);
        buttonSubmit.setSelected(false);
        jDialog.setVisible(false);
        isVisible = false;
    }

    @Override
    public void setPanel() {
        //jPanel.setLayout(layout);
        jPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 60, 100));

        gameIdLabel.setLocation(50, 50);
        gameId.setLocation(150, 50);
        colorLabel.setLocation(50, 100);
        buttonBlack.setLocation(150, 100);
        buttonWhite.setLocation(220, 100);
        buttonSubmit.setLocation(150, 200);

        gameIdLabel.setPreferredSize(new Dimension(100, 35));
        gameId.setPreferredSize(new Dimension(80, 35));
        colorLabel.setPreferredSize(new Dimension(100, 35));
        buttonBlack.setPreferredSize(new Dimension(80, 35));
        buttonWhite.setPreferredSize(new Dimension(80, 35));
        buttonSubmit.setPreferredSize(new Dimension(80, 35));

        jPanel.add(gameIdLabel);
        jPanel.add(gameId);
        jPanel.add(colorLabel);
        jPanel.add(buttonBlack);
        jPanel.add(buttonWhite);
        jPanel.add(buttonSubmit);
        jDialog.add(jPanel);
        jDialog.setSize(Settings.PANEL_WIDTH, Settings.PANEL_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String line = e.getActionCommand();
        if(line.equals(Settings.BLACK)) {
            buttonWhite.setSelected(false);
            buttonBlack.setSelected(true);
        }
        if(line.equals(Settings.WHITE)) {
            buttonWhite.setSelected(true);
            buttonBlack.setSelected(false);
        }
        if(line.equals(Settings.SUBMIT)) {
            if (buttonWhite.isSelected()) {
                drawGui.scannerGui.setColor(Color.WHITE);
            } else if (buttonBlack.isSelected()) {
                drawGui.scannerGui.setColor(Color.BLACK);
            } else {
                drawGui.scannerGui.setColor(Color.EMPTY);
            }

            String gameStrNumber = gameId.getText();
            if(!gameStrNumber.isEmpty()) {
                drawGui.scannerGui.setGameId(Integer.valueOf(gameStrNumber));
                drawGui.joinGamePanel.hidePanel();
                drawGui.gameFieldPanel.showPanel();
            }
            else {
                drawGui.showMessage("Ошибка", "Введите номер игры!");
            }
        }
    }
}