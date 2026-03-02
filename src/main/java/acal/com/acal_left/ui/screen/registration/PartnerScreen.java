/*
 * Created by JFormDesigner on Sun Mar 01 19:26:07 BRT 2026
 */

package acal.com.acal_left.ui.screen.registration;

import org.jdesktop.swingx.VerticalLayout;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * @author alex
 */
@Component
public class PartnerScreen extends JPanel {
    public PartnerScreen() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
        panel6 = new JPanel();
        panel7 = new JPanel();
        label1 = new JLabel();
        textPartnerName = new JTextField();
        panel4 = new JPanel();
        button1 = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(1024, 768));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
        . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing
        . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
        Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );
        setLayout(new BorderLayout());

        //======== panel6 ========
        {
            panel6.setLayout(new VerticalLayout(10));

            //======== panel7 ========
            {
                panel7.setLayout(new VerticalLayout());

                //---- label1 ----
                label1.setText("Nome:");
                panel7.add(label1);

                //---- textPartnerName ----
                textPartnerName.setToolTipText("Nome:");
                panel7.add(textPartnerName);
            }
            panel6.add(panel7);
        }
        add(panel6, BorderLayout.CENTER);

        //======== panel4 ========
        {
            panel4.setLayout(new FlowLayout(FlowLayout.RIGHT));

            //---- button1 ----
            button1.setText("Confirmar");
            panel4.add(button1);
        }
        add(panel4, BorderLayout.PAGE_END);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
    private JPanel panel6;
    private JPanel panel7;
    private JLabel label1;
    private JTextField textPartnerName;
    private JPanel panel4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
