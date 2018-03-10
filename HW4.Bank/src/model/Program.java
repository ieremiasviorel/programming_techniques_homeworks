package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.StartViewController;
import factory.BankFactory;
import presentation.StartView;

@SuppressWarnings("unused")
public class Program {

	public static void main(String[] args) {
		
		Bank b = null;
	
		/*b = BankFactory.createBank();
		
		File bankData = new File("BankData.txt");
		if (!bankData.exists()) {
			try {
				bankData.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(bankData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        ObjectOutputStream oout = null;
		try {
			oout = new ObjectOutputStream(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

        try {
			oout.writeObject(b);
		} catch (IOException e) {
			e.printStackTrace();
		}

        try {
			oout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        
		FileInputStream in = null;
		try {
			in = new FileInputStream("BankData.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        ObjectInputStream oin = null;
		try {
			oin = new ObjectInputStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			b = (Bank)oin.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		b.reestablishRelations();
		new StartViewController(b, new StartView());
	}
}