package acal.com.acal_left.ui.flatlaf.screen.login;

import javax.swing.*;
import javax.swing.border.*;
import acal.com.acal_left.core.model.LoginAttempt;
import acal.com.acal_left.core.model.User;
import acal.com.acal_left.core.usecase.login.LoginUseCase;
import acal.com.acal_left.ui.event.LoginSuccessEvent;
import lombok.val;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;

@Component
public class LoginScreen extends JPanel {

    private final LoginUseCase loginUseCase;
    private final ApplicationEventPublisher eventPublisher;

    public LoginScreen(LoginUseCase loginUseCase, ApplicationEventPublisher eventPublisher) {
        this.loginUseCase = loginUseCase;
        this.eventPublisher = eventPublisher;

        initComponents();
        init();
        remembered();
        successLogin(new User());
    }

    private void init(){
        textFieldUsername.setName("usernameField");
        passwordFieldPassword.setName("passwordField");
        buttonConfirm.setName("loginButton");
    }

    private void remembered(){
        if(hasRememberMe()){
            textFieldUsername.setText(getRememberedUsername());
            passwordFieldPassword.setText(getRememberedPassword());
            checkBoxRememberMe.setSelected(true);
        }
    }

    private void rememberMe(LoginAttempt attempt){
        val prefs = Preferences.userRoot().node("br.com.acal.login");
        prefs.put("username", attempt.getUsername());
        prefs.put("password", attempt.getPassword());
    }

    private void login(){
        LoginAttempt attempt = getLoginAttempt();

        if (attempt.isNotValid()) {
            invalidLogin();
            return;
        }

        loginUseCase
            .login(attempt)
            .ifPresentOrElse(this::successLogin, this::errorLogin);
    }

    private LoginAttempt getLoginAttempt(){
        return LoginAttempt.builder()
                .username(getUsername())
                .password(getPassword())
                .build();
    }

    private void successLogin(User user) {
        eventPublisher.publishEvent(new LoginSuccessEvent(this, user));
        if(checkBoxRememberMe.isSelected()){
            rememberMe(getLoginAttempt());
        }

        close();
    }

    private void invalidLogin(){
        JOptionPane.showMessageDialog(
                this,
                "Por favor, preencha todos os campos corretamente.",
                "Validação",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void errorLogin(){
        JOptionPane.showMessageDialog(
                this,
                "Username ou senha inválidos.",
                "Erro de Login",
                JOptionPane.ERROR_MESSAGE
        );
    }

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

    private void close(){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.dispose();
            }
        });
    }

    private Boolean hasRememberMe(){
        val prefs = Preferences.userRoot().node("br.com.acal.login");
        val username = prefs.get("username", null);
        return username != null;
    }

    private String getRememberedUsername(){
        val prefs = Preferences.userRoot().node("br.com.acal.login");
        return prefs.get("username", null);
    }

    private String getRememberedPassword(){
        val prefs = Preferences.userRoot().node("br.com.acal.login");
        return prefs.get("password", null);
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


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel1 = new JPanel();
        panel6 = new JPanel();
        panel3 = new JPanel();
        label1 = new JLabel();
        textFieldUsername = new JTextField();
        panel4 = new JPanel();
        label2 = new JLabel();
        passwordFieldPassword = new JPasswordField();
        panel5 = new JPanel();
        checkBoxRememberMe = new JCheckBox();
        panel2 = new JPanel();
        buttonConfirm = new JButton();

        //======== this ========
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(500, 300));
        setMinimumSize(new Dimension(500, 300));
        setMaximumSize(new Dimension(500, 300));
        setLayout(new BorderLayout(10, 10));

        //======== panel1 ========
        {
            panel1.setBorder(new EtchedBorder());
            panel1.setLayout(new VerticalLayout(10));

            //======== panel6 ========
            {
                panel6.setBorder(new EmptyBorder(5, 5, 5, 5));
                panel6.setLayout(new VerticalLayout());

                //======== panel3 ========
                {
                    panel3.setLayout(new VerticalLayout());

                    //---- label1 ----
                    label1.setText("Nome:");
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
                panel6.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new VerticalLayout());

                    //---- label2 ----
                    label2.setText("Senha:");
                    panel4.add(label2);

                    //---- passwordFieldPassword ----
                    passwordFieldPassword.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            passwordFieldPasswordKeyPressed(e);
                        }
                    });
                    panel4.add(passwordFieldPassword);
                }
                panel6.add(panel4);

                //======== panel5 ========
                {
                    panel5.setLayout(new FlowLayout());

                    //---- checkBoxRememberMe ----
                    checkBoxRememberMe.setText("Lembre-se de mim:");
                    panel5.add(checkBoxRememberMe);
                }
                panel6.add(panel5);
            }
            panel1.add(panel6);
        }
        add(panel1, BorderLayout.CENTER);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());

            //---- buttonConfirm ----
            buttonConfirm.setText("Entrar");
            buttonConfirm.setPreferredSize(new Dimension(100, 35));
            buttonConfirm.addActionListener(e -> buttonLogin(e));
            panel2.add(buttonConfirm, BorderLayout.CENTER);
        }
        add(panel2, BorderLayout.PAGE_END);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel1;
    private JPanel panel6;
    private JPanel panel3;
    private JLabel label1;
    private JTextField textFieldUsername;
    private JPanel panel4;
    private JLabel label2;
    private JPasswordField passwordFieldPassword;
    private JPanel panel5;
    private JCheckBox checkBoxRememberMe;
    private JPanel panel2;
    private JButton buttonConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
