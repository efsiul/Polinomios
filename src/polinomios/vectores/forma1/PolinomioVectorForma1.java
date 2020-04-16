/*
 * Copyright 2019 Carlos Alejandro Escobar Marulanda ealejandro101@gmail.com
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the 
 * Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package polinomios.vectores.forma1;

/**
 *
 * @author alejandroescobar
 */
public class PolinomioVectorForma1 {

    private int[] arregloA;

    /**
     * Constructor que crea un arreglo sin coeficientes de grado n
     *
     * @param n es el grado
     */
    public PolinomioVectorForma1(int n) {
        arregloA = new int[n + 2];
        for (int i = 0; i < arregloA.length; i++) {
            arregloA[i] = 0;
        }
        arregloA[0] = n;
    }

    /**
     * Constructor de un polinomio conociendo el arreglo de enteros
     *
     * @param arreglo
     */
    public PolinomioVectorForma1(int[] arreglo) {
        this.arregloA = arreglo;
    }

    /**
     * Constructor que cree un arreglo sin coeficiente
     */
    PolinomioVectorForma1() {
        arregloA = new int[1];
        arregloA[0] = -1;
    }

    /**
     * Obtener el grado
     *
     * @return
     * @throws Exception
     */
    public int getGrado() throws Exception {
        if (arregloA != null) {
            return arregloA[0];
        } else {
            throw new NullPointerException("El arreglo esta nulo");
        }
    }

    /**
     * Obtiene el coeficiente dado un exponente
     *
     * @param exp
     * @return coeficiente
     * @throws java.lang.Exception
     */
    public int getCoeficiente(int exp) throws Exception {
        int pos = getGrado() - exp + 1;
        if (pos >= arregloA.length) {
            throw new ArrayIndexOutOfBoundsException("El arreglo es de menor tamaño que la posición calculada");
        }
        return arregloA[pos];
    }

    /**
     * Obtiene el exponente dado una posición
     *
     * @param pos
     * @return exponente
     * @throws java.lang.Exception
     */
    public int getExponente(int pos) throws Exception {
        if (pos >= arregloA.length) {
            throw new ArrayIndexOutOfBoundsException("El arreglo es de menor tamaño que la posición calculada");
        }
        int exp = getGrado() - pos + 1;
        return exp;
    }

    @Override
    public String toString() {
        StringBuilder polinomio = new StringBuilder();
        try {
            for (int i = 1; i < arregloA.length; i++) {
                int j = arregloA[i];
                // Para adicionar el simbolo del coeficiente para numeros positivos, excluyendo el simbolo + del primer termino si es positivo.
                if (j >= 0 && i != 1) {
                    polinomio.append("+");
                }
                polinomio.append(j).append("X^").append(getExponente(i)).append(" ");
            }
        } catch (Exception e) {
            polinomio.append("El arreglo estaba NULO");
        }
        return polinomio.toString();
    }

    public void setCoeficiente(int c, int e) throws Exception {
        if (e > getGrado()) {
            throw new ArrayIndexOutOfBoundsException("El exponente es superior al grdo del polinomio");
        }
        int pos = getGrado() - e + 1;
        arregloA[pos] = c;
    }

    /**
     * Función para Sumar dos polinomios, entrega un nuevo polinomio resultado
     * de la operación suma. No modifica el polinomio que representa el objeto
     *
     * @param polinomioB
     * @return
     * @throws java.lang.Exception
     */
    public PolinomioVectorForma1 sumar(PolinomioVectorForma1 polinomioB) throws Exception {

        PolinomioVectorForma1 polinomioC;
        if (polinomioB == null) {
            throw new Exception("El polinomio b es null");
        }

        // Bloque para validar si los arreglos son nulos o no
        int[] arregloB = polinomioB.getArreglo();
        if (arregloB == null) {
            if (arregloA == null) {
                polinomioC = new PolinomioVectorForma1();
            } else {
                int[] arregloNuevo = new int[arregloA.length];
                System.arraycopy(arregloA, 0, arregloNuevo, 0, arregloA.length);
                polinomioC = new PolinomioVectorForma1(arregloNuevo);
                return polinomioC;
            }
        } else {
            if (arregloA == null) {
                int[] arregloNuevo = new int[arregloB.length];
                System.arraycopy(arregloB, 0, arregloNuevo, 0, arregloB.length);
                polinomioC = new PolinomioVectorForma1(arregloNuevo);
                return polinomioC;
            }
        }

        // Este es el caso en que ambos arreglos no son nulos
        int gradoA = getGrado();
        int gradoB = polinomioB.getGrado();
        int nGrado = (gradoA > gradoB) ? gradoA : gradoB;
        polinomioC = new PolinomioVectorForma1(nGrado);

        int e = 0;
        while (e <= gradoA && e <= gradoB) {
            int nC = getCoeficiente(e) + polinomioB.getCoeficiente(e);
            polinomioC.setCoeficiente(nC, e);
            e++;
        }

        while (e <= gradoA) {
            polinomioC.setCoeficiente(getCoeficiente(e), e);
            e++;
        }

        while (e <= gradoB) {
            polinomioC.setCoeficiente(polinomioB.getCoeficiente(e), e);
            e++;
        }

        polinomioC.normalizar();
        return polinomioC;
    }

    public int getDiferentesCero() {
        return 0;
    }

    public int[] getArreglo() {
        return arregloA;
    }

    public void ingresar(double coeficiente, int exponente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Permite validar el arreglo y garantizar que no tenga terminos iniciales
     * en 0
     */
    private void normalizar() {

        // Si la primera posición es 0
        if (arregloA[1] == 0) {
            int i = 1;
            while (i < arregloA.length && arregloA[i] == 0) {
                i++;
            }
            if (i < arregloA.length) {
                int nuevoGrado = i - 1;
                int nuevoArreglo[] = new int[nuevoGrado + 2];
                nuevoArreglo[0] = nuevoGrado;
                System.arraycopy(arregloA, i, nuevoArreglo, 1, arregloA.length - i);
                arregloA = nuevoArreglo;
            } else {
                arregloA = new int[1];
                arregloA[0] = -1;
            }
        }
    }

    /**
     * Sumar al polinomio a (this) un termino, la estrategia es crear un
     * polinomio b de un solo termino y luego sumar a y b
     *
     * @param coeficiente
     * @param exponente
     * @return
     * @throws Exception
     */
    public PolinomioVectorForma1 sumar(int coeficiente, int exponente) throws Exception {
        PolinomioVectorForma1 polB;
        if (coeficiente != 0) {
            /**
             * El tamaño del arreglo es de grado n + 2, como el polinomio
             * resultante va a ser de un solo termino, el grado es el mismo
             * exponente
             */
            int[] arregloPol = new int[exponente + 2];
            for (int i = 0; i < arregloPol.length; i++) {
                arregloPol[i] = 0;
            }
            arregloPol[0] = exponente;
            arregloPol[1] = coeficiente;
            polB = new PolinomioVectorForma1(arregloPol);
        } else {
            polB = new PolinomioVectorForma1();
        }
        return this.sumar(polB);

    }

}
