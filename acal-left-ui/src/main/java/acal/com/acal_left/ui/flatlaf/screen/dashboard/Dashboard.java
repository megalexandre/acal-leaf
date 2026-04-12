package acal.com.acal_left.ui.flatlaf.screen.dashboard;

import acal.com.acal_left.ui.event.ChangeScreenEvent;
import acal.com.acal_left.ui.event.LoginSuccessEvent;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.address.address.AddressScreen;
import acal.com.acal_left.ui.flatlaf.screen.category.category.CategoryScreen;
import acal.com.acal_left.ui.flatlaf.screen.charge.ChargeScreen;
import acal.com.acal_left.ui.flatlaf.screen.invoice.create.InvoiceCreateScreen;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.InvoiceScreen;
import acal.com.acal_left.ui.flatlaf.screen.invoice.receiver.ReceiverInvoicePayment;
import acal.com.acal_left.ui.flatlaf.screen.link.link.LinkScreen;
import acal.com.acal_left.ui.flatlaf.screen.person.partner.PersonScreen;
import acal.com.acal_left.ui.flatlaf.screen.register.RegisterScreen;
import acal.com.acal_left.ui.flatlaf.screen.water.WaterAnalysisScreen;
import acal.com.acal_left.ui.routes.ScreenManager;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import static acal.com.acal_left.ui.event.Screen.ADDRESS;
import static acal.com.acal_left.ui.event.Screen.CATEGORY;
import static acal.com.acal_left.ui.event.Screen.CHARGE;
import static acal.com.acal_left.ui.event.Screen.INVOICE;
import static acal.com.acal_left.ui.event.Screen.INVOICE_CREATE;
import static acal.com.acal_left.ui.event.Screen.LINK;
import static acal.com.acal_left.ui.event.Screen.PARTNER;
import static acal.com.acal_left.ui.event.Screen.REGISTER;
import static acal.com.acal_left.ui.event.Screen.WATER_ANALYSIS;


@Lazy
@Component
public abstract class Dashboard extends JFrame {

    private static final int MENU_ICON_SIZE = 16;

    private final ScreenManager screenManager;
    private final ApplicationContext applicationContext;

    @Lookup
    public abstract CategoryScreen getCategoryScreen();

    @Lookup
    public abstract PersonScreen getPartnerScreen();

    @Lookup
    public abstract AddressScreen getAddressScreen();

    @Lookup
    public abstract LinkScreen getLinkScreen();

    @Lookup
    public abstract InvoiceScreen getInvoiceScreen();

    @Lookup
    public abstract InvoiceCreateScreen getInvoiceCreateScreen();

    @Lookup
    public abstract RegisterScreen getRegisterScreen();

    @Lookup
    public abstract WaterAnalysisScreen getWaterAnalysisScreen();

    @Lookup
    public abstract ChargeScreen getChargeScreen();


    @Lookup
    public abstract ReceiverInvoicePayment receiverInvoicePayment(Window owner);


