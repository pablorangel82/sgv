package br.com.sgv.model;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 24/04/2021
 * @brief class PrevisaoDePreco
 */
import br.com.paim.conclusion.FuzzyConclusion;
import br.com.paim.operation.IFuzzyOperation;
import br.com.paim.operation.fuzzy.AndF;
import br.com.paim.operation.fuzzy.IsF;
import br.com.paim.operation.reasoner.FuzzyRule;
import br.com.paim.operation.reasoner.FuzzyRuleSet;
import br.com.paim.operation.reasoner.RuleSet;
import br.com.paim.type.NumericalData;
import br.com.paim.type.TrapezeFunctionData;

public class SugestaoDePreco extends FuzzyRuleSet {

    private float coeficienteDeVariacao;
    private float numeroAlteracoesPreco;
    private double resultado;

    public final TrapezeFunctionData coeficienteDeVariacaoBaixo = new TrapezeFunctionData(0, 0, 0.2, 0.3, "Coeficiente de Variação Baixo");
    public final TrapezeFunctionData coeficienteDeVariacaoMedio = new TrapezeFunctionData(0.2, 0.4, 0.6, 1, "Coeficiente de Variação Medio");
    public final TrapezeFunctionData coeficienteDeVariacaoAlto = new TrapezeFunctionData(0.6, 0.8, 0.9, 1, "Coeficiente de Variação Alto");

    public final TrapezeFunctionData numeroAlteracoesPrecoBaixo = new TrapezeFunctionData(0, 8, 16, 20, "Numero de alterações de preço baixo");
    public final TrapezeFunctionData numeroAlteracoesPrecoMedio = new TrapezeFunctionData(16, 25, 25, 30, "Numero de alterações de preço médio");
    public final TrapezeFunctionData numeroAlteracoesPrecoAlto = new TrapezeFunctionData(25, 28, 35, 35, "Numero de alterações de preço alto");

    public final TrapezeFunctionData aumentoBaixo = new TrapezeFunctionData(0, 0, 0.02, 0.03, "Aumento Baixo");
    public final TrapezeFunctionData aumentoMedio = new TrapezeFunctionData(0.02, 0.05, 0.05, 0.07, "Aumento Medio");
    public final TrapezeFunctionData aumentoGrande = new TrapezeFunctionData(0.05, 0.07, 0.1, 0.1, "Aumento Grande");   

    
    private NumericalData MI_coeficienteDeVariacaoBaixo;
    private NumericalData MI_coeficienteDeVariacaoMedio;
    private NumericalData MI_coeficienteDeVariacaoAlto;

    private NumericalData MI_numeroAlteracoesPrecoBaixo;
    private NumericalData MI_numeroAlteracoesPrecoMedio;
    private NumericalData MI_numeroAlteracoesPrecoAlto;

    @Override
    public void response(double result) {
        System.out.println(result);
        setResultado(result);
    }

    @Override
    public void setData() {
        this.setCoeficienteDeVariacao();
        this.setMINumeroAlteracoesPreco();
    }

    private void setCoeficienteDeVariacao() {
        IsF isFLow = new IsF(coeficienteDeVariacao, coeficienteDeVariacaoBaixo);
        this.MI_coeficienteDeVariacaoBaixo.setValue(isFLow.doFuzzyOperation());
        IsF isFAvg = new IsF(coeficienteDeVariacao, coeficienteDeVariacaoMedio);
        this.MI_coeficienteDeVariacaoMedio.setValue(isFAvg.doFuzzyOperation());
        IsF isFHigh = new IsF(coeficienteDeVariacao, coeficienteDeVariacaoAlto);
        this.MI_coeficienteDeVariacaoAlto.setValue(isFHigh.doFuzzyOperation());
    }

    private void setMINumeroAlteracoesPreco() {
        IsF isFLow = new IsF(numeroAlteracoesPreco, numeroAlteracoesPrecoBaixo);
        this.MI_numeroAlteracoesPrecoBaixo.setValue(isFLow.doFuzzyOperation());
        IsF isFAvg = new IsF(numeroAlteracoesPreco, numeroAlteracoesPrecoMedio);
        this.MI_numeroAlteracoesPrecoMedio.setValue(isFAvg.doFuzzyOperation());
        IsF isFHigh = new IsF(numeroAlteracoesPreco, numeroAlteracoesPrecoAlto);
        this.MI_numeroAlteracoesPrecoAlto.setValue(isFHigh.doFuzzyOperation());
    }

    @Override
    public String getStatus() {
        return "";
    }

