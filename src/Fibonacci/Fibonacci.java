package Fibonacci;

public class Fibonacci {

    public int tam;
    public String nombre;


    public Fibonacci(String nombre, int tam){
        this.nombre = nombre;
        this.tam = tam;
    }

    public Fibonacci() {

    }

    public String [] mostrarSerie(){
        String []num = new String[tam];
        for (int i = 0; i < tam; i++) {
            num[i] = fibonacci(i)+"";
        }
        return num;
    }

    int fibonacci(int n)
    {
        if (n>1){
            return fibonacci(n-1) + fibonacci(n-2);
        }
        else if (n==1) {
            return 1;
        }
        else if (n==0){
            return 0;
        }
        else{
            System.out.println("Debes ingresar un tamaño mayor o igual a 1");
            return -1;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

}