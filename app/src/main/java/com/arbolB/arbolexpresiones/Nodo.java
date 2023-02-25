package com.arbolB.arbolexpresiones;

public class Nodo {


        private String dato;

        private Nodo HI;
        private Nodo HD;

        public Nodo(){
            this.dato="";
            this.HI=null;
            this.HD=null;

        }
        public Nodo(String datosInput){
            this.dato=datosInput;
            this.HI=null;
            this.HD=null;

        }

        public  Nodo(Nodo op1, String pop, Nodo op2) {
            this.HI=op1;
            this.dato=pop;
            this.HD=op2;

        }

        public String getDato() {
            return dato;
        }

        public void setDato(String datoInput) {
            this.dato = datoInput;
        }

        public Nodo getHI() {
            return HI;
        }

        public void setHI(Nodo nodoHijoIzquierdo) {
            this.HI = nodoHijoIzquierdo;
        }

        public Nodo getHD() {
            return HD;
        }

        public void setHD(Nodo nodoHijoDerecho) {
            this.HD = nodoHijoDerecho;
        }



}
