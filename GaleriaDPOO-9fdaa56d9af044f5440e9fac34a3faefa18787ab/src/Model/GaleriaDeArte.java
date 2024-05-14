package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Pieza.Pieza;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Cliente;
import Usuario.Operador;
import Usuario.Usuario;


public class GaleriaDeArte {

	
	// Mapa de usuarios
	private static Map<String, Usuario> usuarios;
	// inventario
	private static Inventario inventario;
	
	//subasta actual en la galeria
	private static Subasta subasta;
	
	private static Administrador administrador;

	private static Operador operador;
	
	private static Cajero cajero;
	
	private static List<List<String>> registrosPorSubasta;
	
	// Se Inicializa una galeria de arte con los contenedores de información
	public GaleriaDeArte()
	
	{
		usuarios = new HashMap<String, Usuario>();
		inventario = new Inventario();
		subasta = null;
		administrador = null;
		operador = null;
		cajero = null;
		registrosPorSubasta = new ArrayList<>();
	}
	
	public void AgregarAdministrador(String password, String login, String name) {
		administrador = new Administrador(password, login, name);
	}
	
	public boolean iniciarSesionAdmin(String nombreUsuario, String password) {
        // Verificar las credenciales del administrador
        if (administrador != null && administrador.getLogin().equals(nombreUsuario) && administrador.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
	
	
	public void AgregarOperador(String password, String login, String name) {
		operador = new Operador(password, login, name);
	}
	
	public boolean iniciarSesionOperador(String nombreUsuario, String password) {
        // Verificar las credenciales del administrador
        if (operador != null && operador.getLogin().equals(nombreUsuario) && operador.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
	
	public void AgregarCajero(String password, String login, String name) {
		cajero = new Cajero(password, login, name);
	}
	
	
	
	public static List<List<String>> getRegistrosPorSubasta() {
		return registrosPorSubasta;
	}

	//Se obtiene una lista con todos los usuarios
	public static Collection<Usuario> getUsuarios()
	{
		return usuarios.values();	
	}
	
	public boolean iniciarSesionCliente(String nombreUsuario, String password) {
		
		if(getUsuario(nombreUsuario)!= null && getUsuario(nombreUsuario).getLogin().equals(nombreUsuario) && getUsuario(nombreUsuario).getPassword().equals(password) ) {
			return true;
		}		
		return false;
		
	}
	
	//Se obtiene un usuario en especifico.
	public static Usuario getUsuario(String login) 
	{
		return usuarios.get(login);
	}
		
	//existe un usuario
	public Boolean existeUsuario(String login) {
	    
	    return usuarios.containsKey(login);
	}
	
	public void agregarUsuarioTipo(String password, String login, String nombre, String tipo) 
	{
		if (tipo.equals("Administrador"))
		usuarios.put(login, new Administrador (password, login, nombre));
		else if (tipo.equals("Cliente"))
		usuarios.put(login, new Cliente (password, login, nombre));
		else if (tipo.equals("Operador"))
		usuarios.put(login, new Operador (password, login, nombre));
		else if (tipo.equals("Cajero"))
		usuarios.put(login, new Cajero (password, login, nombre));
	}

	public void AgregarUsuario(Usuario usuario) {
		usuarios.put(usuario.getLogin(), usuario);
	}

	public static Administrador getAdministrador() {
		return administrador;
	}
	public static Cajero getCajero() {
		return cajero;
	}
	public static Operador getOperador() {
		return operador;
	}
	public static Inventario getInventario() {
		return inventario;
	}

	public static Subasta getSubasta() {
		return subasta;
	}


    public static void setSubasta(Subasta subasta) {
        GaleriaDeArte.subasta = subasta;
    }

	public static void setInventario(Inventario inventario) {
		GaleriaDeArte.inventario = inventario;
	}
	
    
}
