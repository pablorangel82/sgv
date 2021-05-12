/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim;

import br.com.paim.operation.reasoner.Reasoner;
import br.com.sgv.model.SugestaoDePreco;


/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 24/04/2021
 * @brief  class FuzzyTest
 */
public class FuzzyTest {
    public static void main(String[] args) {
        SugestaoDePreco pdp = new SugestaoDePreco();
        pdp.setNumeroAlteracoesPreco(3);
        pdp.setCoeficienteDeVariacao(1);
        pdp.setData();
        Reasoner res = new Reasoner(true);
        res.execute(pdp.getRules());
    }
}
