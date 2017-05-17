package edu.bsuir.univer.services.fact;

import edu.bsuir.univer.services.*;
import edu.bsuir.univer.services.impl.*;


public class ServicesFactoryImpl implements ServicesFactory {

	@Override
	public ProviderServices getProviderServices() {
		return new ProviderServicesImpl();
	}

	@Override
	public UserServices getUserServices() {
		return new UserServicesImpl();
	}
	
	@Override
	public GoodsTypeServices getGoodsTypeServices() {
		return new GoodsTypeServicesImpl();
	}
	
	@Override
	public GoodsServices getGoodsServices() {
		return new GoodsServicesImpl();
	}

	@Override
	public GoodsSupplyServices getGoodsSupplyServices() {
		return new GoodsSupplyServicesImpl();
	}

}
