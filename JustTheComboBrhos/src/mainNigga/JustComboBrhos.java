package mainNigga;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.naming.CommunicationException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JustComboBrhos extends JFrame {

	private JPanel contentPane;
	private JTable tblFamili;
	private Connection conexion;
	private JComboBox<Object> cbxFamilia;
	private JComboBox<Object> cbxSubFamilia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JustComboBrhos frame = new JustComboBrhos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JustComboBrhos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		JButton btnDel = new JButton("DEL");
		
		JScrollPane scrollPane = new JScrollPane();
		
		cbxFamilia = new JComboBox<Object>();
		cbxFamilia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				WomboComboSubfamilia(cbxFamilia.getSelectedItem());
				
			}
		});
		WomboComboFamilia();
		
		cbxSubFamilia = new JComboBox();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(283, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addGap(92)
					.addComponent(btnDel)
					.addGap(95))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(cbxSubFamilia, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxFamilia, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addGap(147)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(94)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnDel))
					.addGap(54))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(116)
					.addComponent(cbxFamilia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(cbxSubFamilia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(249, Short.MAX_VALUE))
		);
		
		tblFamili = new JTable();
		Tabla();
		scrollPane.setViewportView(tblFamili);
		contentPane.setLayout(gl_contentPane);
	}
	
	
//METODO DE TABLA
	public void Tabla(){
		// Establaciendo Modelo de la tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tblFamili.setModel(modeloTabla);
        // Conexion a la base de datos
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        try {
			conexion = DriverManager.getConnection(url, "usuario/system", "TU CONTRASEÑA");
        //Consulta de la tabla
			Statement stat = (Statement) conexion.createStatement();
			ResultSet rslt =  ((java.sql.Statement) stat).executeQuery("SELECT NOM_FAMILIA, NOM_SUBFAMILIA FROM FAMILIA, SUBFAMILIA WHERE COD_FAMILIA = FAMILIA_COD_FAMILIA");								
			ResultSetMetaData rsMd = rslt.getMetaData();
			//Poniendo el Nombre de las Columnas
			int columnas = rsMd.getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                modeloTabla.addColumn(rsMd.getColumnLabel(i));
               }	            	            
            //Metiendo datos
            while (rslt.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                  fila[i]=rslt.getObject(i+1);
                }
                modeloTabla.addRow(fila);
               }
               rslt.close();
               conexion.close();	            	           	               
		} catch (SQLException e) {

			e.printStackTrace();
		}           									
	}
	

//METODO COMBOBOX FAMILIA
	//RELLENAR COMBOBOX FAMILIA

	public void WomboComboFamilia(){
		
		DefaultComboBoxModel<Object> modelFamilia = new DefaultComboBoxModel<Object>();
		
		
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        try {
			conexion = DriverManager.getConnection(url, "Usuario/system", "Tu contraseña");
        //Consulta de la tabla
			Statement stat = conexion.createStatement();
			ResultSet rslt =  stat.executeQuery("SELECT NOM_FAMILIA FROM FAMILIA");								

		
			
			
			while(rslt.next()){
				
			modelFamilia.addElement(rslt.getObject(1));

			
			}
			
			
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
        
        
        cbxFamilia.setModel(modelFamilia);
	    
	}
	
//METODO COMBOBOX SUBFAMILIA
	//RELLENAR COMBOBOX SUBFAMILIA
	
	public void WomboComboSubfamilia(Object family){
		
		
		DefaultComboBoxModel<Object> modelSubFamilia = new DefaultComboBoxModel<Object>();
		
		
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        try {
			conexion = DriverManager.getConnection(url, "Usuario/system", "Tu contraseña");
        //Consulta de la tabla
			Statement stat = conexion.createStatement();
			ResultSet rslt =  stat.executeQuery("SELECT NOM_SUBFAMILIA FROM SUBFAMILIA WHERE FAMILIA_COD_FAMILIA = (SELECT COD_FAMILIA FROM FAMILIA WHERE NOM_FAMILIA = '" + family + "')");								
		
		
			
			
			while(rslt.next()){
				
			modelSubFamilia.addElement(rslt.getObject(1));
			
			}
			
			
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
        
        
        cbxSubFamilia.setModel(modelSubFamilia);
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
