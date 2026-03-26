package acal.com.acal_left.bdd.steps;

import acal.com.acal_left.bdd.config.LoginBddTestConfig;
import acal.com.acal_left.bdd.support.LoginEventProbe;
import acal.com.acal_left.core.model.User;
import acal.com.acal_left.ui.flatlaf.screen.login.LoginScreen;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Window;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    @Autowired
    private LoginScreen loginScreen;

    @Autowired
    private LoginEventProbe loginEventProbe;

    private FrameFixture window;
    private JFrame frame;

    @Before
    public void beforeScenario() {
        clearRememberMe();
        loginEventProbe.reset();

        frame = org.assertj.swing.edt.GuiActionRunner.execute(() -> {
            JFrame loginFrame = new JFrame("Acal Left - Login");
            loginFrame.setName("loginFrame");
            loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            loginFrame.setContentPane(loginScreen);
            loginFrame.pack();
            loginFrame.setSize(400, 300);
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
            return loginFrame;
        });

        window = new FrameFixture(frame);
        window.show();
        usernameField().setText("");
        passwordField().setText("");
    }

    @After
    public void afterScenario() {
        if (window != null) {
            window.cleanUp();
        }

        for (Window awtWindow : Window.getWindows()) {
            if (awtWindow instanceof JDialog dialog && dialog.isShowing()) {
                org.assertj.swing.edt.GuiActionRunner.execute(dialog::dispose);
            }
        }

        clearRememberMe();
    }

    @Dado("que a tela de login esta aberta")
    public void telaDeLoginAberta() {
        window.requireVisible();
        usernameField().requireVisible();
        passwordField().requireVisible();
        loginButton().requireVisible();
    }

    @Quando("eu informo usuario {string} e senha {string}")
    public void informoUsuarioESenha(String usuario, String senha) {
        usernameField().setText(usuario);
        passwordField().setText(senha);
    }

    @Quando("eu clico em entrar")
    public void clicoEmEntrar() {
        window.button(new GenericTypeMatcher<>(JButton.class) {
            @Override
            protected boolean isMatching(JButton button) {
                return button.isShowing() && "Entrar".equals(button.getText());
            }
        }).click();
    }

    @Quando("eu nao preencho usuario e senha")
    public void naoPreenchoUsuarioESenha() {
        usernameField().setText("");
        passwordField().setText("");
    }

    @Entao("o login deve ser realizado com sucesso")
    public void loginComSucesso() throws InterruptedException {
        waitUntil(() -> !frame.isDisplayable(), 4000);

        assertThat(loginEventProbe.successCount()).isEqualTo(1);
        User user = loginEventProbe.lastUser();
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(LoginBddTestConfig.VALID_USERNAME);
    }

    @Entao("devo ver a mensagem de credenciais invalidas")
    public void mensagemCredenciaisInvalidas() throws InterruptedException {
        JDialog dialog = waitForDialog("Erro de Login", 4000);
        assertThat(dialog).isNotNull();
        org.assertj.swing.edt.GuiActionRunner.execute(dialog::dispose);
        assertThat(loginEventProbe.successCount()).isZero();
    }

    @Entao("devo ver a mensagem de validacao de campos obrigatorios")
    public void mensagemValidacaoCamposObrigatorios() throws InterruptedException {
        JDialog dialog = waitForDialog("Validação", 4000);
        assertThat(dialog).isNotNull();
        org.assertj.swing.edt.GuiActionRunner.execute(dialog::dispose);
        assertThat(loginEventProbe.successCount()).isZero();
    }

    private JTextComponentFixture usernameField() {
        return window.textBox(new GenericTypeMatcher<>(JTextField.class) {
            @Override
            protected boolean isMatching(JTextField textField) {
                return textField.getClass().equals(JTextField.class) && textField.isShowing();
            }
        });
    }

    private JTextComponentFixture passwordField() {
        return window.textBox(new GenericTypeMatcher<>(JPasswordField.class) {
            @Override
            protected boolean isMatching(JPasswordField passwordField) {
                return passwordField.getClass().equals(JPasswordField.class) && passwordField.isShowing();
            }
        });
    }

    private org.assertj.swing.fixture.JButtonFixture loginButton() {
        return window.button(new GenericTypeMatcher<>(JButton.class) {
            @Override
            protected boolean isMatching(JButton button) {
                return button.isShowing() && "Entrar".equals(button.getText());
            }
        });
    }

    private void waitUntil(Check condition, long timeoutMillis) throws InterruptedException {
        long end = System.currentTimeMillis() + timeoutMillis;
        while (System.currentTimeMillis() < end) {
            if (condition.ok()) {
                return;
            }
            Thread.sleep(100);
        }
    }

    private JDialog waitForDialog(String title, long timeoutMillis) throws InterruptedException {
        long end = System.currentTimeMillis() + timeoutMillis;
        while (System.currentTimeMillis() < end) {
            for (Window awtWindow : Window.getWindows()) {
                if (awtWindow instanceof JDialog dialog && dialog.isShowing() && title.equals(dialog.getTitle())) {
                    return dialog;
                }
            }
            Thread.sleep(100);
        }
        return null;
    }

    private void clearRememberMe() {
        try {
            Preferences.userRoot().node("br.com.acal.login").clear();
        } catch (BackingStoreException ignored) {
        }
    }

    @FunctionalInterface
    private interface Check {
        boolean ok();
    }
}

