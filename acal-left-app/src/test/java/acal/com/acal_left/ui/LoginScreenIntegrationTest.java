package acal.com.acal_left.ui;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LoginScreenIntegrationTest {

    private FrameFixture window;

    @BeforeAll
    static void setupOnce() {
        System.setProperty("java.awt.headless", "false");
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    void setup() {
        // Espera até que a janela com o nome "mainFrame" apareça (subida do Spring)
        window = WindowFinder.findFrame("mainFrame")
                .withTimeout(Duration.ofSeconds(10).toMillis())
                .using(org.assertj.swing.core.BasicRobot.robotWithCurrentAwtHierarchy());
    }

    @AfterEach
    void tearDown() {
        if (window != null) {
            window.cleanUp();
        }
    }

    @Test
    @DisplayName("Fluxo Completo: Login -> Dashboard")
    void deveLogarEIrParaDashboard() {
        window.textBox("usernameField").enterText("admin");
        window.button("loginButton").click();
        window.requireNotVisible();
    }

}