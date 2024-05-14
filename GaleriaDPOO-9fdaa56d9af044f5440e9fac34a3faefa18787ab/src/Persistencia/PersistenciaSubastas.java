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
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class PersistenciaSubastas {

	public JSONArray SalvarSubastas(GaleriaDeArte galeria) {
		
		JSONArray jSubastas = new JSONArray();
		
		List<List<String>> subastasGaleria = galeria.getRegistrosPorSubasta();
		
		for (List<String> subasta : subastasGaleria) 
		{
			JSONArray jSubasta = new JSONArray();
			for(String puja : subasta) {
				jSubasta.put(puja);
			}
			jSubastas.put(jSubasta);
		}
		
		return jSubastas;
		
	}
	
	
	public void CargarSubastas(JSONArray jSubastas)
	{
		List<List<String>> subastas = new ArrayList<List<String>>();
		
		for (int i = 0; i< jSubastas.length(); i++) 
		{
			JSONArray jSubasta = jSubastas.getJSONArray(i);
			List<String> subasta = new ArrayList<String>();
			for (int j = 0; j< jSubasta.length(); j++) {
				subasta.add(jSubasta.getString(j));
			}
			subastas.add(subasta);
		}
		
	}
	
}
