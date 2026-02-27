/*
 * Created by JFormDesigner on Fri Feb 27 17:21:56 BRT 2026
 */

package acal.com.acal_left.ui.login;

import java.awt.*;
import acal.com.acal_left.core.LoginUseCase;
import acal.com.acal_left.core.model.LoginAttempt;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Component
public class LoginScreen extends JPanel {

    private final LoginUseCase loginUseCase;

    public LoginScreen(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
        initComponents();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Acal Left - Login");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setContentPane(LoginScreen.this);
            mainFrame.pack();
            mainFrame.setSize(400, 300);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
        });
    }



    private void login(){
        LoginAttempt attempt = new LoginAttempt(getUsername(), getPassword());

        if (attempt.isNotValid()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, preencha todos os campos corretamente.",
                    "Validação",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        var user = loginUseCase.login(attempt);

        if (user.isPresent()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Login realizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Username ou senha inválidos.",
                    "Erro de Login",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /* listeners */
    private void buttonLogin(ActionEvent e) {
        login();
    }

    private void passwordFieldPasswordKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }

    private void textFieldUsernameKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }

    private String getUsername() {
        return textFieldUsername.getText();
    }

    private String getPassword() {
        return new String(passwordFieldPassword.getPassword());
    }

   
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
        panel4 = new JPanel();
        panel3 = new JPanel();
        label1 = new JLabel();
        textFieldUsername = new JTextField();
        panel2 = new JPanel();
        label2 = new JLabel();
        passwordFieldPassword = new JPasswordField();
        panel5 = new JPanel();
        label3 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
        (0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing.border
        .TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
        propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException()
        ;}});
        setLayout(new BorderLayout());

        //======== panel4 ========
        {
            panel4.setLayout(new VerticalLayout());

            //======== panel3 ========
            {
                panel3.setLayout(new VerticalLayout());

                //---- label1 ----
                label1.setText("Us\u00faario:");
                panel3.add(label1);

                //---- textFieldUsername ----
                textFieldUsername.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        textFieldUsernameKeyPressed(e);
                    }
                });
                panel3.add(textFieldUsername);
            }
            panel4.add(panel3);

            //======== panel2 ========
            {
                panel2.setLayout(new VerticalLayout());

                //---- label2 ----
                label2.setText("Password:");
                panel2.add(label2);

                //---- passwordFieldPassword ----
                passwordFieldPassword.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        passwordFieldPasswordKeyPressed(e);
                    }
                });
                panel2.add(passwordFieldPassword);
            }
            panel4.add(panel2);
        }
        add(panel4, BorderLayout.CENTER);

        //======== panel5 ========
        {
            panel5.setLayout(new VerticalLayout());

            //---- label3 ----
            label3.setText("Entrar:");
            panel5.add(label3);

            //---- button1 ----
            button1.setText("Login");
            button1.addActionListener(e -> buttonLogin(e));
            panel5.add(button1);
        }
        add(panel5, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
    private JPanel panel4;
    private JPanel panel3;
    private JLabel label1;
    private JTextField textFieldUsername;
    private JPanel panel2;
    private JLabel label2;
    private JPasswordField passwordFieldPassword;
    private JPanel panel5;
    private JLabel label3;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
