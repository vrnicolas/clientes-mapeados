package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.Controller;

public class Serializador {

	private static String urlDelArchivo = "persistencia";

	private static void serializar(Controller data) {
		try {
			FileOutputStream fos = new FileOutputStream(urlDelArchivo);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(data);
			out.close();
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	private static Controller deserializar() {
		Controller ret = null;
		try {
			FileInputStream fis = new FileInputStream(urlDelArchivo);
			ObjectInputStream in = new ObjectInputStream(fis);
			ret = (Controller) in.readObject();
			in.close();
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return ret;
	}

	public static void saveData(Controller estructura) {
		serializar(estructura);
	}

	public static Controller getData() {
		if (deserializar() == null)
			return Controller.getInstance();
		return deserializar();
	}

	public static void showData() {
		System.out.println(getData());
	}

	// public void resetData() {
	// saveData(null);
	// serializar(Controller.getInstance(), urlDelArchivo);
	// }
}
