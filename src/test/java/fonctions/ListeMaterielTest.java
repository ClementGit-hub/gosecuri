//package fonctions;
//
//import java.util.Map;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.epsi.fr.gosecuri.models.personnes.listes.ListeMateriel;
//import com.epsi.fr.gosecuri.models.personnes.materiels.Materiel;
//
//public class ListeMaterielTest {
//	
//	@BeforeAll
//	void beforeAll() {
//		System.out.println("RecuperationTest a commencé !");
//	}
//	
//	@AfterAll
//	void afterAll() {
//		System.out.println("RecuperationTest fini !");
//	}
//	
//	@Test
//	void ListeMateriel() {
//		
//		//Initialisation
//		Map<String, Materiel> liste = null;
//		
//		//Données à tester
//		try {
//			liste = new ListeMateriel().getListeMateriel();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		//Test
//		Assertions.assertTrue(false, "Erreur : " + " Materiel ");
//		
//		
//	}
//	
//
//	
//}
