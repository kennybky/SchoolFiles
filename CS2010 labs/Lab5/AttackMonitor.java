import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class AttackMonitor {
private List<MonsterAttack> monsterAttacks = new ArrayList<MonsterAttack>();

public void monitor() {
	String[] choices  = {"Quit", "Input information on  a Monster attack", 
			"Show list of attacks", "Delete an attack", "Export Attacks", "importAttacks"};
	int choice;
	do {
		choice = JOptionPane.showOptionDialog(null, "Main menu", "Main Menu",
				0, JOptionPane.QUESTION_MESSAGE, null, choices, null);
		switch (choice) {
		case 0:
			break;
		case 1:
			addAttack();
			break;
		case 2:
			showAttacks();
			break;
		case 3:
			if(!monsterAttacks.isEmpty())
			deleteAttack();
			else
			JOptionPane.showMessageDialog(null, "There are no monster attacks to delete!");
			break; 
		case 4:
			try {
			exportAttacks();
			} catch(IOException e) {
				JOptionPane.showMessageDialog(null, e);
			}
			break;
		case 5:
			try {
			importAttacks();
			} catch(IOException e) {
				JOptionPane.showMessageDialog(null, e);
			}
			break;
		}
		
	}while(choice !=0);
}

private void addAttack() {
	int id = Integer.parseInt(JOptionPane.showInputDialog("Please enter the attack ID"));
	String name = JOptionPane.showInputDialog("Please enter the name of the monster");
	String location = JOptionPane.showInputDialog("Please enter the location of the attack");
	String date = JOptionPane.showInputDialog("Please enter the date of the attack in thisi format MM/DD/YYYY");
	int numVictims = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of victims"));
	
	monsterAttacks.add(new MonsterAttack(id, name, location, date, numVictims));
}

private void showAttacks() {
	List<MonsterAttack> attacks = monsterAttacks;
	StringBuilder sB = new StringBuilder("These are the current list of attacks:\n");
	if (attacks.isEmpty()) {
		sB.append("There are no monster attacks");
	} else {
		for (MonsterAttack m: attacks)
		sB.append(m + "\n");
	}
	JOptionPane.showMessageDialog(null, sB);
}


private void deleteAttack() {
	List<MonsterAttack> attacks = monsterAttacks;
	MonsterAttack hold = new MonsterAttack();
	showAttacks();
	int id = Integer.parseInt(JOptionPane.showInputDialog("Please enter the Id of the attack"));
	for (MonsterAttack m : attacks) {
		if (id == m.getId()) {
			hold = m;
		break;
		} else {
			JOptionPane.showMessageDialog(null, "Record doesn't exist");
		}
		}
	attacks.remove(hold);
	}
private void exportAttacks() throws IOException {
	File toFile = new File(JOptionPane.showInputDialog("Enter the file path"));
	BufferedWriter writer = new BufferedWriter(new FileWriter(toFile));
	for (MonsterAttack m : monsterAttacks) {
		writer.write(m.getId() + "," + m.getName() + "," + m.getLocation() + "," +
				m.getMonth() + "/" + m.getDay() + "/" + m.getYear() + "," + m.getNumVictims());
		writer.newLine();
	}
	writer.close();
}

private void importAttacks() throws IOException {
	monsterAttacks.clear();
	File fromFile = new File(JOptionPane.showInputDialog("Enter the file path"));
	Scanner reader = new Scanner(fromFile);
	int id, num;
	String name, location, date, line;
	
	while(reader.hasNextLine()) {
	line = reader.nextLine();
	String[] data = line.split("," , 0);
	id = Integer.parseInt(data[0]);
	name = data[1];
	location = data[2];
	date = data[3];
	num = Integer.parseInt(data[4]);
	monsterAttacks.add(new MonsterAttack(id, name, location, date, num));
	}
	reader.close();
}


}

