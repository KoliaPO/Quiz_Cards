package com.QuizCard;

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class QuizCardBuilder {
    private JTextArea questions;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private JFrame frame;

    public static void main(String[] args) {
        new QuizCardBuilder().go();

    }

    public void go() {
        //Create GUI

        frame = new JFrame("Quiz Card Builder");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        questions = new JTextArea(6, 20);
        questions.setLineWrap(true);
        questions.setWrapStyleWord(true);
        questions.setFont(bigFont);

        JScrollPane qScroller = new JScrollPane(questions);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);

        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        JButton nextButton = new JButton("Next Card");

        cardList = new ArrayList<>();

        JLabel qLabel = new JLabel("Questions: ");
        JLabel aLabel = new JLabel("Answer: ");

        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(e -> {
            QuizCard card = new QuizCard(questions.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        });
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(e -> {
            cardList.clear();
            clearCard();
        });

        saveMenuItem.addActionListener(e -> {
            QuizCard card = new QuizCard(questions.getText(), answer.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        });
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }


    private void clearCard() {
        //Clear text before new card

        questions.setText("");
        answer.setText("");
        questions.requestFocus();
    }

    private void saveFile(File file){
        //Save tha cards set

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(QuizCard card:cardList){
                writer.write(card.getQuestions() + "/");
                writer.write(card.getAnswer()+System.getProperty("line.separator"));
            }
            writer.close();
        }catch (IOException ex){
            System.out.println("couldn't write the cardList out");
        }

    }


}

