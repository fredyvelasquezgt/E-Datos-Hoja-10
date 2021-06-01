  
  //Recordar borrar el package!

//Fredy Velasquez
//201011
//Algortimos y estructuras de datos
//31-05-2021
//Hoja de trabajo #10
  
  
import java.util.ArrayList;

//Clase que maneja el adyacente
class Adyacente{
	
	
	//Declaro mis atributos
    int destino; //A donde voy
    int peso; //Cuanto me cuesta ir a ese lugar
	
	
	
    Adyacente(int codAdy, double p){ destino=codAdy; peso = (int) p; } //Indico que va haciendo
    public String toString(ArrayList lug) //Uso la list LUG
    {
		
        return lug.get(destino-1) + "("+ peso + ")";
		
    }
    public String toString() //Aqui doy fomato
    {
		
        return destino + "("+ peso + ")"; //Imprimo de forma que se entienda
		
    }
}