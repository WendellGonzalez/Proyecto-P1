/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DocumentFilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author wendellgonzalez
 */
public class ColegiaturaDocumentFilter extends DocumentFilter{
    private static final int MAX_LENGTH = 11;
    
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        StringBuilder sb = new StringBuilder(string);
        for (int i = sb.length() - 1; i >= 0; i--) {
            char ch = sb.charAt(i);
            if (!isValidChar(ch, fb.getDocument().getLength() + offset + i)) {
                sb.deleteCharAt(i);
            }
        }
        if (sb.length() > 0) {
            super.insertString(fb, offset, sb.toString(), attr);
        }
    }
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) {
            super.replace(fb, offset, length, text, attrs);
            return;
        }

        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

        if (newText.length() > MAX_LENGTH) {
            return;
        }

        for (int i = 0; i < newText.length(); i++) {
            if (!isValidChar(newText.charAt(i), i)) {
                return; // Ignora el reemplazo si el carácter no es válido
            }
        }
        super.replace(fb, offset, length, text, attrs);
    }
    
    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    private boolean isValidChar(char ch, int position) {
        if (position == 2 || position == 5) {
            return ch == '-';
        } else {
            return Character.isDigit(ch);
        }
    }
    
}
