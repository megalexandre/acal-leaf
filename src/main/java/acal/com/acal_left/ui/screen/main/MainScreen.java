/*
 * Created by JFormDesigner on Fri Feb 27 20:41:55 BRT 2026
 */

package acal.com.acal_left.ui.screen.main;

import acal.com.acal_left.core.event.ChangeScreenEvent;
import acal.com.acal_left.core.event.LoginSuccessEvent;
import acal.com.acal_left.core.event.Screen;
import acal.com.acal_left.resouces.model.User;
import acal.com.acal_left.ui.routes.ScreenManager;
import acal.com.acal_left.ui.screen.search.category.serch.CategorySearch;
import acal.com.acal_left.ui.screen.search.customer.CustomersSearch;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static acal.com.acal_left.core.event.Screen.CUSTOMER_REGISTER;
import static acal.com.acal_left.core.event.Screen.INVOICE_SEARCH;

@Component
public class MainScreen extends JFrame {

    private final ScreenManager screenManager;
    private final CustomersSearch customersSearch;
    private final CategorySearch categorySearch;

    private User user;

    public MainScreen(
        ScreenManager screenManager,
        CustomersSearch customersSearch,
        CategorySearch categorySearch
    ) {
        this.screenManager = screenManager;
        this.customersSearch = customersSearch;
        this.categorySearch = categorySearch;
        initComponents();

        //mainPanel.add(partnerScreen, "partner");
    }


    @EventListener
    public void onLoginSuccess(LoginSuccessEvent event) {
        this.user = event.getUser();
        this.labelUsername.setText(this.user.getUsername());
        this.setVisible(true);
    }

    @EventListener
    public void onAreaChange(ChangeScreenEvent event) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

        switch(event.getScreen()) {
            case CUSTOMER_REGISTER:
                mainPanel.add(customersSearch, "customersSearch");
                cardLayout.show(mainPanel, "customersSearch");
                break;
            case INVOICE_SEARCH:
                break;
            case CATEGORY_SEARCH:
                mainPanel.add(categorySearch, "categorySearch");
                cardLayout.show(mainPanel, "categorySearch");
                break;
        }
    }

    private void customerRegisterClick(ActionEvent e) {
        screenManager.changeScreen(CUSTOMER_REGISTER);
    }

    private void invoiceSearchClick(ActionEvent e) {
        screenManager.changeScreen(INVOICE_SEARCH);
    }

    private void categorySearchClick(ActionEvent e) {
        screenManager.changeScreen(Screen.CATEGORY_SEARCH);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItemCustomerRegister = new JMenuItem();
        menuItemCategorySearch = new JMenuItem();
        menu2 = new JMenu();
        menuItem5 = new JMenuItem();
        menu3 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItemInvoiceSearch = new JMenuItem();
        menuItem4 = new JMenuItem();
        panel1 = new JPanel();
        labelUsername = new JLabel();
        mainPanel = new JPanel();
        panel3 = new JPanel();

        //======== this ========
        setMinimumSize(new Dimension(1024, 768));
        setTitle("Acal ");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Cadastros");

                //---- menuItemCustomerRegister ----
                menuItemCustomerRegister.setText("S\u00f3cios");
                menuItemCustomerRegister.addActionListener(e -> customerRegisterClick(e));
                menu1.add(menuItemCustomerRegister);

                //---- menuItemCategorySearch ----
                menuItemCategorySearch.setText("Categorias");
                menuItemCategorySearch.addActionListener(e -> categorySearchClick(e));
                menu1.add(menuItemCategorySearch);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("Financeiro");

                //---- menuItem5 ----
                menuItem5.setText("Caixa");
                menu2.add(menuItem5);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("Faturas");

                //---- menuItem3 ----
                menuItem3.setText("Gerar Faturas");
                menu3.add(menuItem3);

                //---- menuItemInvoiceSearch ----
                menuItemInvoiceSearch.setText("Buscar Faturas");
                menuItemInvoiceSearch.addActionListener(e -> invoiceSearchClick(e));
                menu3.add(menuItemInvoiceSearch);
                menu3.addSeparator();

                //---- menuItem4 ----
                menuItem4.setText("Cobran\u00e7as");
                menu3.add(menuItem4);
            }
            menuBar1.add(menu3);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog"
            ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder
            ( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );
            panel1.setLayout(new HorizontalLayout());
            panel1.add(labelUsername);
        }
        contentPane.add(panel1, BorderLayout.SOUTH);

        //======== mainPanel ========
        {
            mainPanel.setLayout(new CardLayout());

            //======== panel3 ========
            {
                panel3.setLayout(new VerticalLayout());
            }
            mainPanel.add(panel3, "card1");
        }
        contentPane.add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItemCustomerRegister;
    private JMenuItem menuItemCategorySearch;
    private JMenu menu2;
    private JMenuItem menuItem5;
    private JMenu menu3;
    private JMenuItem menuItem3;
    private JMenuItem menuItemInvoiceSearch;
    private JMenuItem menuItem4;
    private JPanel panel1;
    private JLabel labelUsername;
    private JPanel mainPanel;
    private JPanel panel3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
