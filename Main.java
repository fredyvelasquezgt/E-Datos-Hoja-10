
//Recordar borrar el package!

//Fredy Velasquez
//201011
//Algortimos y estructuras de datos
//31-05-2021
//Hoja de trabajo #10

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//Importo todo lo que voy a usar


public class Main {
//Clase que se encarga de manejar el resto de cosas
    public static void main(String[] args) {
        ArrayList lugares = new ArrayList<String>(); //Creo array que va a almacenar los sitios que voy a visitar
        int lugt =  leer(lugares); 
        System.out.println(lugt);
        GrafoD g = new GrafoD(lugt); //g=creo el grafo

       leer(lugares,g);



        System.out.println("El grafo es:\n"+g.toString(lugares)); //Le doy formato para imprimir el grafo
     /*   System.out.println("existeArista(Mixco,SantaLucia) = " + g.existeArista(lugares.indexOf("Mixco")+1,lugares.indexOf("SantaLucia")+1));
        ListaConPI<Adyacente> l = g.adyacentesDe(1);
        System.out.println("Los adyacentes al vértice Mixco son: ");
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            System.out.println("(Mixco, " + lugares.get(l.recuperar().destino-1) + ")");
        }*/

        Integer[][] matriz  = new Integer[lugares.size()][lugares.size()];
        Integer[][] M  = new Integer[lugares.size()][lugares.size()];

        for (int x = 0; x < lugares.size(); x++) {
            for (int y = 0; y < lugares.size(); y++) {
                if(x==y){
                    matriz[x][y] = 0;
                    M[x][y] = 0;
                }
                else if(g.existeArista(x+1,y+1)){
                    matriz[x][y] = g.pesoArista(x+1,y+1);
                    M[x][y] = g.pesoArista(x+1,y+1);
                }
                else{
                    matriz[x][y] = 1000000;
                    M[x][y] = 1000000;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        System.out.println("---Matriz del Documento:---\n");
        PrintMatriz(lugares, matriz, res);
        res.delete(0,res.length());

        Floyd fl = new Floyd();
        System.out.println("---Matriz despues de Floyd:---\n");
        fl.recacl(M);
        PrintMatriz(lugares, M, res);

        boolean bandera = true;
        Scanner scan = new Scanner(System.in);
        while (bandera){ //Menu que me ayuda a manejar las opciones del menu
            System.out.println("(1) Calcular Destino");
            System.out.println("(2) Indicar Centro del Grafo");
            System.out.println("(3) Cambiar valor de arco a arco");
            System.out.println("(4) Eliminar valor de arco a arco");
            System.out.println("(5) Salir");
            String op = scan.nextLine();
            String origen = "";
            String destino = "";
            switch (op) {
                case "1" -> {
                    boolean existe = false;
                    while (!existe) {
                        System.out.println("Lugares Disponibles:" + lugares);
                        System.out.println("Ingrese el lugar de Origen");
                        origen = scan.nextLine();

                        if (lugares.contains(origen)) {
                            existe = true;
                        } else {
                            System.out.println("Ingrese un lugar Valido");
                        }
                    }
                    existe = false;
                    while (!existe) {
                        System.out.println("Lugares Disponibles:" + lugares);
                        System.out.println("Ingrese el lugar de Destino");
                        destino = scan.nextLine();

                        if (lugares.contains(destino)) {
                            existe = true;
                        } else {
                            System.out.println("Ingrese un lugar Valido");
                        }
                    }
                    int q = lugares.indexOf(origen);
                    int r = lugares.indexOf(destino);
                    if (M[q][r] == 1000000) {
                        System.out.println("NO HAY CONEXION");
                    } else {
                        System.out.println("Se tardo un tipempo de: " + M[q][r]);
                        fl.path(q, r, lugares);
                    }

                    break;
                }
                case "2" ->{ //En caso de ser op 2
                    fl.centro(M,lugares);
                }
                case "3" -> {  //En caso de ser op 3

                    g.CambiarValores(matriz,lugares);
                    copiar(matriz, M);
                    fl.recacl(M);
                    PrintMatriz(lugares, M, res);

                }
                case "4" ->{ //En caso de ser op 4
                    g.BorrarValores(matriz,lugares);
                    copiar(matriz, M);
                    System.out.println(Arrays.deepToString(matriz));
                    fl.recacl(M);
                    PrintMatriz(lugares, M, res);
                }
                case "5" -> bandera = false; //En caso de ser op 5 cierro
            }
        }

    }

    private static void PrintMatriz(ArrayList lugares, Integer[][] matriz, StringBuilder res) {
        for (Object lugar : lugares) {
            res.append(lugar).append("\t");

        }
        res.append("\n");
        for (int i = 0; i < lugares.size(); i++) {
            for (int j = 0; j < lugares.size(); j++) {
                res.append(matriz[i][j]);
                if(matriz[i][j] == null){
                    res.append("\t");
                }else{
                    res.append("\t\t");
                }
            }
            res.append("\n");
        }
        System.out.println(res);
        res.delete(0,res.length());
    }

    private static void buscar(ArrayList lugares, GrafoD g, String[] dato) {
        int ontas;
        if(lugares.contains(dato[0])){
            ontas = lugares.indexOf(dato[0])+1;
            if(lugares.contains(dato[1])){
                int ontas2 = lugares.indexOf(dato[1])+1;
                g.insertarArista(ontas,ontas2,Integer.parseInt(dato[2]));

            }else{
                lugares.add(dato[1]);
                int ontas2 = lugares.indexOf(dato[1])+1;
                g.insertarArista(ontas,ontas2,Integer.parseInt(dato[2]));
            }
        }else{
            lugares.add(dato[0]);
            ontas = lugares.indexOf(dato[0])+1;
            if(lugares.contains(dato[1])){
                int ontas2 = lugares.indexOf(dato[1]);
                g.insertarArista(ontas,ontas2,Integer.parseInt(dato[2]));

            }else{
                lugares.add(dato[1]);
                int ontas2 = lugares.indexOf(dato[1]) +1;
                g.insertarArista(ontas,ontas2,Integer.parseInt(dato[2]));
            }
        }
    }

    private static void leer(ArrayList lugares, GrafoD g){
        try {
            File myObj = new File("guategrafo.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] temp = data.split("\\s+");
                buscar(lugares,g,temp);
            }
            myReader.close();
        }catch (Exception e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
    }

    private static int leer(ArrayList lugares){
        try {
            File myObj = new File("guategrafo.txt");//Aqui leo el archivo
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] temp = data.split("\\s+");
                buscar(lugares,temp);
            }
            myReader.close();
        }catch (Exception e) {
            System.out.println("Archivo no encontrado"); //En caso de no encontrar el archivo
            e.printStackTrace();
        }
        System.out.println(lugares);
        return lugares.size();
    }

    private static void buscar(ArrayList lugares, String[] dato) {
        if(lugares.contains(dato[0])){
            if(lugares.contains(dato[1])){
            }else{
                lugares.add(dato[1]);
            }
        }else{
            lugares.add(dato[0]);
            if(lugares.contains(dato[1])){
            }else{
                lugares.add(dato[1]);
            }
        }
    }

    private static void copiar(Integer[][] map1,Integer[][]map2){
        for (int x = 0; x < map1.length; x++) {
            System.arraycopy(map1[x], 0, map2[x], 0, map1.length);
        }
    }
}