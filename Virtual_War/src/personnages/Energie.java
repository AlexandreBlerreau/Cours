/**
 * 
 */
package personnages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Louis
 *
 */
public class Energie extends JPanel {

	JFrame jf = new JFrame();
	int nbMine1 = Jouer.equipeA.nbMine;
	int nbMine2 = Jouer.equipeB.nbMine;
	public Energie() {
		
		jf.setTitle("Energie");
		jf.setSize(250, 600);

		jf.setLocation(10, 10);
		this.setBackground(Color.blue);
		jf.setContentPane(this);
		jf.setDefaultCloseOperation(3);
		jf.setVisible(true);
		jf.toFront();

	}

	public void paintComponent(Graphics g) {
		int x = 20;
		int y = 20;

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 250, 600);
		Font myFont = new Font("Sans Serif", 1, 17);
		Font myFont2 = new Font("Sans Serif", 1, 25);
		g.setColor(Color.white);
		g.setFont(myFont2);
		
<<<<<<< HEAD
		nbMine1 = Jouer.equipeA.nbMine;
		
		g.drawString("Equipe 1 ", 60, y + 10);
		
=======
		nbMine1 = Essai.equipeA.nbMine;
		
		g.drawString("Equipe 1 ", 60, y + 10);
		
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
		y += 35;
		if(nbMine1 < 5){
			g.setColor(Color.RED);
		}
		g.setFont(myFont);
		g.drawString("Mines restantes " + nbMine1 + "" , 45, y);
		y += 25;
<<<<<<< HEAD
		if (Jouer.infoRobotA.size() != 0) {
			for (Robot r : Jouer.infoRobotA) {
=======
		if (Essai.infoRobotA.size() != 0) {
			for (Robot r : Essai.infoRobotA) {
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
				g.setColor(Color.WHITE);
				g.setFont(myFont);
				
				
				g.setColor(Color.WHITE);
				g.drawString("Energie du " + r.getType() + " " + r.getCoordonnee().toString(), x, y);
				
				
				y += 10;
				

				double pourc;
				int vie;
				if (r.getType().equals("Tireur")) {
					pourc = r.getEnergie() / 40.0;
					vie = (int) (pourc * 100);

				} else if (r.getType().equals("Piegeur")) {
					pourc = r.getEnergie() / 50.0;
					vie = (int) (pourc * 100);
				} else {
					pourc = r.getEnergie() / 60.0;
					vie = (int) (pourc * 100);
				}
				if (vie >= 50) {
					g.setColor(Color.GREEN);
					g.drawRect(x, y, 200, 20);
				} else if (vie < 50 && vie > 25) {
					g.setColor(Color.orange);
					g.drawRect(x, y, 200, 20);
				} else {
					g.setColor(Color.red);
					g.drawRect(x, y, 200, 20);
				}
				g.fillRect(x, y, vie * 2, 20);
				g.setColor(Color.BLACK);
				g.drawString(vie + " %", 100, y + 15);

				y += 40;

			}
		}
		y += 10;
		g.setColor(Color.white);
		g.setFont(myFont2);
<<<<<<< HEAD
		nbMine2 = Jouer.equipeB.nbMine;
=======
		nbMine2 = Essai.equipeB.nbMine;
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
		
		g.drawString("Equipe 2 ", 60, y);
		
		y+=25;
		
		if(nbMine2 < 5){
			g.setColor(Color.RED);
		}
		g.setFont(myFont);
		g.drawString("Mines restantes " + nbMine2 + "" , 45, y);
		
		
		y += 25;
		if (Jouer.infoRobotB.size() != 0) {
			for (Robot r : Jouer.infoRobotB) {

				g.setFont(myFont);
				
				
			
				
				//y += 25;
				g.setColor(Color.WHITE);
				g.drawString("Energie du " + r.getType() + " " + r.getCoordonnee().toString(), x, y);
				y += 10;
				double pourc;
				int vie;
				if (r.getType().equals("Tireur")) {
					pourc = r.getEnergie() / 40.0;
					vie = (int) (pourc * 100);

				} else if (r.getType().equals("Piegeur")) {
					pourc = r.getEnergie() / 50.0;
					vie = (int) (pourc * 100);
				} else {
					pourc = r.getEnergie() / 60.0;
					vie = (int) (pourc * 100);
				}
				if (vie >= 50) {
					g.setColor(Color.GREEN);
					g.drawRect(x, y, 200, 20);
				} else if (vie < 50 && vie > 25) {
					g.setColor(Color.orange);
					g.drawRect(x, y, 200, 20);
				} else {
					g.setColor(Color.red);
					g.drawRect(x, y, 200, 20);
				}
				g.fillRect(x, y, vie * 2, 20);
				g.setColor(Color.BLACK);
				g.drawString(vie + " %", 100, y + 15);

				y += 40;

			}
		}

	}

	public void affichage() {
		repaint();
	}

}
