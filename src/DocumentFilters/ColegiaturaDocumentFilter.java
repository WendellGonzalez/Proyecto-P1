package DocumentFilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ColegiaturaDocumentFilter extends DocumentFilter {

    private static final int MAX_DIGITS = 9;

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null) return;
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        if (text == null) return;

        // Quitar caracteres no numéricos
        text = text.replaceAll("[^0-9]", "");

        // Obtener el texto actual sin guiones
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength()).replaceAll("-", "");

        // Validar los índices
        int safeOffset = Math.min(Math.max(0, offset), currentText.length());
        int safeEnd = Math.min(safeOffset + length, currentText.length());

        // Reemplazar en base segura
        StringBuilder newRawText = new StringBuilder(currentText);
        newRawText.replace(safeOffset, safeEnd, text);

        if (newRawText.length() > MAX_DIGITS) return;

        String formattedText = formatWithDashes(newRawText.toString());
        fb.replace(0, fb.getDocument().getLength(), formattedText, attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {

        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength()).replaceAll("-", "");

        int safeOffset = Math.min(Math.max(0, offset), currentText.length());
        int safeEnd = Math.min(safeOffset + length, currentText.length());

        StringBuilder sb = new StringBuilder(currentText);
        sb.delete(safeOffset, safeEnd);

        String formattedText = formatWithDashes(sb.toString());
        fb.replace(0, fb.getDocument().getLength(), formattedText, null);
    }

    private String formatWithDashes(String raw) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < raw.length(); i++) {
            if (i == 2 || i == 4) sb.append('-');
            sb.append(raw.charAt(i));
        }
        return sb.toString();
    }
}
