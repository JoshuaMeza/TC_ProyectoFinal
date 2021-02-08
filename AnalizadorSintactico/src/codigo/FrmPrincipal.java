/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;

/**
 *
 * @author Jonathan Gómez and Joshua Meza
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Analyzer  MIO by Jonathan Gómez and Joshua Meza");
    }
    
    private void analizarLexico() throws IOException{  
        try {
            String expr = (String) txtResultado.getText();
            Lexer lexer = new Lexer(new StringReader(expr));
            FileOutputStream lex = new FileOutputStream("factorial.lex");
            PrintStream lexResul = new PrintStream(lex);
            FileOutputStream sim = new FileOutputStream("factorial.sim");
            PrintStream simResul = new PrintStream(sim);
            String resultado = "";
            String factorial = "";
            ArrayList<String> identificadores = new ArrayList();
            ArrayList<String> texto = new ArrayList();
            ArrayList<String> valores = new ArrayList();
            identificadores.add("0");
            texto.add("ignore123");
            valores.add("ignore");
            while (true) {
                Tokens tokens = lexer.yylex();
                if (tokens == null) {
                    factorial += "IDS\n";
                    for (int i=1; i<identificadores.size();i++){
                        if(i<10){
                            factorial += identificadores.get(i) + ", ID0" + i + "\n";
                        } else {
                            factorial += identificadores.get(i) + ", ID" + i + "\n";
                        }
                        
                    }
                    factorial += "\nTXT\n";
                    for (int i=1; i<texto.size();i++){
                        if(i<10){
                            factorial += texto.get(i) + ", TX0" + i + "\n";
                        } else {
                            factorial += texto.get(i) + ", TX" + i + "\n";
                        }
                        
                    }
                    factorial += "\nVAL\n";
                    for (int i=1; i<valores.size();i++){
                        factorial += valores.get(i) + "\n";
                    }
                    String salida = resultado + "\n\n" + factorial;
                    txtAnalizarLex.setText(salida);
                    
                    lexResul.print(resultado);
                    simResul.print(factorial);
                    return;
                }
                switch (tokens) {
                    case ERROR:
                        resultado += "Simbolo no definido\n";
                        break;
                    case Identificador: 
                        for (int i=0; i<identificadores.size();i++){
                             if(identificadores.contains(lexer.lexeme)){
                                 int posicion = identificadores.indexOf(lexer.lexeme);
                                 if(posicion<10){
                                     resultado += "[id] ID0" + posicion + "\n";
                                 } else {
                                     resultado += "[id] ID" + posicion + "\n";
                                 }
                                 break;
                             } else {
                                 identificadores.add(lexer.lexeme);
                                 int posicion = identificadores.indexOf(lexer.lexeme);
                                 if(posicion<10){
                                     resultado += "[id] ID0" + posicion + "\n";
                                 } else {
                                     resultado += "[id] ID" + posicion + "\n";
                                 }
                                 break;
                             }
                        }
                    break;
                    case Numero: 
                        for (int i=0; i<valores.size();i++){
                             if(valores.contains(lexer.lexeme)){
                                 resultado += "[val]\n";
                                 break;
                             } else {
                                 valores.add(lexer.lexeme);
                                 resultado += "[val]\n";
                                 break;
                             }
                        }
                        break;
                    case Reservadas: 
                        resultado += lexer.lexeme + "\n";
                        break;
                    case OperadorRelacional: 
                        resultado+= "[op_rel]\n";
                        break;
                    case OperadorAritmetico: 
                        resultado += "[op_ar]\n";
                        break;
                    case Comentario: 
                    break;
                    case Asignacion:
                        resultado+= "=\n";
                        break;
                    case Texto:
                    for (int i=0; i<texto.size();i++){
                             if(texto.contains(lexer.lexeme)){
                                 int posicion = texto.indexOf(lexer.lexeme);
                                 if(posicion<10){
                                     resultado += "[txt] TX0" + posicion + "\n";
                                 } else {
                                     resultado += "[txt] TX" + posicion + "\n";
                                 }
                                 break;
                             } else {
                                 texto.add(lexer.lexeme);
                                 int posicion = texto.indexOf(lexer.lexeme);
                                 if(posicion<10){
                                     resultado += "[txt] TX0" + posicion + "\n";
                                 } else {
                                     resultado += "[txt] TX" + posicion + "\n";
                                 }
                                 break;
                             }
                        }
                    break;
                    default:
                        resultado += "Token: " + tokens + "\n";
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnalizarLex = new javax.swing.JTextArea();
        btnAnalizarLex = new javax.swing.JButton();
        btnLimpiarLex = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnalizarSin = new javax.swing.JTextArea();
        btnAnalizarSin = new javax.swing.JButton();
        btnLimpiarSin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analizador Lexico", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        btnArchivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnArchivo.setText("Abrir archivo");
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        txtAnalizarLex.setEditable(false);
        txtAnalizarLex.setColumns(20);
        txtAnalizarLex.setRows(5);
        txtAnalizarLex.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtAnalizarLexPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(txtAnalizarLex);

        btnAnalizarLex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAnalizarLex.setText("Analizar");
        btnAnalizarLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarLexActionPerformed(evt);
            }
        });

        btnLimpiarLex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimpiarLex.setText("Limpiar");
        btnLimpiarLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarLexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAnalizarLex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiarLex))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(386, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArchivo)
                    .addComponent(btnAnalizarLex)
                    .addComponent(btnLimpiarLex))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(50, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analizador Sintactico", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txtAnalizarSin.setEditable(false);
        txtAnalizarSin.setColumns(20);
        txtAnalizarSin.setRows(5);
        jScrollPane3.setViewportView(txtAnalizarSin);

        btnAnalizarSin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAnalizarSin.setText("Analizar");
        btnAnalizarSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarSinActionPerformed(evt);
            }
        });

        btnLimpiarSin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimpiarSin.setText("Limpiar");
        btnLimpiarSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarSinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAnalizarSin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiarSin))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalizarSin)
                    .addComponent(btnLimpiarSin))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        // TODO add your handling code here:
        
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File archivo = new File(chooser.getSelectedFile().getAbsolutePath());
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtResultado.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e){
            
        }
    }//GEN-LAST:event_btnArchivoActionPerformed

    private void btnLimpiarLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarLexActionPerformed
        // TODO add your handling code here:
        txtAnalizarLex.setText(null);
    }//GEN-LAST:event_btnLimpiarLexActionPerformed

    private void btnLimpiarSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarSinActionPerformed
        // TODO add your handling code here:
        txtAnalizarSin.setText(null);
    }//GEN-LAST:event_btnLimpiarSinActionPerformed

    private void btnAnalizarLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarLexActionPerformed
        try {
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalizarLexActionPerformed

    private void btnAnalizarSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarSinActionPerformed
        // TODO add your handling code here:
        String ST = txtResultado.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
        
        try {
            s.parse();
            txtAnalizarSin.setText("Compilacion exitosa");
            txtAnalizarSin.setForeground(new Color(25, 111, 61));
        } catch (Exception ex) {
            Symbol sym = s.getS();
            if(sym.right==-1){
                txtAnalizarSin.setText("Error de sintaxis. Linea: " + (sym.right + 1 ) + " Columna: " + (sym.left +1) + ", Se esperaba PROGRAMA <IDENTIFICADOR> <SENTS> FINPROG,\npuede estar faltando etiqueta de apertura o de cierre.\n"  );
                txtAnalizarSin.setForeground(Color.red);
            } else {
                try {
                    if(Double.valueOf(sym.value.toString()) != 3){
                    txtAnalizarSin.setText("Error de sintaxis. Linea: " + (sym.right + 1 ) + " Columna: " + (sym.left +1) + ", No se esperaba [val] = " + sym.value + "\n" );
                    txtAnalizarSin.setForeground(Color.red);
                    } else{
                        txtAnalizarSin.setText("Error de sintaxis. Linea: " + (sym.right + 1 ) + " Columna: " + (sym.left +1) + ", Puede estar faltando abrir o cerrar una instruccion en \"" + sym.value + "\"\n" );
                        txtAnalizarSin.setForeground(Color.red);
                    }
                } catch (NumberFormatException excepcion) {
                    txtAnalizarSin.setText("Error de sintaxis. Linea: " + (sym.right + 1 ) + " Columna: " + (sym.left +1) + ", Se esperaba otro tipo de sentencia antes de : \"" + sym.value + "\" \nPuede estar escribiendo <ELEM> <ELEM>, faltando una asignacion o una etiqueta de apertura o cierre de subrutina o de codigo." );
                    txtAnalizarSin.setForeground(Color.red);
                }
                
               
            }
            
        }
    }//GEN-LAST:event_btnAnalizarSinActionPerformed

    private void txtAnalizarLexPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtAnalizarLexPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnalizarLexPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizarLex;
    private javax.swing.JButton btnAnalizarSin;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnLimpiarLex;
    private javax.swing.JButton btnLimpiarSin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtAnalizarLex;
    private javax.swing.JTextArea txtAnalizarSin;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
