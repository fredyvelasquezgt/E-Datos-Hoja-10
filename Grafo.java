//Recordar borrar el package!

//Fredy Velasquez
//201011s
//Algortimos y estructuras de datos
//31-05-2021
//Hoja de trabajo #10

public abstract class Grafo {

    public abstract int numVertices();
    public abstract int numAristas();
    public abstract boolean existeArista(int i, int j);
    public abstract int pesoArista(int i, int j);
	public abstract void insertarArista(int i, int j);
	public abstract void insertarArista(int i, int j, double p);
	public abstract ListaConPI<Adyacente> adyacentesDe(int i);
	public String toString(){
        String res = "" ;
        for (int i = 1 ; i <= numVertices() ; i++) {
        res += "Vértice: " + i;
        ListaConPI<Adyacente> l = adyacentesDe(i);
        res += (l.esVacia()) ? " sin Adyacentes " : " con Adyacentes: ";
        for (l.inicio(); !l.esFin() ; l.siguiente()) res += l.recuperar() + " ";
        res += "\n";
        } return res;
        }
}