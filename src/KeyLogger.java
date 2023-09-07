import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

public class KeyLogger extends JFrame {
    static int width = 800;
    static int height = 600;
    public FileWriter log;
    public JPanel panel;
    public KeyLogger() throws IOException {
        super("key logger");
        initGui();
    }

    private void initGui() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(KeyLogger.width, KeyLogger.height));
        this.setLocation(d.width / 2 - KeyLogger.width / 2, d.height / 2 - KeyLogger.height / 2);
        this.getContentPane().setBackground(Color.orange);
        this.setResizable(false);

        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setFocusable(true);
        panel.setLayout(null);
        this.getContentPane().add(panel);

        log = new FileWriter("log.txt", true);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println(e.getKeyChar());
                try {
                    log.write(String.valueOf(e.getKeyChar()));
                    log.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    KeyLogger frame = new KeyLogger();
                    frame.pack();
                    frame.setVisible(true);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
