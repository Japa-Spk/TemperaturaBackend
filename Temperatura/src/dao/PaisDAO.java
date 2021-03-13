package dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

import model.JPAUtil;
import model.Pais;

public class PaisDAO {

	
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	//Create Pais
	public void guardar(Pais pais) {
		entity.getTransaction().begin();
		entity.persist(pais);
		entity.getTransaction().commit();
		JPAUtil.shutdown();
	}
	
	//Edit Pais
	public void editar(Pais pais) {
		entity.getTransaction().begin();
		entity.merge(pais);
		entity.getTransaction().commit();
		JPAUtil.shutdown();
	}
	
	//Delete Pais
	public void eliminar(Pais pais) {
		entity.getTransaction().begin();
		entity.merge(pais);
		entity.getTransaction().commit();
		JPAUtil.shutdown();
	}
	
	//Buscar Pais
	public Pais buscar(int id) {
		Pais p = new Pais();
		p=entity.find(Pais.class, id);
		JPAUtil.shutdown();
		return p;
	}
	
	//Lista Paises
	public List<Pais> obtenerPaises(){
		List<Pais> listaPaises = new ArrayList<>();
		Query q = entity.createQuery("SELECT p FROM Pais p");
		listaPaises = q.getResultList();
		return listaPaises;
	}
	
	
	
}
