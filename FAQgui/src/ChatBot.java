
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class ChatBot extends JFrame implements KeyListener {

    JPanel p = new JPanel();
    JTextArea dialog = new JTextArea(20, 50);
    JTextArea input = new JTextArea(1, 50);
    JScrollPane scroll = new JScrollPane(
            dialog,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    String[][] chatBot = {
        {"City university Contact number","number","contact number"},
        {"01245667777", "0175437778"},
        
        {"City University Address","DetailsAddress","Address"},
        {"Panthapath, Dhaka, Bnagladesh", "Panthopath, Dhaka"},
        
         {"City university Contact number","Campus","campus"},
        {"Panthapath, Dhaka, Bnagladesh", "Dhaka","Panthopoth"},
        
        {"Hi","Hello","hello","hi"},
        {"Hello"},
        
            {("Bye")}
    };

    public static void main(String[] args) {
        new ChatBot();
    }

    public ChatBot() {
        super("ChatBot");
        setSize(800, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dialog.setEditable(false);
        input.addKeyListener(this);

        p.add(scroll);
        p.add(input);
        p.setBackground(new Color(0, 255, 51));
        add(p);

        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            input.setEditable(false);
            //-----grab quote-----------
            String quote = input.getText();
            input.setText("");
            addText("ChatBot :\t" + quote);
            quote.trim();
            while (quote.charAt(quote.length() - 1) == '!'
                    || quote.charAt(quote.length() - 1) == '.'
                    || quote.charAt(quote.length() - 1) == '?') {
                quote = quote.substring(0, quote.length() - 1);
            }
            quote.trim();
            byte response = 0;
            /*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
             */
            //-----check for matches----
            int j = 0;//which group we're checking
            while (response == 0) {
                if (inArray(quote.toLowerCase(), chatBot[j * 2])) {
                    response = 2;
                    int r = (int) Math.floor(Math.random() * chatBot[(j * 2) + 1].length);
                    addText("\nSystem-->\t" + chatBot[(j * 2) + 1][r]);
                }
                j++;
                if (j * 2 == chatBot.length - 1 && response == 0) {
                    response = 1;
                }
            }

            //-----default--------------
            if (response == 1) {
                int r = (int) Math.floor(Math.random() * chatBot[chatBot.length - 1].length);
                addText("\nSystem-->\t" + chatBot[chatBot.length - 1][r]);
            }
            addText("\n");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            input.setEditable(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void addText(String str) {
        dialog.setText(dialog.getText() + str);
    }

    public boolean inArray(String in, String[] str) {
        boolean match = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(in)) {
                match = true;
            }
        }
        return match;
    }
}
