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
package polinomios.listas.simple;

import polinomios.util.Termino;

/**
 *
 * @author alejandroescobar
 */
public class EjemploPolinomioListaSimple {

    public static void main(String[] args) {
        PolinomioListaSimpleConCabeza pA = new PolinomioListaSimpleConCabeza();
        Termino t = new Termino(10, 5);
        Nodo n = new Nodo(t);
        Nodo cA = pA.getCabeza();
        cA.setLiga(n);
        cA = n;

        t = new Termino(0, 20);
        n = new Nodo(t);
        cA.setLiga(n);

        PolinomioListaSimpleConCabeza pB = pA;

        PolinomioListaSimpleConCabeza pC = pA.sumar(pB);

        System.out.println(pA);
        System.out.println(pB);
        System.out.println(pC);

        System.out.println("Coeficiente con exponente 2 " + pC.getCoeficiente(2));
        System.out.println("Coeficiente con exponente 0 " + pC.getCoeficiente(0));

    }

}