    public Dashboard(
            ScreenManager screenManager,
            ApplicationContext applicationContext
            ) {
        this.screenManager = screenManager;
        this.applicationContext = applicationContext;

        initComponents();
        configureMenuIcons();
        addScreens();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutdownApplication();
            }
        });
    }

    private void configureMenuIcons() {
        setMenuIcon(menu1, "FileChooser.homeFolderIcon");
        setMenuIcon(menu4, "FileView.computerIcon");
        setMenuIcon(menu2, "FileView.floppyDriveIcon");
        setMenuIcon(menu3, "Tree.openIcon");

        setMenuItemIcon(menuItemCategory, "FileView.directoryIcon");
        setMenuItemIcon(menuItemPartener, "FileChooser.detailsViewIcon");
        setMenuItemIcon(menuItemAddress, "FileView.hardDriveIcon");
        setMenuItemIcon(menuItem4, "FileView.computerIcon");
        setMenuItemIcon(menuItem5, "OptionPane.questionIcon");
        setMenuItemIcon(menuItemInvoice, "FileView.fileIcon");
        setMenuItemIcon(menuItemCharge, "OptionPane.warningIcon");
        setMenuItemIcon(menuItemCreateInvoice, "FileChooser.newFolderIcon");
        setMenuItemIcon(menuItemReceiver, "OptionPane.informationIcon");
        setMenuItemIcon(menuItem3, "Tree.closedIcon");
    }

    private void setMenuIcon(JMenu menu, String iconKey) {
        Icon icon = UIManager.getIcon(iconKey);
        if (icon == null) {
            icon = UIManager.getIcon("Tree.leafIcon");
        }
        if (icon != null) {
            menu.setIcon(resizeIcon(icon, MENU_ICON_SIZE, MENU_ICON_SIZE));
        }
    }

    private void setMenuItemIcon(JMenuItem menuItem, String iconKey) {
        Icon icon = UIManager.getIcon(iconKey);
        if (icon == null) {
            icon = UIManager.getIcon("Tree.leafIcon");
        }
        if (icon != null) {
            menuItem.setIcon(resizeIcon(icon, MENU_ICON_SIZE, MENU_ICON_SIZE));
        }
    }

    private Icon resizeIcon(Icon icon, int targetWidth, int targetHeight) {
        int sourceWidth = Math.max(1, icon.getIconWidth());
        int sourceHeight = Math.max(1, icon.getIconHeight());

        double scale = Math.min((double) targetWidth / sourceWidth, (double) targetHeight / sourceHeight);
        int drawWidth = Math.max(1, (int) Math.round(sourceWidth * scale));
        int drawHeight = Math.max(1, (int) Math.round(sourceHeight * scale));
        int x = (targetWidth - drawWidth) / 2;
        int y = (targetHeight - drawHeight) / 2;

        BufferedImage canvas = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = canvas.createGraphics();
        try {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (icon instanceof ImageIcon imageIcon) {
                g2.drawImage(imageIcon.getImage(), x, y, drawWidth, drawHeight, null);
            } else {
                BufferedImage source = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D sourceGraphics = source.createGraphics();
                try {
                    icon.paintIcon(null, sourceGraphics, 0, 0);
                } finally {
                    sourceGraphics.dispose();
                }
                g2.drawImage(source, x, y, drawWidth, drawHeight, null);
            }
        } finally {
            g2.dispose();
        }
        return new ImageIcon(canvas);
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
            System.exit(0);
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
            case INVOICE_CREATE -> getInvoiceCreateScreen();
            case REGISTER -> getRegisterScreen();
            case CHARGE ->  getChargeScreen();
            case WATER_ANALYSIS -> getWaterAnalysisScreen();
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

    private void createInvoiceActionListener(ActionEvent e) {
        screenManager.changeScreen(INVOICE_CREATE);
    }

    private void menuItemRegister(ActionEvent e) {
        screenManager.changeScreen(REGISTER);
    }

    private void chargeActionListener(ActionEvent e) {
        screenManager.changeScreen(CHARGE);
    }

    private void menuWaterAnalysis(ActionEvent e) {
        screenManager.changeScreen(WATER_ANALYSIS);
    }

    private void receiverActionListener(ActionEvent e) {
        ReceiverInvoicePayment dialog = receiverInvoicePayment(this);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
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
        menuItemCharge = new JMenuItem();
        menuItemCreateInvoice = new JMenuItem();
        menuItemReceiver = new JMenuItem();
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
                menuItem5.addActionListener(e -> menuWaterAnalysis(e));
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

                //---- menuItemCharge ----
                menuItemCharge.setText("Cobran\u00e7as");
                menuItemCharge.addActionListener(e -> chargeActionListener(e));
                menu2.add(menuItemCharge);

                //---- menuItemCreateInvoice ----
                menuItemCreateInvoice.setText("Gerar Faturas");
                menuItemCreateInvoice.addActionListener(e -> {
			createInvoiceActionListener(e);
			createInvoiceActionListener(e);
		});
                menu2.add(menuItemCreateInvoice);
                menu2.addSeparator();

                //---- menuItemReceiver ----
                menuItemReceiver.setText("Receber ");
                menuItemReceiver.addActionListener(e -> receiverActionListener(e));
                menu2.add(menuItemReceiver);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("Caixa");

                //---- menuItem3 ----
                menuItem3.setText("Resumo");
                menuItem3.addActionListener(e -> menuItemRegister(e));
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
    private JMenuItem menuItemCharge;
    private JMenuItem menuItemCreateInvoice;
    private JMenuItem menuItemReceiver;
    private JMenu menu3;
    private JMenuItem menuItem3;
    private JPanel mainPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
