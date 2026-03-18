import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.JFrame;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class SwingIntegrationTest {

    protected FrameFixture window;

    @BeforeClass
    public static void setupOnce() {
        System.setProperty("java.awt.headless", "false");
        FailOnThreadViolationRepaintManager.install();
    }

    protected void setupWindow(JFrame frame) {
        window = new FrameFixture(frame);
        window.show();
    }

    @After
    public void tearDown() {
        if (window != null) {
            window.cleanUp();
        }
    }
}