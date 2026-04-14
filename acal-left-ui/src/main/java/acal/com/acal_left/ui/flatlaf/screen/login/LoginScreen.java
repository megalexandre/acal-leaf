package acal.com.acal_left.ui.flatlaf.screen.login;

import acal.com.acal_left.core.model.LoginAttempt;
import acal.com.acal_left.core.model.User;
import acal.com.acal_left.core.usecase.login.LoginUseCase;
import acal.com.acal_left.ui.event.LoginSuccessEvent;
import acal.com.acal_left.ui.flatlaf.component.utils.AppFontUtils;
import acal.com.acal_left.ui.flatlaf.component.utils.ButtonIconUtils;
import acal.com.acal_left.ui.flatlaf.component.utils.ButtonStyleUtils;
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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;

@Component
public class LoginScreen extends JPanel {

    private static final int LOGIN_ICON_SIZE = 16;

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
        applyModernTheme();
        configureLoginIcons();
    }

    private void applyModernTheme() {
        Font labelFont = AppFontUtils.font(Font.PLAIN, 12f);
        Font fieldFont = AppFontUtils.font(Font.PLAIN, 13f);
        Font checkFont = AppFontUtils.font(Font.PLAIN, 12f);

        label1.setFont(labelFont);
        label2.setFont(labelFont);
        checkBoxRememberMe.setFont(checkFont);
        checkBoxRememberMe.setText("Lembrar de mim");

        textFieldUsername.setFont(fieldFont);
        textFieldUsername.setPreferredSize(new Dimension(280, 32));
        passwordFieldPassword.setFont(fieldFont);
        passwordFieldPassword.setPreferredSize(new Dimension(280, 32));

        setBorder(new EmptyBorder(12, 16, 12, 16));
        panel1.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel6.setBorder(new EmptyBorder(8, 16, 0, 16));
        panel3.setBorder(new EmptyBorder(0, 0, 4, 0));
        panel4.setBorder(new EmptyBorder(0, 0, 4, 0));

        panel5.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel5.setBorder(new EmptyBorder(1, 0, 0, 0));

        // Mantem o botao com o tamanho padrao compartilhado.
        ButtonStyleUtils.applyPrimary(buttonConfirm);
        buttonConfirm.setText("Entrar");

        // Alinha o rodape com os campos para evitar botao "solto" no canto.
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel2.setBorder(new EmptyBorder(6, 16, 0, 16));
        panel2.removeAll();
        panel2.add(buttonConfirm);
        panel2.revalidate();
        panel2.repaint();
    }

    private void configureLoginIcons() {
        label1.setIcon(ButtonIconUtils.createUserIcon(label1.getForeground(), LOGIN_ICON_SIZE));
        label1.setIconTextGap(6);
        label2.setIcon(ButtonIconUtils.createLockIcon(label2.getForeground(), LOGIN_ICON_SIZE));
        label2.setIconTextGap(6);
        checkBoxRememberMe.setIcon(null);
        checkBoxRememberMe.setSelectedIcon(null);
        checkBoxRememberMe.setIconTextGap(6);
        ButtonIconUtils.applyIcon(buttonConfirm,
                ButtonIconUtils.createLoginArrowIcon(buttonConfirm.getForeground(), LOGIN_ICON_SIZE), 8);
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
            mainFrame.setMinimumSize(mainFrame.getSize());
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
        setPreferredSize(new Dimension(420, 240));
        setMinimumSize(new Dimension(420, 240));
        setMaximumSize(new Dimension(420, 240));
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBorder(null);
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //======== panel6 ========
            {
                panel6.setBorder(new EmptyBorder(5, 5, 5, 5));
                panel6.setLayout(new VerticalLayout(10));

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
            panel1.add(panel6, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
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
