/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DocumentFilters;

import javax.swing.text.DocumentFilter;
import java.util.regex.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author wendellgonzalez
 */

public class FechaDocumentFilter extends DocumentFilter{
    
    private static final Pattern DIGITS_ONLY = Pattern.compile("\\d*");
    
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if(string == null) return;
        replace(fb, offset, 0, string, attr);
    }
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, string);
        
        String newText = sb.toString().replaceAll("[^\\d]", "");
        
        if(newText.length() > 8) newText = newText.substring(0, 8);
        
        StringBuilder formatted = new StringBuilder();
        for(int i = 0; i < newText.length(); i++) {
            if (i == 4 || i == 6) formatted.append("-");
            formatted.append(newText.charAt(i));
        }
        
        fb.replace(0, doc.getLength(), formatted.toString(), attrs);
    }
    
}
