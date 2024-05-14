package Persistencia;

import Model.Compra;
import Model.GaleriaDeArte;
import Pieza.Pieza;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Cliente;
import Usuario.Operador;
import Usuario.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;



public class PersistenciaUsuarios {
	
	private List<Pieza> piezasCargadas;
	
	public PersistenciaUsuarios() {
		
		piezasCargadas = new ArrayList<Pieza>();
	}
	
	public JSONArray salvarUsuarios( GaleriaDeArte galeria)
    {
        JSONArray jUsuarios = new JSONArray( );
        
        Administrador administrador = galeria.getAdministrador();
        String loginAdmin = administrador.getLogin();
        String nameAdmin = administrador.getNombre();
        String passwordAdmin = administrador.getPassword();
        JSONObject jAdministrador = new JSONObject();
        jAdministrador.put("password", passwordAdmin);
        jAdministrador.put("login", loginAdmin);
        jAdministrador.put("nombre", nameAdmin);
        jUsuarios.put(jAdministrador);
        
        Cajero cajero = galeria.getCajero();
        String loginCajero = cajero.getLogin();
        String nameCajero = cajero.getNombre();
        String passwordCajero = cajero.getPassword();
        JSONObject jCajero= new JSONObject();
        jCajero.put("password", passwordCajero);
        jCajero.put("login", loginCajero);
        jCajero.put("nombre", nameCajero);
        jUsuarios.put(jCajero);
        
        Operador operador = galeria.getOperador();
        String loginOperador = operador.getLogin();
        String nameOperador = operador.getNombre();
        String passwordOperador = operador.getPassword();
        JSONObject jOperador = new JSONObject();
        jOperador.put("password", passwordOperador);
        jOperador.put("login", loginOperador);
        jOperador.put("nombre", nameOperador);
        jUsuarios.put(jOperador);
        
        for( Usuario cliente : galeria.getUsuarios( ) )
        {
        	JSONObject jClienteSubasta = SalvarUsuario(cliente);
        	jUsuarios.put(jClienteSubasta);
        	
        }
        return jUsuarios;
    }
	
	public JSONObject SalvarUsuario(Usuario usuario) {
		
		Cliente cliente = (Cliente) usuario;
		JSONObject jCliente = new JSONObject();
		
		String password = cliente.getPassword();
		String login = cliente.getLogin();
		String nombre = cliente.getNombre();
		int maximo = cliente.getValorMaximo();
		int saldo = cliente.getSaldo();
		List<Compra> compras = cliente.getCompras();
		JSONArray listaCompras = crearJSONArrayComprasDesdeLista(compras);
		
		List<Pieza> piezas = cliente.getPasadas();
		JSONArray listapiezas = crearJSONArrayPiezasDesdeLista(piezas);
		
		List<Pieza> piezasPasadas = cliente.getPiezasPasadas();
		JSONArray listapiezasPasadas = crearJSONArrayPiezasDesdeLista(piezasPasadas);
		
		jCliente.put("login", login);
		jCliente.put("password", password);
		jCliente.put("nombre", nombre);
		jCliente.put("maximo", maximo);
		jCliente.put("saldo",saldo );
		jCliente.put("compras", listaCompras);
		jCliente.put("piezas", listapiezas);
		jCliente.put("piezasPasadas", listapiezasPasadas);
		
		return jCliente;
		
	}
	
    public JSONArray crearJSONArrayComprasDesdeLista(List<Compra> lista ) {
        JSONArray jCompras = new JSONArray();
        
        for (Compra compra : lista) {
        	JSONObject jCompra = new JSONObject();
        	
        	Pieza pieza = compra.getPieza();
        	JSONObject jPieza = piezaToJson(pieza);
        	
        	int valor = compra.getPrecio();
        	
        	jCompra.put("pieza", jPieza);
        	jCompra.put("valor", valor);
        	jCompras.put(jCompra);
        }
        return jCompras;
    }
    
