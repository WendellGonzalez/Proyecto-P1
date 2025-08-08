/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DocumentFilters;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author wendellgonzalez
 */
public class TelefonoDocumentFilter extends DocumentFilter{
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) return;

        // Obtener el texto actual del documento
        Document doc = fb.getDocument();
        String currentText = doc.getText(0, doc.getLength());

        // Quitar todo excepto números
        StringBuilder digits = new StringBuilder(currentText.replaceAll("[^0-9]", ""));
        digits.replace(offset, Math.min(offset + length, digits.length()), text.replaceAll("[^0-9]", ""));

        // Limitar a 10 dígitos
        if (digits.length() > 10) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }

        // Formatear como (XXX) - XXX - XXXX
        StringBuilder formatted = new StringBuilder("(   ) -    -    ");
        int digitIndex = 0;

        for (int i = 0; i < formatted.length() && digitIndex < digits.length(); i++) {
            char c = formatted.charAt(i);
            if (c == ' ') {
                formatted.setCharAt(i, digits.charAt(digitIndex++));
            }
        }

        // Reemplazar todo
        fb.replace(0, doc.getLength(), formatted.toString(), attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        // Simula reemplazo con string vacío
        replace(fb, offset, length, "", null);
    }
}
