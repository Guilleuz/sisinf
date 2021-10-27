package es.unizar.sisinf.grpV2_B.model;
import java.util.List;

public class pruebaDAO {
	public static void main(String []args) {
		int bicis = new biziDAO().getNumeroBicis(35);
		System.out.println("El número de bicis disponibles es: " + bicis);
		
		List<llegadaAutobusVO> llegadas = new llegadaAutobusDAO().getLlegadas(687);
		for (llegadaAutobusVO i : llegadas) {
			System.out.println(i.getLinea() + " " + i.getSentido() + ": " + i.getPrimero() + " y " + i.getSegundo());
		}
		
		llegadaTranviaVO llegada = new llegadaTranviaDAO().getLlegadas(101);
		System.out.println(llegada.getPrimero() + " y " + llegada.getSegundo());
	}
}
