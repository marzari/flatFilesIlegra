/**
 * 
 */
package br.com.ilegra.testeSelecao.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ilegra.testeSelecao.domain.CalcReport;
import br.com.ilegra.testeSelecao.domain.Line;
import br.com.ilegra.testeSelecao.domain.LineBuyerFactory;
import br.com.ilegra.testeSelecao.domain.LineSalesFactory;
import br.com.ilegra.testeSelecao.domain.LineSalesmanFactory;

/**
 * @author tiago.marzari
 * @since 10/09/2018
 *
 */
@Service
public class ReportBuilderService {

	public CalcReport dataProcess(List<Line> lineParsed) {
		long sumBuyers = sumBuyers(lineParsed);
		long sumSalesman = sumSalesman(lineParsed);
		long idMmostExpensiveSale = idMmostExpensiveSale(lineParsed);
		LineSalesmanFactory worstSalesman = worstSalesman(lineParsed);
		return new CalcReport(idMmostExpensiveSale, sumBuyers, sumSalesman, worstSalesman);
	}

	private long sumSalesman(List<Line> lineParsed) {
		return lineParsed.stream().filter(LineSalesmanFactory.class::isInstance).count();
	}

	private long sumBuyers(List<Line> lineParsed) {
		return lineParsed.stream().filter(LineBuyerFactory.class::isInstance).count();
	}

	/**
	 * @param lineParsed
	 * @return
	 * Verifica qual foi a melhor venda
	 */
	private long idMmostExpensiveSale(List<Line> lineParsed) {
		List<LineSalesFactory> sales = getSales(lineParsed);

		if (sales.isEmpty()) return 0l;

		//Retorna a o id da maior venda
		return Collections.max(sales, Comparator.comparing(LineSalesFactory::getSumCost)).getIdSale();
	}

	private List<LineSalesFactory> getSales(List<Line> lineParsed) {
		return lineParsed.stream().filter(LineSalesFactory.class::isInstance).map(x -> (LineSalesFactory) x).collect(Collectors.toList());
	}

	
	/**
	 * @param linesParsed
	 * @return
	 * Verifica o vendedor com a pior venda
	 */
	private LineSalesmanFactory worstSalesman(List<Line> linesParsed) {
		
		List<LineSalesmanFactory> salesmans = getSalesman(linesParsed);
		List<LineSalesFactory> sales = getSales(linesParsed);
		Map<LineSalesmanFactory, Double> relacaoDeVendasPorVendedor = new HashMap<>();

		salesmans.forEach(salesman -> {
			List<LineSalesFactory> salesBySalesman = sales.stream().filter(x -> x.getSalesman()
					.equals(salesman.getName())).collect(Collectors.toList());

			Double sumSales = salesBySalesman.stream().mapToDouble(LineSalesFactory::getSumCost).sum();

			relacaoDeVendasPorVendedor.put(salesman, sumSales);
		});
		
		//Retornando a chave da pior venda
		return Collections.min(relacaoDeVendasPorVendedor.entrySet(), Comparator.comparing(Entry::getValue)).getKey();
	}

	private List<LineSalesmanFactory> getSalesman(List<Line> linesParsed) {
		return linesParsed.stream().filter(LineSalesmanFactory.class::isInstance).map(x -> (LineSalesmanFactory) x).collect(Collectors.toList());
	}

}
