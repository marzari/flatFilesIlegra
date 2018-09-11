package br.com.ilegra.testeSelecao.domain;

import java.util.List;

/**
 * @author tiago marzari
 * @since 10/09/2018
 */
public class LineSalesFactory extends Line {
	
	private long idSale;
	private List<Item> items;
	private String salesman;
	
	/**
	 * @param idSale
	 * @param items
	 * @param salesman
	 */
	public LineSalesFactory(String idSale, List<Item> items, String salesman) {
		super();
		this.idSale = Long.parseLong(idSale);
		this.items = items;
		this.salesman = salesman;
	}
	
	public Double getSumCost() {
		return this.items.stream().mapToDouble(x -> x.getCost().doubleValue() * x.getQuantity()).sum();
	}

	/**
	 * @return the idSale
	 */
	public final long getIdSale() {
		return idSale;
	}

	/**
	 * @param idSale the idSale to set
	 */
	public final void setIdSale(Integer idSale) {
		this.idSale = idSale;
	}

	/**
	 * @return the items
	 */
	public final List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public final void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * @return the salesman
	 */
	public final String getSalesman() {
		return salesman;
	}

	/**
	 * @param salesman the salesman to set
	 */
	public final void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	@Override
	public String toString() {
		return "LineSalesFactory [idSale=" + idSale + ", items=" + items + ", salesman=" + salesman + "]";
	}
	
}
