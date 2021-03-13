package dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

import model.JPAUtil;
import model.Ciudade;

public class CiudadDAO {

	
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	//Create Ciudad
	public void guardar(Ciudade ciudad) {
		entity.getTransaction().begin();
		entity.persist(ciudad);
		entity.getTransaction().commit();
		JPAUtil.shutdown();
	}
	
	//Edit ciudad
	public void editar(Ciudade ciudad) {
		entity.getTransaction().begin();
		entity.merge(ciudad);
		entity.getTransaction().commit();
		JPAUtil.shutdown();
	}
	
	//Delete Ciudad
	public void eliminar(Ciudade ciudad) {
		entity.getTransaction().begin();
		entity.merge(ciudad);
		entity.getTransaction().commit();
		JPAUtil.shutdown();
	}
	
	//Buscar Ciudad
	public Ciudade buscar(int id) {
		Ciudade c = new Ciudade();
		c=entity.find(Ciudade.class, id);
		JPAUtil.shutdown();
		return c;
	}
	
	//Lista Ciudades
	public List<Ciudade> obtenerCiudades(){
		List<Ciudade> listaCiudades = new ArrayList<>();
		Query q = entity.createQuery("SELECT p FROM Ciudade p");
		listaCiudades = q.getResultList();
		return listaCiudades;
	}
	
	
	
}