    public JSONArray crearJSONArrayPiezasDesdeLista(List<Pieza> lista ) {
        JSONArray jPiezas = new JSONArray();
        
        for (Pieza pieza : lista) {
        	JSONObject jPieza = new JSONObject();
        	
        	String anoCreacion = pieza.getAnoCreacion();
        	String autor = pieza.getAutor();
        	String codigo = pieza.getCodigoPieza();
        	String consignacion = pieza.getFechaConsignacion();
        	String lugar = pieza.getLugar();
        	String lugarCreacion = pieza.getLugarCreacion();
        	String titulo = pieza.getTitulo();
        	int precioCompra = pieza.getPrecioCompra();
        	
        	jPieza.put("anoCreacion", anoCreacion);
        	jPieza.put("autor", autor);
        	jPieza.put("codigo", codigo);
        	jPieza.put("consignacion", consignacion);
        	jPieza.put("lugar", lugar);
        	jPieza.put("lugarCreacion", lugarCreacion);
        	jPieza.put("titulo", titulo);
        	jPieza.put("precioCompra", precioCompra);
        	
        	jPiezas.put(jPieza);
        }
        return jPiezas;
    }
    
    
    public JSONObject piezaToJson(Pieza pieza) {
    	JSONObject jPieza = new JSONObject();
    	
    	String anoCreacion = pieza.getAnoCreacion();
    	String autor = pieza.getAutor();
    	String codigo = pieza.getCodigoPieza();
    	String consignacion = pieza.getFechaConsignacion();
    	String lugar = pieza.getLugar();
    	String lugarCreacion = pieza.getLugarCreacion();
    	String titulo = pieza.getTitulo();
    	int precioCompra = pieza.getPrecioCompra();
    	
    	jPieza.put("anoCreacion", anoCreacion);
    	jPieza.put("autor", autor);
    	jPieza.put("codigo", codigo);
    	jPieza.put("consignacion", consignacion);
    	jPieza.put("lugar", lugar);
    	jPieza.put("lugarCreacion", lugarCreacion);
    	jPieza.put("titulo", titulo);
    	jPieza.put("precioCompra", precioCompra);
    	
    	
    	return jPieza;
    	
    }
    
    
    
    public void CargarUsuarios(GaleriaDeArte galeria, JSONArray jUsuarios) {
    	
    	for (int i = 0; i < jUsuarios.length(); i++ ) {
    		if(i == 0) 
    		{
    			JSONObject jAdministardor = jUsuarios.getJSONObject( i );
    			String login = jAdministardor.getString("login");
    			String password = jAdministardor.getString("password");
    			String name = jAdministardor.getString("nombre");
    			galeria.AgregarAdministrador(password, login, name);
    			
    		}else if(i == 1)
    		{
    			JSONObject jCajero = jUsuarios.getJSONObject( i );
    			String login = jCajero.getString("login");
    			String password = jCajero.getString("password");
    			String name = jCajero.getString("nombre");   
    			galeria.AgregarCajero(password, login, name);
    			
    		}else if(i == 2) 
    		{
    			JSONObject jOperador = jUsuarios.getJSONObject( i );
    			String login = jOperador.getString("login");
    			String password = jOperador.getString("password");
    			String name = jOperador.getString("nombre");   		
    			galeria.AgregarOperador(password, login, name);
    		}
    		else 
    		{
    			JSONObject jCliente = jUsuarios.getJSONObject(i);
    			Usuario clienteGaleria = CargarUsuario(jCliente);
    			galeria.AgregarUsuario(clienteGaleria);
    		}
    	}
    	
    	AsociarUsariosPiezas(galeria);
    }
    
    
    
    private void AsociarUsariosPiezas(GaleriaDeArte galeria) {
		List<Usuario> usuariosLista =  (List<Usuario>) galeria.getUsuarios();
		for ( Usuario usuario : usuariosLista ) 
		{
			Cliente cliente = (Cliente) usuario;
			
			List<Pieza>  piezas = cliente.getPasadas();
			for ( Pieza pieza : piezas ) 
			{
				pieza.setPropietario(cliente);
			}
			
		}
	}
    
    

