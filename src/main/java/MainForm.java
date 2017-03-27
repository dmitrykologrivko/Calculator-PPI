import javax.swing.*;
import java.awt.event.*;

public class MainForm extends JFrame {

    private JPanel MainPanel;
    private JTextField editInch;
    private JTextField editWidth;
    private JTextField editHeight;
    private JButton buttonOk;
    private JLabel textInch;
    private JLabel textWidth;
    private JLabel textHeight;
    private JButton buttonClear;
    private JButton buttonHelp;

    public MainForm() {
        setContentPane(MainPanel);
        setVisible(true);
        setSize(200, 285);
        setResizable(false);
        setLocationRelativeTo(null);
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Вызывем метод проверки заполненности полей ввода */
                /* Calling a verification method of filling the input fields */
                checkSpace();
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editInch.setText(null);
                editWidth.setText(null);
                editHeight.setText(null);
            }
        });
        buttonHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainPanel, "Diagonal(Inch) can be" +
                        " a fractional number. \nThe width and height of the screen there must be a integer number. \nExample:" +
                        " diagonal(Inch) = 4.5; width = 540; height = 960;");

            }
        });
        editInch.addKeyListener(new KeyListener() {

            /* Переменная для блокировки повторной проверки символа '.' */
            /* Variable for block check char '.' */
            boolean pointBlock = false;

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                /* Снимаем блокировку проверки символа '.' если пользователь очистит поле ввода кнопкой clear на форме */
                /* Unlocks check char '.' if user click button Clear in form */
                if (editInch.getText().length() == 0) {
                    pointBlock = false;
                }
               /* Условие сработает если пользователь очистит поле ввода кнопками back space или delete*/
               /* Condition works if user clear all input fields with buttons back space or delete */
                if ((c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
                    /* Снимаем блокировку проверки символа '.' */
                   /* Unlocks check char '.' */
                    pointBlock = false;
                    return;
                }
                /* Если блокировка проверки символа '.' включена, то проверять
                вводимые символы на преднодлежность к числам */
                /* If block check char '.' is enabled, the check
                typed characters to the numbers*/
                if (pointBlock) {
                    if (!((c >= '0') && (c <= '9'))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
                /* Иначе проверять вводимые символы на преднадлежность к числам и символу '.' */
                /* Else check typed characters to the numbers and char '.' */
                else {
                    if (!((c >= '0') && (c <= '9') || (c == '.'))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                /* Переменная для проверки на наличие более двух символов '.' в водимой строке */
                /* Variable for check >2 characters '.' in typed string */
                int j = 0;
                if (editInch.getText().length() == 1) {
                    char c = e.getKeyChar();
                    /* Добавляем "0." в начало строки, если введеный первый символ '0' или '.' */
                    /* Add "0." in start line, if typed first char is '0' or '.' */
                    if (c == '0' || c == '.') {
                        editInch.setText("0.");
                    }
                }
                /* Для каждого симвода в введенной строке проверяем наличие более двух символов '.',
                если условие выполняется, то включаем блокировку повторной проверки символа '.'  */
                /* For each char in typed string: check >2 characters '.' if condition works - enabled
                check char '.' */
                for (int i = 0; i < editInch.getText().length(); i++) {
                    if (editInch.getText().charAt(i) == '.') {
                        j++;
                        if (j > 0) {
                            pointBlock = true;
                        }
                    }
                }
            }
        });
        editWidth.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
                    return;
                }
                if (!((c >= '0') && (c <= '9'))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        editHeight.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
                    return;
                }
                if (!((c >= '0') && (c <= '9'))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    private void checkSpace() {
        if (editInch.getText().length() == 0 || editWidth.getText().length() == 0 || editHeight.getText().length() == 0) {
            JOptionPane.showMessageDialog(MainPanel, "Fill out all input fields!");
            return;
        }
        computation();
    }

    private void computation() {
        double width = Double.valueOf(editWidth.getText());
        double height = Double.valueOf(editHeight.getText());
        double inch = Double.valueOf(editInch.getText());

        double ppi = PpiHelper.ppi(width, height, inch);
        String classification = PpiHelper.classification(ppi);

        JOptionPane.showMessageDialog(MainPanel, "PPI = " + ppi + '\n' + "Classification: " + classification);
    }
}