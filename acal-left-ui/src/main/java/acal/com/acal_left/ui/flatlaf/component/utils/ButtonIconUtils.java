package acal.com.acal_left.ui.flatlaf.component.utils;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public final class ButtonIconUtils {

    private ButtonIconUtils() {
    }

    public static void applyIcon(AbstractButton button, Icon icon, int textGap) {
        if (icon == null) {
            return;
        }
        button.setIcon(icon);
        button.setIconTextGap(textGap);
    }

    public static void applyMenuIcon(JMenu menu, String iconKey, int size) {
        Icon icon = resolveIcon(iconKey, size);
        if (icon != null) {
            menu.setIcon(icon);
        }
    }

    public static void applyMenuItemIcon(JMenuItem item, String iconKey, int size) {
        Icon icon = resolveIcon(iconKey, size);
        if (icon != null) {
            item.setIcon(icon);
        }
    }

    private static Icon resolveIcon(String iconKey, int size) {
        Icon icon = UIManager.getIcon(iconKey);
        if (icon == null) {
            icon = UIManager.getIcon("Tree.leafIcon");
        }
        if (icon == null) {
            return null;
        }
        return resizeIcon(icon, size, size);
    }

    public static Icon fromUIManager(String iconKey, int size) {
        Icon icon = UIManager.getIcon(iconKey);
        if (icon == null) {
            return null;
        }
        return resizeIcon(icon, size, size);
    }

    public static Icon resizeIcon(Icon icon, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        try {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            icon.paintIcon(null, g2, 0, 0);
        } finally {
            g2.dispose();
        }
        return new ImageIcon(image);
    }

    public static Icon createUserIcon(Color color, int size) {
        return new Icon() {
            @Override
            public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(color);
                    g2.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    // cabeça
                    float r = size * 0.22f;
                    float cx = x + size / 2f;
                    float cy = y + size * 0.30f;
                    g2.draw(new Ellipse2D.Float(cx - r, cy - r, r * 2, r * 2));
                    // corpo (arco)
                    float bodyW = size * 0.55f;
                    float bodyH = size * 0.30f;
                    g2.draw(new java.awt.geom.Arc2D.Float(
                            cx - bodyW / 2f, y + size * 0.60f,
                            bodyW, bodyH,
                            0, 180, java.awt.geom.Arc2D.OPEN));
                } finally {
                    g2.dispose();
                }
            }
            @Override public int getIconWidth()  { return size; }
            @Override public int getIconHeight() { return size; }
        };
    }

    public static Icon createLockIcon(Color color, int size) {
        return new Icon() {
            @Override
            public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(color);
                    g2.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    // corpo do cadeado
                    float bw = size * 0.55f, bh = size * 0.40f;
                    float bx = x + (size - bw) / 2f, by = y + size * 0.48f;
                    g2.drawRoundRect((int) bx, (int) by, (int) bw, (int) bh, 4, 4);
                    // argola
                    float aw = size * 0.35f, ah = size * 0.35f;
                    float ax = x + (size - aw) / 2f, ay = y + size * 0.16f;
                    g2.draw(new java.awt.geom.Arc2D.Float(ax, ay, aw, ah, 0, 180, java.awt.geom.Arc2D.OPEN));
                    // buraco
                    float dr = size * 0.07f;
                    float dcx = x + size / 2f - dr, dcy = y + size * 0.60f;
                    g2.fill(new Ellipse2D.Float(dcx, dcy, dr * 2, dr * 2));
                } finally {
                    g2.dispose();
                }
            }
            @Override public int getIconWidth()  { return size; }
            @Override public int getIconHeight() { return size; }
        };
    }

    public static Icon createCheckIcon(Color color, int size) {
        return new Icon() {
            @Override
            public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(color);
                    g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2.draw(new Line2D.Float(x + 2f, y + size / 2f, x + size * 0.42f, y + size - 3f));
                    g2.draw(new Line2D.Float(x + size * 0.42f, y + size - 3f, x + size - 2f, y + 3f));
                } finally {
                    g2.dispose();
                }
            }
            @Override public int getIconWidth()  { return size; }
            @Override public int getIconHeight() { return size; }
        };
    }

    public static Icon createLoginArrowIcon(Color color, int size) {
        return new Icon() {
            @Override
            public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(color);
                    g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    float mid = y + size / 2f;
                    float tip = x + size - 3f;
                    // linha horizontal
                    g2.draw(new Line2D.Float(x + 2f, mid, tip, mid));
                    // ponta da seta
                    g2.draw(new Line2D.Float(tip - size * 0.25f, mid - size * 0.22f, tip, mid));
                    g2.draw(new Line2D.Float(tip - size * 0.25f, mid + size * 0.22f, tip, mid));
                } finally {
                    g2.dispose();
                }
            }
            @Override public int getIconWidth()  { return size; }
            @Override public int getIconHeight() { return size; }
        };
    }

    public static Icon createPlusIcon(Color color, int size) {
        return new Icon() {
            @Override
            public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(color);
                    g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    float mid = size / 2f;
                    g2.draw(new Line2D.Float(x + 3f, y + mid, x + size - 3f, y + mid));
                    g2.draw(new Line2D.Float(x + mid, y + 3f, x + mid, y + size - 3f));
                } finally {
                    g2.dispose();
                }
            }

            @Override
            public int getIconWidth() {
                return size;
            }

            @Override
            public int getIconHeight() {
                return size;
            }
        };
    }

    public static Icon createSearchIcon(Color color, int size) {
        return new Icon() {
            @Override
            public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(color);
                    g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                    float circleSize = 8f;
                    float circleX = x + 2f;
                    float circleY = y + 2f;
                    g2.draw(new Ellipse2D.Float(circleX, circleY, circleSize, circleSize));
                    g2.draw(new Line2D.Float(
                            circleX + circleSize - 0.5f,
                            circleY + circleSize - 0.5f,
                            x + size - 2f,
                            y + size - 2f
                    ));
                } finally {
                    g2.dispose();
                }
            }

            @Override
            public int getIconWidth() {
                return size;
            }

            @Override
            public int getIconHeight() {
                return size;
            }
        };
    }
}

