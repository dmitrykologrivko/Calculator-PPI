import javax.swing.*;
import java.awt.event.*;

public class MainForm extends JFrame {

    private JPanel MainPanel;
    private JTextField tfInch;
    private JTextField tfWidth;
    private JTextField tfHeight;
    private JButton btnOK;
    private JLabel lInch;
    private JLabel lWidth;
    private JLabel lHeight;
    private JButton btnClear;
    private JButton btnHelp;

    public MainForm() {
        setContentPane(MainPanel);
        setVisible(true);
        setSize(200, 285);
        setResizable(false);
        setLocationRelativeTo(null);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Вызывем метод проверки заполненности полей ввода */
                /* Calling a verification method of filling the input fields */
                checkSpace();
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfInch.setText(null);
                tfWidth.setText(null);
                tfHeight.setText(null);
            }
        });
        btnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainPanel, "RUS\nДиагональ может быть дробным числом.\nШирина и высота экрана " +
                        "должны быть целыми числами. \nПример: диагональ = 4.5; ширина = 540; высота = 960; \nENG\nDiagonal(Inch) can be" +
                        " a fractional number. \nThe width and height of the screen there must be a integer number. \nExample:" +
                        " diagonal(Inch) = 4.5; width = 540; height = 960;");

            }
        });
        tfInch.addKeyListener(new KeyListener() {

            /* Переменная для блокировки повторной проверки символа '.' */
            /* Variable for block check char '.' */
            boolean pointBlock = false;

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                /* Снимаем блокировку проверки символа '.' если пользователь очистит поле ввода кнопкой clear на форме */
                /* Unlocks check char '.' if user click button Clear in form */
                if (tfInch.getText().length() == 0) {
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
                if (tfInch.getText().length() == 1) {
                    char c = e.getKeyChar();
                    /* Добавляем "0." в начало строки, если введеный первый символ '0' или '.' */
                    /* Add "0." in start line, if typed first char is '0' or '.' */
                    if (c == '0' || c == '.') {
                        tfInch.setText("0.");
                    }
                }
                /* Для каждого симвода в введенной строке проверяем наличие более двух символов '.',
                если условие выполняется, то включаем блокировку повторной проверки символа '.'  */
                /* For each char in typed string: check >2 characters '.' if condition works - enabled
                check char '.' */
                for (int i = 0; i < tfInch.getText().length(); i++) {
                    if (tfInch.getText().charAt(i) == '.') {
                        j++;
                        if (j > 0) {
                            pointBlock = true;
                        }
                    }
                }
            }
        });
        tfWidth.addKeyListener(new KeyAdapter() {
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
        tfHeight.addKeyListener(new KeyAdapter() {
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
        if (tfInch.getText().length() == 0 || tfWidth.getText().length() == 0 || tfHeight.getText().length() == 0) {
            JOptionPane.showMessageDialog(MainPanel, "Заполните все поля! \n\nFill out all input fields!");
            return;
        }
        computation();
    }

    private void computation() {
        double inch = 0;
        double width = 0;
        double heigth = 0;
        double result = 0;
        double fractionalPart = 0;
        int integerPart = 0;
        String classification = "";

        inch = Double.parseDouble(tfInch.getText());
        width = Double.parseDouble(tfWidth.getText());
        heigth = Double.parseDouble(tfHeight.getText());

        /* Получаем результат вычисляя согласно данной формуле: ppi = Sqrt((width^2 + heigth^2) / inch^2) */
        /* Obtain the result calculated according to this formula: ppi = Sqrt((width^2 + heigth^2) / inch^2) */
        result = Math.sqrt((Math.pow(width, 2) + Math.pow(heigth, 2)) / Math.pow(inch, 2));
        /* Находим целую и дробую части */
        /* Find integer and fractional parts */
        integerPart = (int) result;
        fractionalPart = result - integerPart;
        /* Округляем если дробная часть больше 0.5 */
        /* Rounding if fraction part >0.5 */
        if (fractionalPart > 0.5) {
            integerPart++;
        }
        /* Устанавливаем классификацию экрана, согласно результату */
        /* Set classification screen */
        if (result < 120) {
            classification = "none";
        }
        if (result < 160 && result > 120 || result == 120) {
            classification = "LDPI";
        }
        if (result < 240 && result > 160 || result == 160) {
            classification = "MDPI";
        }
        if (result < 320 && result > 240 || result == 240) {
            classification = "HDPI";
        }
        if (result < 480 && result > 320 || result == 320) {
            classification = "XHDPI";
        }
        if (result < 640 && result > 480 || result == 480) {
            classification = "XXHDPI";
        }
        if (result > 640 || result == 640) {
            classification = "XXXHDPI";
        }
        JOptionPane.showMessageDialog(MainPanel, "PPI = " + integerPart + '\n' + "Classification: " + classification);
    }

}