    public SugestaoDePreco() {
        this.setMode(RuleSet.GROUP_DRIVEN);

        this.MI_coeficienteDeVariacaoAlto = new NumericalData();
        this.MI_coeficienteDeVariacaoMedio = new NumericalData();
        this.MI_coeficienteDeVariacaoBaixo = new NumericalData();
        this.MI_numeroAlteracoesPrecoAlto = new NumericalData();
        this.MI_numeroAlteracoesPrecoMedio = new NumericalData();
        this.MI_numeroAlteracoesPrecoBaixo = new NumericalData();
        
        /* First Rule */
        IFuzzyOperation operation1 = new AndF( MI_coeficienteDeVariacaoBaixo, MI_numeroAlteracoesPrecoBaixo);
        FuzzyConclusion conclusion1 = new FuzzyConclusion(aumentoGrande);
        FuzzyRule rule1 = new FuzzyRule(operation1, conclusion1);
        /* End of Rule */
        
        /* Second Rule */
        IFuzzyOperation operation2 = new AndF( MI_coeficienteDeVariacaoBaixo, MI_numeroAlteracoesPrecoMedio);
        FuzzyConclusion conclusion2 = new FuzzyConclusion(aumentoMedio);
        FuzzyRule rule2 = new FuzzyRule(operation2, conclusion2);
        /* End of Rule */
        
        /* Third Rule */
        IFuzzyOperation operation3 = new AndF( MI_coeficienteDeVariacaoBaixo, MI_numeroAlteracoesPrecoAlto);
        FuzzyConclusion conclusion3 = new FuzzyConclusion(aumentoMedio);
        FuzzyRule rule3 = new FuzzyRule(operation3, conclusion3);
        /* End of Rule */
        
        /* Fourth Rule */
        IFuzzyOperation operation4 = new AndF( MI_coeficienteDeVariacaoMedio, MI_numeroAlteracoesPrecoBaixo);
        FuzzyConclusion conclusion4 = new FuzzyConclusion(aumentoMedio);
        FuzzyRule rule4 = new FuzzyRule(operation4, conclusion4);
        /* End of Rule */

        /* Fifth Rule */
        IFuzzyOperation operation5 = new AndF( MI_coeficienteDeVariacaoMedio, MI_numeroAlteracoesPrecoMedio);
        FuzzyConclusion conclusion5 = new FuzzyConclusion(aumentoBaixo);
        FuzzyRule rule5 = new FuzzyRule(operation5, conclusion5);
        /* End of Rule */
        
        /* Sixth Rule */
        IFuzzyOperation operation6 = new AndF( MI_coeficienteDeVariacaoMedio, MI_numeroAlteracoesPrecoAlto);
        FuzzyConclusion conclusion6 = new FuzzyConclusion(aumentoBaixo);
        FuzzyRule rule6 = new FuzzyRule(operation6, conclusion6);
        /* End of Rule */
        
        /* Seventh Rule */
        IFuzzyOperation operation7 = new AndF( MI_coeficienteDeVariacaoAlto, MI_numeroAlteracoesPrecoBaixo);
        FuzzyConclusion conclusion7 = new FuzzyConclusion(aumentoMedio);
        FuzzyRule rule7 = new FuzzyRule(operation7, conclusion7);
        /* End of Rule */
        
        /* Eight Rule */
        IFuzzyOperation operation8 = new AndF( MI_coeficienteDeVariacaoAlto, MI_numeroAlteracoesPrecoMedio);
        FuzzyConclusion conclusion8 = new FuzzyConclusion(aumentoMedio);
        FuzzyRule rule8 = new FuzzyRule(operation8, conclusion8);
        /* End of Rule */
        
        /* Nineth Rule */
        IFuzzyOperation operation9 = new AndF( MI_coeficienteDeVariacaoAlto, MI_numeroAlteracoesPrecoAlto);
        FuzzyConclusion conclusion9 = new FuzzyConclusion(aumentoBaixo);
        FuzzyRule rule9 = new FuzzyRule(operation9, conclusion9);
        /* End of Rule */
        
        this.addRule(rule1);
        this.addRule(rule2);
        this.addRule(rule3);
        this.addRule(rule4);
        this.addRule(rule5);
        this.addRule(rule6);
        this.addRule(rule7);
        this.addRule(rule8);
        this.addRule(rule9);
    }

    public float getCoeficienteDeVariacao() {
        return coeficienteDeVariacao;
    }

    public void setCoeficienteDeVariacao(float coeficienteDeVariacao) {
        this.coeficienteDeVariacao = coeficienteDeVariacao;
    }

    public float getNumeroAlteracoesPreco() {
        return numeroAlteracoesPreco;
    }

    public void setNumeroAlteracoesPreco(float numeroAlteracoesPreco) {
        this.numeroAlteracoesPreco = numeroAlteracoesPreco;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
}
