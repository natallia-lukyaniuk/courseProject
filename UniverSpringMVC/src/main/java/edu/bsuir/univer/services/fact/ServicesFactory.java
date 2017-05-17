package edu.bsuir.univer.services.fact;

import edu.bsuir.univer.services.*;

public interface ServicesFactory {
	
	public UserServices getUserServices();
	public ProviderServices getProviderServices();
	public GoodsTypeServices getGoodsTypeServices();	
	public GoodsServices getGoodsServices();
	public GoodsSupplyServices getGoodsSupplyServices();
	
}
