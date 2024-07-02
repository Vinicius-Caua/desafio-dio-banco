package conta;

import cliente.Cliente;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected TipoConta tipoConta;
    protected Cliente cliente;

    public Conta(Cliente cliente, TipoConta tipoConta) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.tipoConta = tipoConta;
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    public double rendimento() {
        double valor = getSaldo();
        if (tipoConta == TipoConta.POUPANCA) {
            valor *= 0.005; // 0,5% ao mês
        }
        return valor;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));

        if (this.tipoConta == TipoConta.CORRENTE) {
            System.out.println(String.format("Saldo: %.2f", this.saldo));
        } else if (this.tipoConta == TipoConta.POUPANCA) {
            double saldoComRendimento = this.saldo + this.rendimento();
            System.out.println(String.format("Saldo com Rendimento: %.2f", saldoComRendimento));
        }
    }
}
