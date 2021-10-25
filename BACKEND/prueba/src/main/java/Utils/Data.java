package Utils;

import java.util.Arrays;
import java.util.List;

import com.prueba.reyes.prueba.entity.Usuario;

public class Data {
	
	public static Usuario crearUsuario() {
		return new Usuario(1,"leandro","novoa","11162783","leandro@gmail.com","2253170");
	}
	public static Usuario crearUsuarioNull() {
		return new Usuario(2,"leandro","novoa",null,"leandro@gmail.com","2253170");
	}
	
	public static Usuario crearUsuarioservice() {
		return new Usuario(3,"Andres","Reyes","121212","Andres@gmail.com","2253170");
	}
	
	public static List<Usuario> listaUsuarios = Arrays.asList(new Usuario(1,"leandro","novoa","11162783","leandro@gmail.com","2253170"),
			new Usuario(2,"Andres","Reyes","3433","Andres@gmail.com","2253170"), new Usuario(3,"Andres","Reyes","121212","Andres@gmail.com","2253170"));

	public static Usuario crearUsuariotel() {
		return new Usuario(3,"Andres","Reyes","121212","Andres@gmail.com","2q253170");
	}

	public static Usuario crearUsuarioced() {
		return new Usuario(3,"Andres","Reyes","121a212","Andres@gmail.com","2253170");
	}
	public static Usuario crearUsuariocorreo() {
		return new Usuario(3,"Andres","Reyes","121a212","leaandro@gmail.com","2253170");
	}
	public static Usuario crearUsuariocorreoup() {
		return new Usuario(4,"Andres","Reyes","121a212","leaandro@gmail.com","2253170");
	}
	public static Usuario crearUsuarioFormatoCorreo() {
		return new Usuario(1,"leandro","novoa","11162783","leandrogmail.com","2253170");
	}


}
