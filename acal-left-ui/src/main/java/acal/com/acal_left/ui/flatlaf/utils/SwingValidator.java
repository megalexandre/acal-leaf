package acal.com.acal_left.ui.flatlaf.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.util.Map;
import java.util.Set;

public class SwingValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * Valida um modelo Jakarta e aplica feedback visual nos componentes Swing.
     * * @param parent O componente pai (geralmente o JDialog) para o JOptionPane.
     * @param model O objeto anotado com Jakarta (ex: LinkCreateModel).
     * @param fieldMap Mapeamento entre o nome do campo no modelo e o JComponent na tela.
     * @return true se estiver válido, false caso contrário.
     */
    public static <T> boolean validate(Component parent, T model, Map<String, JComponent> fieldMap) {
        
        // 1. Limpa estados de erro anteriores de todos os componentes mapeados
        fieldMap.values().forEach(c -> c.putClientProperty("JComponent.outline", null));

        // 2. Executa a validação do Jakarta
        Set<ConstraintViolation<T>> violations = validator.validate(model);

        if (violations.isEmpty()) {
            if (parent != null) parent.repaint();
            return true;
        }

        // 3. Aplica a borda de erro (FlatLaf) nos componentes que falharam
        violations.forEach(v -> {
            String property = v.getPropertyPath().toString();
            JComponent component = fieldMap.get(property);
            if (component != null) {
                component.putClientProperty("JComponent.outline", "error");
            }
        });

        // 4. Mostra a primeira mensagem de erro
        String firstMessage = violations.iterator().next().getMessage();
        JOptionPane.showMessageDialog(parent, 
            firstMessage, 
            "Aviso", 
            JOptionPane.WARNING_MESSAGE);

        if (parent != null) parent.repaint();
        return false;
    }
}