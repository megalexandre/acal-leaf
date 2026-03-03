package acal.com.acal_left.ui.flatlaf.screen.dashboard;

import acal.com.acal_left.ui.event.ChangeScreenEvent;
import acal.com.acal_left.ui.event.LoginSuccessEvent;
import acal.com.acal_left.ui.flatlaf.screen.category.category.CategoryScreen;
import acal.com.acal_left.ui.flatlaf.screen.partner.partner.PartnerScreen;
import acal.com.acal_left.ui.routes.ScreenManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static acal.com.acal_left.ui.event.Screen.CATEGORY;
import static acal.com.acal_left.ui.event.Screen.PARTNER;


@Component
public class Dashboard extends JFrame {

    private final ScreenManager screenManager;

    private final CategoryScreen categoryScreen;
    private final PartnerScreen partnerScreen;


    public Dashboard(
            ScreenManager screenManager,
            CategoryScreen categoryScreen,
            PartnerScreen partnerScreen
            ) {
        this.screenManager = screenManager;
        this.categoryScreen = categoryScreen;
        this.partnerScreen = partnerScreen;

        initComponents();
        addScreens();
    }

    private void addScreens() {
        mainPanel.add(new JPanel(), "empty");
        mainPanel.add(categoryScreen, categoryScreen.name);
        mainPanel.add(partnerScreen, partnerScreen.name);

        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "empty");
    }

    @EventListener
    public void onLoginSuccess(LoginSuccessEvent event) {
        this.setVisible(true);
    }

    @EventListener
    public void onAreaChange(ChangeScreenEvent event) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

        String cardName = switch(event.getScreen()) {
            case CATEGORY -> CATEGORY.name();
            case PARTNER -> PARTNER.name();
        };

        cardLayout.show(mainPanel, cardName);
    }

    private void categoryActionListener(ActionEvent e) {
        screenManager.changeScreen(CATEGORY);
    }

    private void partnerActionListener(ActionEvent e) {
        screenManager.changeScreen(PARTNER);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItemCategory = new JMenuItem();
        menuItemPartener = new JMenuItem();
        mainPanel = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(1024, 768));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Cadastros");

                //---- menuItemCategory ----
                menuItemCategory.setText("Categorias");
                menuItemCategory.addActionListener(e -> categoryActionListener(e));
                menu1.add(menuItemCategory);

                //---- menuItemPartener ----
                menuItemPartener.setText("S\u00f3cios");
                menuItemPartener.addActionListener(e -> partnerActionListener(e));
                menu1.add(menuItemPartener);
            }
            menuBar1.add(menu1);
        }
        contentPane.add(menuBar1, BorderLayout.PAGE_START);

        //======== mainPanel ========
        {
            mainPanel.setLayout(new CardLayout());
        }
        contentPane.add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItemCategory;
    private JMenuItem menuItemPartener;
    private JPanel mainPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
