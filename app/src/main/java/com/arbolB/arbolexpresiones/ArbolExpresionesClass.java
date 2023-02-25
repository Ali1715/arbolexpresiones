package com.arbolB.arbolexpresiones;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;
import java.util.StringTokenizer;

public class ArbolExpresionesClass extends AppCompatActivity {
    Stack<Nodo> piladeoperandos = new Stack<Nodo>();
    Stack<String> piladeoperadores = new Stack<String>();

    private Nodo raiz;
    final String espacio;           // Cadena de espacios en espacio
    final String operadores;       // Cadena con operadores para expresiones
    final String operadores2;
    final String operadores3;


    /**
     * Constructor por omision
     */
    public ArbolExpresionesClass() {

        espacio = " \t";
        operadores = ")+-!*/^VSC(";  //acomodados por precedencia;
        operadores2 = "+-!*/^VSC";
        operadores3 = "+-!*/^VSC(";
    }

    public Nodo getRaiz() {
        return this.raiz;
    }

    public void setRaiz(Nodo r) {
        this.raiz = r;
    }

    public boolean Hoja(Nodo p) {
        return p.getHI() == null && p.getHD() == null;
    }

    //Acomodar expresion
    public String AcomodarExpresion(String exp,String n) {
        String auxS = exp;

        for (int i = 0; i < auxS.length(); i++) {
            if(auxS.charAt(i)=='n')
                auxS = auxS.replaceAll("n",n);
        }

        auxS = auxS.replace(")(", ")*(");
        for (int i = 0; i <= 9; i++) {
            auxS = auxS.replace(Integer.toString(i) + "(", Integer.toString(i) + "*(");
        }
        for (int i = 0; i <= 9; i++) {
            auxS = auxS.replace(")" + Integer.toString(i), ")*" + Integer.toString(i));
        }

        String[] partes = auxS.split("");
        int i = 0;
        String mod = "";

        while (i < partes.length) {
            if ("-".equals(partes[i])) {
                if (i == 0) {
                    mod = mod + "(0-";
                    int k = 1;
                    while (k < partes.length && !operadores2.contains(partes[k])) {
                        mod = mod + partes[k];
                        k++;
                        i++;

                    }
                    i++;
                    mod = mod + ")";
                } else if (operadores3.contains(partes[i - 1])) {
                    mod = mod + "(0-";
                    int k = i + 1;
                    while (k < partes.length && !operadores2.contains(partes[k])) {
                        mod = mod + partes[k];
                        k++;
                        i++;

                    }
                    i++;
                    mod = mod + ")";
                }
            }
            if (i < partes.length) {
                mod = mod + partes[i];
            }
            i++;
        }
        return mod;
    }

    public Nodo Arbol(String expresion) {
        try {
            StringTokenizer tokenizer;
            String token;
            tokenizer = new StringTokenizer(expresion, espacio + operadores, true);
            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                if (espacio.contains(token))
                    ; // Es un espacio en espacio, se ignora
                else if (!operadores.contains(token)) {
                    // Es operando y lo guarda como nodo del arbol

                    piladeoperandos.push(new Nodo(token));
                } else if (token.equals(")")) { // Saca elementos hasta encontrar (
                    while (!piladeoperadores.empty() && !piladeoperadores.peek().equals("(")) {
                        guardarSubArbol();
                    }
                    piladeoperadores.pop();  // Saca el parentesis izquierdo
                } else {
                    if (!token.equals("(") && !piladeoperadores.empty()) {
                        //operador diferente de cualquier parentesis
                        String op = (String) piladeoperadores.peek();
                        while (!op.equals("(") && !piladeoperadores.empty()
                                && operadores.indexOf(op) >= operadores.indexOf(token)) {
                            guardarSubArbol();
                            if (!piladeoperadores.empty()) {
                                op = (String) piladeoperadores.peek();
                            }
                        }
                    }
                    piladeoperadores.push(token);  //Guarda el operador
                }
            }
            //Sacar todo lo que queda
            raiz = (Nodo) piladeoperandos.peek();
            while (!piladeoperadores.empty()) {
                if (piladeoperadores.peek().equals("(")) {
                    piladeoperadores.pop();
                } else {
                    guardarSubArbol();
                    raiz = (Nodo) piladeoperandos.peek();
                }
            }
            return raiz;
        } catch (Exception e1) {
            Toast.makeText(this, "Expresion mal escrita", Toast.LENGTH_SHORT);
            raiz = null;
            return raiz;
        }
    }

    /*
     * Metodo privado para almacenar en la pila un subarbol
     */
    private void guardarSubArbol() {
        Nodo der, izq;
        if (!piladeoperandos.empty()) {
            der = (Nodo) piladeoperandos.pop();
        } else {
            der = null;
        }
        if (!piladeoperandos.empty()) {
            izq = (Nodo) piladeoperandos.pop();
        } else {
            izq = null;
        }
        piladeoperandos.push(new Nodo(izq, piladeoperadores.pop(), der));

    }

    public void Postorden(Nodo n) {
        if (n != null) {
            Postorden(n.getHI());
            Postorden(n.getHD());
            System.out.print(n.getDato() + " ");
        }
    }

    public void Preorden(Nodo n) {
        if (n != null) {
            System.out.print(n.getDato() + " ");

            Preorden(n.getHD());
            Preorden(n.getHI());
        }
    }

    public void Cambiarxn(String x)
    {
        Cambiarxn(raiz,x);
    }

    private void  Cambiarxn(Nodo p, String x){
        String n="n";
        if(p==null)
            ;
        if(this.Hoja(p) && p.getDato()==n)
            p.setDato(x);
       ;


    }

    ////Resultado
    public double Resultado() {
        return Resultado(raiz);
    }

    private double Resultado(Nodo p) {
        double res = 0;
        if (p == null) {
            return res = 0;
        } else if (Hoja(p)) // Operando
        {
            String aux = p.getDato();
            res = Double.parseDouble(aux);
        } else {
            double vizq = Resultado(p.getHI());
            double vder = Resultado(p.getHD());
            Character op = p.getDato().charAt(0);
            switch (op) {
                case '+':
                    res = vizq + vder;
                    break;
                case '-':
                    res = vizq - vder;
                    break;
                case '*':
                    res = vizq * vder;
                    break;
                case '/':
                    res = vizq / vder;
                    break;
                case '^':
                    res = Math.pow(vizq, vder);
                    break;
                case '!':
                    res = factorial(vder) + vizq;
                    break;
                /*case '!':
                    if(vizq!=0){
                        res = factorial(vizq);
                    }
                    if(vder!=0){
                        res = factorial(vder);
                    }
                    break;*/
                case 'S':
                    if (vizq != 0) {
                        vizq = Math.toRadians(vizq);
                        res = Math.sin(vizq);
                    }
                    if (vder != 0) {
                        vder = Math.toRadians(vder);
                        res = Math.sin(vder);
                    }
                    break;
                case 'C':
                    if (vizq != 0) {
                        //vizq=Math.toRadians(vizq);
                        res = Math.cos(vizq * 2.0 * Math.PI / 360.0);
                    }
                    if (vder != 0) {
                        //vder=Math.toRadians(vder);
                        res = Math.cos(vder * 2.0 * Math.PI / 360.0);
                    }
                    break;
                case 'V':
                    res = Math.pow(vder, 1 / vizq);
                    break;
                default:
                    ;
                    break;
            }

        }
        return res;
    }

    private double factorial(double num) {
        double res = 1;
        for (int i = 1; i <= num; i++) {
            res = res * i;
        }
        return res;

    }
}
