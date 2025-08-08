/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model;

import Vista.LoginORSignIn;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author wendellgonzalez
 */
public class AppClinica {
Medico medico;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al establecer el Look and Feel 'Nimbus'");
            e.printStackTrace();
        }
        // --- FIN DEL CODIGO LOOK AND FEEL ---

        // Iniciar la interfaz gráfica en el hilo de eventos de AWT (EDT)
        SwingUtilities.invokeLater(() -> {
            new LoginORSignIn().setVisible(true);

        });
    }

}
