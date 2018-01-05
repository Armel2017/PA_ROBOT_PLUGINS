package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * Classe permettant de creer l'interface 
 * principale du jeu et de gerer le jeu
 */
@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener {
	
	// DIMENSIONS DE LA FENETRE
	private final static int HEIGHT = 600;
	private final static int WIDTH= 800;
	
	
	
	// MENU SIMULATION
	protected final static String START_ACTION_COMMAND = "start";
	protected final static String RESTART_ACTION_COMMAND = "restart";
	protected final static String STOP_ACTION_COMMAND = "stop";
	
	// MENU PLUGINS
	protected final static String NEW_REPOSITORY_ACTION_COMMAND = "newRepo";
	protected final static String CONFIGURE_PLUGIN_ACTION_COMMAND = "configurePluginss";
	
	protected static final String NOUVEAU_ACTION_COMMAND = "nouveau";
	
	private JMenuBar menuBar = new JMenuBar();


	

	/**
	 * Methode permettant de construire le 
	 * menu 
	 */
	private void buildJMenu() {
		// Noms des menus
		JMenu menuSimu = new JMenu("Simulation");
		JMenu menuPlugins = new JMenu("Plugins");
		


		// Creation menu Simulation
		JMenuItem itemStart = new JMenuItem("Démarrer");
		itemStart.addActionListener(this);
		itemStart.setActionCommand(START_ACTION_COMMAND);
		JMenuItem itemStop = new JMenuItem("Arrêter");
		itemStop.addActionListener(this);
		itemStop.setActionCommand(STOP_ACTION_COMMAND);
		JMenuItem itemRestart = new JMenuItem("Redémarrer");
		itemRestart.addActionListener(this);
		itemRestart.setActionCommand(RESTART_ACTION_COMMAND);
		
		menuSimu.add(itemStart);
		menuSimu.add(itemStop);
		menuSimu.add(itemRestart);
		
		// Creation menu Plugins
		JMenuItem itemNewRepo = new JMenuItem("Nouveau repertoire de plugins");
		itemNewRepo.addActionListener(this);
		itemNewRepo.setActionCommand(NEW_REPOSITORY_ACTION_COMMAND);
		JMenuItem itemConfigurePlugins = new JMenuItem("Configurations");
		itemConfigurePlugins.addActionListener(this);
		itemConfigurePlugins.setActionCommand(CONFIGURE_PLUGIN_ACTION_COMMAND);
		
		menuPlugins.add(itemConfigurePlugins);
		menuPlugins.addSeparator();
		menuPlugins.add(itemNewRepo);
		
		menuBar.add(menuSimu);
		menuBar.add(menuPlugins);
		
		setJMenuBar(menuBar);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
