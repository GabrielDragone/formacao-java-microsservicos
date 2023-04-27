package br.com.gabrieldragone.mspagamentos.model;

import java.util.List;

public class Pedido {

    private List<ItemDoPedido> itens;

    public Pedido() {
    }

    public Pedido(List<ItemDoPedido> itens) {
        this.itens = itens;
    }

    public List<ItemDoPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoPedido> itens) {
        this.itens = itens;
    }

}
