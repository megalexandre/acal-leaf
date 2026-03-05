package acal.com.acal_left.ui.flatlaf.screen.dashboard;

import acal.com.acal_left.ui.event.ChangeScreenEvent;
import acal.com.acal_left.ui.event.LoginSuccessEvent;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.address.address.AddressScreen;
import acal.com.acal_left.ui.flatlaf.screen.category.category.CategoryScreen;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.InvoiceScreen;
import acal.com.acal_left.ui.flatlaf.screen.link.link.LinkScreen;
import acal.com.acal_left.ui.flatlaf.screen.partner.partner.PartnerScreen;
import acal.com.acal_left.ui.routes.ScreenManager;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static acal.com.acal_left.ui.event.Screen.ADDRESS;
import static acal.com.acal_left.ui.event.Screen.CATEGORY;
import static acal.com.acal_left.ui.event.Screen.INVOICE;
import static acal.com.acal_left.ui.event.Screen.LINK;
import static acal.com.acal_left.ui.event.Screen.PARTNER;


@Component
public abstract class Dashboard extends JFrame {

    private final ScreenManager screenManager;
    private final ApplicationContext applicationContext;

    @Lookup
    public abstract CategoryScreen getCategoryScreen();

    @Lookup
    public abstract PartnerScreen getPartnerScreen();

    @Lookup
    public abstract AddressScreen getAddressScreen();

    @Lookup
    public abstract LinkScreen getLinkScreen();

    @Lookup
    public abstract InvoiceScreen getInvoiceScreen();

    public Dashboard(
            ScreenManager screenManager,
            ApplicationContext applicationContext
            ) {
        this.screenManager = screenManager;
        this.applicationContext = applicationContext;

        initComponents();
        addScreens();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutdownApplication();
            }
        });
    }

    private void addScreens() {
        mainPanel.add(new JPanel(), "empty");
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "empty");
    }

    @EventListener
    public void onLoginSuccess(LoginSuccessEvent event) {
        this.setVisible(true);
    }

    @EventListener
    public void onAreaChange(ChangeScreenEvent event) {
        Screen newScreen = event.getScreen();
        JPanel screenInstance = createScreenInstance(newScreen);
        if (screenInstance != null) {
            mainPanel.removeAll();
            mainPanel.add(screenInstance, newScreen.name());
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, newScreen.name());
            this.setTitle(newScreen.getTitle());
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private void shutdownApplication() {
        try {
            if (applicationContext != null) {
                SpringApplication.exit(applicationContext, () -> 0);
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }

    private JPanel createScreenInstance(Screen screen) {
        return switch (screen) {
            case CATEGORY -> getCategoryScreen();
            case PARTNER -> getPartnerScreen();
            case ADDRESS -> getAddressScreen();
            case LINK -> getLinkScreen();
            case INVOICE -> getInvoiceScreen();
        };
    }
    private void categoryActionListener(ActionEvent e) {
        screenManager.changeScreen(CATEGORY);
    }

    private void partnerActionListener(ActionEvent e) {
        screenManager.changeScreen(PARTNER);
    }

    private void addressActionListener(ActionEvent e) {
        screenManager.changeScreen(ADDRESS);
    }

    private void linkActionListener(ActionEvent e) {
        screenManager.changeScreen(LINK);
    }

    private void invoiceActionListener(ActionEvent e) {
        screenManager.changeScreen(INVOICE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItemCategory = new JMenuItem();
        menuItemPartener = new JMenuItem();
        menuItemAddress = new JMenuItem();
        menu4 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menu2 = new JMenu();
        menuItemInvoice = new JMenuItem();
        menuItemOverdue = new JMenuItem();
        menu3 = new JMenu();
        menuItem3 = new JMenuItem();
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

                //---- menuItemAddress ----
                menuItemAddress.setText("Logradouros");
                menuItemAddress.addActionListener(e -> addressActionListener(e));
                menu1.add(menuItemAddress);
            }
            menuBar1.add(menu1);

            //======== menu4 ========
            {
                menu4.setText("\u00c1gua");

                //---- menuItem4 ----
                menuItem4.setText("Liga\u00e7\u00f5es ");
                menuItem4.addActionListener(e -> linkActionListener(e));
                menu4.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("Par\u00e2metros");
                menu4.add(menuItem5);
            }
            menuBar1.add(menu4);

            //======== menu2 ========
            {
                menu2.setText("Financeiro");

                //---- menuItemInvoice ----
                menuItemInvoice.setText("Faturas");
                menuItemInvoice.addActionListener(e -> invoiceActionListener(e));
                menu2.add(menuItemInvoice);

                //---- menuItemOverdue ----
                menuItemOverdue.setText("Cobran\u00e7as");
                menu2.add(menuItemOverdue);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("Caixa");

                //---- menuItem3 ----
                menuItem3.setText("Resumo");
                menu3.add(menuItem3);
            }
            menuBar1.add(menu3);
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
    private JMenuItem menuItemAddress;
    private JMenu menu4;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenu menu2;
    private JMenuItem menuItemInvoice;
    private JMenuItem menuItemOverdue;
    private JMenu menu3;
    private JMenuItem menuItem3;
    private JPanel mainPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
