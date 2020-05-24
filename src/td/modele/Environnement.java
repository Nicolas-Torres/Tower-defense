package td.modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Environnement {

	private Map map;
	ArrayList<Tourelle> tours;
	ArrayList<Personnage> persos;
	private BFS bfs;
	
	public Environnement() {
		this.tours = new ArrayList<Tourelle>();
		this.persos = new ArrayList<Personnage>();
		this.map= new Map("src/Sources/map.csv");
		this.bfs = new BFS(this.getMap());
	}
	
	public void ajouterPers(Personnage p) {
		this.persos.add(p);
	}
	
	public void unTour() {
		for(Personnage p : this.persos) {
			p.agit();
		}
	}

	public int[][] getMap () {
		return this.map.getMap();
	}
	public ArrayList<Personnage> getPersos(){
		return this.persos;
	}
	public ArrayList<Tourelle> getTours(){
		return this.tours;
	}


	public HashMap<Sommet, Sommet> getHashMap(){
		return this.bfs.getHashMap();
	}
	public Sommet trouverSommet(int x, int y){
		return this.bfs.trouverSommet(x,y);
	}
	
	public void creerArbre() {
		this.bfs.creationChemin();
	}
}