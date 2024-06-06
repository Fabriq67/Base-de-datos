package Logica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClienteForm extends JFrame {
    private JTextField txtClicodigo;
    private JTextField txtClinombre;
    private JTextField txtCliidentificacion;
    private JTextField txtClidireccion;
    private JTextField txtClitelefono;
    private JTextField txtClicelular;
    private JTextField txtCliemail;
    private JComboBox<String> cbClitipo;
    private JComboBox<String> cbClistatus;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnRetrieve;
    private JTable tableClientes;
    private DefaultTableModel tableModel;

    public ClienteForm() {
        // Configuración del formulario
        setTitle("MerlacEstudios - Gestión de Clientes");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(45, 52, 54));  // Fondo oscuro
        add(mainPanel, BorderLayout.CENTER);

        // Panel de título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(45, 52, 54));  // Fondo oscuro
        titlePanel.setLayout(new BorderLayout());

        // Cargar y redimensionar la imagen
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/resources/nuevaeras.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH); // Ajustar el tamaño según necesites
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledIcon);
        titlePanel.add(logoLabel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("MerlacEstudios - Gestión de Clientes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 184, 148));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Panel de formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 184, 148)), "Información del Cliente", 0, 0, null, new Color(0, 184, 148)));
        formPanel.setBackground(new Color(45, 52, 54));  // Fondo oscuro
        mainPanel.add(formPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblClicodigo = createStyledLabel("Código:");
        JLabel lblClinombre = createStyledLabel("Nombre:");
        JLabel lblCliidentificacion = createStyledLabel("Identificación:");
        JLabel lblClidireccion = createStyledLabel("Dirección:");
        JLabel lblClitelefono = createStyledLabel("Teléfono:");
        JLabel lblClicelular = createStyledLabel("Celular:");
        JLabel lblCliemail = createStyledLabel("Email:");
        JLabel lblClitipo = createStyledLabel("Tipo:");
        JLabel lblClistatus = createStyledLabel("Status:");

        txtClicodigo = createStyledTextField();
        txtClicodigo.setEnabled(false); // El campo CLICODIGO es auto-generado
        txtClinombre = createStyledTextField();
        txtCliidentificacion = createStyledTextField();
        txtClidireccion = createStyledTextField();
        txtClitelefono = createStyledTextField();
        txtClicelular = createStyledTextField();
        txtCliemail = createStyledTextField();
        cbClitipo = createStyledComboBox(new String[]{"nat", "jur"});
        cbClistatus = createStyledComboBox(new String[]{"ACT", "INA"});
        btnAdd = createStyledButton("Agregar");
        btnUpdate = createStyledButton("Actualizar");
        btnRetrieve = createStyledButton("Recuperar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblClicodigo, gbc);
        gbc.gridx = 1;
        formPanel.add(txtClicodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblClinombre, gbc);
        gbc.gridx = 1;
        formPanel.add(txtClinombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblCliidentificacion, gbc);
        gbc.gridx = 1;
        formPanel.add(txtCliidentificacion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblClidireccion, gbc);
        gbc.gridx = 1;
        formPanel.add(txtClidireccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblClitelefono, gbc);
        gbc.gridx = 1;
        formPanel.add(txtClitelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lblClicelular, gbc);
        gbc.gridx = 1;
        formPanel.add(txtClicelular, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(lblCliemail, gbc);
        gbc.gridx = 1;
        formPanel.add(txtCliemail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(lblClitipo, gbc);
        gbc.gridx = 1;
        formPanel.add(cbClitipo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(lblClistatus, gbc);
        gbc.gridx = 1;
        formPanel.add(cbClistatus, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        formPanel.add(btnAdd, gbc);
        gbc.gridx = 2;
        formPanel.add(btnUpdate, gbc);
        gbc.gridx = 3;
        formPanel.add(btnRetrieve, gbc);

        // Panel de tabla
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 184, 148)), "Lista de Clientes", 0, 0, null, new Color(0, 184, 148)));
        tablePanel.setBackground(new Color(45, 52, 54));  // Fondo oscuro
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("CLICODIGO");
        tableModel.addColumn("CLINOMBRE");
        tableModel.addColumn("CLIIDENTIFICACION");
        tableModel.addColumn("CLIDIRECCION");
        tableModel.addColumn("CLITELEFONO");
        tableModel.addColumn("CLICELULAR");
        tableModel.addColumn("CLIEMAIL");
        tableModel.addColumn("CLITIPO");
        tableModel.addColumn("CLISTATUS");

        tableClientes = new JTable(tableModel);
        tableClientes.setFont(new Font("Arial", Font.PLAIN, 14));
        tableClientes.setRowHeight(25);
        tableClientes.setBackground(new Color(45, 52, 54));  // Fondo oscuro
        tableClientes.setForeground(new Color(255, 255, 255));  // Texto blanco
        tableClientes.setSelectionBackground(new Color(0, 184, 148));  // Selección azul
        tableClientes.setSelectionForeground(new Color(0, 0, 0));  // Texto selección negro
        tableClientes.setGridColor(new Color(0, 184, 148));

        JScrollPane scrollPane = new JScrollPane(tableClientes);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Añadir manejadores de eventos
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCliente();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCliente();
            }
        });

        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveClientes();
            }
        });

        tableClientes.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tableClientes.getSelectedRow() != -1) {
                loadClienteDetails(tableClientes.getSelectedRow());
            }
        });

        retrieveClientes(); // Cargar los clientes al inicio
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(0, 184, 148));
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(new Color(99, 110, 114));
        textField.setForeground(new Color(255, 255, 255));
        textField.setCaretColor(new Color(255, 255, 255));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 184, 148), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return textField;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBackground(new Color(99, 110, 114));
        comboBox.setForeground(new Color(255, 255, 255));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(0, 184, 148), 1));
        return comboBox;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 184, 148));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setBorder(new LineBorder(new Color(0, 184, 148), 2, true));
        button.setUI(new BasicButtonUI());
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 204, 204));
                button.setBorder(new LineBorder(new Color(0, 204, 204), 2, true));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 184, 148));
                button.setBorder(new LineBorder(new Color(0, 184, 148), 2, true));
            }
        });
        return button;
    }

    private void addCliente() {
        Cliente cliente = new Cliente();
        cliente.setClinombre(txtClinombre.getText());
        cliente.setCliidentificacion(txtCliidentificacion.getText());
        cliente.setClidireccion(txtClidireccion.getText());
        cliente.setClitelefono(txtClitelefono.getText());
        cliente.setClicelular(txtClicelular.getText());
        cliente.setCliemail(txtCliemail.getText());
        cliente.setClitipo(cbClitipo.getSelectedItem().toString());
        cliente.setClistatus(cbClistatus.getSelectedItem().toString());

        ClienteDAO dao = new ClienteDAO();
        try {
            dao.addCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente");
            retrieveClientes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar el cliente: " + ex.getMessage());
        }
    }

    private void updateCliente() {
        if (txtClicodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para actualizar");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setClicodigo(Integer.parseInt(txtClicodigo.getText()));
        cliente.setClinombre(txtClinombre.getText());
        cliente.setCliidentificacion(txtCliidentificacion.getText());
        cliente.setClidireccion(txtClidireccion.getText());
        cliente.setClitelefono(txtClitelefono.getText());
        cliente.setClicelular(txtClicelular.getText());
        cliente.setCliemail(txtCliemail.getText());
        cliente.setClitipo(cbClitipo.getSelectedItem().toString());
        cliente.setClistatus(cbClistatus.getSelectedItem().toString());

        ClienteDAO dao = new ClienteDAO();
        try {
            dao.updateCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente");
            retrieveClientes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente: " + ex.getMessage());
        }
    }

    private void retrieveClientes() {
        ClienteDAO dao = new ClienteDAO();
        try {
            List<Cliente> clientes = dao.getAllClientes();
            tableModel.setRowCount(0);
            for (Cliente cliente : clientes) {
                tableModel.addRow(new Object[]{
                    cliente.getClicodigo(),
                    cliente.getClinombre(),
                    cliente.getCliidentificacion(),
                    cliente.getClidireccion(),
                    cliente.getClitelefono(),
                    cliente.getClicelular(),
                    cliente.getCliemail(),
                    cliente.getClitipo(),
                    cliente.getClistatus()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los clientes: " + ex.getMessage());
        }
    }

    private void loadClienteDetails(int row) {
        txtClicodigo.setText(tableModel.getValueAt(row, 0).toString());
        txtClinombre.setText(tableModel.getValueAt(row, 1).toString());
        txtCliidentificacion.setText(tableModel.getValueAt(row, 2).toString());
        txtClidireccion.setText(tableModel.getValueAt(row, 3).toString());
        txtClitelefono.setText(tableModel.getValueAt(row, 4).toString());
        txtClicelular.setText(tableModel.getValueAt(row, 5).toString());
        txtCliemail.setText(tableModel.getValueAt(row, 6).toString());
        cbClitipo.setSelectedItem(tableModel.getValueAt(row, 7).toString());
        cbClistatus.setSelectedItem(tableModel.getValueAt(row, 8).toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteForm form = new ClienteForm();
            form.setVisible(true);
        });
    }
}
