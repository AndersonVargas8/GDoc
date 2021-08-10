package gui.cliente.login;

import gui.servicios.serviciosLogicos.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegistrarComponent implements ActionListener, MouseListener {
    private RegistrarTemplate registrarTemplate;
    private LoginComponent loginComponent;
    private UsuarioService sUsuario;
    private String[] placeholders = { "Ingrese un nuevo usuario", "Contraseña" };

    public RegistrarComponent(LoginComponent loginComponent){
        sUsuario = UsuarioService.getServicio();
        this.loginComponent = loginComponent;
        this.registrarTemplate = new RegistrarTemplate(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registrarTemplate.getbRegistrar()){
            this.enviarDatos();
        }
    }

    public RegistrarTemplate getRegistrarTemplate(){
        return this.registrarTemplate;
    }

    public void enviarDatos(){
        String nombreUsuario = registrarTemplate.gettNombreUsuario().getText();
        String claveUsuario = new String(registrarTemplate.gettClaveUsuario().getPassword());

        //----------------------------------------
        //nombreUsuario = "Anderson";
        //claveUsuario = "1234";
        // ----------------------
        if(!nombreUsuario.isEmpty() && !claveUsuario.isEmpty() && !nombreUsuario.equals(placeholders[0]) && !claveUsuario.equals(placeholders[1])){
            if(!sUsuario.verificarUsuarioExistente(nombreUsuario)) {
                sUsuario.crearUsuario(nombreUsuario, claveUsuario);

                JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Mensaje", 1);
                restaurarValores();
            }
            else
                JOptionPane.showMessageDialog(null,"El usuario ya existe", "Error", 2);
        }else{
            JOptionPane.showMessageDialog(null,"Ingrese todos los datos", "Error", 2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField textField = ((JTextField) e.getSource());

            if (textField.getText().equals(placeholders[0]) || textField.getText().equals(placeholders[1]))
                textField.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(new Color(6, 120, 172));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton boton = ((JButton) e.getSource());
            boton.setBackground(new Color(0,134,190));
        }
    }

    public void restaurarValores(){
        this.getRegistrarTemplate().gettNombreUsuario().setText("Ingrese un nuevo usuario");
        this.getRegistrarTemplate().gettClaveUsuario().setText("Contraseña");
    }
}
