/**
 * 
 */
package br.com.ilegra.testeSelecao.parse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ilegra.testeSelecao.config.PositionFileConfig;
import br.com.ilegra.testeSelecao.domain.Item;
import br.com.ilegra.testeSelecao.domain.Line;
import br.com.ilegra.testeSelecao.domain.LineSalesFactory;

/**
 * @author tiago marzari
 * @since 10/09/2018
 */
@Component
public class LineParseSales implements LineParse {
	
	private static final String SEPARATOR_BIGIN = "[";
	private static final String SEPARATOR_END = "]";
	private static final String SEPARADOR_ITEM = ",";
	private static final String SEPARADOR_PROPERTIE = "-";
	
	@Override
	public Line parse(String[] data) {
		String itens = data[PositionFileConfig.PLACE_SALE_ITENS];
		String saleman = data[PositionFileConfig.PLACE_SALE_SALESMAN];
		String idSale = data[PositionFileConfig.PLACE_SALE_ID];

		List<Item> itemBuild = buildItem(itens);

		return new LineSalesFactory(idSale, itemBuild, saleman);
	}
	
	private List<Item> buildItem(String items) {
		List<Item> out = new ArrayList<>();

		String[] listItems = items.replace(SEPARATOR_BIGIN, "")
				.replace(SEPARATOR_END, "").split(SEPARADOR_ITEM);

		for (String item : listItems) {
			String[] propertiesItem = item.split(SEPARADOR_PROPERTIE);

			Item itemBuild = new Item(propertiesItem[PositionFileConfig.PLACE_SALE_ITEM_ID],
					propertiesItem[PositionFileConfig.PLACE_SALE_ITEM_QUANTITY],
					propertiesItem[PositionFileConfig.PLACE_SALE_ITEM_COST]);
			out.add(itemBuild);
		}

		return out;
	}

}