	public Usuario  CargarUsuario(JSONObject cliente)
    {
    	
    	String login = cliente.getString("login");
    	String password = cliente.getString("password");
    	String name = cliente.getString("nombre");
    	int saldo = cliente.getInt("saldo");
    	int maximo = cliente.getInt("maximo");
    			
    	
    	JSONArray Compras = cliente.getJSONArray("compras");
    	List<Compra> comprasCliente = JsonArrayComprasToPiezas(Compras);
    	
    	JSONArray jPiezas = cliente.getJSONArray("compras");
    	List<Pieza> PiezasCliente = JSONPiezasToPiezas(jPiezas);
    	
    	JSONArray jPiezasPasadas = cliente.getJSONArray("compras");
    	List<Pieza> PiezasPasadas = JSONPiezasToPiezas(jPiezasPasadas);
    	
    	Cliente clienteGaleria = new Cliente(password, login, name);
    	clienteGaleria.setSaldo(saldo);
    	clienteGaleria.setValorMaximo(maximo);
    	clienteGaleria.setCompras(comprasCliente);
    	clienteGaleria.setPiezas(PiezasCliente);
    	clienteGaleria.setPiezasPasadas(PiezasPasadas);
    
    	return clienteGaleria;
    	
    	
    }

    
    
    
    
    public List<Compra> JsonArrayComprasToPiezas(JSONArray comprasJSON)
    {
    	List<Compra> comprasCliente = new ArrayList<Compra>();
    	
    	for (int i = 0; i < comprasJSON.length(); i++ ) 
    	{
    		JSONObject jCompra = comprasJSON.getJSONObject(i);
    		JSONObject jpiezaCompra = jCompra.getJSONObject("pieza");
    		Pieza piezaCompra = JSONPiezaToPieza(jpiezaCompra);
    		int valorCompra = jCompra.getInt("valor");
    		Compra compraCliente = new Compra(piezaCompra);
    		comprasCliente.add(compraCliente);

    	}
		return comprasCliente;
    	
    }
    
    public Pieza JSONPiezaToPieza(JSONObject jPieza) {
    	
    	String anoCreacion = jPieza.getString("anoCreacion");
    	String autor = jPieza.getString("autor");
    	String codigo = jPieza.getString("codigo");
    	String consignacion = jPieza.getString("consignacion");
    	String lugar = jPieza.getString("lugar");
    	String lugarCreacion = jPieza.getString("lugarCreacion");
    	String titulo = jPieza.getString("titulo");
    	int precioCompra = jPieza.getInt("precioCompra");
    	
		return new Pieza(codigo, anoCreacion, autor, lugarCreacion, titulo, consignacion, precioCompra, null, lugar);
    	
    }
    
    public List<Pieza> JSONPiezasToPiezas(JSONArray jPiezas) 
    {
    	ArrayList<Pieza> piezasCliente = new ArrayList<Pieza>();
    	
    	for (int i = 0; i < jPiezas.length(); i++ ) 
    	{
    		JSONObject jPieza = jPiezas.getJSONObject(i);
    		
        	String anoCreacion = jPieza.getString("anoCreacion");
        	String autor = jPieza.getString("autor");
        	String codigo = jPieza.getString("codigo");
        	String consignacion = jPieza.getString("consignacion");
        	String lugar = jPieza.getString("lugar");
        	String lugarCreacion = jPieza.getString("lugarCreacion");
        	String titulo = jPieza.getString("titulo");
        	int precioCompra = jPieza.getInt("precioCompra");
        	
        	Pieza pieza =  new Pieza(codigo, anoCreacion, autor, lugarCreacion, titulo, consignacion, precioCompra, null, lugar);
        	piezasCliente.add(pieza);
    	}
    	return piezasCliente;
    }
    
    
}

