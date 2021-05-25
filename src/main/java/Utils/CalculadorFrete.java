/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Gabriel
 */
public class CalculadorFrete {

    private double preco = 16.90;

    public double getFrete(double subtotal) {

        double precoFinal = preco;

        if (subtotal > 100 && subtotal <= 150) {
            precoFinal = this.preco - 3;
        } else if (subtotal > 150) {
            precoFinal = this.preco - 6;
        }

        precoFinal = Utils.retornaReal(precoFinal);

        return precoFinal;
    }

    public double Frete2(int tipoFrete) {

        double frete = 16.9;
        
        if (tipoFrete == 2) {
            frete = 19.90;
        }

        return frete;
    }

}
